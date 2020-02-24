package cn.nextop.guava.widgets.datetime.render.popup.calendar.date.part;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;

public class TopPanel extends Figure {
	//
	private final int mid = 0;
	private final int left = 1;
	private final int right = 2;
	//
	private DatePanel datePanel;
	private Year yLeft, yRight, yMid;
	private Month mLeft, mRight, mMid;
	
	/**
	 * 
	 */
	public TopPanel(DatePanel datePanel) {
		this.datePanel = datePanel;
		add(yLeft = new Year(left));
		add(mLeft = new Month(left));
		add(mMid = new Month(left));
		add(yMid = new Year(left));
		add(mRight = new Month(left));
		add(yRight = new Year(left));
	}
	
	@Override
	protected void paintChildren(Graphics graphics) {
		super.paintChildren(graphics);
	}
	
	/**
	 * 
	 */
	protected class Layout {
		//
		public void layout(TopPanel parent) {
			Rectangle r = parent.getClientArea();
			//
			final int x = r.x, y = r.y, w = r.width, h = r.height;
			final int margin = 10, ww = 20, my = (h - ww) / 2;
			Rectangle r1 = new Rectangle(margin + x, my, ww, ww); yLeft.setBounds(r1);
			Rectangle r2 = new Rectangle(r1.x + r1.width , my, ww, ww); mLeft.setBounds(r1);
		}
	}
	
	protected class Year extends Figure {
		private int type;
		public Year(int type) { this.type = type; }
	}
	
	protected class Month extends Figure {
		private int type;
		public Month(int type) { this.type = type; }
	}
}
