package cn.nextop.guava.widgets.combo;

import java.util.List;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import cn.nextop.guava.widgets.combo.model.ComboItem;
import cn.nextop.guava.widgets.combo.model.ComboModel;
import cn.nextop.guava.widgets.combo.model.ComboPopupModel;
import cn.nextop.guava.widgets.combo.render.ComboPopupPanel;

public class ComboPopup extends FigureCanvas {
	//
	private Combo combo;
	private ComboPopupModel model;
	private ComboPopupPanel panel;
	
	public Combo getCombo() { return combo; }
	public ComboPopupPanel getPanel() { return panel; }

	/**
	 * 
	 */
	public ComboPopup(Combo combo, int style) {
		super(shell(combo), style);
		this.combo = combo;
		this.model = new ComboPopupModel();
		this.setHorizontalScrollBarVisibility(NEVER);
		this.setVerticalScrollBarVisibility(AUTOMATIC);
		this.setContents(this.panel = new ComboPopupPanel(this));
		
		getShell().addListener(SWT.Deactivate, new DeactivateListener());
	}
	
	public void hide() {
		this.combo.getPanel().deactive();
		this.combo.setPopup(null); getShell().dispose();
	}
	
	public void show() {
		this.model.getLayout().layout(this.combo); getShell().open();
		final ComboModel model = this.combo.getModel();
		final List<ComboItem> input = model.getInput();
		for (ComboItem item : input) {
			panel.addItem(item);
		}
	}
	
	protected static Shell shell(Combo combo) {
		Shell parent = new Shell(combo.getShell(), SWT.NO_TRIM | SWT.ON_TOP);
		parent.setLayout(new FillLayout()); return parent;
	}
	
	private class DeactivateListener implements Listener { @Override public void handleEvent(Event event) { hide(); }}
}
