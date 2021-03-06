package cn.nextop.guava.widgets.datetime;

import static org.eclipse.swt.SWT.DOUBLE_BUFFERED;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import cn.nextop.guava.widgets.datetime.model.XDateTimeModel;
import cn.nextop.guava.widgets.datetime.render.text.TextPanel;

/**
 * @author jonny
 */
public class XDateTime extends Canvas {
	//
	private TextPanel textPanel;
	private XDateTimeModel model;
	private XDateTimePopup popup;
	private LightweightSystem lws;
	
	/**
	 * 
	 */
	public XDateTimeModel getModel() { return model; }
	public XDateTimePopup getPopup() { return popup; }
	public TextPanel getTextPanel() { return textPanel; }
	public void setModel(XDateTimeModel model) { this.model = model; }
	public void setPopup(XDateTimePopup popup) { this.popup = popup; }
	
	/**
	 * 
	 */
	public XDateTime(Composite parent) {
		super(parent, DOUBLE_BUFFERED);
		this.model = new XDateTimeModel();
		this.lws = new LightweightSystem(this);
		this.lws.setContents(textPanel = new TextPanel(this));
		//
		this.textPanel.addMouseListener(new ShowPopupAction());
	}
	
	/**
	 * 
	 */
	public long getInput() {
		return this.model.getTime();
	}

	public void setInput(long time) {
		this.model.setTime(time); textPanel.repaint();
	}
	
	/**
	 * 
	 */
	private class ShowPopupAction extends MouseListener.Stub {
		@Override public void mouseReleased(MouseEvent me) {
			if(me.button != 1) return;
			XDateTime xTime = XDateTime.this; 
			TextPanel textPanel = (TextPanel)me.getSource();
			XDateTimePopup next = new XDateTimePopup(xTime);
			xTime.setPopup(next); next.show(); textPanel.repaint();
		}
	}
}
