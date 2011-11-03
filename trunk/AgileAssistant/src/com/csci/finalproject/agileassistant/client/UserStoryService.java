/*
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.csci.finalproject.agileassistant.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("UserStoryService")
public interface UserStoryService extends RemoteService {
	
	// User Stories
	/**
	 * Asynchronous call returning a list of Notecards which represent all
	 * of the UserStories for the currently logged in user. 
	 * @return List< UserStories >
	 * @throws NotLoggedInException
	 */
	public List<UserStoryData> getAllUserStories() throws NotLoggedInException;
	
	/**
	 * Asynchronous call that adds a UserStory and returns a Notecard to
	 * represent it.
	 * @param title
	 * @return Notecard
	 * @throws NotLoggedInException
	 */
	public UserStoryData addUserStory( String title ) throws NotLoggedInException;
	
	/**
	 * Asynchronous call that removes the UserStory with the given 'id'
	 * from the database.
	 * @param id
	 * @throws NotLoggedInException
	 */
	public void removeUserStory( Long id ) throws NotLoggedInException;
	
	/*
	 * Tasks
	 */
	/**
	 * Asynchronous call that removes a task specified by 'taskID' from
	 * a UserStory specified by 'UserStoryID'
	 * @param UserStoryID
	 * @param taskID
	 * @throws NotLoggedInException
	 */
	public void removeTask( Long userStoryID, Long taskID ) throws NotLoggedInException;
	
	/**
	 * Asynchronous call that adds a task with the specified 'title' to the
	 * UserStory specified by 'UserStoryID'
	 * @param UserStoryID
	 * @param title
	 * @throws NotLoggedInException
	 */
	public TaskData addTask( Long userStoryID, String title ) throws NotLoggedInException;
	
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static UserStoryServiceAsync instance;
		public static UserStoryServiceAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(UserStoryService.class);
			}
			return instance;
		}
	}
}
