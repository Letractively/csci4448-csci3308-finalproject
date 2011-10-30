package com.csci.finalproject.agileassistant.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AgileAssistant implements EntryPoint {
	// Google Login/Logout stuff
	private LoginInfo loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label("Please sign in to your Google Account to access your Project Board.");
	private Anchor signInLink = new Anchor("Sign In");
	private Anchor signOutLink = new Anchor("Sign Out");
	
	// RPC serices
	UserStoryServiceAsync usrStryServ = GWT.create(UserStoryService.class);
	
	// Project variables
	private List<Notecard> notecards;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// Check login status using login service.
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
			public void onFailure(Throwable error) {}
			public void onSuccess(LoginInfo result) {
				loginInfo = result;
				if(loginInfo.isLoggedIn()) {
					/*
					 * This is where we will have the logic to load an appropriate page
					 * aka. do we load the white board page, the backlog page, the new
					 * project page...
					 */
					
					// Load the projects notecards
					loadProjectNotecards();
					
					// Load the white board
					loadWhiteBoard();
				} else {
					loadLogin();
				}
			}
		});
	}
	
	/*
	 * Page Loads
	 */
	private void loadLogin() {
		// Assemble login panel.
		signInLink.setHref(loginInfo.getLoginUrl());
		loginPanel.add(loginLabel);
		loginPanel.add(signInLink);
		RootPanel.get().add(loginPanel);
	}
	
	private void loadWhiteBoard() {
		WhiteBoard wb = new WhiteBoard();
		RootPanel.get("whiteboard").add(wb);
	}
	/*
	 * RPC service methods
	 */
	public void loadProjectNotecards() {
		if( usrStryServ == null ) {
			usrStryServ = GWT.create(UserStoryService.class);
		}
		
		usrStryServ.getAllUserStories( new AsyncCallback<List<Notecard>>() {
			public void onFailure( Throwable error ) {
				handleError( error );
				// TODO: add functionality to handle a failed RPC call
			}
			public void onSuccess( List<Notecard> ncList ) {
				setNotecards( ncList );
				Window.alert("You have successfully logged in and you have loaded "
						+ notecards.size() + " notecards!");
			}
		});
	}
	
	/*
	 * Helper functions
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
}
