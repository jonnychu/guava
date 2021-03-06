package cn.nextop.guava.widgets.datetime.action.actor.shortcut.impl;

import static cn.nextop.guava.support.Objects.cast;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.XDateTimePopup;
import cn.nextop.guava.widgets.datetime.action.actor.shortcut.AbstractShortcutActor;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.model.XDateTimeModel;
import cn.nextop.guava.widgets.datetime.render.popup.shortcut.ShortcutContent;
import cn.nextop.guava.widgets.datetime.render.popup.shortcut.widget.ShortcutItem;
import cn.nextop.guava.widgets.datetime.support.glossary.Shortcut;

/**
 * @author jonny
 */
public class ShortcutActor extends AbstractShortcutActor {

	@Override
	protected boolean updateData(IFigure container, IFigure widget) {
		final ShortcutItem item = cast(widget);
		final ShortcutContent sc = cast(container);
		XDateTimePopup popup = sc.getFactory().getDateTimePopup();
		final XDateTimeModel model = popup.getDateTime().getModel();
		final DummyCalendar dummyCalendar = popup.getDummyCalendar();
		
		
		Shortcut shortcut = item.getShortcut();
		if(shortcut == Shortcut.NOW) {
			long now = System.currentTimeMillis();
			model.setTime(now); dummyCalendar.select(now);
		} else if(shortcut == Shortcut.EOD) {
			
		} else if(shortcut == Shortcut.SOD) {
			
		} else {
			throw new RuntimeException("None Shortcut");
		}
		return false;
	}
}
