package com.csci.finalproject.agileassistant.server;

import java.util.LinkedList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.users.User;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class UserStory {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long ID;
	
	@Persistent
	private User user;
	
	@Persistent
	private String title;
	
	@Persistent
	private List<Task> tasks;
	
	@Persistent
	private int points;
	
	@Persistent
	private int condition; // 0=UserStoryPile 1=Backlog 2=Whiteboard

	public UserStory(User user, String title) {
		super();
		this.user = user;
		this.title = title;
		this.tasks = new LinkedList<Task>();
	}
	
	/**
	 * This function adds an already created task to this user
	 * story's task list
	 * 
	 * @return void
	 * @param tsk
	 */
	public void addTask( Task tsk ) {
		this.tasks.add( tsk );
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

	public int getCondition() {
		return condition;
	}

	public void setCondition(int condition) {
		this.condition = condition;
	}

	public Long getID() {
		return ID;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
}
