package cn.nextop.guava.widgets.datetime.render.popup.calendar.date.bottom;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.datetime.render.AbstractPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.bottom.widget.OkButtonWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.bottom.widget.TimeButtonWidget;

/**
 * @author jonny
 */
public class BottomPanel extends AbstractPanel {
	//
	private DatePanel datePanel;
	private OkButtonWidget btnOk;
	private TimeButtonWidget btnTime;
	
	/**
	 * 
	 */
	public DatePanel getDatePanel() { return datePanel; }

	/**
	 * 
	 */
	public BottomPanel(DatePanel datePanel) {
		this.datePanel = datePanel;
		add(btnTime = new TimeButtonWidget("OK"));
		add(btnOk = new OkButtonWidget("Select Time"));
	}
	
	@Override
	protected void paintBorder(Graphics g) {
		super.paintBorder(g);
		final Rectangle r = getBounds();
		g.setForegroundColor(Colors.COLOR_DARK_GRAY);
		g.drawLine(r.x, r.y + 2, r.x + r.width , r.y + 2);
	}

	@Override
	protected void layoutManager(IFigure container) {
		BottomPanel parent = (BottomPanel)container;
		final Rectangle r = parent.getBounds();
		final int x = r.x, y = r.y, w = r.width, h = r.height;
		//
		int w1 = w / 2 , h1 = h - 4;
		Rectangle r1 = new Rectangle(x, y + (h - h1) / 2, w1, h1); btnOk.setBounds(r1);
		Rectangle r2 = new Rectangle(x + r1.width, y + (h - h1) / 2, w1, h1); btnTime.setBounds(r2);
	}
}
