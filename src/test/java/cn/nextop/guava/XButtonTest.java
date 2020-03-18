package cn.nextop.guava;

import static cn.nextop.guava.utils.SwtUtils.creator;
import static cn.nextop.guava.utils.SwtUtils.dispatch;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.Panel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.patrikdufresne.fontawesome.FontAwesome;

import cn.nextop.guava.scroll.XButton;
import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.utils.SwtUtils;
import net.miginfocom.swt.MigLayout;

/**
 * @author jonny
 */
public class XButtonTest {
	public static void main(String[] args) {
		final String name = "Widget Example";
		Shell shell = creator(500, 400, name);
		Composite cmp = SwtUtils.creator(shell);
		cmp.setLayout(new MigLayout("insets 5, gap 0 0","[fill,grow]","[fill,grow]"));
		Canvas canvas = new Canvas(cmp, SWT.DOUBLE_BUFFERED | SWT.BORDER); canvas.setLayoutData("cell 0 0, width 10:150:,height 10:200");
		LightweightSystem lws = new LightweightSystem(canvas);
		
		Panel panel = new Panel() {
			@Override
			protected void paintFigure(Graphics g) {
				super.paintFigure(g);
				g.setBackgroundColor(Colors.COLOR_WHITE);
				g.fillRectangle(getClientArea());
			}
		
		}; lws.setContents(panel);
		XButton btnOk = new XButton(FontAwesome.caret_up); //btnOk.setBounds(new Rectangle(0,0,16,16));
		btnOk.setSize(btnOk.getPreferredSize());
		panel.add(btnOk);
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				System.out.println(event.getActionName());
			}
		});
		//
		shell.open(); dispatch(() -> shell.isDisposed());
	}
}
