package cn.nextop.guava.widgets.combo.render.popup.widget;

import static cn.nextop.guava.widgets.table.support.util.Objects.cast;
import static com.patrikdufresne.fontawesome.FontAwesome.check;
import static org.eclipse.draw2d.TextUtilities.INSTANCE;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;

import com.patrikdufresne.fontawesome.FontAwesome;

import cn.nextop.guava.widgets.combo.render.AbstractCellWidget;
import cn.nextop.guava.widgets.combo.support.property.Property;

/**
 * @author jonny
 */
public class BoolCellWidget extends AbstractCellWidget {
	
	public BoolCellWidget() {
		super(false, false);
	}
	
	@Override
	protected void paintClientArea(Graphics g) {
		super.paintClientArea(g);
		final Rectangle r = getClientArea();
		final int align = column.getAlign();
		Property<?> property = column.getProperty();
		boolean selected = cast(property.getValue(cast(row)));
		g.setFont(FontAwesome.getFont()); this.text = selected ? check : "";
		Dimension d1 = INSTANCE.getStringExtents(text, g.getFont());
		if(align == SWT.LEFT) {
			g.drawText(this.text, r.x + margin, r.y + (r.height - d1.height) / 2);
		} else if(align == SWT.CENTER) {
			g.drawText(this.text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
		} else {
			g.drawText(this.text, r.x + r.width - d1.width - margin, r.y + (r.height - d1.height) / 2);
		}
	}
}
