package cn.nextop.guava.widgets.combo.render.text;

import static cn.nextop.guava.support.Objects.cast;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.support.swt.Colors;
import cn.nextop.guava.widgets.combo.XCombo;
import cn.nextop.guava.widgets.combo.render.AbstractComboPanel;
import cn.nextop.guava.widgets.combo.render.text.widget.TextWidget;

/**
 * @author jonny
 */
public class TextPanel extends AbstractComboPanel {
	//
	private XCombo combo;
	private TextWidget text;
	
	//
	public TextWidget getTextWidget() { return text; }
	public XCombo getXCombo() { return combo; }
	
	/**
	 * 
	 */
	public TextPanel(XCombo combo) {
		super("text.panel"); 
		this.combo = combo;
		add(text = new TextWidget());
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		g.setBackgroundColor(Colors.COLOR_WHITE);
		g.fillRoundRectangle(getBounds(), arc, arc);
	}
	
	@Override protected void layoutManager(IFigure container) {
		TextPanel parent = cast(container);
		text.setBounds(parent.getBounds());
	}
}
