package cn.nextop.guava.widgets.spinner;

import static java.lang.Long.parseLong;
import static org.eclipse.swt.SWT.LEFT;
import static org.eclipse.swt.SWT.RIGHT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.spinner.model.XSpinnerModel;
import cn.nextop.guava.widgets.spinner.render.widget.XSpinnerTextWidget;

public class XSpinnerText extends Shell {
	//
	private Text text;
	private boolean showing;
	private XSpinner spinner;
	private Pattern pattern = Pattern.compile("[0-9]\\d*");
	
	/**
	 * 
	 */
	protected XSpinnerText(XSpinner spinner) {
		super(spinner.getShell(), SWT.NO_BACKGROUND);
		this.spinner = spinner;
		this.setLayout(new FillLayout());
		boolean isHorz = spinner.isHorz();
		this.text = new Text(this, isHorz ? LEFT : RIGHT);
		this.text.setBackground(Colors.COLOR_WHITE);
		this.addListener(SWT.Deactivate, new DeactivateListener());
		this.text.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				Matcher matcher = pattern.matcher(e.text);
				if (matcher.matches()) e.doit = true;
				else if (e.text.length() > 0) e.doit = false;
				else e.doit = true;
			}
		});
	}

	
	public String getText() {
		return text.getText();
	}

	public void setText(String text) {
		this.text.setText(text == null ? "" : text);
	}

	@Override
	protected void checkSubclass() { }
	
	public void dispose() {
		if (this.showing) hide(); super.dispose();
	}

	public void setShellBounds(int x, int y, int width, int height) {
		if (isDisposed()) return;
		this.setBounds(x, y, width, height);
		this.text.setBounds(0, 2, width, height);
	}
	
	public void hide() {
		if (!isDisposed()) this.setVisible(false); 
		final String val = this.text.getText();
		XSpinnerModel model = this.spinner.getModel();
		XSpinnerTextWidget w = this.spinner.getBuilder().getTxtSpinner();
		w.setText(val); w.repaint(); model.setValue( parseLong(val)); showing = false; 
	}
	
	public void show() {
		if (!isDisposed()) this.setVisible(true); this.setFocus(); showing = true;
		
	}
	
	private class DeactivateListener implements Listener { @Override public void handleEvent(Event event) { hide(); }}
}
