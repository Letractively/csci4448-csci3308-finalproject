package com.csci.finalproject.agileassistant.client.popups;

import com.csci.finalproject.agileassistant.client.AbstractProject;
import com.csci.finalproject.agileassistant.client.Notecard;
import com.csci.finalproject.agileassistant.client.Postit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * A {@link com.google.gwt.user.client.ui.PopupPanel} that is
 * used in an {@link AbstractProject} to add a new 
 * {@link com.csci.finalproject.agileassistant.server.Task} to
 * an existing {@link com.csci.finalproject.agileassistant.server.UserStory}.
 * This also results in a new {@link Postit} being added to an 
 * existing {@link Notecard}. 
 * 
 * @author Jacob
 */
public class AddTaskPopupPanel extends PopupPanel {
	private TextBox titleTextBox;
	private AbstractProject project;
	private Long usID = null;

	public AddTaskPopupPanel( AbstractProject project ) {
		super(true);
		this.project = project;
		
		this.setGlassEnabled(true);
		
		FlowPanel flowPanel = new FlowPanel();
		setWidget(flowPanel);
		flowPanel.setSize("258px", "116px");
		
		Label lblAddTask = new Label("Add Task");
		lblAddTask.setStyleName("AddUserStoryPopupPanel-Label");
		flowPanel.add(lblAddTask);
		
		FlowPanel flowPanel_1 = new FlowPanel();
		flowPanel_1.setStyleName("AddUserStoryPopupPanel-FormRow");
		flowPanel.add(flowPanel_1);
		
		Label label_1 = new Label("Title:");
		label_1.setStyleName("AddUserStoryPopupPanel-FormRowLabel");
		flowPanel_1.add(label_1);
		
		titleTextBox = new TextBox();
		titleTextBox.setStyleName("AddUserStoryPopupPanel-FormRowTextBox");
		flowPanel_1.add(titleTextBox);
		titleTextBox.setWidth("213px");
		
		FlowPanel flowPanel_2 = new FlowPanel();
		flowPanel_2.setStyleName("AddUserStoryPopupPanel-FormRow");
		flowPanel.add(flowPanel_2);
		
		Button button = new Button("Add!");
		button.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if( getTitleTextBox().getValue() != null && getTitleTextBox().getValue() != "" ) {
					addTaskToUserStory( getTitleTextBox().getValue() );
					AddTaskPopupPanel.this.hide();
					AddTaskPopupPanel.this.usID = null;
					getTitleTextBox().setValue("");
				}
			}
		});
		flowPanel_2.add(button);
	}
	
	/*
	 * HELPER METHODS
	 */
	public void addTaskToUserStory( String title ) {
		if( this.usID == null ) {
			Window.alert( "Sorry, I was unable to determine which User Story " +
					"this task was supposed to be added to. Please try again!" );
			return;
		}
		project.addTaskToUserStory(this.usID, title);
	}
	
	/*
	 * GETTERS & SETTERS
	 */
	public Long getUsID() {
		return usID;
	}

	public void setUsID(Long usID) {
		this.usID = usID;
	}
	
	public TextBox getTitleTextBox() {
		return titleTextBox;
	}
}
