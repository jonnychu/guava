package cn.nextop.guava;

import static cn.nextop.guava.support.swt.SwtUtils.creator;
import static cn.nextop.guava.support.swt.SwtUtils.dispatch;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import cn.nextop.guava.support.draw2d.scroll.bar.XScrollBar;
import cn.nextop.guava.support.swt.Colors;
import cn.nextop.guava.support.swt.SwtUtils;
import cn.nextop.guava.widgets.AbstractPanel;
import net.miginfocom.swt.MigLayout;

/**
 * @author jonny
 */
public class XScrollBarTest {
	public static void main(String[] args) {
		final String name = "Widget Example";
		Shell shell = creator(500, 400, name);
		Composite cmp = SwtUtils.creator(shell);
		cmp.setLayout(new MigLayout("insets 5, gap 0 0","[fill,grow]","[fill,grow]"));
		Canvas canvas = new Canvas(cmp, SWT.DOUBLE_BUFFERED | SWT.BORDER); canvas.setLayoutData("cell 0 0");
		LightweightSystem lws = new LightweightSystem(canvas);
		
		XScrollBarTest test = new XScrollBarTest();
		Panel panel = test.new Panel("test"); lws.setContents(panel);
		//
		shell.open(); dispatch(() -> shell.isDisposed());
	}
	
	public class Panel extends AbstractPanel {
		
		protected XScrollBar hBar;
		protected XScrollBar vBar;
		
		/**
		 */
		public Panel(String name) {
			super(name);
			add(hBar = new XScrollBar("scroll.hbar", true));
			add(vBar = new XScrollBar("scroll.vbar", false));
		}

		@Override
		protected void paintFigure(Graphics g) {
			super.paintFigure(g);
			g.setBackgroundColor(Colors.COLOR_WHITE);
			g.fillRectangle(getClientArea());
		}

		@Override
		protected void layoutManager(IFigure container) {
			Panel panel = (Panel) container;
			Rectangle r = panel.getClientArea();
			Dimension d1 = hBar.getPreferredSize(r.width, r.height);
			hBar.setBounds(new Rectangle(r.x, r.y + r.height - d1.height, d1.width - 16, d1.height));
			
			Dimension d2 = vBar.getPreferredSize(r.width, r.height);
			vBar.setBounds(new Rectangle(r.x + r.width - d2.width, r.y, d2.width, d2.height - 16));
		}
	}
}
