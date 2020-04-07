package cn.nextop.guava;

import static cn.nextop.guava.utils.SwtUtils.creator;
import static cn.nextop.guava.utils.SwtUtils.dispatch;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.utils.SwtUtils;

public class GridFigureTest {

	
	public static void main(String[] args) {
		final String name = "Widget Example";
		Shell shell = creator(500, 400, name);
		Composite cmp = SwtUtils.creator(shell);
		cmp.setLayout(new FillLayout());
		Canvas canvas = new Canvas(cmp, SWT.DOUBLE_BUFFERED); //canvas.setLayoutData("cell 0 0, width 10:150:,height 10:200");
		LightweightSystem lws = new LightweightSystem(canvas);
		Figure f1 = new Figure() {
			@Override
			protected void paintFigure(Graphics g) {
				super.paintFigure(g);
				g.setBackgroundColor(Colors.COLOR_WHITE);
				g.fillRectangle(getBounds());
			}
			
			@Override
			protected void paintClientArea(Graphics g) {
				super.paintClientArea(g);
				g.setForegroundColor(Colors.COLOR_GRAY);
				FigureUtilities.paintGrid(g, this, new Point(-1, 0), 0, 20);
			}
		};
		
		lws.setContents(f1);
		shell.open(); dispatch(() -> shell.isDisposed());
	}

}
