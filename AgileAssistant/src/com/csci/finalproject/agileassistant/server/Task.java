package com.csci.finalproject.agileassistant.server;


import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Task {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;				// DataStore assigned key to identify this Task
	
	@Persistent
	private UserStory userStory;	// User Story this task belongs to
	
	@Persistent
	private String title;			// Title of the Task

	@Persistent
	private int task_numb;			// is this task 1, 2, 3,....
	
	@Persistent
	private int condition;			// 0=To Do, 1=In Prog, 2=In Veri, 3=Complete

	@Persistent
	private String owner;			// Developer who owns this task
	
	public Task(String title, UserStory userStory, int task_numb) {
		super();
		this.title = title;
		this.userStory = userStory;
		this.task_numb = task_numb;
	}

	/*
	 * GETTERS & SETTERS
	 */
	public String getTitle() {
		return title;
	}

	public Key getKey() {
		return key;
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

	public UserStory getUserStory() {
		return userStory;
	}
}
