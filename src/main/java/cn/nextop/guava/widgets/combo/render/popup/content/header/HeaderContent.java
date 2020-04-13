package cn.nextop.guava.widgets.combo.render.popup.content.header;

import static cn.nextop.guava.support.Objects.cast;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.widgets.combo.model.colum.Column;
import cn.nextop.guava.widgets.combo.render.AbstractColumnWidget;
import cn.nextop.guava.widgets.combo.render.AbstractComboPanel;
import cn.nextop.guava.widgets.combo.render.popup.PopupPanel;
import cn.nextop.guava.widgets.combo.render.popup.widget.DefaultColumnWidget;

/**
 * @author jonny
 */
public class HeaderContent extends AbstractComboPanel {
	//
	private PopupPanel panel;
	private AbstractColumnWidget[] widgets;
	
	//
	public PopupPanel getPanel() { return panel; }
	
	/**
	 * 
	 */
	public HeaderContent(String name, List<Column<?>> columns) {
		super(name);
		widgets = new DefaultColumnWidget[columns.size()];
		for (int i = 0; i < columns.size(); i++) {
			add(widgets[i] = ((Column<?>)columns.get(i)).getColumnwidget());
		}
	}

	@Override
	protected void layoutManager(IFigure container) {
		if(widgets == null || widgets.length == 0) return;
		//
		HeaderContent content = cast(container);
		final Rectangle r = content.getClientArea();
		int x = r.x, y = r.y, w = r.width, h = r.height;
		
		//
		int total = 0; for (int i = 0; i < widgets.length; i++) {
			total = total +  widgets[i].getColumn().getWeight();
		}
		
		int aw = w / total, cx = 0, rw = w;
		for (int i = 0; i < widgets.length; i++) {
			AbstractColumnWidget w1 = widgets[i];
			if(i == widgets.length - 1) {
				w1.setBounds(new Rectangle(x + cx, y, rw, h));
			} else {
				w1.setBounds(new Rectangle(x + cx, y, aw, h));
			}
			cx = cx + aw; rw = rw - aw;
		}
	}
}
