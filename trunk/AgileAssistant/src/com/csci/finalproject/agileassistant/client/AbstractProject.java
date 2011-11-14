package com.csci.finalproject.agileassistant.client;

import java.util.LinkedList;
import java.util.List;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

public abstract class AbstractProject {
	/*
	 * FIELDS
	 */
	// Abstract Project values
	protected String title;
	protected Long ID;
	
	// RPC serices
	private static final UserStoryServiceAsync usrStryServ = 
			GWT.create(UserStoryService.class);
	
	// Drag Controllers
	protected final PickupDragController dragCon_notecard = 
			new PickupDragController(RootPanel.get("projectDiv"), false);
	protected final PickupDragController dragCon_postit = 
			new PickupDragController(RootPanel.get("projectDiv"), false);

	// Popups
	protected final AddUserStoryPopupPanel addUserStoryPopup = 
			new AddUserStoryPopupPanel(this);
	protected final AddTaskPopupPanel addTaskPopup = 
			new AddTaskPopupPanel(this);

	// Local variables
	protected LoginInfo loginInfo;
	protected List<Notecard> notecards;

	
	/*
	 * CONSTRUCTORS
	 */
	public AbstractProject(String title, Long iD, LoginInfo loginInfo) {
		super();
		this.title = title;
		ID = iD;
		this.loginInfo = loginInfo;
		notecards = null;
	}
	
	
	/*
	 * ABSTRACT METHODS
	 */
	
	/**
	 * Adds a Notecard to this project
	 * @param nc
	 */
	abstract public void addNotecard( Notecard nc );
	
	/**
	 * Adds a postit to the specified notecard in this project
	 * @param nc_ID
	 * @param postit
	 */
	abstract public void addPostitToNotecard( Long nc_ID, Postit postit );
	
	/**
	 * Launches the project into the browser
	 */
	abstract public void launch();
	
	
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
				notecards.add(nc);
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
	
	public void persistUserStory( Notecard nc ) {
		usrStryServ.persistUserStory(new UserStoryData(nc), 
				new AsyncCallback<Void>() {
			
			@Override
			public void onFailure(Throwable caught) {
				handleError( caught );
			}

			@Override
			public void onSuccess(Void result) {}
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
