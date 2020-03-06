package cn.nextop.guava.widgets.datetime.render.popup.calendar.time.min.widget;

import static java.lang.String.valueOf;
import static org.eclipse.draw2d.TextUtilities.INSTANCE;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.datetime.render.AbstractWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.TimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.action.MinuteAction;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.min.MinContent;
import cn.nextop.guava.widgets.datetime.render.text.acton.ShowTextAction;

/**
 * @author jonny
 */
public class MinWidet extends AbstractWidget {
	//
	private int minute;
	
	/**
	 * 
	 */
	public int getMinute() { return minute; }

	/**
	 * 
	 */
	public MinWidet(int minute, boolean selected) {
		this.minute = minute; this.text = valueOf(minute); this.selected = selected;
	}
	
	public void setValue(int minute, boolean selected) {
		this.minute = minute; this.text = valueOf(minute); this.selected = selected;
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		Rectangle rect = getBounds();
		if(enter) g.setBackgroundColor(Colors.COLOR_CYAN);
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
		MinContent content = (MinContent)getParent();
		TimePanel tp = content.getMinPanel().getTimePanel();
		new MinuteAction().onAction(tp, this); new ShowTextAction().onAction(tp.getTextPanelFromTime(), null);
	}
}
