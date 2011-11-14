package com.csci.finalproject.agileassistant.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * A concrete implementation of {@link AbstractProject} that
 * is specifically for an Agile Software Development cycle. An
 * AgileProject has a {@link AgileUserStoryPile}, a 
 * {@link AgileBacklog}, and an {@link AgileWhiteBoard}.  
 * 
 * @author Jacob
 */
public class AgileProject extends AbstractProject {
	/*
	 * LOCAL FIELDS
	 */

	// Components
	protected final VerticalPanel projectContainer = new VerticalPanel();
	protected final AbstractUserStoryPile usp = new AgileUserStoryPile( this );
	protected final AbstractBacklog bl = new AgileBacklog( this );
	protected final AbstractWhiteBoard wb = new AgileWhiteBoard( this );

	public AgileProject(String title, Long iD, LoginInfo loginInfo) {
		super(title, iD, loginInfo);
		wb.registerDropControllers();
	}


	/*
	 * METHOD OVERRIDES
	 */
	@Override
	public void addNotecard(Notecard nc) {		
		// Add it to the appropriate component
		switch( nc.getCondition() ) {
			case USP:
				usp.add(nc);
				break;
			case BL:
				bl.add(nc);
				break;
			case WB:
				bl.add(nc);
				wb.add(nc);
				break;
		}

		// Make everything draggable
		dragCon_notecard.makeDraggable(nc, nc.getDragHandle());
		for( Postit p : nc.getPostits() ) {
			dragCon_postit.makeDraggable(p, p.getDragHandle());
		}
	}

	@Override
	public void addPostitToNotecard(Long nc_ID, Postit postit) {
		for( Notecard nc : notecards ) {
			if( nc.getID() == nc_ID ) {
				nc.addPostit(postit);
				if(nc.getCondition() == UserStoryCondition.WB) {
					wb.add(postit);
				}
				break;
			}
		}

		dragCon_postit.makeDraggable(postit, postit.getDragHandle());
	}

	@Override
	public void launch() {
		/*
		 * TODO:
		 * This needs to also load in the UserStoryPile and Backlog
		 * once we have them implemented.
		 */
		projectContainer.add(usp);
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
