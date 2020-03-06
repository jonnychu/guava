package cn.nextop.guava.widgets.datetime;

import static cn.nextop.guava.utils.SwtUtils.shell;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import cn.nextop.guava.draw2d.scroll.ScrollEventDispatcher;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.popup.PopupPanel;

/**
 * @author jonny
 */
public class XDateTimePopup extends Canvas {
	//
	private Layout layout;
	private PopupPanel popup;
	private XDateTime dateTime;
	private LightweightSystem lws;
	private DummyCalendar dummyCalendar;
	
	/**
	 * 
	 */
	public PopupPanel getPopup() { return popup; }
	public XDateTime getDateTime() { return dateTime; }
	public DummyCalendar getDummyCalendar() { return dummyCalendar; }
	
	/**
	 * 
	 */
	public XDateTimePopup(XDateTime dateTime) {
		super(shell(dateTime.getShell()), SWT.DOUBLE_BUFFERED);
		this.dateTime = dateTime;
		this.dummyCalendar = new DummyCalendar(dateTime.getModel().getTime1());
		//
		this.layout = new Layout();	this.lws = new LightweightSystem(this);
		this.lws.setContents(popup = new PopupPanel(this));
		this.lws.setEventDispatcher(new ScrollEventDispatcher());
		
		//
		getShell().addListener(SWT.Deactivate, new DeactivateListener());
	}

	public void show() {
		layout.layout(dateTime, this); getShell().open();
	}
	
	public void hide() {
		this.dateTime.setPopup(null); this.dateTime.redraw(); getShell().dispose();
	}
	
	/**
	 * 
	 */
	private class DeactivateListener implements Listener { @Override public void handleEvent(Event event) { hide(); }}
	
	/**
	 * popup shell layout
	 */
	private final class Layout {
		/**
		 * 
		 */
		public void layout(XDateTime time, XDateTimePopup popup) {
			final Shell shell = popup.getShell();
			final Composite parent = time.getParent();
			final Rectangle bounds = time.getBounds();
			final Display display = shell.getDisplay();
			final Rectangle r1 = display.map(parent, null, bounds);
			final Rectangle r2 = shell.getMonitor().getClientArea();
			// bound
			shell.setSize(300, 290);
			// location
			final int margin = 2;
			final Point size = shell.getSize();
			int x = r1.x, y = r1.y + r1.height + margin;
			if(y + size.y > r2.y + r2.height) y = r1.y - size.y - margin;
			if(x < r2.x) x = r2.x; else if(x + size.x > r2.x + r2.width) x = r2.x + r2.width - size.x;
			shell.setLocation(x, y);
		}
	}
}
