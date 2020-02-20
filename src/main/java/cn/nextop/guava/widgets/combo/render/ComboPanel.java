package cn.nextop.guava.widgets.combo.render;

import static cn.nextop.guava.utils.SwtUtils.async;
import static com.patrikdufresne.fontawesome.FontAwesome.caret_down;
import static com.patrikdufresne.fontawesome.FontAwesome.caret_up;
import static org.eclipse.draw2d.TextUtilities.INSTANCE;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import com.patrikdufresne.fontawesome.FontAwesome;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.utils.SwtUtils;

/**
 * 
 */
public class ComboPanel extends Figure {
	//
	private Model model;
	private Text txtWord;
	private Button btnSelection;
	//
	@Override protected boolean useLocalCoordinates() { return true; }
	
	/**
	 * 
	 */
	public ComboPanel() {
		this.model = new Model();
		add(txtWord = new Text());
		add(btnSelection = new Button());
		addMouseListener(new MouseListener.Stub());
		addMouseMotionListener(new MouseMotionListener.Stub());
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		Rectangle rect = getClientArea();
		g.setBackgroundColor(Colors.COLOR_WHITE);
		g.fillRoundRectangle(new Rectangle(0, 0, rect.width - 1, rect.height - 1), model.arc, model.arc);
		
	}
	
	@Override
	protected void paintBorder(Graphics g) {
		super.paintBorder(g);
		Rectangle rect = getBounds();
		if(model.enter || model.popup) {
			g.setForegroundColor(Colors.COLOR_BLUE);
		} else g.setForegroundColor(Colors.COLOR_DARK_GRAY);
		g.drawRoundRectangle(new Rectangle(0, 0, rect.width - 1, rect.height - 1), model.arc, model.arc);
	}
	
	@Override
	protected void paintChildren(Graphics g) {
		super.paintChildren(g);
		Rectangle rect = getBounds();
		txtWord.setBounds(new Rectangle(0, 0, rect.width - model.wh, rect.height));
		btnSelection.setBounds(new Rectangle(rect.width - model.wh, 0, model.wh, rect.height));
	}
	
	public void setText(String text) {
		async(SwtUtils.getDisplay(), () -> { txtWord.text = text; repaint();});
	}
	
	public void deactive() {
		async(SwtUtils.getDisplay(), () -> { model.enter = false; model.popup = false; repaint();});
	}
	
	/**
	 * 
	 */
	@Override public void handleMouseExited(MouseEvent event) { super.handleMouseExited(event); model.enter = false; repaint(); }
	@Override public void handleMouseEntered(MouseEvent event) { super.handleMouseEntered(event); model.enter = true; repaint(); }
	@Override public void handleMouseReleased(MouseEvent event) { super.handleMouseReleased(event); model.popup = !model.popup; repaint(); }
	
	/**
	 * 
	 */
	protected class Model {
		protected final int arc = 5;
		protected final int wh = 26;
		protected boolean enter = false;
		protected boolean popup = false;
	}
	
	/**
	 * 
	 */
	protected class Text extends Figure {
		//
		private String text = "";
		
		@Override protected void paintFigure(Graphics g) {
			super.paintFigure(g);
			Rectangle rect = getBounds();
			if(text != null || "".equals(text)) {
				Dimension d = INSTANCE.getStringExtents(text, g.getFont());
				g.drawString(text, new Point(rect.x + 8, (rect.height - d.height) / 2));
			}
		}
	}
	
	/**
	 * 
	 */
	protected class Button extends Figure {
		
		@Override protected void paintFigure(Graphics g) {
			super.paintFigure(g);
			String icon = model.popup ? caret_up : caret_down;
			Rectangle rect = getBounds(); g.setFont(FontAwesome.getFont());
			final Dimension d = INSTANCE.getStringExtents(icon, g.getFont());
			g.drawString(icon, new Point(rect.x + (rect.width - d.width) / 2, rect.y + (rect.height - d.height) / 2));
		}
	}
}
