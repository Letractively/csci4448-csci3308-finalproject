package com.csci.finalproject.agileassistant.server;

import java.util.LinkedList;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.csci.finalproject.agileassistant.client.TaskData;
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
	private int condition; // 0=UserStoryPile 1=Backlog 2=Whiteboard

	/*
	 * CONSTRUCTORS
	 */
	public UserStory(PersistentProject persistentProject, String title) {
		super();
		this.persistentProject = persistentProject;
		this.title = title;
		this.tasks = new LinkedList<Task>();
		this.points = 0;
		
		// TODO: condition needs to be initialized to 0 once we have the UserStoryPile
		this.condition = 2;
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
