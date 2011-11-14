package com.csci.finalproject.agileassistant.client;
/**
 * An enum that specifies what the condition of an 
 * {@link com.csci.finalproject.agileassistant.server.Task}
 * is inside of an {@link AgileProject}.
 * 
 * @author Jacob
 */
public enum TaskCondition {
	/**
	 * Signifies that a {@link com.csci.finalproject.agileassistant.server.Task}
	 * has not yet been started by a developer.
	 */
	TO_DO,
	
	/**
	 * Signifies that a {@link com.csci.finalproject.agileassistant.server.Task}
	 * is claimed and in progress by a developer.
	 */
	IN_PROGRESS,
	
	/**
	 * Signifies that a {@link com.csci.finalproject.agileassistant.server.Task}
	 * has completed the development phase and is now being verified.
	 */
	IN_VERIFICATION,
	
	/**
	 * Signifies that a {@link com.csci.finalproject.agileassistant.server.Task}
	 * has completed its development and has been verified to work as needed.
	 * At this point, it has been finished.
	 */
	COMPLETE;
}
