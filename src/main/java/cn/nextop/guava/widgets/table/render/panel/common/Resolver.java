package cn.nextop.guava.widgets.table.render.panel.common;

import static cn.nextop.guava.draw2d.scroll.support.glossary.Type.ALWAYS;
import static cn.nextop.guava.draw2d.scroll.support.glossary.Type.NEVER;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.draw2d.scroll.support.glossary.Type;

/**
 * @author jonny
 */
public class Resolver {
	
	/**
	 * 
	 */
	public static class Result {
		public boolean showH;
		public boolean showV;
		public Rectangle viewportArea;
		public Insets insets;
		
		@Override
		public String toString() {
			return "[showH:"+showH+",showV:"+showV+", Insets:"+ insets +", viewportArea :" + viewportArea+" ]";
		}
	}
	
	/**
	 * 
	 */
	public static Result solve(Rectangle clientArea, Viewport viewport,
			Type hVis, Type vVis, int vBarWidth, int hBarHeight) {
		final Result r = new Result();
		
		final Insets i1 = new Insets(0, 0, hBarHeight, vBarWidth);

		Dimension d1 = clientArea.getSize();
		int s1 = (vVis == NEVER) ? 0 : i1.right;
		int s2 = (hVis == NEVER) ? 0 : i1.bottom;
		Dimension d2 = d1.getCopy().shrink(s1, s2);
		d2.width = Math.max(d2.width, 0);
		d2.height = Math.max(d2.height, 0);
		int wHint = d2.width, hHint = d2.height;

		final Insets i2 = viewport.getInsets();
		Dimension minSize = new Dimension(i2.getWidth(), i2.getHeight());
		if (viewport.getContents() != null) {
			if (wHint > -1) wHint = Math.max(0, wHint - i2.getWidth());
			if (hHint > -1) hHint = Math.max(0, hHint - i2.getHeight());
			minSize.expand(viewport.getContents().getMinimumSize(wHint, hHint));
		}
		Dimension preferred = viewport.getPreferredSize(wHint, hHint).getCopy();
		preferred.height = minSize.height;
		preferred.width = minSize.width;

		boolean none = d1.contains(preferred);
		boolean both = !none && preferred.containsProper(d2);
		boolean sh = both || preferred.width > d1.width;
		boolean sv = both || preferred.height > d1.height;
		boolean showV = vVis != NEVER && (sv || vVis == ALWAYS);
		boolean showH = hVis != NEVER && (sh || hVis == ALWAYS);
		
		if (!showV) i1.right = 0; if (!showH) i1.bottom = 0;
		Rectangle r1 = clientArea.getShrinked(i1);
		
		r.showH = showH; r.showV = showV; r.insets = i1; r.viewportArea = r1; return r;
	}
}
