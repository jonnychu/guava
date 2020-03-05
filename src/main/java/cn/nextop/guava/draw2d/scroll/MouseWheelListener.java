package cn.nextop.guava.draw2d.scroll;

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