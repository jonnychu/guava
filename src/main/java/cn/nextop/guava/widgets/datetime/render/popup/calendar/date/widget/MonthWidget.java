package cn.nextop.guava.widgets.datetime.render.popup.calendar.date.widget;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import com.patrikdufresne.fontawesome.FontAwesome;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.utils.Fonts;
import cn.nextop.guava.widgets.datetime.AbstractWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.action.MonthAction;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.MonthPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.action.ShowMonthAction;
import cn.nextop.guava.widgets.datetime.support.glossary.Type;

/**
 * @author jonny
 */
public class MonthWidget extends AbstractWidget {
	//
	private Type type;
	
	/**
	 * 
	 */
	public MonthWidget(String text, Type type) {
		this.type = type; this.text = text;
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		Rectangle r = getBounds();
		if(this.type == Type.DOWN) {
			if(this.enter) g.setForegroundColor(Colors.COLOR_BLUE);
			else g.setForegroundColor(Colors.COLOR_DARK_GRAY);
			g.setFont(FontAwesome.getFont(12));
			Dimension d1 = TextUtilities.INSTANCE.getStringExtents(text, g.getFont());
			g.drawString(text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
		} else if(this.type == Type.UP) {
			if(this.enter) g.setForegroundColor(Colors.COLOR_BLUE);
			else g.setForegroundColor(Colors.COLOR_DARK_GRAY);
			g.setFont(FontAwesome.getFont(12));
			Dimension d1 = TextUtilities.INSTANCE.getStringExtents(text, g.getFont());
			g.drawString(text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
		} else if(this.type == Type.SELECT) {
			if (this.enter) g.setForegroundColor(Colors.COLOR_BLUE);
			else g.setForegroundColor(Colors.COLOR_BLACK);
			g.setFont(Fonts.size(g.getFont(), 3));
			Dimension d1 = TextUtilities.INSTANCE.getTextExtents(text, g.getFont());
			g.drawString(text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
		}
	}
	
	@Override
	public void handleMouseReleased(MouseEvent event) {
		super.handleMouseReleased(event); 
		DatePanel dp = (DatePanel)getParent(); MonthPanel mp = dp.getCalendarPanel().getMonthPanel();
		new MonthAction(type).onAction(dp, this); if(type == Type.SELECT) new ShowMonthAction().onAction(mp, null);
	}
}