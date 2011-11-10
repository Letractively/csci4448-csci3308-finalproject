package com.csci.finalproject.agileassistant.client;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickHandler;

public class UserStoryData implements Serializable {
    private Long ID;
	private String title;
	private List<TaskData> taskDataList;
	private int points;
	private int condition; // 0=UserStoryPile 1=Backlog 2=Whiteboard
	
	/*
	 * CONSTRUCTORS
	 */
	public UserStoryData() {}
	public UserStoryData(Long iD, String title, List<TaskData> taskDataList, int points, int condition) {
		super();
		ID = iD;
		this.title = title;
		this.taskDataList = taskDataList;
		this.points = points;
		this.condition = condition;
	}
	public UserStoryData(Long iD, String title, int points, int condition) {
		super();
		ID = iD;
		this.title = title;
		this.points = points;
		this.condition = condition;
		this.taskDataList = new LinkedList<TaskData>();
	}
	
	/*
	 * METHODS
	 */
	public void addTaskData( TaskData td ) {
		taskDataList.add(td);
	}
	public void addTaskData( Long id, Long userStoryID, String title, int task_numb, int condition, String owner ) {
		taskDataList.add(new TaskData(id, userStoryID, title, task_numb, condition, owner) );
	}
	
	public Notecard genNotecard( AbstractProject project ) {
		Notecard nc = new Notecard( ID, title, points, condition, project );
		for( TaskData td : taskDataList ) {
			nc.addPostit(new Postit( nc.getID(), td.getID(), td.getTitle(), td.getTask_numb(), td.getCondition(), td.getOwner() ));
		}
		return nc;
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

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
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

	public List<TaskData> getTaskDataList() {
		return taskDataList;
	}
}
