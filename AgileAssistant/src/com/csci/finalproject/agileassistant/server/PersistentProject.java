package com.csci.finalproject.agileassistant.server;

import java.util.LinkedList;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class PersistentProject {
	/*
	 * FIELDS
	 */
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

	@Persistent
	private User user;

	@Persistent(mappedBy = "persistentProject")
	@Element(dependent = "true")
	private List<UserStory> userStories;

	/*
	 * CONSTRUCTOR
	 */
	public PersistentProject(User user) {
		super();
		this.user = user;
		this.userStories = new LinkedList<UserStory>();
	}
	
	/*
	 * METHODS
	 */
	public UserStory addUserStory( String title ) {
		UserStory us = new UserStory(this, title);
		userStories.add( us );
		return us;
	}
	
	public int removeUserStory( Long ID ) {
		int deleteCount = 0;
		for( UserStory us : userStories ) {
			if( us.getKey().getId() == ID ) {
				userStories.remove(us);
				deleteCount++;
			}
		}
		
		return deleteCount;
	}
	
	public Task addTask( Long userStoryID, String title ) {
		Task tsk = null;
		for( UserStory us : userStories ) {
			if( us.getKey().getId() == userStoryID ) {
				tsk = us.addTask(title);
			}
		}
		
		if( tsk == null ) {
			System.out.println( "Unable to add Task to UserStory: " +
					"UserStory not found in project!" );
		}
		return tsk;
	}
	
	public int removeTask( Long userStoryID, Long taskID ) {
		int deleteCount = 0;
		for( UserStory us : userStories ) {
			if( us.getKey().getId() == userStoryID ) {
				deleteCount += us.removeTask(taskID);
			}
		}
		
		if( deleteCount == 0 ) {
			System.out.println( "Unable to remove Task from UserStory: " +
					"UserStory not found in PersistentProject!" );
		}
		
		return deleteCount;
	}

	/*
	 * GETTERS & SETTERS
	 */
	public Key getKey() {
		return key;
	}

	public User getUser() {
		return user;
	}

	public List<UserStory> getUserStories() {
		return userStories;
	}
}
