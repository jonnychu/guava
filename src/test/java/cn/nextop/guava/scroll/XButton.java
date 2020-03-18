package cn.nextop.guava.scroll;

import static cn.nextop.guava.utils.SwtUtils.sync;
import static org.eclipse.draw2d.FigureUtilities.getTextExtents;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import com.patrikdufresne.fontawesome.FontAwesome;

import cn.nextop.guava.utils.Colors;

/**
 * FontAwesome.caret_up
 * FontAwesome.caret_down
 * @author jonny
 */
public class XButton extends Figure {
	//
	private String text, name;
	private XButtonModel model;
	private boolean clicked = false, enter = false;

	/**
	 */
	public XButton(String text, String name) {
		this.text = text; this.name = name;
		this.model = new XButtonModel();
		addMouseListener(new MouseListener.Stub() {
			@Override
			public void mousePressed(MouseEvent me) {
				super.mousePressed(me); model.pressed();
			}
			
			@Override
			public void mouseReleased(MouseEvent me) {
				super.mouseReleased(me); model.released();
			}
		});
		addMouseMotionListener(new MouseMotionListener.Stub());
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g); Rectangle r = getClientArea();
		g.setFont(FontAwesome.getFont(11));
		Dimension d = getTextExtents(text, g.getFont());
		if(clicked) {
			g.setForegroundColor(Colors.COLOR_WHITE);
			g.setBackgroundColor(Colors.COLOR_WIDGET_PRESS);
		} else {
			g.setForegroundColor(Colors.COLOR_BLACK);
			if(enter) {
				g.setBackgroundColor(Colors.COLOR_WIDGET_ENTER);
			}
		}
		g.fillRectangle(r);
		g.drawString(text, r.x + (r.width - d.width) / 2, r.y + (r.height - d.height) / 2);
	}
	
	public void addActionListener(ActionListener listener) {
		addListener(ActionListener.class, listener);
	}
	
	@SuppressWarnings({ "rawtypes" })
	public void fireActionPerformed(){
		ActionEvent action = new ActionEvent(this, name);
		Iterator iter = getListeners(ActionListener.class);
		while (iter.hasNext())
			((ActionListener) iter.next()).actionPerformed(action);
	}
	
	@Override
	public void handleMouseEntered(MouseEvent event) {
		super.handleMouseEntered(event); this.enter = true; repaint();
	}
	
	@Override
	public void handleMouseExited(MouseEvent event) {
		super.handleMouseExited(event); this.enter = false; this.clicked = false; repaint();
	}
	
	@Override
	public void handleMousePressed(MouseEvent event) {
		super.handleMousePressed(event); this.clicked = true; repaint();
	}
	
	@Override
	public void handleMouseReleased(MouseEvent event) {
		super.handleMouseReleased(event); this.clicked = false; repaint();
	}
	
	/**
	 * 
	 */
	protected class XButtonModel {
		//
		private Timer timer;
		private final int INITIAL = 250, STEP = 40;

		public void released() { suspend(); }
		
		public void pressed() {
			fireActionPerformed();
			if (!isEnabled()) return; timer = new Timer();
			timer.scheduleAtFixedRate(new Task(timer), INITIAL, STEP);
		}

		public void suspend() {
			if (timer == null) return; timer.cancel(); timer = null;
		}

		class Task extends TimerTask {
			private Timer timer;
			public Task(Timer timer) { this.timer = timer; }
			public void run() {
				sync(null, () -> { if (!isEnabled()) timer.cancel(); fireActionPerformed(); });
			}
		}
	}
}
