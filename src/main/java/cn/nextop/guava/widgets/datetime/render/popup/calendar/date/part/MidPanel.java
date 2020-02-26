package cn.nextop.guava.widgets.datetime.render.popup.calendar.date.part;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.datetime.render.AbstractPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;
import cn.nextop.guava.widgets.datetime.render.utils.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.utils.DummyModel;

public class MidPanel extends AbstractPanel {
	//
	private WeekItem[] weeks;
	private DateItem[][] dates;
	private DatePanel datePanel;
	
	/**
	 * 
	 */
	public DatePanel getDatePanel() { return datePanel; }

	/**
	 * 
	 */
	public MidPanel(DatePanel datePanel) {
		this.datePanel = datePanel;
		this.weeks = new WeekItem[7];
		this.dates = new DateItem[6][7];
		final DummyCalendar dummy = datePanel.getDummyCalendar();
		
		// week symbols
		String[] symbols = dummy.getWeekSymbols();
		for (int i = 0; i < symbols.length; i++) {
			add(weeks[i] = new WeekItem(symbols[i]));
		}
		
		// day of month
		DummyModel[][] models = dummy.getCalendar();
		for (int i = 0; i < models.length; i++) {
			for (int j = 0; j < models[i].length; j++) {
				DummyModel m = models[i][j];
				add(dates[i][j] = new DateItem(m.getDay(), m.isEditable()));
			}
		}
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		// 7 * 7
		MidPanel parent = (MidPanel)container;
		final Rectangle r = parent.getBounds();
		final int x = r.x, y = r.y, w = r.width, h = r.height;
		int aw = w / 7, ah = h / 7;
		int amin = aw < ah ? aw : ah, amax = aw > ah ? aw : ah;
		// title
		for (int i = 0; i < weeks.length; i++) {
			weeks[i].setBounds(new Rectangle(x + i * amax, y, amin, amin));
			
		}
		// 
		int ty = y + amax;
		for (int i = 0; i < dates.length; i++) {
			for (int j = 0; j < dates[i].length; j++) {
				dates[i][j].setBounds(new Rectangle(x + j * amax, ty + amin * i, amin, amin));
			}
		}
	}
	
	/**
	 * 
	 */
	protected class WeekItem extends Figure {
		protected String text;
		public WeekItem(String text) {
			this.text = text;
		}
		
		@Override
		protected void paintFigure(Graphics g) {
			super.paintFigure(g);
			Rectangle r = getBounds();
			g.setForegroundColor(Colors.COLOR_DARK_GRAY);
			Dimension d1 = TextUtilities.INSTANCE.getTextExtents(text, g.getFont());
			g.drawString(text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
		}
	}
	
	protected class DateItem extends Figure {
		protected int day;
		protected String text;
		protected boolean editable;
		
		public DateItem(int day, boolean editable) {
			this.text = String.valueOf(day);
			this.day = day; this.editable = editable;
		}
		
		@Override
		protected void paintFigure(Graphics g) {
			super.paintFigure(g);
			Rectangle r = getBounds();
			if(!editable) g.setForegroundColor(Colors.COLOR_DARK_GRAY);
			Dimension d1 = TextUtilities.INSTANCE.getTextExtents(text, g.getFont());
			g.drawString(text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
		}
	}
}
