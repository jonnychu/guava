package cn.nextop.guava.widgets.datetime.render.text;

import static com.patrikdufresne.fontawesome.FontAwesome.calendar_minus_o;
import static com.patrikdufresne.fontawesome.FontAwesome.calendar_plus_o;
import static org.eclipse.draw2d.TextUtilities.INSTANCE;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import com.patrikdufresne.fontawesome.FontAwesome;

import cn.nextop.guava.utils.CGUtils;
import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.datetime.XDateTime;
import cn.nextop.guava.widgets.datetime.model.XDateTimeModel;

/**
 * @author jonny
 */
public class TextPanel extends Figure {
	//
	private boolean focus;
	private XDateTime dateTime;
	private XDateTimeModel timeModel;
	private final int margin = 8, arc = 5;
	
	/**
	 * 
	 */
	public TextPanel(XDateTime dateTime) {
		this.dateTime = dateTime;
		this.timeModel = this.dateTime.getModel();
		addMouseMotionListener(new MouseMotionListener.Stub());
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
		// text
		Rectangle rect = getBounds(); String text = this.timeModel.getTime();
		Dimension d1 = TextUtilities.INSTANCE.getStringExtents(text, g.getFont());
		g.drawString(text, rect.x + margin, rect.y + (rect.height - d1.height) / 2);
		// icon
		String icon = dateTime.getPopup() != null ? calendar_minus_o : calendar_plus_o; 
		g.setFont(FontAwesome.getFont(10)); Dimension d2 = INSTANCE.getStringExtents(icon, g.getFont());
		g.fillRectangle(rect.x + rect.width - d2.width - margin * 2, rect.y + (rect.height - d2.height) / 2,
				d2.width + margin * 2, d2.height);
		g.drawString(icon, rect.x + rect.width - d2.width - margin, rect.y + (rect.height - d2.height) / 2);
	}
	
	@Override public void handleMouseExited(MouseEvent event) { focus = false; repaint(); }
	@Override public void handleMouseEntered(MouseEvent event) { focus = true; repaint(); }
}
