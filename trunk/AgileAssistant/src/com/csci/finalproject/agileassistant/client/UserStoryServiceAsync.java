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

	void addTask(Long userStoryID, String title, AsyncCallback<TaskData> callback);

	/**
	 * Asynchronous call that changes the index of a UserStory object in a 
	 * {@link com.csci.finalproject.agileassistant.server.PersistentProject} 
	 * objects userStoryList from oldIndex to newIndex.
	 * @param oldIndex
	 * @param newIndex
	 * @param callback
	 */
	void moveUserStory(int oldIndex, int newIndex, AsyncCallback<Void> callback);
	
	void persistProject(List<UserStoryData> usdList,
			AsyncCallback<Void> callback);

	void persistUserStory(UserStoryData usd, int index,
			AsyncCallback<UserStoryData> callback);

	void persistTask(TaskData td, AsyncCallback<Void> callback);
}
