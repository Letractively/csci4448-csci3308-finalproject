package com.csci.finalproject.agileassistant.server;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.users.User;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Task {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private long ID;
	
	@Persistent
	private String title;
	
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

	public long getID() {
		return ID;
	}

	public int getTask_numb() {
		return task_numb;
	}

	public User getUser() {
		return user;
	}

	@Persistent
	private int task_numb; //is this task 1, 2, 3,....
	
	@Persistent
	private int condition; // 0=To Do 1=In Progress 2=In Verification 3= Complete

	@Persistent
	private User user;
	
	public Task(User user, String title,int condition,int task_numb) {
		super();
		this.user = user;
		this.title = title;
		this.condition = condition;
		this.task_numb = task_numb;
		
	}
	
}
