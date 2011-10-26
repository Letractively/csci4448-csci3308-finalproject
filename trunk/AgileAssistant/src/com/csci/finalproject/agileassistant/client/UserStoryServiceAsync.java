package com.csci.finalproject.agileassistant.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserStoryServiceAsync {

	void getAllUserStories(AsyncCallback<List<Notecard>> callback);

	void addUserStory(String title, AsyncCallback<Notecard> callback);

	void removeUserStory(Long id, AsyncCallback<Void> callback);

	void removeTask(Long taskID, AsyncCallback<Void> callback);

	void addTask(Long UserStoryID, String title, AsyncCallback<Void> callback);

}
