package cn.nextop.guava;

import static cn.nextop.guava.utils.SwtUtils.creator;
import static cn.nextop.guava.utils.SwtUtils.dispatch;
import static cn.nextop.guava.utils.SwtUtils.getDisplay;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.utils.SwtUtils;

public class TestCircleFigure {
	public static void main(String[] args) {
		final String name = "Widget Example";
		Shell shell = creator(500, 400, name);
		Composite cmp = SwtUtils.creator(shell);
		Canvas canvas = new Canvas(cmp, SWT.DOUBLE_BUFFERED);
		LightweightSystem lws = new LightweightSystem(canvas);
		
		lws.setContents(new TestCircleFigure().new Circle());
		
		shell.open(); dispatch(() -> shell.isDisposed());
	}
	
	public class Circle extends Figure {
		@Override
		protected void paintFigure(Graphics g) {
			super.paintFigure(g);
			g.setBackgroundColor(Colors.COLOR_WHITE);
			g.fillRectangle(getClientArea());
		}
		
		@Override
		protected void paintClientArea(Graphics g) {
			super.paintClientArea(g);
			g.setAdvanced(true); g.setAntialias(SWT.ON);
			Path p1 = new Path(getDisplay());
			p1.addArc(0, 0, 200, 200, -270, -10);
			p1.lineTo(100 , 100); p1.close();
			
			g.setBackgroundColor(Colors.COLOR_CYAN);
			g.fillPath(p1);	p1.dispose();
			
			Path p2 = new Path(getDisplay());
			p2.addArc(0, 0, 200, 200, -270, 350);
			p2.lineTo(100 , 100); p2.close();
			
			g.setBackgroundColor(Colors.COLOR_WIDGET_BACKGROUND);
			g.fillPath(p2);	p2.dispose();
			
			g.setBackgroundColor(Colors.COLOR_WHITE);
			g.fillOval(15, 15, 170, 170);
		}
	}
}
