package com.csci.finalproject.agileassistant.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserStoryServiceAsync {

	void getAllUserStories(AsyncCallback<List<UserStoryData>> callback);

	void addUserStory(String title, AsyncCallback<UserStoryData> callback);

	/**
	 * Asynchronous call that removes the UserStory with the given 'id'
	 * from the database.
	 * @param id
	 * @param callback
	 */
	void removeUserStory(Long id, AsyncCallback<Void> callback);

	void removeTask(Long userStoryID, Long taskID, AsyncCallback<Void> callback);

	void addTask(Long userStoryID, String title,
			AsyncCallback<TaskData> callback);

}
