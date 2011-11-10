package com.csci.finalproject.agileassistant.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class AgileProject extends AbstractProject {
	/*
	 * LOCAL FIELDS
	 */

	// Components
	protected final AbstractUserStoryPile usp = new AgileUserStoryPile( this );
	protected final AbstractBacklog bl = new AgileBacklog( this );
	protected final AbstractWhiteBoard wb = new AgileWhiteBoard( this );

	public AgileProject(String title, Long iD, LoginInfo loginInfo) {
		super(title, iD, loginInfo);
	}


	/*
	 * METHOD OVERRIDES
	 */
	@Override
	public void addNotecard(Notecard nc) {
		switch( nc.getCondition() ) {
			case 1:
				bl.addNotecard(nc);
				break;
			case 2:
				bl.addNotecard(nc);
				wb.add(nc);
				break;
			default:
				usp.addNotecard(nc);
		}

		dragCon_notecard.makeDraggable(nc, nc.getDragHandle());
		for( Postit p : nc.getPostits() ) {
			dragCon_postit.makeDraggable(p, p.getDragHandle());
		}
	}

	@Override
	public void addPostitToNotecard(Long nc_ID, Postit postit) {
		Window.alert("Postit condition:\t"+postit.getCondition());
		for( Notecard nc : notecards ) {
			if( nc.getID() == nc_ID ) {
				dragCon_postit.makeDraggable(postit);
				nc.addPostit(postit);
				if(nc.getCondition() == 2) {
					wb.add(postit);
				}
			}
		}
	}

	@Override
	public void launch() {
		/*
		 * TODO:
		 * 	This needs to also load in the UserStoryPile and Backlog
		 * once we have them implemented. For right now, this is
		 * only loading the whiteboard
		 */
		VerticalPanel projectContainer = new VerticalPanel();
		Button addUserStoryButton = new Button("Add User Story");
		addUserStoryButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addUserStoryPopup.center();	
			}
		});

		projectContainer.add(addUserStoryButton);
		projectContainer.add(wb);
		RootPanel.get("projectDiv").add(projectContainer);
		
		// Add all the notecards
		if( notecards != null ) {
			for( Notecard nc : notecards ) {
				addNotecard( nc );
			}
		}
	}
}
