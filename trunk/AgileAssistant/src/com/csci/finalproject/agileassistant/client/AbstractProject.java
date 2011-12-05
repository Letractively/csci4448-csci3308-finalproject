package com.csci.finalproject.agileassistant.client;

import java.util.LinkedList;
import java.util.List;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.csci.finalproject.agileassistant.client.popups.AbstractPermissionsPopup;
import com.csci.finalproject.agileassistant.client.popups.AddTaskPopupPanel;
import com.csci.finalproject.agileassistant.client.popups.AddUserStoryPopupPanel;
import com.csci.finalproject.agileassistant.client.rpc.UserStoryService;
import com.csci.finalproject.agileassistant.client.rpc.UserStoryServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

public abstract class AbstractProject implements HasProjectPermissions {
	/*
	 * FIELDS
	 */

	// AbstractProject values
	protected String title;
	protected Long ID;
	
	// RPC serices
	private static final UserStoryServiceAsync usrStryServ = 
			GWT.create(UserStoryService.class);
	
	// Drag Controllers
	protected final NotecardDragController dragCon_notecard = 
			new NotecardDragController(RootPanel.get(HTML_PROJECT_CONTAINER_DIV), false, this);
	protected final PickupDragController dragCon_postit = 
			new PickupDragController(RootPanel.get(HTML_PROJECT_CONTAINER_DIV), false);

	// Popups
	protected final AddUserStoryPopupPanel addUserStoryPopup = 
			new AddUserStoryPopupPanel(this);
	protected final AddTaskPopupPanel addTaskPopup = 
			new AddTaskPopupPanel(this);
	protected AbstractPermissionsPopup permissionsPopup;

	// Local variables
	protected LoginInfo loginInfo;
	protected List<Notecard> notecards;
	
	// CONSTANTS
	protected static final String HTML_PROJECT_CONTAINER_DIV = "projectContainer";

	
	/*
	 * CONSTRUCTORS
	 */
	public AbstractProject(String title, Long iD, LoginInfo loginInfo) {
		super();
		this.title = title;
		ID = iD;
		this.loginInfo = loginInfo;
		notecards = null;
		
		dragCon_notecard.setBehaviorMultipleSelection(false);
		dragCon_postit.setBehaviorMultipleSelection(false);
		
		genPermissionsLevel();
	}
	
	
	/*
	 * ABSTRACT METHODS
	 */
	/**
	 * Adds a Notecard to this project
	 * @param nc
	 */
	abstract public void addNotecard( Notecard nc );
	
	abstract public void restoreNotecard(Notecard nc);
	
	/**
	 * Adds a postit to the specified notecard in this project
	 * @param nc_ID
	 * @param postit
	 */
	abstract public void addPostitToNotecard( Long nc_ID, Postit postit );
	
	/**
	 * Launches the AbstractProject into the browser
	 */
	abstract public void launch();
	
	/**
	 * Saves the current state of the project. This is most applicable as an 
	 * error saftey in case there is an uncaught exception. This will persist
	 * anything that needs to be persisted before the program crashes.
	 */
	abstract public void saveProjectState();
	
	abstract public int unusedNotecardCount();
	
	
	/*
	 * PUBLIC METHODS
	 */
	public void popupAddUserStoryPopupPanel() {
		addUserStoryPopup.center();
	}
	
	public void popupAddTaskPopupPanel(Long usID) {
		addTaskPopup.setUsID(usID);
		addTaskPopup.center();
	}
	
	public int getNotecardIndex(Notecard nc) {
		return notecards.indexOf(nc);
	}
	
	
	/*
	 * ASYNCRONOUS RPCs
	 */
	public void addUserStory( String title ) {
		usrStryServ.addUserStory(title, new AsyncCallback<UserStoryData>() {
			public void onFailure( Throwable error ) {
				handleError( error );
				// TODO: add functionality to handle a failed RPC
			}
			public void onSuccess( UserStoryData usd ) {
				Notecard nc = usd.genNotecard( AbstractProject.this );
				notecards.add(0, nc);
				addNotecard( nc );
			}
		});
	}
	
	public void addTaskToUserStory( Long usID, String title ) {
		usrStryServ.addTask(usID, title, new AsyncCallback<TaskData>() {
			@Override
			public void onFailure(Throwable caught) {
				handleError( caught );
			}

			@Override
			public void onSuccess(TaskData td) {
				addPostitToNotecard( td.getUserStoryID(), td.genPostit() );
			}
		});
	}
	
	public void persistProject() {
		List<UserStoryData> usdList = new LinkedList<UserStoryData>();
		for( Notecard nc : notecards ) {
			usdList.add( new UserStoryData(nc) );
		}
		
		usrStryServ.persistProject(usdList, new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				handleError( caught );
			}

			@Override
			public void onSuccess(Void result) {}
		});
	}
	
	/**
	 * Updates the index of the {@link Notecard} passed in and persists the 
	 * Notecard's current state to the data base. An index of -1 will 
	 * leave the Notecard at its current location. 
	 * @param nc The notecard to be persisted.
	 * @param index The index at which the notecard should be moved to in the 
	 * projects list of notecards, or -1 to maintain its current position.
	 */
	public void persistUserStory( final Notecard nc, int index ) {
		if( index != -1 ) {
			notecards.remove(nc);
			notecards.add(index, nc);
		}

		usrStryServ.persistUserStory(new UserStoryData(nc), notecards.indexOf(nc),
				new AsyncCallback<UserStoryData>() {
			
			@Override
			public void onFailure(Throwable caught) {
				handleError( caught );
			}

			@Override
			public void onSuccess(UserStoryData usd) {
				nc.setID(usd.getID());
			}
		});
	}
	
	public void persistTask( Postit p ) {
		usrStryServ.persistTask(new TaskData(p), new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				handleError( caught );
			}

			@Override
			public void onSuccess(Void result) {}
		});
	}
	
	
	/*
	 * Private/Protected Helper functions
	 */
	protected void handleError(Throwable error) {
		Window.alert("handleError says:\n\n"+error.getMessage());
		if (error instanceof NotLoggedInException) {
			Window.Location.replace(loginInfo.getLogoutUrl());
		}
	}
	
	/**
	 * Adjusts the order of this {@link AbstractProject} object's notecard list 
	 * by moving {@link Notecard} nc from it's current position to a newIndex. 
	 * @param nc
	 * @param newIndex
	 */
	public void moveNotecard(Notecard nc, int newIndex) {
		if( notecards.indexOf(nc) != newIndex ) {
			notecards.remove(nc);
			notecards.add(newIndex, nc);
		}
	}

	/**
	 * Removes the specified {@link Notecard} from the {@AbstractProject}. It
	 * also persists this change via an asyncronous call to the server.
	 * @param nc
	 */
	public void removeNotecard(Notecard nc) {		
		usrStryServ.removeUserStory(nc.getID(), new AsyncCallback<Void>(){
			@Override
			public void onFailure(Throwable caught) {
				handleError( caught );
			}

			@Override
			public void onSuccess(Void result) {}
		});
	}


	/*
	 * GETTERS & SETTERS
	 */	
	public List<Notecard> getNotecards() {
		return notecards;
	}


	public void setNotecards(List<Notecard> notecards) {
		this.notecards = notecards;
	}


	public String getTitle() {
		return title;
	}


	public Long getID() {
		return ID;
	}


	public UserStoryServiceAsync getUsrStryServ() {
		return usrStryServ;
	}


	public PickupDragController getDragCon_notecard() {
		return dragCon_notecard;
	}


	public PickupDragController getDragCon_postit() {
		return dragCon_postit;
	}


	public AddUserStoryPopupPanel getAddUserStoryPopup() {
		return addUserStoryPopup;
	}


	public AddTaskPopupPanel getAddTaskPopup() {
		return addTaskPopup;
	}


	public LoginInfo getLoginInfo() {
		return loginInfo;
	}
}
