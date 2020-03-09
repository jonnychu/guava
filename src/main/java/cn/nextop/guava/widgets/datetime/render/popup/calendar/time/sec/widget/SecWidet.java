package cn.nextop.guava.widgets.datetime.render.popup.calendar.time.sec.widget;

import static java.lang.String.valueOf;
import static org.eclipse.draw2d.TextUtilities.INSTANCE;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.datetime.render.AbstractWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.TimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.action.SecondAction;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.sec.SecContent;
import cn.nextop.guava.widgets.datetime.render.text.acton.ShowTextAction;
import cn.nextop.guava.widgets.datetime.render.util.Faster;

/**
 * @author jonny
 */
public class SecWidet extends AbstractWidget {
	//
	private int second;
	
	/**
	 * 
	 */
	public int getSecond() { return second; }

	/**
	 * 
	 */
	public SecWidet(int second, boolean selected) {
		this.second = second; this.text = valueOf(second); this.selected = selected;
	}
	
	public void setValue(int second, boolean selected) {
		this.second = second; this.text = valueOf(second); this.selected = selected;
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
		SecContent content = (SecContent)getParent();
		TimePanel tp = content.getSecPanel().getTimePanel();
		new SecondAction().onAction(tp, this); new ShowTextAction().onAction(Faster.getTextPanelFromTime(tp), null);
	}
}
