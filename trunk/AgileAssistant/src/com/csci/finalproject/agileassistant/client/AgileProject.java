package com.csci.finalproject.agileassistant.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

public class AgileProject extends AbstractProject {
	/*
	 * LOCAL FIELDS
	 */

	// Components
	private final AbstractUserStoryPile usp = new AgileUserStoryPile( this );
	private final AbstractBacklog bl = new AgileBacklog( this );
	private final AbstractWhiteBoard wb = new AgileWhiteBoard( this );

	public AgileProject(String title, Long iD, LoginInfo loginInfo) {
		super(title, iD, loginInfo);
	}

	
	/*
	 * METHOD OVERRIDES
	 */
	@Override
	public void addNotecard(Notecard nc) {
		notecards.add(nc);
		
		switch( nc.getCondition() ) {
		case 0:
			usp.addNotecard(nc);
		case 1:
			bl.addNotecard(nc);
		case 2:
			bl.addNotecard(nc);
			wb.add(nc);
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
		Window.alert("hiya 0"); 
		RootPanel.get("projectDiv").add(wb);
		Window.alert("hiya 1");
		/*
		 * TODO:
		 * 	This needs to also load in the UserStoryPile and Backlog
		 * once we have them implemented. For right now, this is
		 * only loading the whiteboard
		 */
	}
}
