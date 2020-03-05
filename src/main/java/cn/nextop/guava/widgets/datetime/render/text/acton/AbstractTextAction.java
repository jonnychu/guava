package cn.nextop.guava.widgets.datetime.render.text.acton;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.XDateTime;
import cn.nextop.guava.widgets.datetime.model.XDateTimeModel;
import cn.nextop.guava.widgets.datetime.render.AbstractAction;
import cn.nextop.guava.widgets.datetime.render.text.TextPanel;

/**
 * @author jonny
 */
public abstract class AbstractTextAction extends AbstractAction {
	
	@Override
	protected void updateUI(IFigure container, IFigure widget) {
		TextPanel textPanel = (TextPanel)container;
		XDateTime dateTime = textPanel.getDateTime();
		XDateTimeModel dateTimeModel = dateTime.getModel();
		
		//
		textPanel.getText().setText(dateTimeModel.getTime());
		
		//
		textPanel.repaint();
	}
}
