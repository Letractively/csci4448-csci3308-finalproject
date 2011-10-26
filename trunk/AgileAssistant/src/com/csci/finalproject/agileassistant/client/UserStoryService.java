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
	public List<Notecard> getAllUserStories() throws NotLoggedInException;
	public Notecard addUserStory( String title ) throws NotLoggedInException;
	public void removeUserStory( Long id ) throws NotLoggedInException;
	
	// Tasks
	public void removeTask( Long taskID ) throws NotLoggedInException;
	public void addTask( Long UserStoryID, String title ) throws NotLoggedInException;
	
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
