package cn.nextop.guava.widgets.datetime.render.popup.shortcut.widget;

import static org.eclipse.draw2d.TextUtilities.INSTANCE;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.datetime.glossary.Shortcut;
import cn.nextop.guava.widgets.datetime.render.AbstractWidget;
import cn.nextop.guava.widgets.datetime.render.popup.shortcut.Content;
import cn.nextop.guava.widgets.datetime.render.popup.shortcut.ShortcutScrollPanel;
import cn.nextop.guava.widgets.datetime.render.popup.shortcut.action.ShortcutAction;
import cn.nextop.guava.widgets.datetime.render.text.acton.ShowTextAction;

/**
 * @author jonny
 */
public class ShortcutItem extends AbstractWidget {
	//
	private Shortcut shortcut;
	
	/**
	 * 
	 */
	public ShortcutItem(Shortcut shortcut) {
		this.shortcut = shortcut;
		this.text = shortcut.name();
	}
	
	public Shortcut getShortcut() { return shortcut; }

	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		Rectangle rect = getBounds();
		if(enter) g.setBackgroundColor(Colors.COLOR_CYAN);
		g.fillRectangle(rect);
		
		//
		Dimension d1 = INSTANCE.getStringExtents(text, g.getFont());
		g.drawString(text, rect.x + 10, rect.y + (rect.height - d1.height) / 2);
	}
	
	@Override
	public void handleMouseReleased(MouseEvent event) {
		super.handleMouseReleased(event); 
		Content content = (Content) getParent();
		ShortcutScrollPanel ssp = content.getShortcutScrollPanel();
		new ShortcutAction().onAction(content, this);
		new ShowTextAction().onAction(ssp.getDateTime().getTextPanel(), null);
	}
}