package cn.nextop.guava.widgets.datetime.render.popup.calendar.date.part;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.utils.Fonts;
import cn.nextop.guava.widgets.datetime.render.AbstractPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;

/**
 * @author jonny
 */
public class BottomPanel extends AbstractPanel {
	//
	private DatePanel datePanel;
	private OkButton btnOk;
	private TimeButton btnTime;
	
	/**
	 * 
	 */
	public DatePanel getDatePanel() { return datePanel; }

	/**
	 * 
	 */
	public BottomPanel(DatePanel datePanel) {
		this.datePanel = datePanel;
		add(btnOk = new OkButton("Select Time"));
		add(btnTime = new TimeButton("OK"));
	}
	
	@Override
	protected void paintBorder(Graphics g) {
		super.paintBorder(g);
		final Rectangle r = getBounds();
		g.setForegroundColor(Colors.COLOR_DARK_GRAY);
		g.drawLine(r.x, r.y + 2, r.x + r.width , r.y + 2);
	}

	@Override
	protected void layoutManager(IFigure container) {
		BottomPanel parent = (BottomPanel)container;
		final Rectangle r = parent.getBounds();
		final int x = r.x, y = r.y, w = r.width, h = r.height;
		//
		int w1 = w / 2 , h1 = h - 4;
		Rectangle r1 = new Rectangle(x, y + (h - h1) / 2, w1, h1); btnOk.setBounds(r1);
		Rectangle r2 = new Rectangle(x + r1.width, y + (h - h1) / 2, w1, h1); btnTime.setBounds(r2);
	}
	
	/**
	 * 
	 */
	protected class OkButton extends Figure {
		private String text;
		public OkButton(String text) {
			this.text = text;
		}
		
		@Override
		protected void paintFigure(Graphics g) {
			super.paintFigure(g);
			Rectangle r = getBounds(); g.setFont(Fonts.size(g.getFont(), 2));
			Dimension d1 = TextUtilities.INSTANCE.getTextExtents(text, g.getFont());
			g.drawString(text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
		}
	}
	
	protected class TimeButton extends Figure {
		private String text;
		public TimeButton(String text) {
			this.text = text;
		}
		
		@Override
		protected void paintFigure(Graphics g) {
			super.paintFigure(g);
			Rectangle r = getBounds(); g.setFont(Fonts.size(g.getFont(), 2));
			Dimension d1 = TextUtilities.INSTANCE.getTextExtents(text, g.getFont());
			g.drawString(text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
		}
	}
}
