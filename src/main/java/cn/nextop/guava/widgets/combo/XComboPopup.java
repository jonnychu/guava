package cn.nextop.guava.widgets.combo;

import static cn.nextop.guava.utils.SwtUtils.shell;

import java.util.List;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import cn.nextop.guava.draw2d.scroll.support.dispatcher.ScrollEventDispatcher;
import cn.nextop.guava.utils.Layout;
import cn.nextop.guava.widgets.combo.model.XComboModel;
import cn.nextop.guava.widgets.combo.model.colum.Column;
import cn.nextop.guava.widgets.combo.model.config.XComboConfig;
import cn.nextop.guava.widgets.combo.model.row.IRow;
import cn.nextop.guava.widgets.combo.render.popup.PopupPanel;
import cn.nextop.guava.widgets.combo.render.popup.content.DataContent;
import cn.nextop.guava.widgets.combo.render.popup.content.HeaderContent;

/**
 * @author jonny
 */
public class XComboPopup extends Canvas {
	//
	private XCombo combo;
	private PopupPanel popup;
	private LightweightSystem lws;
	
	/**
	 * 
	 */
	public XCombo getXCombo() { return combo; }
	public PopupPanel getPopup() { return popup; }
	
	/**
	 * 
	 */
	public XComboPopup(XCombo combo) {
		super(shell(combo.getShell()), SWT.DOUBLE_BUFFERED);
		this.combo = combo;
		//
		this.lws = new LightweightSystem(this);
		this.lws.setEventDispatcher(new ScrollEventDispatcher());
		this.lws.setContents(popup = new PopupPanel("popup", this));
		
		//
		getShell().addListener(SWT.Deactivate, new DeactivateListener());
	}

	public void input(List<IRow> rows) {
		
	}
	
	public void show() {
		final XComboModel model = combo.getModel();
		final XComboConfig cfg = model.getXComboConfig();
		final List<Column<?>> cols = model.getColums().getColums();
		final int w = cfg.getPopupWidth(), h = cfg.getPopupHeight();
		Layout.layout(combo, getShell(), w, h); if(cfg.hasHeader()) {
			popup.setHeaderContents(new HeaderContent("header.content", cols));
		}
		DataContent data = new DataContent("data.content", popup, cols);
		popup.setDataContents(data); data.invalidate();
		getShell().open();
	}
	
	public void hide() {
		this.combo.setPopup(null); this.combo.redraw(); getShell().dispose();
	}
	
	/**
	 * 
	 */
	private class DeactivateListener implements Listener { @Override public void handleEvent(Event event) { hide(); }}
}
