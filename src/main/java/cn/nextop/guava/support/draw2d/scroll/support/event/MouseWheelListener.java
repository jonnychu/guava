package cn.nextop.guava.support.draw2d.scroll.support.event;

/**
 * @author jonny
 */
public interface MouseWheelListener {
	
	/**
	 * 
	 */
	void mouseScrolled(ScrollEvent me);
	
	/**
	 * 
	 */
	public class Stub implements MouseWheelListener {
		@Override public void mouseScrolled(ScrollEvent me) {}
	}
}