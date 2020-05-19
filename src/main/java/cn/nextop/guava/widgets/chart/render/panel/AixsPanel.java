package cn.nextop.guava.widgets.chart.render.panel;

import static cn.nextop.guava.support.Objects.cast;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.chart.builder.XChartFactory;
import cn.nextop.guava.widgets.chart.render.AbstractXChartPanel;

/**
 * @author jonny
 */
public class AixsPanel extends AbstractXChartPanel {
	//
	private Orientation orientation;
	private TickPanel tick;
	//
	public enum Orientation { HORIZONTAL, VERTICAL }
	
	public Orientation getOrientation() { return orientation; }

	/**
	 * 
	 */
	public AixsPanel(String name, XChartFactory factory, Orientation orientation) {
		super(name, factory); this.orientation = orientation;
		add(this.tick = new TickPanel("ticks", getFactory(), this));
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		AixsPanel parent = cast(container);
		this.tick.setBounds(parent.getBounds());
	}
}
