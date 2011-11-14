package com.csci.finalproject.agileassistant.server;

import java.util.LinkedList;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.csci.finalproject.agileassistant.client.TaskData;
import com.csci.finalproject.agileassistant.client.UserStoryCondition;
import com.csci.finalproject.agileassistant.client.UserStoryData;
import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class UserStory {
	/*
	 * FIELDS
	 */
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	@Persistent
	private String title;
	
	@Persistent
	private PersistentProject persistentProject;

	@Persistent(mappedBy = "userStory")
	@Element(dependent = "true")
	private List<Task> tasks;

	@Persistent
	private int points;
	
	@Persistent
	private UserStoryCondition condition;

	/*
	 * CONSTRUCTORS
	 */
	public UserStory(PersistentProject persistentProject, String title) {
		super();
		this.persistentProject = persistentProject;
		this.title = title;
		this.tasks = new LinkedList<Task>();
		this.points = 0;
		this.condition = UserStoryCondition.USP;
	}
	
	
	/*
	 * METHODS
	 */
	/**
	 * This function adds an already created task to this user
	 * story's task list
	 * 
	 * @return void
	 * @param tsk
	 */
	public Task addTask( String title ) {
		Task tsk = new Task( title, this, tasks.size() );
		tasks.add( tsk );
		return tsk;
	}
	
	public int removeTask( Long taskID ) {
		int deleteCount = 0;
		for( Task t : tasks ) {
			if( t.getKey().getId() == taskID ) {
				tasks.remove(t);
				deleteCount++;
			}
		}
		return deleteCount;
	}
	
	public UserStoryData genUserStoryData() {
		List<TaskData> taskDataList = new LinkedList<TaskData>();
		for( Task t : tasks ) {
			taskDataList.add( t.genTaskData() );
		}
		return new UserStoryData(key.getId(), title, taskDataList, points, condition);
	}
	
	public Task getTask( Long taskID ) {
		for( Task t : tasks ) {
			if( t.getKey().getId() == taskID ) {
				return t;
			}
		}
		return null;
	}
	
	public void updateUserStory( UserStoryData usd ) {
		this.title = usd.getTitle();
		this.points = usd.getPoints();
		this.condition = usd.getCondition();
	}
	
	public void updateTask( TaskData td ) {
		for( Task t : tasks ) {
			if( t.getKey().getId() == td.getID() ) {
				t.setCondition(td.getCondition());
				t.setOwner(td.getOwner());
				t.setTask_numb(td.getTask_numb());
				t.setTitle(td.getTitle());
				
				return;
			}
		}
	}

	/*
	 * GETTERS & SETTERS
	 */
	public Key getKey() {
		return key;
	}

	public String getTitle() {
		return title;
	}

	public PersistentProject getPersistentProject() {
		return persistentProject;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public UserStoryCondition getCondition() {
		return condition;
	}

	public void setCondition(UserStoryCondition condition) {
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
