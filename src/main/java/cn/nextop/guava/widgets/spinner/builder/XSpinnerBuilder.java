package cn.nextop.guava.widgets.spinner.builder;

import static cn.nextop.guava.widgets.table.support.util.Objects.cast;
import static com.patrikdufresne.fontawesome.FontAwesome.caret_down;
import static com.patrikdufresne.fontawesome.FontAwesome.caret_left;
import static com.patrikdufresne.fontawesome.FontAwesome.caret_right;
import static com.patrikdufresne.fontawesome.FontAwesome.caret_up;

import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.widgets.Canvas;

import cn.nextop.guava.widgets.spinner.XSpinner;
import cn.nextop.guava.widgets.spinner.render.panel.XSpinnerPanel;
import cn.nextop.guava.widgets.spinner.render.widget.XSpinnerButton;
import cn.nextop.guava.widgets.spinner.render.widget.XSpinnerText;

/**
 * @author jonny
 */
public class XSpinnerBuilder extends AbstractBuilder {
	//
	private XSpinnerText txtSpinner;
	private XSpinnerPanel spinnerPanel;
	private XSpinnerButton btnUp, btnDown;
	
	@Override
	public IFigure build(Canvas parent) {
		final XSpinner spinner = cast(parent);
		final boolean isHorz = spinner.isHorz();
		String s1 = isHorz ? caret_left : caret_up;
		String s2 = isHorz ? caret_right : caret_down;
		this.txtSpinner = new XSpinnerText("spinner.text");
		this.btnUp = new XSpinnerButton(s1, "spinner.button.up");
		this.btnDown = new XSpinnerButton(s2, "spinner.button.down");
		this.spinnerPanel = new XSpinnerPanel("spinner.panel", spinner);
		this.spinnerPanel.add(this.btnUp);
		this.spinnerPanel.add(this.btnDown);
		this.spinnerPanel.add(this.txtSpinner);
		return this.spinnerPanel;
	}
	
	/**
	 * 
	 */
	public XSpinnerButton getBtnUp() {
		return btnUp;
	}

	public XSpinnerButton getBtnDown() {
		return btnDown;
	}
	
	public XSpinnerText getTxtSpinner() {
		return txtSpinner;
	}

	public XSpinnerPanel getSpinnerPanel() {
		return spinnerPanel;
	}
}
