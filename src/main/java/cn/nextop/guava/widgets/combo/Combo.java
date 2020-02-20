package cn.nextop.guava.widgets.combo;

import java.util.List;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener.Stub;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import cn.nextop.guava.widgets.combo.model.ComboItem;
import cn.nextop.guava.widgets.combo.model.ComboModel;
import cn.nextop.guava.widgets.combo.render.ComboPanel;

/**
 * 
 */
public class Combo extends Canvas {
	//
	private ComboModel model;
	private ComboPopup popup;
	private ComboPanel panel;
	private LightweightSystem lws;
	//
	public ComboPopup getPopup() { return popup; }
	public void setPopup(ComboPopup popup) { this.popup = popup; }
	public ComboPanel getPanel() { return panel; }
	public void setPanel(ComboPanel panel) { this.panel = panel; }
	public ComboModel getModel() { return model; }
	public void setModel(ComboModel model) { this.model = model; }
	
	/**
	 * 
	 */
	public Combo(Composite parent, int style) {
		super(parent, style);
		this.model = new ComboModel();
		this.lws = new LightweightSystem(this);
		this.lws.setContents(panel = new ComboPanel());
		this.panel.addMouseListener(new ShowAction(this));
	}
	
	public void setInput(List<ComboItem> input) {
		this.model.setInput(input);
		ComboItem item = this.model.getSelection();
		if(item != null) getPanel().setText(item.getValue());
	}
	
	/**
	 * 
	 */
	private class ShowAction extends Stub {
		//
		private Combo combo;
		
		/**
		 * 
		 */
		public ShowAction(Combo combo) {
			this.combo = combo;
		}
		
		@Override
		public void mouseReleased(MouseEvent me) {
			super.mouseReleased(me);
			combo.setPopup(new ComboPopup(combo, SWT.NONE));
			combo.getPopup().show();
		}
	}
}
