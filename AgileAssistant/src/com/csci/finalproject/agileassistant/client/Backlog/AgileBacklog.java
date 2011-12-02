package com.csci.finalproject.agileassistant.client.Backlog;

import java.util.LinkedList;

import com.csci.finalproject.agileassistant.client.AbstractProject;

public class AgileBacklog extends AbstractBacklog {
	
	public AgileBacklog(AbstractProject project) {
		super(project);
	}


	/*
	 * IMPLEMENTATIONS FOR SUPERCLASS `AbstractBacklog`
	 */
	@Override
	public void registerDropControllers() {
		dragCon_slider.registerDropController(dropController);
		project.getDragCon_notecard().registerDropController(dropController);
	}

	@Override
	protected void generatePointsSliders() {
		sliders = new LinkedList<ValueSlider>();
		AbstractBacklog.FIRST_POINTS_SLIDER_ENUM = 
				BacklogValueSlider_Agile.THREE;
		AbstractBacklog.LAST_POINTS_SLIDER_ENUM = 
				BacklogValueSlider_Agile.ONEHUNDREN;
		
		for( BacklogValueSlider sliderEnum : BacklogValueSlider_Agile.values() ) {
			if( sliderEnum == LAST_POINTS_SLIDER_ENUM ) break;
			
			ValueSlider slider = new ValueSlider(sliderEnum);
			slider.addStyleName(CSS_BACKLOG_SLIDER);
			sliders.add(slider);
			add(slider);
		}
	}
}
