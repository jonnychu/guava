package cn.nextop.guava.widgets.datetime.render.popup.calendar.month.mid;

import static java.lang.Math.min;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.datetime.render.AbstractPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.MonthPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.mid.widget.MonthItem;
import cn.nextop.guava.widgets.datetime.utils.DummyCalendar;

/**
 * @author jonny
 */
public class MidPanel extends AbstractPanel {
	//
	private MonthItem[][] dates;
	private MonthPanel monthPanel;
	
	/**
	 * 
	 */
	public MonthItem[][] getDates() { return dates; }
	public MonthPanel getMonthPanel() { return monthPanel; }

	/**
	 * 
	 */
	public MidPanel(MonthPanel monthPanel) {
		this.monthPanel = monthPanel;
		this.dates = new MonthItem[4][3];
		final DummyCalendar dummy = monthPanel.getDummyCalendar();
		
		// month symbols
		int month = 0; for (int i = 0; i < dates.length; i++) {
			for (int j = 0; j < dates[i].length; j++) {
				String name = dummy.getMonthSymbol(month);
				add(dates[i][j] = new MonthItem(month, name)); month++;
			}
		}
	}
	
	@Override
	protected void paintBorder(Graphics g) {
		super.paintBorder(g);
		final Rectangle r = getBounds();
		g.setForegroundColor(Colors.COLOR_DARK_GRAY);
		g.drawLine(r.x, r.y, r.x + r.width , r.y);
		g.drawLine(r.x, r.y + r.height - 1, r.x + r.width , r.y + r.height - 1);
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		// 4 * 3
		MidPanel parent = (MidPanel)container;
		final Rectangle r = parent.getBounds();
		final int x = r.x, y = r.y, w = r.width, h = r.height;
		final int w1 = min( h / 4, w / 3), margin1 = (w - w1 * 3) / 4, margin2 = (h - w1 * 4) / 5 ;
		for (int i = 0; i < dates.length; i++) {
			for (int j = 0; j < dates[i].length; j++) {
				dates[i][j].setBounds(new Rectangle(margin1 * (j + 1) + x + j * w1, margin2 * (i + 1) + y + w1 * i, w1, w1));
			}
		}
	}
}
