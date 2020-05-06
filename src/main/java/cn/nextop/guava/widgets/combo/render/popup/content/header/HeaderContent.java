package cn.nextop.guava.widgets.combo.render.popup.content.header;

import static cn.nextop.guava.support.util.Objects.cast;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.widgets.combo.model.colum.Column;
import cn.nextop.guava.widgets.combo.render.AbstractColumnWidget;
import cn.nextop.guava.widgets.combo.render.AbstractComboPanel;
import cn.nextop.guava.widgets.combo.render.popup.PopupPanel;

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
		widgets = new AbstractColumnWidget[columns.size()];
		for (int i = 0; i < columns.size(); i++) {
			try {
				Column<?> column = columns.get(i);
				Class<?> clazz = column.getColumnwidget();
				this.widgets[i] = cast(clazz.newInstance());
				this.widgets[i].setColumn(column); add(this.widgets[i]);
			} catch (InstantiationException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
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
			int wt = w1.getColumn().getWeight();
			if(i == widgets.length - 1) {
				w1.setBounds(new Rectangle(x + cx, y, rw, h));
			} else {
				w1.setBounds(new Rectangle(x + cx, y, aw * wt, h));
			}
			cx = cx + aw * wt; rw = rw - aw * wt;
		}
	}
}
