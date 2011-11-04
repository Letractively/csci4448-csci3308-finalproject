package com.csci.finalproject.agileassistant.client;

import com.google.gwt.user.client.ui.Composite;

public abstract class AbstractWhiteBoard extends Composite {
	/*
	 * FIELDS
	 */
	// Abstract Project values
	protected AbstractProject project;
	
	public AbstractWhiteBoard( AbstractProject project ) {
		this.project = project;
	}
	
	/*
	 * ABSTRACT PUBLIC METHODS
	 */
	abstract public void addNotecard( Notecard nc );
	abstract public void addPostit( Postit postit );

}
