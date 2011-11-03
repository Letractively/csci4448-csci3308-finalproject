package com.csci.finalproject.agileassistant.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;

public class AddUserStoryPopupPanel extends PopupPanel {

	private AgileAssistant assistant;
	private TextBox title_textBox;

	public AddUserStoryPopupPanel( AgileAssistant aa ) {
		super(true);
		this.assistant = aa;

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

		assistant.addUserStory( title );
	}

	public TextBox getTitle_textBox() {
		return title_textBox;
	}
}
