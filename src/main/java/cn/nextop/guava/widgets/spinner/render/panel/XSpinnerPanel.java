package cn.nextop.guava.widgets.spinner.render.panel;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.support.swt.CGUtils;
import cn.nextop.guava.support.swt.Colors;
import cn.nextop.guava.widgets.spinner.XSpinner;
import cn.nextop.guava.widgets.spinner.builder.XSpinnerBuilder;
import cn.nextop.guava.widgets.spinner.render.AbstractXSpinnerPanel;
import cn.nextop.guava.widgets.spinner.render.widget.XSpinnerButtonWidget;
import cn.nextop.guava.widgets.spinner.render.widget.XSpinnerTextWidget;

/**
 * @author jonny
 */
public class XSpinnerPanel extends AbstractXSpinnerPanel {
	//
	protected XSpinner<?> spinner;
	
	/**
	 * 
	 */
	public XSpinner<?> getSpinner() { return spinner; }

	/**
	 * 
	 */
	public XSpinnerPanel(String name, XSpinner<?> spinner) {
		super(name); this.spinner = spinner;
		addMouseMotionListener(new MouseMotionListener.Stub());
	}
	
	@Override
	protected void paintBorder(Graphics g) {
		super.paintBorder(g);
		g.setForegroundColor(Colors.COLOR_DARK_GRAY);
		g.drawRectangle(CGUtils.getBorderRect(getBounds()));
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		g.setBackgroundColor(Colors.COLOR_WHITE);
		g.fillRectangle(getBounds());
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		XSpinnerPanel parent = (XSpinnerPanel) container;
		XSpinnerBuilder builder = spinner.getBuilder();
		final XSpinnerButtonWidget btnUp = builder.getBtnUp();
		final XSpinnerButtonWidget btnDown = builder.getBtnDown();
		final XSpinnerTextWidget txtSpinner = builder.getTxtSpinner();
		//
		final Rectangle r = parent.getClientArea();
		final int x = r.x, y = r.y, h = r.height, w = r.width;
		if(spinner.isHorz()) {
			Dimension d1 = btnUp.getPreferredSize(w, h);
			Dimension d2 = btnDown.getPreferredSize(w, h);
			Rectangle r1 = new Rectangle(x, y, d1.width, d1.height); 
			Rectangle r2 = new Rectangle(r1.x + d1.width, y, w - d1.width - d2.width, h);
			Rectangle r3 = new Rectangle(x + w - d2.width, y, d2.width, d2.height);
			btnDown.setBounds(r1); txtSpinner.setBounds(r2); btnUp.setBounds(r3);
		} else {
			Dimension d1 = btnUp.getPreferredSize(w, h);
			Dimension d2 = btnDown.getPreferredSize(w, h);
			Rectangle r1 = new Rectangle(x, y, w - d1.width, h); 
			Rectangle r2 = new Rectangle(r1.x + r1.width, y, d1.width, h / 2);
			Rectangle r3 = new Rectangle(r1.x + r1.width, y + h / 2, d2.width, h / 2);
			txtSpinner.setBounds(r1); btnUp.setBounds(r2); btnDown.setBounds(r3);
		}
	}
}
