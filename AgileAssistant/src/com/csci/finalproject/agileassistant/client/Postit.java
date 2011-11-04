package com.csci.finalproject.agileassistant.client;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

public class Postit extends Composite {
	
	/*
	 * FIELDS
	 */
	private Long userStoryID;
	private Long ID;
	private String title;
	private int task_numb; // is this task 1, 2, 3,....
	private int condition; // 0=To Do, 1=In Prog, 2=In Veri, 3=Complete
	private String owner; // Developer who owns this task
	private Button dragHandleButton;

	/*
	 * CONSTRUCTORS
	 */
	public Postit(Long userStoryID, Long ID, String title, int task_numb, int condition, String owner) {
		super();
		this.userStoryID = userStoryID;
		this.ID = ID;
		this.title = title;
		this.task_numb = task_numb;
		this.condition = condition;
		this.owner = owner;
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setStyleName("Postit-Wrapper");
		initWidget(absolutePanel);
		absolutePanel.setSize("60px", "60px");
		
		Label lblNewLabel = new Label(this.title);
		lblNewLabel.setStyleName("Postit-TitleLabel");
		absolutePanel.add(lblNewLabel, 0, 0);
		lblNewLabel.setSize("60px", "33px");
		
		Label lblNewLabel_1 = new Label("<"+this.owner+">");
		lblNewLabel_1.setStyleName("Postit-OwnerLabel");
		absolutePanel.add(lblNewLabel_1, 0, 39);
		lblNewLabel_1.setSize("60px", "21px");
		
		dragHandleButton = new Button("");
		dragHandleButton.setText("");
		dragHandleButton.setStyleName("dragHandleButton");
		absolutePanel.add(dragHandleButton, 0, 0);
		dragHandleButton.setSize("60px", "60px");
	}

	/*
	 * GETTERS & SETTERS
	 */
	public String getTitle() {
		return title;
	}

	public Long getID() {
		return ID;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTask_numb() {
		return task_numb;
	}

	public void setTask_numb(int task_numb) {
		this.task_numb = task_numb;
	}

	public int getCondition() {
		return condition;
	}

	public void setCondition(int condition) {
		this.condition = condition;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Long getUserStoryID() {
		return userStoryID;
	}
	public Button getDragHandle() {
		return dragHandleButton;
	}
}
