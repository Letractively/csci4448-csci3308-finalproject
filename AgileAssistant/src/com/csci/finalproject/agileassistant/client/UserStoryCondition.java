package com.csci.finalproject.agileassistant.client;

/**
 * An enum that specifies what a {@link com.csci.finalproject.agileassistant.server.UserStory}
 * object's condition is.
 * 
 * @author Jacob
 */
public enum UserStoryCondition {
	/**
	 * Signifies that a {@link com.csci.finalproject.agileassistant.server.UserStory} has
	 * been created, but has not been sorted into the {@link AgileBacklog} yet. Thus, the
	 * story resides in a {@link AgileUserStoryPile}
	 */
	USP,
	
	/**
	 * Signifies that a {@link com.csci.finalproject.agileassistant.server.UserStory} has
	 * been sorted into an {@link AgileBacklog} and is now ready to be pulled into a 
	 * development sprint.
	 */
	BL,
	
	/**
	 * Signifies that a {@link com.csci.finalproject.agileassistant.server.UserStory} has
	 * been pulled into a development sprint and is located on an {@link AgileWhiteBoard}.
	 */
	WB;
}
