package com.csci.finalproject.agileassistant.client;

import java.io.Serializable;

import com.csci.finalproject.agileassistant.server.Task;


public class TaskData implements Serializable {
	
	/*
	 * FIELDS
	 */
	private Long ID;
	private Long userStoryID;
	private String title;
	private int task_numb;
	private TaskCondition condition;
	private String owner;	// Developer who owns this task
	
	/*
	 * CONSTRUCTOR
	 */
	public TaskData() {}
	public TaskData(Long iD, Long userStoryID, String title, int task_numb, TaskCondition condition, String owner) {
		super();
		this.ID = iD;
		this.userStoryID = userStoryID;
		this.title = title;
		this.task_numb = task_numb;
		this.condition = condition;
		this.owner = owner;
	}
	public TaskData( Postit postit ) {
		super();
		this.ID = postit.getID();
		this.userStoryID = postit.getUserStoryID();
		this.title = postit.getTitle();
		this.task_numb = postit.getTask_numb();
		this.condition = postit.getCondition();
		this.owner = postit.getOwner();
	}
	
	/*
	 * METHODS
	 */
	public Postit genPostit() {
		return new Postit( userStoryID, ID, title, task_numb, condition, owner);
	}

	/*
	 * GETTERS & SETTERS
	 */
	public Long getUserStoryID() {
		return userStoryID;
	}

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

	public TaskCondition getCondition() {
		return condition;
	}

	public void setCondition(TaskCondition condition) {
		this.condition = condition;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Long getID() {
		return ID;
	}
}
