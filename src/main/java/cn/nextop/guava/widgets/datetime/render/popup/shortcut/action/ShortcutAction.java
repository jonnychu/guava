package cn.nextop.guava.widgets.datetime.render.popup.shortcut.action;

import static java.lang.System.currentTimeMillis;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.glossary.Shortcut;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.model.XDateTimeModel;
import cn.nextop.guava.widgets.datetime.render.popup.shortcut.Content;
import cn.nextop.guava.widgets.datetime.render.popup.shortcut.widget.ShortcutItem;

/**
 * @author jonny
 */
public class ShortcutAction extends AbstractShortcutAction {

	@Override
	protected boolean updateData(IFigure container, IFigure widget) {
		final Content content = (Content) container;
		final ShortcutItem item = (ShortcutItem) widget;
		final DummyCalendar dc = content.getDummyCalendarFromContent();
		final XDateTimeModel model = content.getXDateTimeModelFromContent();
		
		
		Shortcut shortcut = item.getShortcut();
		if(shortcut == Shortcut.NOW) {
			dc.select(System.currentTimeMillis());
			model.setTime(currentTimeMillis());
		} else if(shortcut == Shortcut.EOD) {
			
		} else if(shortcut == Shortcut.SOD) {
			
		} else {
			throw new RuntimeException("None Shortcut");
		}
		return false;
	}
}
