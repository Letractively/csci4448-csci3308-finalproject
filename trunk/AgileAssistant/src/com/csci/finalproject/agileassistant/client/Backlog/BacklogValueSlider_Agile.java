package com.csci.finalproject.agileassistant.client.Backlog;

import com.csci.finalproject.agileassistant.client.AgileProject;
import com.csci.finalproject.agileassistant.client.Notecard;

/**
 * This are the possible point values for {@link Notecard} objects in an 
 * {@link AgileProject}. 
 * @author Jacob
 */
public enum BacklogValueSlider_Agile implements BacklogValueSlider {

	THREE		(3,		"/images/BacklogPointsSlider_3points.png"),
	FIVE		(5,		"/images/BacklogPointsSlider_5points.png"),
	EIGHT		(8,		"/images/BacklogPointsSlider_8points.png"),
	THIRTEEN	(13,	"/images/BacklogPointsSlider_13points.png"),
	TWENTYONE	(21,	"/images/BacklogPointsSlider_21points.png"),
	FIFTY		(50,	"/images/BacklogPointsSlider_50points.png"),
	ONEHUNDREN	(100,	"/images/BacklogPointsSlider_100points.png");
	
	private final int points;
	private final String href;
	
	BacklogValueSlider_Agile( int points, String href ) {
		this.points = points;
		this.href = href;
	}

	/*
	 * IMPLEMENTATIONS FOR INTERFACE `BacklogValueSlider`
	 */
	@Override
	public String getImageURL() {
		return href;
	}

	@Override
	public int getValue() {
		return points;
	}
}
