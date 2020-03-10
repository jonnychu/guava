package cn.nextop.guava.widgets.datetime.render.text;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.CGUtils;
import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.datetime.XDateTime;
import cn.nextop.guava.widgets.datetime.render.AbstractTimePanel;
import cn.nextop.guava.widgets.datetime.render.text.widget.IconWidget;
import cn.nextop.guava.widgets.datetime.render.text.widget.TextWidget;

/**
 * @author jonny
 */
public class TextPanel extends AbstractTimePanel {
	//
	private boolean focus;
	private IconWidget icon;
	private TextWidget text;
	private XDateTime dateTime;
	
	//
	public TextWidget getTextWidget() { return text; }
	public XDateTime getDateTime() { return dateTime; }
	
	/**
	 * 
	 */
	public TextPanel(XDateTime dateTime) {
		super("text.panel"); this.dateTime = dateTime;
		//
		addMouseMotionListener(new MouseMotionListener.Stub());
		add(text = new TextWidget()); add(icon = new IconWidget());
	}
	
	@Override
	protected void paintBorder(Graphics g) {
		super.paintBorder(g);
		if(focus || dateTime.getPopup() != null)
			g.setForegroundColor(Colors.COLOR_BLUE);
		else
			g.setForegroundColor(Colors.COLOR_DARK_GRAY);
		g.drawRoundRectangle(CGUtils.getBorderRect(getBounds()), arc, arc);
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		g.setBackgroundColor(Colors.COLOR_WHITE);
		g.fillRoundRectangle(getBounds(), arc, arc);
	}
	
	@Override protected void layoutManager(IFigure container) {
		TextPanel parent = (TextPanel) container;
		final Rectangle r = parent.getBounds();
		final int x = r.x, y = r.y, w = r.width, h = r.height;
		Rectangle r1 = new Rectangle(x + margin, y, w - 22, h); text.setBounds(r1);
		Rectangle r2 = new Rectangle(r1.x + r1.width - margin - 2, r1.y, 22, h); icon.setBounds(r2);
	}
	
	@Override public void handleMouseExited(MouseEvent event) { focus = false; repaint(); }
	@Override public void handleMouseEntered(MouseEvent event) { focus = true; repaint(); }
}
