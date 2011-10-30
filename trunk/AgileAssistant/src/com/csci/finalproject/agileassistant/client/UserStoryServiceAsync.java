package com.csci.finalproject.agileassistant.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserStoryServiceAsync {

	/**
	 * Asynchronous call returning a list of Notecards which represent all
	 * of the UserStories for the currently logged in user. 
	 * @param callback
	 */
	void getAllUserStories(AsyncCallback<List<Notecard>> callback);

	/**
	 * Asynchronous call that adds a UserStory with the specified 'title'
	 * and returns a Notecard to represent it.
	 * @param title
	 * @param callback
	 */
	void addUserStory(String title, AsyncCallback<Notecard> callback);

	/**
	 * Asynchronous call that removes the UserStory with the given 'id'
	 * from the database.
	 * @param id
	 * @param callback
	 */
	void removeUserStory(Long id, AsyncCallback<Void> callback);

	void removeTask(Long UserStoryID, int taskNum, AsyncCallback<Void> callback);

	/**
	 * Asynchronous call that adds a task with the specified 'title' to the
	 * UserStory specified by 'UserStoryID'
	 * @param UserStoryID
	 * @param title
	 * @param callback
	 */
	void addTask(Long UserStoryID, String title, AsyncCallback<Void> callback);

}
