package cn.nextop.guava.widgets.combo;

import static cn.nextop.guava.support.Objects.cast;
import static org.eclipse.swt.SWT.DOUBLE_BUFFERED;

import java.util.List;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import cn.nextop.guava.widgets.combo.model.XComboModel;
import cn.nextop.guava.widgets.combo.model.row.IRow;
import cn.nextop.guava.widgets.combo.model.row.Rows;
import cn.nextop.guava.widgets.combo.render.text.TextPanel;

/**
 * @author jonny
 */
public class XCombo extends Canvas {
	//
	private XComboModel model;
	private XComboPopup popup;
	private TextPanel textPanel;
	private LightweightSystem lws;
	
	/**
	 * 
	 */
	public XComboModel getModel() { return model; }
	public XComboPopup getPopup() { return popup; }
	public TextPanel getTextPanel() { return textPanel; }
	public void setModel(XComboModel model) { this.model = model; }
	public void setPopup(XComboPopup popup) { this.popup = popup; }
	
	/**
	 * @param parent
	 * @param style SWT.SINGLE or SWT.MULTI
	 */
	public XCombo(Composite parent) {
		super(parent, DOUBLE_BUFFERED);
		this.model = new XComboModel();
		this.lws = new LightweightSystem(this);
		this.lws.setContents(textPanel = new TextPanel(this));
		//
		this.textPanel.addMouseListener(new ShowPopupAction());
	}
	
	/**
	 * 
	 */
	public void input(List<IRow> rows) {
		this.model.setRows(new Rows(rows));
	}
	
	/**
	 * 
	 */
	private class ShowPopupAction extends MouseListener.Stub {
		@Override public void mouseReleased(MouseEvent me) {
			if(me.button != 1) return;
			XCombo combo = XCombo.this; 
			TextPanel textPanel = cast(me.getSource());
			XComboPopup next = new XComboPopup(combo);
			combo.setPopup(next); next.show(); textPanel.repaint();
		}
	}
}
