package cn.nextop.guava.widgets.datetime.render;

import java.util.Iterator;

import org.eclipse.draw2d.ScrollPane;

import cn.nextop.guava.draw2d.MouseWheelListener;
import cn.nextop.guava.draw2d.ScrollEvent;

/**
 * @author jonny
 */
public class AbstractScrollPanel extends ScrollPane {
	
	public AbstractScrollPanel() {
		addListener(MouseWheelListener.class, new MouseWheelListener.Stub());
	}
	
	@SuppressWarnings("rawtypes")
	public void handleMouseWheel(ScrollEvent event) {
		Iterator iter = getListeners(MouseWheelListener.class);
		while (iter.hasNext()) ((MouseWheelListener) iter.next()).mouseScrolled(event);
	}
}
