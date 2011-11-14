package com.csci.finalproject.agileassistant.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserStoryServiceAsync {

	void loadProjectData(AsyncCallback<ProjectData> callback);

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

	void persistProject(List<UserStoryData> usdList,
			AsyncCallback<Void> callback);

	void persistUserStory(UserStoryData usd, AsyncCallback<Void> callback);

	void persistTask(TaskData td, AsyncCallback<Void> callback);

}
