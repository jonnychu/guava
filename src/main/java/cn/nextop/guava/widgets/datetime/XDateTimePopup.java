package cn.nextop.guava.widgets.datetime;

import static cn.nextop.guava.utils.SwtUtils.shell;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import cn.nextop.guava.widgets.datetime.render.popup.PopupPanel;

/**
 * @author jonny
 */
public class XDateTimePopup extends Canvas {
	//
	private XDateTime dateTime;
	private LightweightSystem lws;
	
	/**
	 * 
	 */
	public XDateTimePopup(XDateTime dateTime) {
		super(shell(dateTime.getShell()), SWT.DOUBLE_BUFFERED);
		this.dateTime = dateTime;
		this.lws = new LightweightSystem(this);
		this.lws.setContents(new PopupPanel(this));
		
		//
		getShell().addListener(SWT.Deactivate, new DeactivateListener());
	}

	public void show() {
		this.dateTime.getModel().getLayout().layout(dateTime); getShell().open();
	}
	
	public void hide() {
		this.dateTime.setPopup(null); this.dateTime.redraw(); getShell().dispose();
	}
	
	private class DeactivateListener implements Listener { @Override public void handleEvent(Event event) { hide(); }}
}
