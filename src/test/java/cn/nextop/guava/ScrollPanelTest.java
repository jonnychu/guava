package cn.nextop.guava;

import static cn.nextop.guava.utils.SwtUtils.creator;
import static cn.nextop.guava.utils.SwtUtils.dispatch;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.utils.SwtUtils;
import cn.nextop.guava.widgets.table.render.panel.XScrollPanel;
import net.miginfocom.swt.MigLayout;

public class ScrollPanelTest {
	public static void main(String[] args) {
		final String name = "Widget Example";
		Shell shell = creator(500, 400, name);
		Composite cmp = SwtUtils.creator(shell);
		cmp.setLayout(new MigLayout("insets 5, gap 0 0","[fill,grow]","[fill,grow]"));
		Canvas canvas = new Canvas(cmp, SWT.DOUBLE_BUFFERED); canvas.setLayoutData("cell 0 0");
		LightweightSystem lws = new LightweightSystem(canvas);
		XScrollPanel panel = new XScrollPanel("scroll"); lws.setContents(panel);
		
		//
		Figure f1 = new Figure() {
			@Override
			protected void paintFigure(Graphics g) {
				super.paintFigure(g);
				Rectangle r = getBounds();
				g.drawString("1111", r.x , r.y);
			}
		};
		
		Figure f11 = new Figure() {
			@Override
			protected void paintFigure(Graphics g) {
				super.paintFigure(g);
				Rectangle r = getBounds();
				g.drawString("6666", r.x , r.y);
			}
		};
		
		Figure f2 = new Figure() {
			@Override
			protected void paintFigure(Graphics g) {
				super.paintFigure(g);
				Rectangle r = getBounds();
				g.drawString("2222", r.x , r.y);
			}
		};
		
		Figure f22 = new Figure() {
			@Override
			protected void paintFigure(Graphics g) {
				super.paintFigure(g);
				Rectangle r = getBounds();
				g.drawString("7777", r.x , r.y);
			}
		};

		f1.setBounds(new Rectangle(0, 0 , 40 , 20));
		f11.setBounds(new Rectangle(550, 380 , 40 , 20)); 
		f2.setBounds(new Rectangle(0, 0 , 40 , 20)); 
		f22.setBounds(new Rectangle(550, 0 , 40 , 20)); 
		Figure dataContent = getContent(600, 400); 
		dataContent.add(f1); dataContent.add(f11);
		panel.setDataContents(dataContent);
		Figure headerContent = getContent(600, 20); 
		headerContent.add(f2); headerContent.add(f22);
		panel.setHeaderContents(headerContent);
		
		shell.open(); dispatch(() -> shell.isDisposed());
	}
	
	protected static Figure getContent(int w, int h) {
		Figure content = new Figure() {
			
			@Override
			protected void paintFigure(Graphics g) {
				super.paintFigure(g);
				Rectangle r = getBounds();
				g.setBackgroundColor(Colors.COLOR_WHITE);
				g.fillRectangle(r);
			}
			
			@Override
			public Dimension getMinimumSize(int wHint, int hHint) {
				return new Dimension(w, h);
			}
		};
		return content;
	}
}
