package cn.nextop.guava.widgets.datetime.render.popup.calendar.date.mid;

import static java.lang.Math.min;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.widgets.datetime.render.AbstractPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.mid.widget.DateItem;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.mid.widget.WeekItem;
import cn.nextop.guava.widgets.datetime.render.utils.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.utils.DummyModel;

/**
 * @author jonny
 */
public class MidPanel extends AbstractPanel {
	//
	private WeekItem[] weeks;
	private DateItem[][] dates;
	private DatePanel datePanel;
	
	/**
	 * 
	 */
	public DateItem[][] getDates() { return dates; }
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
		final int w1 = min( h / 7, w / 7), margin1 = (w - w1 * 7) / 8;
		
		// title
		for (int i = 0; i < weeks.length; i++) {
			weeks[i].setBounds(new Rectangle(margin1 * (i + 1) + x + i * w1, margin1 + y, w1, w1));
			
		}
		// 
		int ty = y + w1 + margin1;
		for (int i = 0; i < dates.length; i++) {
			for (int j = 0; j < dates[i].length; j++) {
				dates[i][j].setBounds(new Rectangle(margin1 * (j + 1) + x + j * w1, margin1 * (i + 1) + ty + w1 * i, w1, w1));
			}
		}
	}
}
