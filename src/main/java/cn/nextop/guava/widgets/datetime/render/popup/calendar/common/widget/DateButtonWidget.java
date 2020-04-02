package cn.nextop.guava.widgets.datetime.render.popup.calendar.common.widget;

import static cn.nextop.guava.widgets.table.support.util.Objects.cast;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.CGUtils;
import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.utils.Fonts;
import cn.nextop.guava.widgets.datetime.render.AbstractTimeWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.CalendarPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.action.ShowDateAction;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.TimePanel;
import cn.nextop.guava.widgets.datetime.support.glossary.PanelType;

/**
 * @author jonny
 */
public class DateButtonWidget extends AbstractTimeWidget {
	/**
	 * 
	 */
	public DateButtonWidget(String text) {
		super(); this.text = text;
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		final Rectangle r = getBounds(); g.setFont(Fonts.size(g.getFont(), 2));
		Dimension d1 = TextUtilities.INSTANCE.getTextExtents(text, g.getFont());
		if(enter) {
			g.setBackgroundColor(Colors.COLOR_WIDGET_MOTION_ENTER);
			g.fillRoundRectangle(CGUtils.scaleRect(r, -3), arc, arc);
		}
		g.drawString(text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
	}
	
	@Override
	public void handleMouseReleased(MouseEvent event) {
		super.handleMouseReleased(event);
		TimePanel tp = cast(getParent());
		DatePanel dp = tp.getBuilder().getDatePanel();
		CalendarPanel cp = tp.getBuilder().getCalendarPanel();
		cp.panel(PanelType.DATE); new ShowDateAction().onAction(dp, null);
	}
}