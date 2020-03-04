package cn.nextop.guava;

import static cn.nextop.guava.utils.SwtUtils.creator;
import static cn.nextop.guava.utils.SwtUtils.dispatch;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.utils.SwtUtils;
import net.miginfocom.swt.MigLayout;

public class ScrollPanelTest {
	public static void main(String[] args) {
		final String name = "Widget Example";
		Shell shell = creator(500, 400, name);
		Composite cmp = SwtUtils.creator(shell);
		cmp.setLayout(new MigLayout("insets 5, gap 0 0","[fill,grow]","[fill,grow][fill,grow]"));
		Canvas canvas = new Canvas(cmp, SWT.DOUBLE_BUFFERED); canvas.setLayoutData("cell 0 0, width 10:150:,height 10:200");
		Button btnOk = new Button(cmp, SWT.NONE); btnOk.setText("OK"); btnOk.setLayoutData("cell 0 1, width 10:150:,height 10:200");
		LightweightSystem lws = new LightweightSystem(canvas);
		lws.setEventDispatcher(new XSWTEventDispatcher());
		ScrollPane panel = new ScrollPane(); lws.setContents(panel);
		
		//
		Figure content = getContent();
		content.setBounds(new Rectangle(0, 0, 450, 400)); panel.setContents(content);
		btnOk.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				content.setPreferredSize(460,  400);
				panel.setValid(false); 
				panel.revalidate();
				System.out.println("click");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		shell.open(); dispatch(() -> shell.isDisposed());

		
//		content.setBounds(new Rectangle(0, 0, 10, 400));
	}
	
	protected static Figure getContent() {
		Figure content = new Figure() {
			@Override
			protected void paintFigure(Graphics g) {
				super.paintFigure(g);
				g.setBackgroundColor(Colors.COLOR_WHITE);
				g.fillRectangle(getBounds());
			}
		};
		return content;
	}
}
