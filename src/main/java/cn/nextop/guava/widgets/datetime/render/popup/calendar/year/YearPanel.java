package cn.nextop.guava.widgets.datetime.render.popup.calendar.year;

import static com.patrikdufresne.fontawesome.FontAwesome.angle_double_left;
import static com.patrikdufresne.fontawesome.FontAwesome.angle_double_right;
import static java.lang.Integer.parseInt;
import static java.lang.Math.min;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.datetime.builder.XDateTimePopupBuilder;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.AbstractTimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.common.widget.LineWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.common.widget.TimeButtonWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.year.widget.YearItemWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.year.widget.YearWidget;
import cn.nextop.guava.widgets.datetime.support.glossary.Type;

/**
 * @author jonny
 */
public class YearPanel extends AbstractTimePanel {
	//
	private LineWidget line1, line2;
	private TimeButtonWidget btnTime;
	private YearItemWidget[][] yearItems;
	private YearWidget rollDownYear, rollUpYear, selectYear;
	
	/**
	 * 
	 */
	public YearWidget getYearWidget() { return selectYear; }
	public YearItemWidget[][] getYearsWidget() { return yearItems; }
	
	/**
	 * 
	 */
	public YearPanel(String name, XDateTimePopupBuilder builder) {
		super(name); this.builder = builder;
		this.yearItems = new YearItemWidget[4][3];
		DummyCalendar dc = builder.getDateTimePopup().getDummyCalendar();
		// add widgets
		add(btnTime = new TimeButtonWidget("Select Time"));
		add(line1 = new LineWidget()); add(line2 = new LineWidget());
		add(rollUpYear = new YearWidget(angle_double_right, Type.UP));
		add(rollDownYear = new YearWidget(angle_double_left, Type.DOWN));
		add(selectYear = new YearWidget(dc.getYearSymbol(), Type.SELECT));
		//
		final String[] years = dc.getYears();
		int index = 0; for (int i = 0; i < yearItems.length; i++) {
			for (int j = 0; j < yearItems[i].length; j++) {
				String v = years[index]; int year = parseInt(v);
				final boolean selected = dc.isSelectedYear(year);
				add(yearItems[i][j] = new YearItemWidget(year, v, selected)); index++;
			}
		}
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		g.setBackgroundColor(Colors.COLOR_WHITE);
		g.fillRectangle(getBounds());
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		YearPanel parent = (YearPanel)container;
		final Rectangle r = parent.getBounds();
		final int x = r.x, y = r.y, w = r.width, h = r.height;
		// h = 40
		final int th = 40, bh = 40, mh = h - th - bh, space = 1;
		{
			final int mgn = 2, w1 = 20, h1 = 20;
			final int y1 = y + (th - w1) / 2, w2 = (w - w1 * 2 - mgn * 2);
			Rectangle r1 = new Rectangle(mgn + x, y1, w1, h1); rollDownYear.setBounds(r1);
			Rectangle r2 = new Rectangle(r1.x + w1, y1, w2, h1); selectYear.setBounds(r2);
			Rectangle r3 = new Rectangle(r2.x + w2, y1, w1, h1); rollUpYear.setBounds(r3);
		}
		
		// h = ?
		final int sy1 = th + space;
		{
			final int w1 = min( mh / 4, w / 3), mgn1 = (w - w1 * 3) / 4, mgn2 = (mh - w1 * 4) / 5 ;
			for (int i = 0; i < yearItems.length; i++) {
				for (int j = 0; j < yearItems[i].length; j++) {
					yearItems[i][j].setBounds(
							new Rectangle(mgn1 * (j + 1) + x + j * w1, mgn2 * (i + 1) + sy1 + w1 * i, w1, w1));
				}
			}
		}
		
		// h = 40
		final int sy2 = th + mh;
		{
			int w1 = w , h1 = bh - 4;
			Rectangle r1 = new Rectangle(x, sy2 + (bh - h1) / 2, w1, h1); btnTime.setBounds(r1);
		}
		
		// line
		{
			line1.setBounds(new Rectangle(x, th, w, space));
			line2.setBounds(new Rectangle(x, h - bh, w, space));
		}
	}
}
