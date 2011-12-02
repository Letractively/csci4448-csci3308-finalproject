package com.csci.finalproject.agileassistant.client.popups;

import com.csci.finalproject.agileassistant.client.AbstractProject;
import com.csci.finalproject.agileassistant.client.Notecard;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * A {@link com.google.gwt.user.client.ui.PopupPanel} that is
 * used in an {@link AbstractProject} to add a new 
 * {@link com.csci.finalproject.agileassistant.server.UserStory}
 * to the project. This also results in a new {@link Notecard}
 * being added to the display.
 * 
 * @author Jacob
 */
public class AddUserStoryPopupPanel extends PopupPanel {

	private AbstractProject project;
	private TextBox title_textBox;

	public AddUserStoryPopupPanel( AbstractProject project ) {
		super(true);
		this.project = project;

		this.setGlassEnabled(true);

		FlowPanel flowPanel = new FlowPanel();
		setWidget(flowPanel);
		flowPanel.setSize("258px", "116px");

		Label lblNewLabel = new Label("Add User Story");
		lblNewLabel.setStyleName("AddUserStoryPopupPanel-Label");
		flowPanel.add(lblNewLabel);

		FlowPanel horizontalPanel = new FlowPanel();
		horizontalPanel.setStyleName("AddUserStoryPopupPanel-FormRow");
		flowPanel.add(horizontalPanel);

		Label lblTitle = new Label("Title:");
		lblTitle.setStyleName("AddUserStoryPopupPanel-FormRowLabel");
		horizontalPanel.add(lblTitle);

		title_textBox = new TextBox();
		title_textBox.setStyleName("AddUserStoryPopupPanel-FormRowTextBox");
		horizontalPanel.add(title_textBox);
		title_textBox.setWidth("213px");

		FlowPanel flowPanel_1 = new FlowPanel();
		flowPanel_1.setStyleName("AddUserStoryPopupPanel-FormRow");
		flowPanel.add(flowPanel_1);

		Button btnAdd = new Button("Add!");
		btnAdd.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if( getTitle_textBox().getValue() != null && getTitle_textBox().getValue() != "" ) {
					addUserStory( getTitle_textBox().getValue() );
				}
			}
		});
		flowPanel_1.add(btnAdd);
	}

	public void addUserStory( String title ) {
		this.hide();
		getTitle_textBox().setValue("");

		project.addUserStory( title );
	}

	public TextBox getTitle_textBox() {
		return title_textBox;
	}
}
