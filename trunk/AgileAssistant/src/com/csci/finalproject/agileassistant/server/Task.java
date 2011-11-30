package com.csci.finalproject.agileassistant.server;


import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.csci.finalproject.agileassistant.client.TaskCondition;
import com.csci.finalproject.agileassistant.client.TaskData;
import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Task {
	/*
	 * FIELDS
	 */
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
	private TaskCondition condition;

	@Persistent
	private String owner;			// Developer who owns this task
	
	/*
	 * CONSTRUCTORS
	 */
	public Task(String title, UserStory userStory, int task_numb) {
		super();
		this.title = title;
		this.userStory = userStory;
		this.task_numb = task_numb;
		this.condition = TaskCondition.TO_DO;
	}
	public Task(UserStory userStory, TaskData td) {
		super();
		this.title = td.getTitle();
		this.userStory = userStory;
		this.task_numb = td.getTask_numb();
		this.condition = td.getCondition();
	}
	
	
	/*
	 * METHODS
	 */
	public TaskData genTaskData() {
		return new TaskData( key.getId(), userStory.getKey().getId(), title, task_numb, condition, owner );
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

	public UserStory getUserStory() {
		return userStory;
	}
}
