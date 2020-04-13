package cn.nextop.guava.support.draw2d.scroll.bar;

import java.util.Iterator;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;

/**
 * @author jonny
 */
public class PageButton extends Figure {
	//
	private String name;
	
	public PageButton(String name) {
		this.name = name;
		addMouseListener(new MouseListener.Stub() {
			@Override
			public void mousePressed(MouseEvent me) {
				super.mousePressed(me);
				fireActionPerformed();
			}
		});
	}
	
	public void addActionListener(ActionListener listener) {
		addListener(ActionListener.class, listener);
	}
	
	@SuppressWarnings({ "rawtypes" })
	public void fireActionPerformed(){
		ActionEvent action = new ActionEvent(this, name);
		Iterator iter = getListeners(ActionListener.class);
		while (iter.hasNext())
			((ActionListener) iter.next()).actionPerformed(action);
	}
}