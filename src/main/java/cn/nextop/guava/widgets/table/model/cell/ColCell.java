package cn.nextop.guava.widgets.table.model.cell;

import static cn.nextop.guava.support.util.Objects.cast;

import cn.nextop.guava.widgets.table.render.AbstractXTableColumnWidget;
import cn.nextop.guava.widgets.table.render.widget.DefaultColumnWidget;

/**
 * @author jonny
 */
public class ColCell {
	
	private Class<? extends AbstractXTableColumnWidget> columnwidget;
	
	public ColCell() {
		this.columnwidget = cast(DefaultColumnWidget.class);
	}

	public Class<? extends AbstractXTableColumnWidget> getColumnwidget() {
		return columnwidget;
	}

	public void setColumnwidget(Class<? extends AbstractXTableColumnWidget> columnwidget) {
		this.columnwidget = columnwidget;
	}
}
