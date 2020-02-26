package cn.nextop.guava.widgets.datetime.render.popup.calendar.date.part;

import static java.lang.String.valueOf;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import com.patrikdufresne.fontawesome.FontAwesome;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.utils.Fonts;
import cn.nextop.guava.widgets.datetime.render.AbstractPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;
import cn.nextop.guava.widgets.datetime.render.utils.DummyCalendar;

/**
 * @author jonny
 */
public class TopPanel extends AbstractPanel {
	//
	private final int mid = 0;
	private final int left = 1;
	private final int right = 2;
	//
	private DatePanel datePanel;
	private DummyCalendar dummyCalendar;
	private Year yLeft, yRight, yMid;
	private Month mLeft, mRight, mMid;
	
	/**
	 * 
	 */
	public DatePanel getDatePanel() { return datePanel; }

	/**
	 * 
	 */
	public TopPanel(DatePanel datePanel) {
		this.datePanel = datePanel;
		this.dummyCalendar = datePanel.getDummyCalendar();
		add(yLeft = new Year(FontAwesome.angle_double_left, left));
		add(mLeft = new Month(FontAwesome.angle_left, left));
		add(mMid = new Month(dummyCalendar.getMonthSymbol(), mid));
		add(yMid = new Year(valueOf(dummyCalendar.getYear()), mid));
		add(mRight = new Month(FontAwesome.angle_right, right));
		add(yRight = new Year(FontAwesome.angle_double_right, right));
	}
	
	@Override
	protected void paintBorder(Graphics g) {
		super.paintBorder(g);
		final Rectangle r = getBounds();
		g.setForegroundColor(Colors.COLOR_DARK_GRAY);
		g.drawLine(r.x, r.height - 1, r.x + r.width , r.height - 1);
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		TopPanel parent = (TopPanel)container;
		final Rectangle r = parent.getBounds();
		//
		final int x = r.x, w = r.width, h = r.height;
		final int margin = 2, w1 = 20, h1 = 20, cy = (h - w1) / 2;
		Rectangle r1 = new Rectangle(margin + x, cy, w1, h1); yLeft.setBounds(r1);
		Rectangle r2 = new Rectangle(r1.x + w1 , cy, w1, h1); mLeft.setBounds(r2);
		final int mmw = (w - w1 * 4 - margin * 2) / 2;
		Rectangle r3 = new Rectangle(r2.x + w1 , cy, mmw, h1); mMid.setBounds(r3);
		Rectangle r4 = new Rectangle(r3.x + mmw, cy, mmw, h1); yMid.setBounds(r4);
		Rectangle r5 = new Rectangle(r4.x + mmw, cy, w1, h1); mRight.setBounds(r5);
		Rectangle r6 = new Rectangle(r5.x + w1 , cy, w1, h1); yRight.setBounds(r6);
	}
	
	protected class Year extends Figure {
		private int type;
		private String text;
		public Year(String text, int type) { this.type = type; this.text = text; }
		
		@Override
		protected void paintFigure(Graphics g) {
			super.paintFigure(g);
			Rectangle r = getBounds();
			if(this.type == left) {
				g.setForegroundColor(Colors.COLOR_DARK_GRAY);
				g.setFont(FontAwesome.getFont(11));
				Dimension d1 = TextUtilities.INSTANCE.getStringExtents(text, g.getFont());
				g.drawString(text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
			} else if(this.type == right) {
				g.setForegroundColor(Colors.COLOR_DARK_GRAY);
				g.setFont(FontAwesome.getFont(11));
				Dimension d1 = TextUtilities.INSTANCE.getStringExtents(text, g.getFont());
				g.drawString(text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
			} else if(this.type == mid) {
				g.setFont(Fonts.size(g.getFont(), 3));
				Dimension d1 = TextUtilities.INSTANCE.getTextExtents(text, g.getFont());
				g.drawString(text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
			}
		}
	}
	
	protected class Month extends Figure {
		private int type;
		private String text;
		public Month(String text, int type) { this.type = type; this.text = text; }
		
		@Override
		protected void paintFigure(Graphics g) {
			super.paintFigure(g);
			Rectangle r = getBounds();
			if(this.type == left) {
				g.setForegroundColor(Colors.COLOR_DARK_GRAY);
				g.setFont(FontAwesome.getFont(11));
				Dimension d1 = TextUtilities.INSTANCE.getStringExtents(text, g.getFont());
				g.drawString(text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
			} else if(this.type == right) {
				g.setForegroundColor(Colors.COLOR_DARK_GRAY);
				g.setFont(FontAwesome.getFont(11));
				Dimension d1 = TextUtilities.INSTANCE.getStringExtents(text, g.getFont());
				g.drawString(text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
			} else if(this.type == mid) {
				g.setFont(Fonts.size(g.getFont(), 3));
				Dimension d1 = TextUtilities.INSTANCE.getTextExtents(text, g.getFont());
				g.drawString(text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
			}
		}
	}
}
