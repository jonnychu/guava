package cn.nextop.guava.widgets.table.render.widget;

import static cn.nextop.guava.support.Objects.cast;

import java.util.List;

import org.eclipse.draw2d.AbstractHintLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.widgets.table.render.AbstractXTableCellWidget;
import cn.nextop.guava.widgets.table.render.widget.external.XTableWidget;

/**
 * @author jonny
 */
public class DefaultCellRenderWidget extends AbstractXTableCellWidget {
	
	public DefaultCellRenderWidget() {
		super(false, false);
		setLayoutManager(new ClassicLayout());
	}
	
	protected class ClassicLayout extends AbstractHintLayout {
		//
		private final int space = 2, margin = 4;
		
		@Override
		public void layout(IFigure container) {
			DefaultCellRenderWidget parent = cast(container);
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
}
