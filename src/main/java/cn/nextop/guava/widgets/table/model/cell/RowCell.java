package cn.nextop.guava.widgets.table.model.cell;

import static cn.nextop.guava.support.util.Objects.cast;

import cn.nextop.guava.widgets.table.render.AbstractXTableCellWidget;
import cn.nextop.guava.widgets.table.render.widget.DefaultCellWidget;
import cn.nextop.guava.widgets.table.render.widget.external.XTableWidget;

/**
 * @author jonny
 */
public class RowCell {
	//
	private XTableWidget[] cellRenderWidgets;
	private Class<? extends AbstractXTableCellWidget> cellWidget;
	
	public RowCell() {
		this.cellWidget = cast(DefaultCellWidget.class);
	}
	
	public XTableWidget[] getCellRenderWidgets() {
		return cellRenderWidgets;
	}

	public void setCellRenderWidgets(XTableWidget[] cellRenderWidgets) {
		this.cellRenderWidgets = cellRenderWidgets;
	}

	public Class<? extends AbstractXTableCellWidget> getCellWidget() {
		return cellWidget;
	}

	public void setCellWidget(Class<? extends AbstractXTableCellWidget> cellWidget) {
		this.cellWidget = cellWidget;
	}
}
