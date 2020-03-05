package cn.nextop.guava.draw2d.scroll;

import org.eclipse.draw2d.EventDispatcher;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;

/**
 * @author jonny
 */
public class ScrollEvent extends MouseEvent {
	//
	private static final long serialVersionUID = 5030811482580369158L;
	
	//
	public int count;
	
	/**
	 * 
	 */
	public ScrollEvent(EventDispatcher dispatcher, IFigure source, 
			org.eclipse.swt.events.MouseEvent me) {
		super(dispatcher, source, me); this.count = me.count;
	}
}
