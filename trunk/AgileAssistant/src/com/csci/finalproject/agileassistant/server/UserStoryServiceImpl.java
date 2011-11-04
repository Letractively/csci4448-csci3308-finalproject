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
import com.csci.finalproject.agileassistant.client.TaskData;
import com.csci.finalproject.agileassistant.client.UserStoryData;
import com.csci.finalproject.agileassistant.client.UserStoryService;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class UserStoryServiceImpl extends RemoteServiceServlet implements UserStoryService {

	/*
	 * FIELDS
	 */
	// Persistence
	private static final Logger LOG = Logger.getLogger(UserStoryServiceImpl.class.getName());
	private static final PersistenceManagerFactory PMF =
			JDOHelper.getPersistenceManagerFactory("transactions-optional");
	
	/*
	 * METHODS
	 */
	@Override
	public List<UserStoryData> getAllUserStories() throws NotLoggedInException {
		checkLoggedIn();
		PersistenceManager pm = getPersistenceManager();

		List<UserStoryData> usdList = new LinkedList<UserStoryData>();
		try {
			PersistentProject pp = getPersistentProject( pm );
			
			for( UserStory us : pp.getUserStories() ) {
				usdList.add( us.genUserStoryData() );
			}
		} finally {
			pm.close();
		}
		return usdList;
	}

	@Override
	public UserStoryData addUserStory(String title) throws NotLoggedInException {
		checkLoggedIn();
		PersistenceManager pm = getPersistenceManager();
		pm.currentTransaction().begin();

		UserStoryData usd = null;
		try {
			PersistentProject pp = getPersistentProject( pm );
			
			usd = pp.addUserStory(title).genUserStoryData();
			
			pm.currentTransaction().commit();
			
		} catch (Throwable error) {
			// rollback in case of errors
			pm.currentTransaction().rollback();
			error.printStackTrace();
			
		} finally {
			pm.close();
		}
		
		return usd;
	}

	@Override
	public void removeUserStory(Long id) throws NotLoggedInException {
		checkLoggedIn();
		PersistenceManager pm = getPersistenceManager();
		pm.currentTransaction().begin();

		try {
			int deleteCount = 0;
			PersistentProject pp = getPersistentProject( pm );
			
			deleteCount = pp.removeUserStory(id);
			if (deleteCount != 1) {
				LOG.log(Level.WARNING, "UserStoryServiceImpl.removeUserStory() " +
						"deleted "+deleteCount+" stories...");
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
	@Override
	public void removeTask(Long userStoryID, Long taskID) throws NotLoggedInException {
		checkLoggedIn();
		PersistenceManager pm = getPersistenceManager();
		pm.currentTransaction().begin();

		try {
			int deleteCount = 0;
			PersistentProject pp = getPersistentProject( pm );

			deleteCount = pp.removeTask(userStoryID, taskID);
			if (deleteCount != 1) {
				LOG.log(Level.WARNING, "UserStoryServiceImpl.removeTask() " +
						"deleted "+deleteCount+" tasks...");
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

	@Override
	public TaskData addTask(Long userStoryID, String title) throws NotLoggedInException {
		checkLoggedIn();
		PersistenceManager pm = getPersistenceManager();
		pm.currentTransaction().begin();

		TaskData td = null;
		try {
			PersistentProject pp = getPersistentProject( pm );

			td = pp.addTask(userStoryID, title).genTaskData();

			pm.currentTransaction().commit();
			
		} catch (Throwable error) {
			// rollback in case of errors
			pm.currentTransaction().rollback();
			error.printStackTrace();
			
		} finally {
			pm.close();
		}
		
		return td;
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

	@SuppressWarnings("unchecked")
	private PersistentProject getPersistentProject( PersistenceManager pm ) {
		PersistentProject pp = null;
		
		Query q = pm.newQuery( PersistentProject.class, "user == u" );
		q.declareParameters("com.google.appengine.api.users.User u");
		List<PersistentProject> ppList = (List<PersistentProject>) q.execute(getUser());
		
		if( ppList.size() == 0 ) {
			pp = new PersistentProject( getUser(), "New Project", "agile" );
			pm.makePersistent( pp );
		} else {
			pp = ppList.get(0); 
		}
		
		return pp;
	}


	/*
	 * GETTERS & SETTERS
	 */
	private PersistenceManager getPersistenceManager() {
		return PMF.getPersistenceManager();
	}
}
