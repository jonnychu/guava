package cn.nextop.guava.widgets.table.render.widget;

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
	private String asc = FontAwesome.caret_up, desc = FontAwesome.caret_down;
	
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
		Font font1 = g.getFont(), font2 = FontAwesome.getFont(8);
		int align = column.getColAlign(); this.text = column.getText();
		final Dimension d1 = INSTANCE.getStringExtents(text, font1);
		String stext = ""; g.setFont(font2);
		if(sort == Sort.ASC) stext = asc; else if(sort == Sort.DESC) stext = desc;
		final Dimension d2 = INSTANCE.getStringExtents(stext, g.getFont());
		
		g.setFont(font1);
		if(align == SWT.LEFT) {
			g.drawText(this.text, r.x, (r.height - d1.height) / 2);
		} else if(align == SWT.RIGHT) {
			g.drawText(this.text, r.x + r.width - d1.width - d2.width - space, (r.height - d1.height) / 2);
		} else {
			g.drawText(this.text, r.x + (r.width - d1.width - d2.width) / 2, r.y + (r.height - d1.height) / 2);
		}
		
		g.setFont(font2);
		if(align == SWT.LEFT) {
			g.drawText(stext, r.x + d1.width + space, (r.height - d2.height) / 2);
		} else if(align == SWT.RIGHT) {
			g.drawText(stext, r.x + r.width - d2.width, (r.height - d2.height) / 2);
		} else {
			g.drawText(stext, r.x + (r.width - d1.width - d2.width) / 2 + d1.width + space, r.y + (r.height - d2.height) / 2);
		}
	}
	
	@Override
	public void handleMousePressed(MouseEvent event) {
		super.handleMousePressed(event);
		final Sort prev = this.column.getSort();
		final Sort next = this.column.getSort().next();
		final XTableModel model = this.factory.getModel();
		final List<IRow> rows = model.getRows().getRows();
		final List<Column<?>> cols = model.getColumns().getColumns();
		for (Column<?> col : cols) {
			if(col.getSort() == Sort.ETERNAL) continue;
			col.setSort(Sort.NONE);
		}
		this.column.setSort(prev.next());
		this.column.sort(rows); this.factory.sort();
		this.column.fire(Column.PROPERTY_SORT, prev, next);
		
	}
}
