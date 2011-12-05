package com.csci.finalproject.agileassistant.server;

import java.util.LinkedList;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.csci.finalproject.agileassistant.client.ProjectData;
import com.csci.finalproject.agileassistant.client.ProjectType;
import com.csci.finalproject.agileassistant.client.TaskData;
import com.csci.finalproject.agileassistant.client.UserStoryData;
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
	private String title;

	@Persistent
	private ProjectType projectType;

	@Persistent
	private User user;

	@Persistent(mappedBy = "persistentProject")
	@Element(dependent = "true")
	private List<UserStory> userStories;

	/*
	 * CONSTRUCTOR
	 */
	public PersistentProject(User user, String title, ProjectType projectType) {
		super();
		this.user = user;
		this.title = title;
		this.projectType = projectType;
		this.userStories = new LinkedList<UserStory>();
	}

	/*
	 * METHODS
	 */
	public UserStory addUserStory( String title ) {
		UserStory us = new UserStory(this, title);
		userStories.add(0, us);
		return us;
	}

	public UserStory addUserStory( UserStoryData usd, int index ) {
		UserStory us = new UserStory(this, usd);
		
		if(index >= userStories.size()) {
			userStories.add(us);
		} else {
			userStories.add(index, us);
		}
		
		return us;
	}

	/**
	 * Searches for a {@link UserStory} with the specified ID and returns it.
	 * Returns null if no {@link UserStory} was found with this ID.
	 * @param id The ID of the {@link UserStory} to be searched for
	 * @return The {@link UserStory} with the specified id or null if there was
	 * not a {@link UserStory} with that ID
	 */
	public UserStory getUserStory( Long id ) {
		UserStory userStory = null;
		for( UserStory us : userStories ) {
			if( us.getKey().getId() == id ) {
				userStory = us;
			}
		}
		return userStory;
	}

	public boolean updateUserStory( UserStoryData usd, int index ) {
		for( UserStory us : userStories ) {
			if( us.getKey().getId() == usd.getID() ) {
				if( userStories.indexOf(us) == index ) {
					us.updateUserStory(usd);
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	public int removeUserStory( Long ID ) {
		List<UserStory> toDelete = new LinkedList<UserStory>();

		for( UserStory us : userStories ) {
			if( us.getKey().getId() == ID ) {
				toDelete.add(us);
			}
		}

		for( UserStory usDel : toDelete ) {
			userStories.remove(usDel);
		}
		
		return toDelete.size();
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

	public void updateTask( TaskData td ) {
		for( UserStory us : userStories ) {
			if( us.getKey().getId() == td.getUserStoryID() ) {
				us.updateTask(td);
				return;
			}
		}
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

	public void moveUserStory(int oldIndex, int newIndex) {
		if( oldIndex >= userStories.size() || newIndex >= userStories.size() ) 
			return;

		userStories.remove(oldIndex);
	}

	public ProjectData genProjectData() {
		/* There is some bug that if PersistentProject is persisted with
		 * no UserStory objects in its userStories list, the list goes
		 * to null. So we have to check and make sure that it is not null
		 */
		if( userStories == null ) {
			userStories = new LinkedList<UserStory>();
		}
		List<UserStoryData> usdList = new LinkedList<UserStoryData>();

		if( userStories.size() > 0 ) {
			for( UserStory us : userStories ) {
				usdList.add(us.genUserStoryData());
			}	
		}

		return new ProjectData(key.getId(), projectType, title, usdList);
	}

	public void printOutOrder() {
		System.out.println("User Story Order:");
		for(UserStory usa : userStories) {
			System.out.println(usa.getTitle());
		}
		System.out.println("\n");
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ProjectType getProjectType() {
		return projectType;
	}

	public List<UserStory> getUserStories() {
		return userStories;
	}

	public void setUserStories(List<UserStory> userStories) {
		this.userStories = userStories;
	}
}
