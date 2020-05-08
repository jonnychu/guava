package cn.nextop.guava.widgets.table.render.widget;

import static cn.nextop.guava.widgets.table.support.glossary.Sort.ASC;
import static cn.nextop.guava.widgets.table.support.glossary.Sort.DESC;
import static cn.nextop.guava.widgets.table.support.glossary.Sort.ETERNAL;
import static com.patrikdufresne.fontawesome.FontAwesome.angle_down;
import static com.patrikdufresne.fontawesome.FontAwesome.angle_up;
import static org.eclipse.draw2d.TextUtilities.INSTANCE;

import java.util.List;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;

import com.patrikdufresne.fontawesome.FontAwesome;

import cn.nextop.guava.widgets.table.model.XTableModel;
import cn.nextop.guava.widgets.table.model.column.Column;
import cn.nextop.guava.widgets.table.model.row.IRow;
import cn.nextop.guava.widgets.table.render.AbstractXTableColumnWidget;
import cn.nextop.guava.widgets.table.support.glossary.Sort;

/**
 * @author jonny
 */
public class DefaultColumnWidget extends AbstractXTableColumnWidget {
	//
	private final int space = 2;
	private final String asc = angle_up, desc = angle_down;
	
	/**
	 * 
	 */
	public DefaultColumnWidget() {
		addMouseListener(new MouseListener.Stub());
	}
	
	@Override
	protected void paintClientArea(Graphics g) {
		super.paintClientArea(g);
		final Rectangle r = getClientArea();
		final Sort sort = this.column.getSort();
		int align = column.getColAlign(); text = column.getText();
		Dimension d1 = INSTANCE.getStringExtents(text, g.getFont());
		if(align == SWT.LEFT) {
			g.drawText(this.text, r.x, (r.height - d1.height) / 2);
		} else if(align == SWT.RIGHT) {
			g.drawText(this.text, r.x + r.width - d1.width - space, (r.height - d1.height) / 2);
		} else {
			g.drawText(this.text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
		}
		
		Font font = FontAwesome.getFont(6); String text1 = ""; g.setFont(font);
		if(sort == ASC) text1 = asc; else if(sort == DESC) text1 = desc;
		final Dimension d2 = INSTANCE.getStringExtents(text1, g.getFont());
		g.drawText(text1, r.x + (r.width - d2.width) / 2 + space, r.y);
	}
	
	@Override
	public void handleMousePressed(MouseEvent event) {
		super.handleMousePressed(event);
		if(column.getSort() == ETERNAL) return;
		final Sort prev = this.column.getSort();
		final Sort next = this.column.getSort().next();
		final XTableModel model = this.factory.getModel();
		final List<IRow> rows = model.getRows().getRows();
		final List<Column<?>> cols = model.getColumns().getColumns();
		for (Column<?> col : cols) { // clear
			if(col.getSort() == ETERNAL) continue; col.setSort(Sort.NONE);
		}
		this.column.setSort(next);
		this.column.sort(rows); this.factory.sort();
		this.column.fire(Column.PROPERTY_SORT, prev, next);
	}
}
