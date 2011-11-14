package com.csci.finalproject.agileassistant.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;

/**
 * Abstract class defining a container for a white board. It is
 * left up to the concrete implementation classes to specify what
 * this container holds. The purpose of this class is to define a
 * container for a series of {@link WhiteBoardColumn} objects.
 *  
 * @author Jacob
 */
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
	
	public void alertIfExists() {
		Window.alert("WB exists!");
	}

	
	/*
	 * GETTERS & SETTERS
	 */
	public AbstractProject getProject() {
		return project;
	}
}
