package cn.nextop.guava.widgets.datetime.support.dispatcher.event;

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