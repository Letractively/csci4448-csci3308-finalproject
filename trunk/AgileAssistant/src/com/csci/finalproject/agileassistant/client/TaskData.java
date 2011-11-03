package com.csci.finalproject.agileassistant.client;

import java.io.Serializable;


public class TaskData implements Serializable {
	
	/*
	 * FIELDS
	 */
	private Long ID;
	private Long userStoryID;
	private String title;			// Title of the Task
	private int task_numb;			// is this task 1, 2, 3,....
	private int condition;			// 0=To Do, 1=In Prog, 2=In Veri, 3=Complete
	private String owner;			// Developer who owns this task
	
	/*
	 * CONSTRUCTOR
	 */
	public TaskData() {}
	public TaskData(Long iD, Long userStoryID, String title, int task_numb, int condition, String owner) {
		super();
		this.ID = iD;
		this.userStoryID = userStoryID;
		this.title = title;
		this.task_numb = task_numb;
		this.condition = condition;
		this.owner = owner;
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

	public Long getID() {
		return ID;
	}
}
