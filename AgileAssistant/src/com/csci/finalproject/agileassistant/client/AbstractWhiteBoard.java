package com.csci.finalproject.agileassistant.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;

public abstract class AbstractWhiteBoard extends Composite implements
		HasWidgets {
	
	protected AbstractProject project;
	protected HorizontalPanel whiteBoardWrapper;

	public AbstractWhiteBoard(AbstractProject project) {
		super();
		this.project = project;
		
		whiteBoardWrapper = new HorizontalPanel();
		whiteBoardWrapper.setStyleName("WhiteBoard-Wrapper");
		initWidget(whiteBoardWrapper);
	}
	
	/*
	 * ABSTRACT PROTECTED METHODS
	 */
	public abstract void registerDropControllers();
}
