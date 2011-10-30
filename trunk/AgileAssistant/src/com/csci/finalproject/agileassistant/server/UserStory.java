package com.csci.finalproject.agileassistant.server;

import java.util.LinkedList;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class UserStory {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

	@Persistent
	private User user;
	
	@Persistent
	private String title;

	@Persistent(mappedBy = "userStory")
	@Element(dependent = "true")
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
	public void addTask( String title ) {
		this.tasks.add( new Task( title, this, tasks.size() ) );
	}
	
	public void removeTask( int taskNum ) {
		tasks.remove(taskNum - 1);
	}

	/*
	 * GETTERS & SETTERS
	 */
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getTitle() {
		return title;
	}

	public User getUser() {
		return user;
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
