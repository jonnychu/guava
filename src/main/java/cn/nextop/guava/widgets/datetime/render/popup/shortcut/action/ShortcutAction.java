package cn.nextop.guava.widgets.datetime.render.popup.shortcut.action;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.XDateTime;
import cn.nextop.guava.widgets.datetime.glossary.Shortcut;
import cn.nextop.guava.widgets.datetime.model.XDateTimeModel;
import cn.nextop.guava.widgets.datetime.render.popup.shortcut.Content;
import cn.nextop.guava.widgets.datetime.render.popup.shortcut.ShortcutScrollPanel;
import cn.nextop.guava.widgets.datetime.render.popup.shortcut.widget.ShortcutItem;

/**
 * @author jonny
 */
public class ShortcutAction extends AbstractShortcutAction {

	@Override
	protected boolean updateData(IFigure container, IFigure widget) {
		Content content = (Content) container;
		ShortcutItem item = (ShortcutItem) widget;
		ShortcutScrollPanel ssp = content.getShortcutScrollPanel();
		final XDateTime dateTime = ssp.getDateTime();
		final XDateTimeModel model = dateTime.getModel();
		
		Shortcut shortcut = item.getShortcut();
		if(shortcut == Shortcut.NOW) {
			model.setTime(System.currentTimeMillis());
		} else if(shortcut == Shortcut.EOD) {
			
		} else if(shortcut == Shortcut.SOD) {
			
		} else {
			throw new RuntimeException("None Shortcut");
		}
		return false;
	}
}
