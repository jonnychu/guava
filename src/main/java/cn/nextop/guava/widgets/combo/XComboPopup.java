package cn.nextop.guava.widgets.combo;

import static cn.nextop.guava.support.swt.SwtUtils.shell;

import java.util.List;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import cn.nextop.guava.support.swt.Layout;
import cn.nextop.guava.widgets.combo.model.XComboModel;
import cn.nextop.guava.widgets.combo.model.colum.Column;
import cn.nextop.guava.widgets.combo.model.config.XComboConfig;
import cn.nextop.guava.widgets.combo.render.popup.PopupPanel;
import cn.nextop.guava.widgets.combo.render.popup.content.data.DataContent;
import cn.nextop.guava.widgets.combo.render.popup.content.header.HeaderContent;

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
		this.lws.setContents(popup = new PopupPanel("popup", this));
		
		//
		getShell().addListener(SWT.MouseWheel, new MouseWhellListener());
		getShell().addListener(SWT.Deactivate, new DeactivateListener());
	}

	public void show() {
		final XComboModel model = combo.getModel();
		final XComboConfig cfg = model.getXComboConfig();
		final int size = model.getRows().getRows().size();
		final List<Column<?>> cols = model.getColums().getColums();
		final int w = cfg.getPopupWidth(), h = cfg.getPopupHeight();
		final int h1 = cfg.getHeaderHeight(), h2 = cfg.getItemHeight();
		boolean x = cfg.hasHeader(); int totalHeight = (x ? h1 : 0) + h2 * size;
		Layout.layout(combo, getShell(), w, totalHeight < h ? totalHeight : h); 
		//
		if(x) popup.setHeaderContents(new HeaderContent("header.content", cols));
		popup.setDataContents(new DataContent("data.content", popup, cols)); getShell().open();
	}
	
	public void hide() {
		this.combo.setPopup(null); this.combo.redraw(); getShell().dispose();
	}
	
	/**
	 * 
	 */
	private class DeactivateListener implements Listener { 
		@Override public void handleEvent(Event event) { hide(); }
	}

	private class MouseWhellListener implements Listener {
		@Override public void handleEvent(Event event) {
			if(event.count > 0) popup.pageUp(); else popup.pageDown();
		}
	}
	
}
