package cn.nextop.guava.widgets.datetime.render.popup.calendar.time.hour.widget;

import static cn.nextop.guava.widgets.datetime.support.tuil.Faster.getTextPanel;
import static java.lang.String.valueOf;
import static org.eclipse.draw2d.TextUtilities.INSTANCE;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.AbstractWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.TimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.action.HourAction;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.hour.HourContent;
import cn.nextop.guava.widgets.datetime.render.text.acton.ShowTextAction;

/**
 * @author jonny
 */
public class HourWidet extends AbstractWidget {
	//
	private int hour;
	
	/**
	 * 
	 */
	public int getHour() { return hour; }

	/**
	 * 
	 */
	public HourWidet(int hour, boolean selected) {
		this.hour = hour; this.text = valueOf(hour); this.selected = selected;
	}
	
	public void setValue(int hour, boolean selected) {
		this.hour = hour; this.text = valueOf(hour); this.selected = selected;
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		Rectangle rect = getBounds();
		if(enter) g.setBackgroundColor(Colors.COLOR_WIDGET_MOTION_ENTER);
		else g.setBackgroundColor(Colors.COLOR_WHITE);
		if(selected) g.setBackgroundColor(Colors.COLOR_LIGHT_BLUE);
		g.fillRectangle(rect);
		
		//
		Dimension d1 = INSTANCE.getStringExtents(text, g.getFont());
		g.drawString(text, rect.x + 10, rect.y + (rect.height - d1.height) / 2);
	}
	
	@Override
	public void handleMouseReleased(MouseEvent event) {
		super.handleMouseReleased(event);
		HourContent content = (HourContent)getParent();
		TimePanel tp = content.getHourPanel().getTimePanel();
		new HourAction().onAction(tp, this); new ShowTextAction().onAction(getTextPanel(tp), null);
	}
}
