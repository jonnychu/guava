package cn.nextop.guava.widgets.table.render.widget;

import static cn.nextop.guava.support.Objects.cast;
import static cn.nextop.guava.support.swt.CGUtils.fillRect;
import static org.eclipse.draw2d.TextUtilities.INSTANCE;

import java.util.List;

import org.eclipse.draw2d.AbstractHintLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;

import cn.nextop.guava.support.property.Property;
import cn.nextop.guava.support.swt.Colors;
import cn.nextop.guava.widgets.table.model.XTableModel;
import cn.nextop.guava.widgets.table.render.AbstractXTableCellWidget;
import cn.nextop.guava.widgets.table.render.widget.external.XTableWidget;
import cn.nextop.guava.widgets.table.support.formatter.XTableFormatter;
import cn.nextop.guava.widgets.table.support.selection.ISelection;

/**
 * @author jonny
 */
public class DefaultCellWidget extends AbstractXTableCellWidget {
	
	public DefaultCellWidget() {
		setLayoutManager(new ClassicLayout());
		addMouseListener(new MouseListener.Stub());
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		if(this.selected) fillRect(g, getBounds(), Colors.COLOR_CYAN);
	}
	
	@Override
	protected void paintClientArea(Graphics g) {
		super.paintClientArea(g);
		List<XTableWidget> widgets = cast(getChildren());
		if(widgets == null || widgets.size() == 0) {
			final Rectangle r = getClientArea();
			final int align = column.getCellAlign();
			Property<?> property = column.getProperty();
			XTableFormatter<?> formatter = column.getFormatter();
			this.text = formatter.valueOf(cast(property.getValue(cast(row))));
			Dimension d1 = INSTANCE.getStringExtents(text, g.getFont());
			if(align == SWT.LEFT) {
				g.drawText(this.text, r.x + margin, r.y + (r.height - d1.height) / 2);
			} else if(align == SWT.CENTER) {
				g.drawText(this.text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
			} else {
				g.drawText(this.text, r.x + r.width - d1.width - margin, r.y + (r.height - d1.height) / 2);
			}
		} else {
			// draw children;
		}
	}
	
	protected class ClassicLayout extends AbstractHintLayout {
		//
		private final int space = 2, margin = 4;
		
		@Override
		public void layout(IFigure container) {
			DefaultCellWidget parent = cast(container);
			List<XTableWidget> widgets = cast(parent.getChildren());
			final Rectangle r = parent.getClientArea();
			if(widgets == null || widgets.size() == 0) return;
			final int x = r.x, y = r.y, w = r.width, h = r.height;
			int cw = (w - margin) / widgets.size(), cx = x + margin;
			for (XTableWidget widget : widgets) {
				Rectangle r1 = new Rectangle(cx, y + margin / 2, cw - space, h - margin * 2 / 2);
				widget.setBounds(r1); cx = cx + cw;
			}
		}

		@Override
		protected Dimension calculatePreferredSize(IFigure container, int wHint, int hHint) {
			return null;
		}
	}
	
	@Override
	public void handleMousePressed(MouseEvent event) {
		super.handleMousePressed(event);
		final XTableModel model = this.factory.getModel();
		final ISelection selection = model.getSelection();
		List<DefaultCellWidget> c = cast(getParent().getChildren());
		if(c == null || c.size() == 0) return; selection.add(this.row.getRowId(), c);
	}
}
