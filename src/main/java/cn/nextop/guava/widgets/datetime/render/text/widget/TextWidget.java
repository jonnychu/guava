package cn.nextop.guava.widgets.datetime.render.text.widget;

import static org.eclipse.draw2d.TextUtilities.INSTANCE;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.widgets.datetime.XDateTime;
import cn.nextop.guava.widgets.datetime.model.XDateTimeModel;
import cn.nextop.guava.widgets.datetime.render.AbstractWidget;
import cn.nextop.guava.widgets.datetime.render.text.TextPanel;

/**
 * @author jonny
 */
public class TextWidget extends AbstractWidget {
	
	/**
	 * 
	 */
	public TextWidget() { super(false, false); }
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		Rectangle rect = getBounds();
		TextPanel textPanel = (TextPanel)getParent();
		XDateTime dateTime = textPanel.getDateTime();
		XDateTimeModel timeModel = dateTime.getModel();
		//
		this.text = timeModel.getTime();
		Dimension d1 = INSTANCE.getStringExtents(this.text, g.getFont());
		g.drawString(this.text, rect.x, rect.y + (rect.height - d1.height) / 2);
	}
}
