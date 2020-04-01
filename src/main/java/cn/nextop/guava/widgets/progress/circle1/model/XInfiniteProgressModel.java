package cn.nextop.guava.widgets.progress.circle1.model;

import static cn.nextop.guava.utils.SwtUtils.async;
import static cn.nextop.guava.utils.SwtUtils.sync;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Timer;
import java.util.TimerTask;

/**
 * angle range >=0 <=45
 * @author jonny
 */
public class XInfiniteProgressModel {
	//
	private Timer timer;
	private boolean running, cancel;
	//
	private double angle, lower = 0d, upper = 45d;
	private final PropertyChangeSupport listeners;
	//
	public static final String PROPERTY_VALUE = "VALUE";
	private final int TIMER_INITIAL = 250, TIMER_STEP = 10, ANGLE_STEP = 3;
	
	/**
	 * 
	 */
	public XInfiniteProgressModel() {
		this.listeners = new PropertyChangeSupport(this);
	}
	
	/**
	 * 
	 */
	public boolean isrunning() {
		return running;
	}

	public void setrunning(boolean running) {
		this.running = running;
	}
	
	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		double ov = this.angle;
		this.angle = angle;
		if(this.angle <= this.lower) this.angle = this.upper;
		if(this.angle >= this.upper) this.angle = this.lower;
		fire(PROPERTY_VALUE, ov, getAngle());
	}
	
	/**
	 * 
	 */
	public void addPropListener(PropertyChangeListener listener) {
		listeners.addPropertyChangeListener(listener);
	}
	
	public void removePropListener(PropertyChangeListener listener) {
		listeners.removePropertyChangeListener(listener);
	}
	
	protected void fire(String string, double oldValue, double newValue) {
		listeners.firePropertyChange(string, oldValue, newValue);
	}
	
	/**
	 * 
	 */
	public void stop() { sync(null, () -> { suspend(); }); }
	
	public void start() {
		if (running && !cancel)	return;
		running = true; timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				async(null, () -> {
					if (cancel) timer.cancel();
					setAngle(getAngle() + ANGLE_STEP);
				});
			}
		}, TIMER_INITIAL, TIMER_STEP);
	}

	public void suspend() {
		if (timer == null) return; timer.cancel(); timer = null; running = false; cancel = false;
	}
}
