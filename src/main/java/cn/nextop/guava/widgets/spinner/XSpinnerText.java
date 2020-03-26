package cn.nextop.guava.widgets.spinner;

import static java.lang.Long.parseLong;
import static org.eclipse.swt.SWT.LEFT;
import static org.eclipse.swt.SWT.RIGHT;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import cn.nextop.guava.widgets.spinner.model.XSpinnerModel;
import cn.nextop.guava.widgets.spinner.render.widget.XSpinnerTextWidget;

public class XSpinnerText {
	//
	private Text text;
	private boolean showing;
	private XSpinner spinner;
	
	/**
	 * 
	 */
	protected XSpinnerText(XSpinner spinner) {
		this.spinner = spinner;
		final boolean isHorz = spinner.isHorz();
		this.text = new Text(spinner, isHorz ? LEFT : RIGHT);
		this.text.addListener(SWT.Deactivate, new DeactivateListener());
	}
	
	public void dispose() {
		if (this.showing) hide(); this.text.dispose();
	}

	public void setShellBounds(int x, int y, int width, int height) {
		if (this.spinner.isDisposed()) return;
		this.text.setBounds(x, y, width, height);
	}
	
	public void hide() {
		if (!this.spinner.isDisposed()) {
			String val = this.text.getText();
			XSpinnerModel model = spinner.getModel();
			XSpinnerTextWidget w = spinner.getBuilder().getTxtSpinner();
			model.setValue(parseLong(val)); w.repaint(); this.text.setVisible(false); 
		}
		showing = false; 
	}
	
	public void show() {
		if (!this.spinner.isDisposed()) {
			XSpinnerModel model = spinner.getModel();
			String val = String.valueOf(model.getValue());
			this.text.setText(val); this.text.setVisible(true); this.text.setFocus(); 
		}
		showing = true;
	}
	
	/**
	 * 
	 */
	protected class DeactivateListener implements Listener {
		@Override public void handleEvent(Event event) {hide();}
	}
}
