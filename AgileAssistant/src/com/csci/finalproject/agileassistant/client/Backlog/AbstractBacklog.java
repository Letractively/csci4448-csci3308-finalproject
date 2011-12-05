package com.csci.finalproject.agileassistant.client.Backlog;

import java.util.List;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.csci.finalproject.agileassistant.client.AbstractProject;
import com.csci.finalproject.agileassistant.client.Notecard;
import com.csci.finalproject.agileassistant.client.UserStoryCondition;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InsertPanel;
import com.google.gwt.user.client.ui.Widget;

abstract public class AbstractBacklog extends AbsolutePanel 
implements InsertPanel.ForIsWidget {
	/*
	 * THE PROJECT THIS BACKLOG BELONGS TO
	 */
	protected static AbstractProject project;
	

	/*
	 * THE FIRST AND LAST BACKLOGVALUESLIDERS
	 */
	protected static BacklogValueSlider FIRST_POINTS_SLIDER_ENUM;
	protected static BacklogValueSlider LAST_POINTS_SLIDER_ENUM;

	
	/*
	 * THIS IS THE HOW POINTS ARE ASSIGNED TO SPECIFIC NOTECARDS
	 */
	protected class ValueSlider extends Image {
		protected int value;
		public ValueSlider( BacklogValueSlider sliderEnum ) {
			super(sliderEnum.getImageURL());
			this.value = sliderEnum.getValue();
			dragCon_slider.makeDraggable(this);
		}
		public int getValue() { return this.value; }
	}
	protected List<ValueSlider> sliders;
	
	
	/*
	 * THE DRAG/DROP CONTROLLERS FOR THIS BACKLOG
	 */
	protected BacklogDropController dropController = 
			new BacklogDropController(this);
	protected final PickupDragController dragCon_slider = 
			new PickupDragController(this, false);

	
	/*
	 * CSS STYLE NAMES
	 */
	protected final static String CSS_ABSTRACT_BACKLOG = "Abstract-Backlog";
	protected final static String CSS_BACKLOG_WRAPPER = "Backlog-Wrapper";
	protected final static String CSS_BACKLOG_SLIDER = "Backlog-PointSlider";

	
	/*
	 * CONSTRUCTORS
	 */
	public AbstractBacklog( AbstractProject project ) {
		AbstractBacklog.project = project;

		// Set CSS styles
		addStyleName(CSS_ABSTRACT_BACKLOG);
		addStyleName(CSS_BACKLOG_WRAPPER);
		
		// Init functions
		generatePointsSliders();
		registerDropControllers();
	}
	
	
	/*
	 * ABSTRACT METHODS
	 */
	abstract public void registerDropControllers();
	abstract protected void generatePointsSliders();
	
	
	/*
	 * HELPER METHODS
	 */
	public int getNotecardIndexWithoutSliders(Notecard nc) {
		int ncIndex = getWidgetIndex(nc);
		if(ncIndex == -1) return ncIndex;		
		
		for( int i=ncIndex; i>=0; i-- ) {
			if( getWidget(i).getClass() == ValueSlider.class )
				ncIndex--;
		}
		return ncIndex;
	}

	public int getNextSliderPoints(int startIndex) {
		int points = LAST_POINTS_SLIDER_ENUM.getValue();
		
		while( startIndex < getWidgetCount() ) {
			Widget w = getWidget(startIndex);
			if( w.getClass() == ValueSlider.class ) { 
				ValueSlider slider = (ValueSlider) w;
				points = slider.getValue();
				break;
			}
			startIndex++;
		}
		
		return points;
	}
	
	public int getNotecardCount() {
		return (getWidgetCount() - (sliders.size()-1));
	}
	
	
	/*
	 * IMPLEMENTATIONS FOR INTERFACE 'InsertPanel'
	 */
	@Override
	public void add(Widget w) {
		if(w instanceof Notecard) {
			Notecard nc = (Notecard) w;
			
			int beforeIndex = 0;
			if(nc.getPoints() == LAST_POINTS_SLIDER_ENUM.getValue()) {
				beforeIndex = getWidgetCount();
				
			} else {
				for(ValueSlider s : sliders) {
					if(nc.getPoints() == s.getValue()) {
						beforeIndex = getWidgetIndex(s);
					}
				}
			}
			
			super.insert(nc, beforeIndex);
			
		} else {
			super.add(w);
		}
	}
	
	@Override
	public void insert(Widget w, int beforeIndex) {
		super.insert(w, beforeIndex);

		if(w instanceof Notecard) {
			Notecard nc = (Notecard) w;
			
			if(nc.getCondition() != UserStoryCondition.BL) {
				nc.setCondition(UserStoryCondition.BL);
			}
			nc.setPoints(getNextSliderPoints(getWidgetIndex(nc)));
			
			project.persistUserStory(nc, getNotecardIndexWithoutSliders(nc));
			
		} else if(w instanceof ValueSlider) {			
			ValueSlider movingSlider = (ValueSlider) w; 
			
			// Set the values for everything above the Slider that was moved
			int points = movingSlider.getValue();
			for(int i=(beforeIndex-1); i>=0; i--) {
				Widget curWidget = getWidget(i);
				if(curWidget instanceof Notecard) {
					Notecard curNc = (Notecard) curWidget;

					if( curNc.getPoints() != points ) {
						curNc.setPoints(points);
						project.persistUserStory(curNc, -1);
					}
				} else if(curWidget instanceof ValueSlider) {
					break;
				}
			}

			// Set the values for everything below the Slider that was moved
			points = getNextSliderPoints(beforeIndex+1);
			for(int i=(beforeIndex+2); i<getWidgetCount(); i++) {
				Widget curWidget = getWidget(i);
				if(curWidget instanceof Notecard) {
					Notecard curNc = (Notecard) curWidget;

					if( curNc.getPoints() != points ) {
						curNc.setPoints(points);
						project.persistUserStory(curNc, -1);
					}
					
				} else if(curWidget instanceof ValueSlider){
					break;
				}
			}
		}
	}
	
	
	/*
	 * GETTERS & SETTERS
	 */
	public AbstractProject getProject() {
		return project;
	}
}
