package com.csci.finalproject.agileassistant.client;

import com.google.gwt.user.client.ui.Composite;

abstract public class AbstractBacklog extends Composite {
	protected AbstractProject project;

	public AbstractBacklog( AbstractProject project ) {
		this.project = project;
	}

}
