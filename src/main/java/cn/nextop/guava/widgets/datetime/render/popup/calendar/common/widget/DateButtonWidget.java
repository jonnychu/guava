package cn.nextop.guava.widgets.datetime.render.popup.calendar.common.widget;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.CGUtils;
import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.utils.Fonts;
import cn.nextop.guava.widgets.datetime.glossary.PanelType;
import cn.nextop.guava.widgets.datetime.render.AbstractWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.CalendarPanel;

/**
 * @author jonny
 */
public class DateButtonWidget extends AbstractWidget {
	/**
	 * 
	 */
	public DateButtonWidget(String text) {
		this.text = text;
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		final Rectangle r = getBounds(); g.setFont(Fonts.size(g.getFont(), 2));
		Dimension d1 = TextUtilities.INSTANCE.getTextExtents(text, g.getFont());
		if(enter) {
			g.setBackgroundColor(Colors.COLOR_CYAN);
			g.fillRoundRectangle(CGUtils.scaleRect(r, -3), arc, arc);
		}
		g.drawString(text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
	}
	
	@Override
	public void handleMouseReleased(MouseEvent event) {
		super.handleMouseReleased(event); System.out.println(111);
		CalendarPanel cp = (CalendarPanel)getParent().getParent(); cp.panel(PanelType.DATE);
	}
}