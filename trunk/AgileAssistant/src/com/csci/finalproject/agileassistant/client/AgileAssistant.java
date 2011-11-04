package com.csci.finalproject.agileassistant.client;

import java.util.LinkedList;
import java.util.List;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
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
	//private Anchor signOutLink = new Anchor("Sign Out");

	// RPC serices
	UserStoryServiceAsync usrStryServ = GWT.create(UserStoryService.class);

	// Drag Controllers
	private final PickupDragController dragCon_notecard = new PickupDragController(RootPanel.get(), false);
	private final PickupDragController dragCon_postit = new PickupDragController(RootPanel.get(), false);

	// Project variables
	private List<Notecard> notecards;
	private WhiteBoard wb;

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

					// Load the white board
					loadWhiteBoard();

					// Load the projects notecards
					loadProjectNotecards();

					// Load the add user story button
					loadAddUserStoryButton();
				} else {
					loadLogin();
				}
			}
		});
	}

	/*
	 * Page Loads and Component Initializations
	 */
	private void loadLogin() {
		// Assemble login panel.
		signInLink.setHref(loginInfo.getLoginUrl());
		loginPanel.add(loginLabel);
		loginPanel.add(signInLink);
		RootPanel.get().add(loginPanel);
	}

	private void loadWhiteBoard() {
		this.wb = new WhiteBoard( this );
		RootPanel.get("whiteboard").add(wb);
	}

	private void loadAddUserStoryButton() {		
		Button btnAdd = new Button("Add User Story");
		btnAdd.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				popupAddUserStoryPopupPanel();
			}
		});

		RootPanel.get("whiteboard").add(btnAdd);
	}

	/*
	 * RPC service methods
	 */
	public void loadProjectNotecards() {
		if( usrStryServ == null ) {
			usrStryServ = GWT.create(UserStoryService.class);
		}

		usrStryServ.getAllUserStories( new AsyncCallback<List<UserStoryData>>() {
			public void onFailure( Throwable error ) {
				handleError( error );
				// TODO: add functionality to handle a failed RPC
			}
			public void onSuccess( List<UserStoryData> usdList ) {
				List<Notecard> ncList = new LinkedList<Notecard>();
				for( UserStoryData usd : usdList ) {
					Notecard nc = usd.genNotecard( AgileAssistant.this );
					for( TaskData td : usd.getTaskDataList() ) {
						nc.addPostit( td.genPostit() );
					}
					ncList.add( nc );
				}
				setNotecards( ncList );
			}
		});
	}

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
				addNotecard( usd.genNotecard( AgileAssistant.this ) );
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

	public void popupAddUserStoryPopupPanel() {
		AddUserStoryPopupPanel popup = new AddUserStoryPopupPanel(this);
		popup.center();
	}

	protected void addNotecard( Notecard nc ) {
		switch( nc.getCondition() ) {
		case 0:
			// TODO: send to user story pile
		case 1:
			//TODO: send to backlog backlog
		case 2:
			// Tac the Notecard to the UserStoryColumn
			wb.getUserStoryColumn().getDragDropPanel().add( nc );
			dragCon_notecard.makeDraggable(nc, nc.getDragHandle());

			// Tac it's Postits to their appropriate Columns
			for( Postit p : nc.getPostits() ) {
				switch( p.getCondition() ) {
				case 0:
					wb.getToDoColumn().getDragDropPanel().add( p );
				case 1:
					wb.getInProgressColumn().getDragDropPanel().add( p );
				case 2:
					wb.getInVerificationColumn().getDragDropPanel().add( p );
				case 3:
					wb.getCompleteColumn().getDragDropPanel().add( p );
				}
				dragCon_postit.makeDraggable(p, p.getDragHandle());
			}
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

		for( Notecard nc : this.notecards ) {
			addNotecard( nc );
		}
	}

	public PickupDragController getDragCon_notecard() {
		return dragCon_notecard;
	}

	public PickupDragController getDragCon_postit() {
		return dragCon_postit;
	}
}
