package cn.nextop.guava.widgets.datetime.render.popup.calendar;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.builder.XDateTimePopupBuilder;
import cn.nextop.guava.widgets.datetime.render.AbstractTimePanel;
import cn.nextop.guava.widgets.datetime.support.glossary.PanelType;

/**
 * @author jonny
 */
public class CalendarPanel extends AbstractTimePanel {
	
	/**
	 * 
	 */
	public CalendarPanel(String name, XDateTimePopupBuilder builder) {
		super(name); this.builder = builder;
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		builder.getTimePanel().setBounds(container.getBounds());
		builder.getDatePanel().setBounds(container.getBounds());
		builder.getYearPanel().setBounds(container.getBounds());
		builder.getMonthPanel().setBounds(container.getBounds());
	}
	
	/**
	 * 
	 */
	public void panel(PanelType type) {
		builder.getTimePanel().setVisible(false);
		builder.getDatePanel().setVisible(false);
		builder.getYearPanel().setVisible(false);
		builder.getMonthPanel().setVisible(false);
		switch (type) {
		case TIME:  builder.getTimePanel().setVisible(true); break;
		case DATE:  builder.getDatePanel().setVisible(true); break;
		case YEAR:  builder.getYearPanel().setVisible(true); break;
		case MONTH: builder.getMonthPanel().setVisible(true); break;
		default: throw new RuntimeException("No Panel Type!");}
	}
}
