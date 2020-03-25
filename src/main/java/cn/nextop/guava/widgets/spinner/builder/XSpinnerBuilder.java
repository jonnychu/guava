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
import cn.nextop.guava.widgets.spinner.render.widget.XSpinnerButtonWidget;
import cn.nextop.guava.widgets.spinner.render.widget.XSpinnerTextWidget;

/**
 * @author jonny
 */
public class XSpinnerBuilder extends AbstractBuilder {
	//
	private XSpinnerPanel spinnerPanel;
	private XSpinnerTextWidget txtSpinner;
	private XSpinnerButtonWidget btnUp, btnDown;
	
	@Override
	public IFigure build(Canvas parent) {
		final XSpinner spinner = cast(parent);
		final boolean isHorz = spinner.isHorz();
		String s1 = isHorz ? caret_right : caret_up;
		String s2 = isHorz ? caret_left : caret_down;
		this.btnUp = new XSpinnerButtonWidget(s1, "button.up");
		this.btnDown = new XSpinnerButtonWidget(s2, "button.down");
		this.txtSpinner = new XSpinnerTextWidget(spinner,  "text");
		this.spinnerPanel = new XSpinnerPanel("spinner.panel", spinner);
		this.spinnerPanel.add(this.btnUp);
		this.spinnerPanel.add(this.btnDown);
		this.spinnerPanel.add(this.txtSpinner);
		
		// listener
		this.btnUp.addActionListener(spinner);
		this.btnDown.addActionListener(spinner);
		return this.spinnerPanel;
	}
	
	/**
	 * 
	 */
	public XSpinnerButtonWidget getBtnUp() {
		return btnUp;
	}

	public XSpinnerButtonWidget getBtnDown() {
		return btnDown;
	}
	
	public XSpinnerTextWidget getTxtSpinner() {
		return txtSpinner;
	}

	public XSpinnerPanel getSpinnerPanel() {
		return spinnerPanel;
	}
}
