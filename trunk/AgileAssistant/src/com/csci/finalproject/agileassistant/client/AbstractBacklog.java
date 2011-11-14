package com.csci.finalproject.agileassistant.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;

abstract public class AbstractBacklog extends Composite implements HasWidgets {
	protected AbstractProject project;

	public AbstractBacklog( AbstractProject project ) {
		this.project = project;
	}
	
	/*
	 * ABSTRACT METHODS
	 */
	public abstract void registerDropControllers();
}
