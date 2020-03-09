package cn.nextop.guava.widgets.datetime.render.text.widget;

import static com.patrikdufresne.fontawesome.FontAwesome.calendar_minus_o;
import static com.patrikdufresne.fontawesome.FontAwesome.calendar_plus_o;
import static org.eclipse.draw2d.TextUtilities.INSTANCE;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import com.patrikdufresne.fontawesome.FontAwesome;

import cn.nextop.guava.widgets.datetime.AbstractWidget;
import cn.nextop.guava.widgets.datetime.XDateTime;
import cn.nextop.guava.widgets.datetime.render.text.TextPanel;

/**
 * @author jonny
 */
public class IconWidget extends AbstractWidget {
	
	/**
	 * 
	 */
	public IconWidget() { super(false, false); }
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		Rectangle rect = getBounds();
		TextPanel textPanel = (TextPanel)getParent();
		XDateTime dateTime = textPanel.getDateTime();
		this.text = dateTime.getPopup() != null ? calendar_minus_o : calendar_plus_o; 
		g.setFont(FontAwesome.getFont(10)); Dimension d2 = INSTANCE.getStringExtents(text, g.getFont());
		g.fillRectangle(rect.x + rect.width - d2.width - margin * 2, rect.y + (rect.height - d2.height) / 2,
				d2.width + margin * 2, d2.height);
		g.drawString(text, rect.x + rect.width - d2.width - margin, rect.y + (rect.height - d2.height) / 2);
	}
}
