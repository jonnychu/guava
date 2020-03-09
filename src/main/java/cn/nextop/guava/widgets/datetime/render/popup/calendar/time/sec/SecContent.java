package cn.nextop.guava.widgets.datetime.render.popup.calendar.time.sec;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.widgets.AbstractPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.sec.widget.SecWidet;

/**
 * @author jonny
 */
public class SecContent extends AbstractPanel {
	//
	private SecWidet[] items;
	private SecPanel secPanel;
	private final int itemHeight = SecPanel.itemHeight;
	
	/**
	 * 
	 */
	public SecPanel getSecPanel() { return secPanel; }

	/**
	 * 
	 */
	public SecContent(SecWidet[] items, SecPanel secPanel) {
		super("second.content"); this.secPanel = secPanel;
		this.items = items;	for (SecWidet item : items) { add(item); }
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		SecContent parent = (SecContent) container;
		final Rectangle r = parent.getBounds();
		//
		int p = 0, h = itemHeight; for (SecWidet item : this.items) {
			item.setBounds(new Rectangle(r.x, p++ * h, r.width, h));
		}
		parent.setBounds(new Rectangle(0, 0, r.width, p * h));
	}

}
