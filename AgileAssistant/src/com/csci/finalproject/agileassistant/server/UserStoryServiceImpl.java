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
package com.csci.finalproject.agileassistant.server;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.csci.finalproject.agileassistant.client.NotLoggedInException;
import com.csci.finalproject.agileassistant.client.Notecard;
import com.csci.finalproject.agileassistant.client.Postit;
import com.csci.finalproject.agileassistant.client.UserStoryService;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class UserStoryServiceImpl extends RemoteServiceServlet implements UserStoryService {

	private static final Logger LOG = Logger.getLogger(UserStoryServiceImpl.class.getName());
	private static final PersistenceManagerFactory PMF =
			JDOHelper.getPersistenceManagerFactory("transactions-optional");
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Notecard> getAllUserStories() throws NotLoggedInException {
		checkLoggedIn();
		PersistenceManager pm = getPersistenceManager();

		List<Notecard> notecards = new LinkedList<Notecard>();
		List<Postit> postitList = new LinkedList<Postit>();
		try {
			Query q = pm.newQuery( UserStory.class, "user == u" );
			q.declareParameters("com.google.appengine.api.users.User u");
			//q.setOrdering("createDate");

			List<UserStory> userStories = (List<UserStory>) q.execute(getUser());
			/*
			 * TODO:Once Postit and Task are defined, we need to change this so
			 * 		so that we are actually passing in a real list of postits
			 * 		to the notecard
			 */
			for (UserStory us : userStories) {
				for( Task tsk : us.getTasks() ) {
					postitList.add( new Postit() );
				}
				notecards.add( new Notecard(us.getID(), us.getTitle(), postitList, us.getPoints(), us.getCondition()) );
			}
		} finally {
			pm.close();
		}
		return notecards;
	}

	@Override
	public Notecard addUserStory(String title) throws NotLoggedInException {
		checkLoggedIn();
		PersistenceManager pm = getPersistenceManager();
		
		Notecard nc = null;
		try {
			UserStory us = new UserStory(getUser(), title);
			pm.makePersistent( us );
			nc = new Notecard(us.getID(), us.getTitle(), new LinkedList<Postit>(), us.getPoints(), us.getCondition());
		} finally {
			pm.close();
		}
		return nc;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeUserStory(Long id) throws NotLoggedInException {
		checkLoggedIn();
		PersistenceManager pm = getPersistenceManager();
		
		try {
			int deleteCount = 0;
			Query q = pm.newQuery( UserStory.class, "user == u" );
			q.declareParameters( "com.google.appengine.api.users.User u" );

			List<UserStory> stories = (List<UserStory>) q.execute(getUser());
			for (UserStory story : stories ) {
				if ( id == story.getID() ) {
					deleteCount++;
					pm.deletePersistent(story);
				}
			}
			if (deleteCount != 1) {
				LOG.log(Level.WARNING, "UserStoryServiceImpl.removeUserStory() deleted "+deleteCount+" stories...");
			}
		} finally {
			pm.close();
		}
	}
	
	/*
	 * Tasks
	 */
	@Override
	public void removeTask(Long taskID) throws NotLoggedInException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTask(Long UserStoryID, String title)
			throws NotLoggedInException {
		// TODO Auto-generated method stub
		
	}


	/*
	 * Helper Methods
	 */
	private void checkLoggedIn() throws NotLoggedInException {
		if (getUser() == null) {
			throw new NotLoggedInException("Not logged in.");
		}
	}

	private User getUser() {
		UserService userService = UserServiceFactory.getUserService();
		return userService.getCurrentUser();
	}


	/*
	 * GETTERS
	 */
	private PersistenceManager getPersistenceManager() {
		return PMF.getPersistenceManager();
	}
}
