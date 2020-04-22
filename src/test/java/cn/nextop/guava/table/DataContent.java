package cn.nextop.guava.table;

import static cn.nextop.guava.support.Objects.cast;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.support.swt.CGUtils;
import cn.nextop.guava.support.swt.Colors;
import cn.nextop.guava.widgets.AbstractPanel;

/**
 * @author jonny
 */
public class DataContent extends AbstractPanel {
	
	/**
	 * 
	 */
	public DataContent(String name) {
		super(name);
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		Rectangle r = getBounds();
		CGUtils.fillRect(g, bounds, Colors.COLOR_WHITE);
		g.drawText("111", 0, 0);
		g.drawText("222", 0, 300);
		g.drawText("333", 0, r.height - 30);
	}
	
	@Override
	public Dimension getPreferredSize(int wHint, int hHint) {
		return new Dimension(wHint, 1000);
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		DataContent content = cast(container);
	}
}
