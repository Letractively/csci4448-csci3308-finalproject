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
		try {
			Query q = pm.newQuery( UserStory.class, "user == u" );
			q.declareParameters("com.google.appengine.api.users.User u");
			//q.setOrdering("createDate");

			List<UserStory> userStories = (List<UserStory>) q.execute(getUser());
			for (UserStory us : userStories) {
				Notecard nc = new Notecard(us.getKey().getId(), us.getTitle(), us.getPoints(), us.getCondition());
				
				for( Task tsk : us.getTasks() ) {
					nc.addPostit(tsk.getTitle(), tsk.getTask_numb(), tsk.getCondition());
				}
				
				notecards.add( nc );
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
			nc = new Notecard(us.getKey().getId(), us.getTitle(), us.getPoints(), us.getCondition());
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
		pm.currentTransaction().begin();

		try {
			int deleteCount = 0;
			Query q = pm.newQuery( UserStory.class, "user == u" );
			q.declareParameters( "com.google.appengine.api.users.User u" );

			List<UserStory> stories = (List<UserStory>) q.execute(getUser());
			for (UserStory us : stories ) {
				if ( id == us.getKey().getId() ) {
					deleteCount++;
					pm.deletePersistent(us);
				}
			}
			if (deleteCount != 1) {
				LOG.log(Level.WARNING, "UserStoryServiceImpl.removeUserStory() deleted "+deleteCount+" stories...");
			}
			
			pm.currentTransaction().commit();
			
		} catch (Throwable error) {
			// rollback in case of errors
			pm.currentTransaction().rollback();
			error.printStackTrace();
			
		} finally {
			pm.close();
		}
	}

	/*
	 * Tasks
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void removeTask(Long UserStoryID, int taskNum) throws NotLoggedInException {
		checkLoggedIn();
		PersistenceManager pm = getPersistenceManager();
		pm.currentTransaction().begin();

		try {
			Query q = pm.newQuery( UserStory.class, "user == u && ID == " + UserStoryID );
			q.declareParameters("com.google.appengine.api.users.User u");

			List<UserStory> userStories = (List<UserStory>) q.execute(getUser());
			UserStory us = (UserStory) userStories.iterator().next();

			if (us == null) {
				System.out.println("UserStoryServieImpl.addTask() did not find a matching instance...");
				pm.currentTransaction().rollback();
				return;
			}

			us.removeTask(taskNum);

			pm.currentTransaction().commit();
			
		} catch (Throwable error) {
			// rollback in case of errors
			pm.currentTransaction().rollback();
			error.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void addTask(Long UserStoryID, String title) throws NotLoggedInException {
		checkLoggedIn();
		PersistenceManager pm = getPersistenceManager();
		pm.currentTransaction().begin();

		try {
			Query q = pm.newQuery( UserStory.class, "user == u && ID == " + UserStoryID );
			q.declareParameters("com.google.appengine.api.users.User u");

			List<UserStory> userStories = (List<UserStory>) q.execute(getUser());
			UserStory us = (UserStory) userStories.iterator().next();

			if (us == null) {
				System.out.println("UserStoryServieImpl.addTask() did not find a matching instance...");
				pm.currentTransaction().rollback();
				return;
			}

			us.addTask( title );

			pm.currentTransaction().commit();
			
		} catch (Throwable error) {
			// rollback in case of errors
			pm.currentTransaction().rollback();
			error.printStackTrace();
			
		} finally {
			pm.close();
		}

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
