package cn.nextop.guava;

import static cn.nextop.guava.support.swt.SwtUtils.creator;
import static cn.nextop.guava.support.swt.SwtUtils.dispatch;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import cn.nextop.guava.support.swt.Colors;
import cn.nextop.guava.support.swt.SwtUtils;
import net.miginfocom.swt.MigLayout;

public class TestFigure {
	
	public static void main(String[] args) {
		final String name = "Widget Example";
		Shell shell = creator(500, 400, name);
		Composite cmp = SwtUtils.creator(shell);
		cmp.setLayout(new MigLayout("insets 5, gap 0 0","[fill,grow]","[fill,grow][fill,grow]"));
		Canvas canvas = new Canvas(cmp, SWT.DOUBLE_BUFFERED); canvas.setLayoutData("cell 0 0, width 10:150:,height 10:200");
		Button btnOk = new Button(cmp, SWT.NONE); btnOk.setText("OK"); btnOk.setLayoutData("cell 0 1, width 10:150:,height 10:200");
		LightweightSystem lws = new LightweightSystem(canvas);
		Figure f1 = new Figure() {
			@Override
			protected void paintFigure(Graphics g) {
				super.paintFigure(g);
				g.setBackgroundColor(Colors.COLOR_BLACK);
				g.fillRectangle(getBounds());
				System.out.println("f1");
			}
		};
		
		
		Figure f2 = new Figure() {

			@Override
			protected void paintFigure(Graphics g) {
				super.paintFigure(g);
					g.clipRect(new Rectangle(0,0,100,10));
					g.setBackgroundColor(Colors.COLOR_RED);
					g.fillRectangle(getBounds());
					g.setBackgroundColor(Colors.COLOR_WHITE);
					g.fillRectangle(getBounds());
				System.out.println("f2");
			}
		};
		
//		Figure f3 = new Figure() {
//			@Override
//			protected void paintFigure(Graphics g) {
//				super.paintFigure(g);
//				g.setBackgroundColor(Colors.COLOR_BLUE);
//				g.fillRectangle(getBounds());
//				System.out.println("f3");
//			}
//		};
		f2.setBounds(new Rectangle(0, 0, 100, 100));
//		f3.setBounds(new Rectangle(100, 0, 100, 100));
		f1.add(f2); //f1.add(f3);
		lws.setContents(f1);
		
		btnOk.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				 f2.repaint();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		shell.open(); dispatch(() -> shell.isDisposed());
	}
}
