package cn.nextop.guava.widgets.datetime.render.popup.calendar.date.widget;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import com.patrikdufresne.fontawesome.FontAwesome;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.utils.Fonts;
import cn.nextop.guava.widgets.datetime.glossary.Type;
import cn.nextop.guava.widgets.datetime.render.AbstractWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.action.MonthAction;

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
			if(this.selected) g.setForegroundColor(Colors.COLOR_BLUE);
			else g.setForegroundColor(Colors.COLOR_DARK_GRAY);
			g.setFont(FontAwesome.getFont(12));
			Dimension d1 = TextUtilities.INSTANCE.getStringExtents(text, g.getFont());
			g.drawString(text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
		} else if(this.type == Type.UP) {
			if(this.selected) g.setForegroundColor(Colors.COLOR_BLUE);
			else g.setForegroundColor(Colors.COLOR_DARK_GRAY);
			g.setFont(FontAwesome.getFont(12));
			Dimension d1 = TextUtilities.INSTANCE.getStringExtents(text, g.getFont());
			g.drawString(text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
		} else if(this.type == Type.SELECT) {
			if (this.selected) g.setForegroundColor(Colors.COLOR_BLUE);
			else g.setForegroundColor(Colors.COLOR_BLACK);
			g.setFont(Fonts.size(g.getFont(), 3));
			Dimension d1 = TextUtilities.INSTANCE.getTextExtents(text, g.getFont());
			g.drawString(text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
		}
	}
	
	@Override
	public void handleMouseReleased(MouseEvent event) {
		super.handleMouseReleased(event); new MonthAction(type).onAction(getParent(), this);
	}
}