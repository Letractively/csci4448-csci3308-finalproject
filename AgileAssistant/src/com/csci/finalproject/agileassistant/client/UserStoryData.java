package com.csci.finalproject.agileassistant.client;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class UserStoryData implements Serializable {
	private Long ID;
	private String title;
	private List<TaskData> taskDataList;
	private int points;
	private UserStoryCondition condition;

	/*
	 * CONSTRUCTORS
	 */
	public UserStoryData() {}
	public UserStoryData(Long iD, String title, List<TaskData> taskDataList, 
			int points, UserStoryCondition condition) {
		
		super();
		ID = iD;
		this.title = title;
		this.taskDataList = taskDataList;
		this.points = points;
		this.condition = condition;
	}
	public UserStoryData(Long iD, String title, int points, 
			UserStoryCondition condition) {
		
		super();
		ID = iD;
		this.title = title;
		this.points = points;
		this.condition = condition;
		this.taskDataList = new LinkedList<TaskData>();
	}
	public UserStoryData( Notecard nc ) {
		super();

		List<TaskData> tdList = new LinkedList<TaskData>();
		for( Postit p : nc.getPostits() ) {
			tdList.add( new TaskData(p) );
		}

		ID = nc.getID();
		this.title = nc.getStoryTitle();
		this.taskDataList = tdList;
		this.points = nc.getPoints();
		this.condition = nc.getCondition();
	}


	/*
	 * METHODS
	 */
	public void addTaskData( TaskData td ) {
		taskDataList.add(td);
	}
	public void addTaskData( Long id, Long userStoryID, String title, 
			int task_numb, TaskCondition condition, String owner ) {
		
		taskDataList.add(new TaskData(id, userStoryID, title, task_numb, 
				condition, owner) );
	}

	public Notecard genNotecard( AbstractProject project ) {
		Notecard nc = new Notecard( ID, title, points, condition, project );
		
		for( TaskData td : taskDataList ) {
			nc.addPostit(new Postit( nc.getID(), td.getID(), td.getTitle(), 
					td.getTask_numb(), td.getCondition(), td.getOwner() ));
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

	public UserStoryCondition getCondition() {
		return condition;
	}

	public void setCondition(UserStoryCondition condition) {
		this.condition = condition;
	}

	public Long getID() {
		return ID;
	}

	public List<TaskData> getTaskDataList() {
		return taskDataList;
	}
}
