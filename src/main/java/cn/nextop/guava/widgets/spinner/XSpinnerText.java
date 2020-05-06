package cn.nextop.guava.widgets.spinner;

import static cn.nextop.guava.support.util.Objects.cast;
import static org.eclipse.swt.SWT.LEFT;
import static org.eclipse.swt.SWT.RIGHT;

import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import cn.nextop.guava.support.util.Objects;
import cn.nextop.guava.widgets.spinner.model.XSpinnerModel;
import cn.nextop.guava.widgets.spinner.render.widget.XSpinnerTextWidget;

/**
 * @author jonny
 */
public class XSpinnerText {
	//
	private boolean showing;
	private XSpinner<?> spinner;
	private FormattedText text;
	
	/**
	 * 
	 */
	protected XSpinnerText(XSpinner<?> spinner) {
		this.spinner = spinner;
		int a = spinner.isHorz() ? LEFT : RIGHT;
		this.text = new FormattedText(spinner, a);
		this.text.setFormatter(spinner.getFormatter());
		this.text.getControl().addListener(SWT.Deactivate, new DeactivateListener());
	}
	
	public void dispose() {
		if (this.showing) hide(); this.text.getControl().dispose();
	}

	public void setShellBounds(int x, int y, int width, int height) {
		if (this.spinner.isDisposed()) return;
		this.text.getControl().setBounds(x, y, width, height);
	}
	
	public void hide() {
		if (!this.spinner.isDisposed()) {
			Object val = this.text.getValue();
			XSpinnerModel<?> model = spinner.getModel();
			XSpinnerTextWidget w = spinner.getBuilder().getTxtSpinner();
			model.setValue(cast(val)); w.repaint(); this.text.getControl().setVisible(false); 
		}
		showing = false; 
	}
	
	public void show() {
		if (!this.spinner.isDisposed()) {
			XSpinnerModel<?> model = spinner.getModel();
			this.text.setValue(Objects.cast(model.getValue())); 
			this.text.getControl().setVisible(true); this.text.getControl().setFocus(); 
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
