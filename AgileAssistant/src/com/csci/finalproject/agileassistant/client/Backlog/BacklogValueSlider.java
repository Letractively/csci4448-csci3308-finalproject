package com.csci.finalproject.agileassistant.client.Backlog;

import com.csci.finalproject.agileassistant.client.AbstractProject;

/**
 * Interface for value sliders in an {@link AbstractBacklog}. This interface
 * is intended to be implemented by Enums that have set values for both image
 * URLs and values for concrete implementations of {@link AbstractBacklog}. See
 * {@link BacklogValueSlider_Agile} for an example of an Enum that implements this and
 * {@link AbstractBacklog.ValueSlider} to see how it is instanciated.
 * @author Jacob
 */
public interface BacklogValueSlider {
	
	/**
	 * Gets the image to be displayed for this slider.
	 * @return The URL for the image that will represent this points slider in
	 * the form of a String
	 */
	public String getImageURL();
	
	/**
	 * Gets the value of this slider as an int. Different implementations of 
	 * {@link AbstractProject} may have different intentions for its values.
	 * Some may have points, others may have time, and others may have money.
	 * @return The value of this slider as an int.
	 */
	public int getValue();
}
