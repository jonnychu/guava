package cn.nextop.guava.widgets.datetime.render.popup.calendar.month.bottom;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.widgets.datetime.render.AbstractPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.MonthPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.bottom.widget.OkButtonWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.bottom.widget.TimeButtonWidget;

/**
 * @author jonny
 */
public class BottomPanel extends AbstractPanel {
	//
	private MonthPanel monthPanel;
	private OkButtonWidget btnOk;
	private TimeButtonWidget btnTime;
	
	/**
	 * 
	 */
	public MonthPanel getMonthPanel() { return monthPanel; }

	/**
	 * 
	 */
	public BottomPanel(MonthPanel monthPanel) {
		this.monthPanel = monthPanel;
		add(btnTime = new TimeButtonWidget("OK"));
		add(btnOk = new OkButtonWidget("Select Time"));
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