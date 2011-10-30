package com.csci.finalproject.agileassistant.client;

import java.io.Serializable;

import com.google.gwt.user.client.ui.Composite;

public class Postit extends Composite implements Serializable {
	
	private Long userStoryID;
	private String title;
	private int task_numb; // is this task 1, 2, 3,....
	private int condition; // 0=To Do, 1=In Prog, 2=In Veri, 3=Complete
	private String owner; // Developer who owns this task

	public Postit(String title, Long storyid, int task_numb, int cond) {
		super();
		this.title = title;
		this.userStoryID = storyid;
		this.task_numb = task_numb;
		this.condition = cond;
	}

	/*
	 * GETTERS & SETTERS
	 */
	public String getTitle() {
		return title;
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
}
