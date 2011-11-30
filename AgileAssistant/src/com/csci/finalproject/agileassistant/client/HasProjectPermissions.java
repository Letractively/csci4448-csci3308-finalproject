package com.csci.finalproject.agileassistant.client;

import com.allen_sauer.gwt.dnd.client.DragContext;

public interface HasProjectPermissions {

	/**
	 * This function is responsible for interacting with the user to get the
	 * current permissions level for the project
	 */
	void genPermissionsLevel();
	
	
	/**
	 * Checks to make sure the move taking place by a drag and drop process is 
	 * permissible for the current permissions level
	 * @param context
	 * @return
	 */
	boolean moveIsPermissable(DragContext context);
}
