package cn.nextop.guava.widgets.slider.render.panel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.widgets.slider.XSlider;
import cn.nextop.guava.widgets.slider.builder.XSliderBuilder;
import cn.nextop.guava.widgets.slider.model.XSliderModel;
import cn.nextop.guava.widgets.slider.render.widget.Indicator;
import cn.nextop.guava.widgets.slider.render.widget.SliderBar;
import cn.nextop.guava.widgets.spinner.render.AbstractXSpinnerPanel;

/**
 * @author jonny
 */
public class XSliderPanel extends AbstractXSpinnerPanel implements PropertyChangeListener {
	//
	protected XSlider slider;
	
	/**
	 * 
	 */
	public XSliderPanel(String name, XSlider slider) {
		super(name); this.slider = slider;
		slider.getModel().addPropListener(this);
	}
	
	@Override
	public boolean isOpaque() {
		return true;
	}
	
	@Override
	public void revalidate() {
		invalidate();
		getUpdateManager().addInvalidFigure(this);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		revalidate();
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		XSliderPanel parent = (XSliderPanel) container;
		XSliderBuilder builder = slider.getBuilder();
		Indicator indicator = builder.getIndicator();
		SliderBar sliderBar = builder.getSliderBar();
		//
		XSliderModel model = slider.getModel();
		final Rectangle r = parent.getClientArea();
		int x = r.x, y = r.y, w = r.width, h = r.height;
		
		//
		Rectangle r1 = new Rectangle(x + h / 4, y, w - h / 2, h/2); 
		sliderBar.setBounds(r1); sliderBar.repaint();
		
		//
		double p = model.getValue() / (model.getUpper() - model.getLower()) * r1.width;
		Rectangle r2 = new Rectangle(x + (int)p, y + h / 2, h/2, h/2); indicator.setBounds(r2);
	}
	
	public class DragListener extends MouseMotionListener.Stub implements MouseListener {
		//
		protected Point start;
		protected boolean armed;
		protected double dragRange, revertValue;
		
		@Override
		public void mousePressed(MouseEvent me) {
			XSliderBuilder builder = slider.getBuilder();
			Indicator indicator = builder.getIndicator();
			IFigure w = indicator.findFigureAt(me.x, me.y);
			if(w == null) return; // not in indicator widget
			//
			final Rectangle r = getClientArea();
			start = me.getLocation(); dragRange = r.width; 
			armed = true; revertValue = slider.getValue(); me.consume();
		}

		@Override
		public void mouseDragged(MouseEvent me) {
			if (!armed) return;
			final double vr = slider.getValueRange();
			final int change = me.getLocation().getDifference(start).width;
			slider.getModel().setValue(revertValue + vr * change / dragRange); me.consume();
		}
		
		@Override
		public void mouseDoubleClicked(MouseEvent me) {}
		
		@Override
		public void mouseReleased(MouseEvent me) {
			if (!armed) return; armed = false; me.consume();
		}
	}
}
