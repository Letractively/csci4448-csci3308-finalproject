package com.csci.finalproject.agileassistant.client;

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
	UserStoryServiceAsync usrStryServ = 
			GWT.create(UserStoryService.class);
	
	// Drag Controllers
	protected final PickupDragController dragCon_notecard = 
			new PickupDragController(RootPanel.get(), false);
	protected final PickupDragController dragCon_postit = 
			new PickupDragController(RootPanel.get(), false);

	// Popups
	/*private final AddUserStoryPopupPanel addUserStoryPopup = 
			new AddUserStoryPopupPanel(this);
	private final AddTaskPopupPanel addTaskPopup = 
			new AddTaskPopupPanel(this);
*/
	// Local variables
	protected LoginInfo loginInfo;
	protected List<Notecard> notecards;

	
	/*
	 * CONSTRUCTORS
	 */
	public AbstractProject(String title, Long iD, LoginInfo loginInfo, 
			List<Notecard> notecards) {
		super();
		this.title = title;
		ID = iD;
		this.loginInfo = loginInfo;
		this.notecards = notecards;
	}
	
	
	/*
	 * PUBLIC METHODS
	 */
	// Abstract public methods
	abstract public void addNotecard( Notecard nc );
	abstract public void addPostitToNotecard( Long usID, Postit postit );
	
	// General public methods
	public void popupAddUserStoryPopupPanel() {
		//addUserStoryPopup.center();
	}
	
	public void popupAddTaskPopupPanel(Long usID) {
		//addTaskPopup.setUsID(usID);
		//addTaskPopup.center();
	}
	
	
	/*
	 * ASYNCRONOUS RPCs
	 */
	public void addUserStory( String title ) {
		if( usrStryServ == null ) {
			usrStryServ = GWT.create(UserStoryService.class);
		}

		usrStryServ.addUserStory(title, new AsyncCallback<UserStoryData>() {
			public void onFailure( Throwable error ) {
				handleError( error );
				// TODO: add functionality to handle a failed RPC
			}
			public void onSuccess( UserStoryData usd ) {
				//addNotecard( usd.genNotecard( AbstractProject.this ) );
			}
		});
	}
	
	public void addTaskToUserStory( Long usID, String title ) {
		if( usrStryServ == null ) {
			usrStryServ = GWT.create(UserStoryService.class);
		}

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
	
	
	/*
	 * Private/Protected Helper functions
	 */
	protected void handleError(Throwable error) {
		Window.alert("handleError says:\n\n"+error.getMessage());
		if (error instanceof NotLoggedInException) {
			Window.Location.replace(loginInfo.getLogoutUrl());
		}
	}
}
