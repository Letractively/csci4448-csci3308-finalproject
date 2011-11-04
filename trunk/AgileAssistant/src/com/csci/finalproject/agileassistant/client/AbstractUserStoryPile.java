package com.csci.finalproject.agileassistant.client;

import com.google.gwt.user.client.ui.Composite;

abstract public class AbstractUserStoryPile extends Composite {
	protected AbstractProject project;

	public AbstractUserStoryPile( AbstractProject project ) {
		this.project = project;
	}

}
