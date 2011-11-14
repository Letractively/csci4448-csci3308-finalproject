package com.csci.finalproject.agileassistant.client;

/**
 * An enum that represents the different types of projects
 * that can be represented by {@link AbstractProject}. The
 * current types include {@link ProjectType#AGILE} and 
 * {@link ProjectType#AGILE_MOBILE}. 
 * 
 * @author Jacob
 */
public enum ProjectType {
	// TODO: add an @link to AGILE_MOBILE once it is created
	/**
	 * Signifies that an {@link AbstractProject} is to be
	 * instantiated as an {@link AgileProject}.
	 */
	AGILE, 
	/**
	 * Signifies that an {@link AbstractProject} is to be
	 * instantiated as an AgileMobileProject.
	 */
	AGILE_MOBILE;
}
