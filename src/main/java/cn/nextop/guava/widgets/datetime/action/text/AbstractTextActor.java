package cn.nextop.guava.widgets.datetime.action.text;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.AbstractActor;
import cn.nextop.guava.widgets.datetime.XDateTime;
import cn.nextop.guava.widgets.datetime.model.XDateTimeModel;
import cn.nextop.guava.widgets.datetime.render.text.TextPanel;

/**
 * @author jonny
 */
public abstract class AbstractTextActor extends AbstractActor {
	
	@Override
	protected void updateUI(IFigure container, IFigure widget) {
		TextPanel textPanel = (TextPanel)container;
		XDateTime dateTime = textPanel.getDateTime();
		XDateTimeModel dateTimeModel = dateTime.getModel();
		//
		final String time = dateTimeModel.getTimeByPattern();
		textPanel.getTextWidget().setText(time); textPanel.repaint();
	}
}
