package com.csci.finalproject.agileassistant.client;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.SimplePanel;

abstract public class AbstractUserStoryPile extends Composite 
implements HasWidgets {
	
	protected static AbstractProject project;
	protected AbsolutePanel dragDropPanel;
	protected UserStoryPileDropController uspDropCon;
	protected Label titleLabel = new Label("Title");
	protected final Button addUserStoryButton = new Button("Add!");
	private final FlowPanel flowPanel = new FlowPanel();

	public AbstractUserStoryPile( AbstractProject project ) {
		this.project = project;
		
		FlowPanel uspWrapper = new FlowPanel();
		uspWrapper.setStyleName("UserStoryPile-Wrapper");
		initWidget(uspWrapper);
		uspWrapper.setSize("400px", "350px");
		
		titleLabel.setStyleName("UserStoryPile-Label");
		uspWrapper.add(titleLabel);
		
		dragDropPanel = new AbsolutePanel();
		dragDropPanel.setStyleName("UserStoryPile-DragDropPanel");
		uspWrapper.add(dragDropPanel);

		uspDropCon = new UserStoryPileDropController( dragDropPanel, this );
		flowPanel.setStyleName("UserStoryPile-AddButtonPanel");
		
		uspWrapper.add(flowPanel);
		flowPanel.add(addUserStoryButton);
		addUserStoryButton.setStyleName("UserStoryPile-addButton");
		registerDropControllers();
	}
	
	/*
	 * ABSTRACT METHODS
	 */	
	public abstract void registerDropControllers();

	
	/*
	 * GETTERS & SETTERS
	 */
	public AbsolutePanel getUspDragDropPanel() {
		return dragDropPanel;
	}

	public AbstractProject getProject() {
		return project;
	}
	public Label getTitleLabel() {
		return titleLabel;
	}
}
