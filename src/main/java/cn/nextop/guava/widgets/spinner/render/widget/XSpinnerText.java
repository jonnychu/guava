package cn.nextop.guava.widgets.spinner.render.widget;

import static java.lang.String.valueOf;
import static org.eclipse.draw2d.TextUtilities.INSTANCE;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.spinner.XSpinner;
import cn.nextop.guava.widgets.spinner.model.XSpinnerModel;
import cn.nextop.guava.widgets.spinner.render.AbstractXSpinnerWidget;

/**
 * @author jonny
 */
public class XSpinnerText extends AbstractXSpinnerWidget {
	//
	protected XSpinner spinner;
	protected XSpinnerModel model;
	
	/**
	 * 
	 */
	public XSpinnerText(XSpinner spinner, String name) {
		super(name, false, false); 
		this.spinner = spinner;
		this.model = spinner.getModel(); 
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		final Rectangle rect = getBounds();
		g.setBackgroundColor(Colors.COLOR_WHITE);
		g.fillRectangle(getClientArea());
		this.text = valueOf(this.model.getValue());
		Dimension d1 = INSTANCE.getStringExtents(this.text, g.getFont());
		if(spinner.isHorz()) {
			g.drawString(this.text, rect.x + this.margin, rect.y + (rect.height - d1.height) / 2);
		} else {
			g.drawString(this.text, rect.width - d1.width - this.margin, rect.y + (rect.height - d1.height) / 2);
		}
	}
}
