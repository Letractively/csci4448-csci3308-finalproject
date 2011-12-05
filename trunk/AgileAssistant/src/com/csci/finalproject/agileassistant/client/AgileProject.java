package com.csci.finalproject.agileassistant.client;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.csci.finalproject.agileassistant.client.Backlog.AbstractBacklog;
import com.csci.finalproject.agileassistant.client.Backlog.AgileBacklog;
import com.csci.finalproject.agileassistant.client.Backlog.BacklogDropController;
import com.csci.finalproject.agileassistant.client.UserStoryPile.AbstractUserStoryPile;
import com.csci.finalproject.agileassistant.client.UserStoryPile.AgileUserStoryPile;
import com.csci.finalproject.agileassistant.client.UserStoryPile.UserStoryPileDropController;
import com.csci.finalproject.agileassistant.client.WhiteBoard.AbstractWhiteBoard;
import com.csci.finalproject.agileassistant.client.WhiteBoard.AgileWhiteBoard;
import com.csci.finalproject.agileassistant.client.WhiteBoard.WhiteBoardDropController;
import com.csci.finalproject.agileassistant.client.popups.AgilePermissionsPopup;
import com.google.gwt.user.client.ui.RootPanel;

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
	private PermissionType_Agile permissionsLevel;


	// Components
	protected AbstractUserStoryPile usp;
	protected AbstractBacklog bl;
	protected AbstractWhiteBoard wb;

	public AgileProject(String title, Long iD, LoginInfo loginInfo) {
		super(title, iD, loginInfo);

		usp = new AgileUserStoryPile( this );
		bl = new AgileBacklog( this );
		wb = new AgileWhiteBoard( this );

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
	public void restoreNotecard(Notecard nc) {
		switch(nc.getCondition()) {
		case USP:
			break;
		case BL:
			bl.add(nc);
			break;
		case WB:
			break;
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
		ProjectDisplay_Agile disp = new ProjectDisplay_Agile(this, usp, bl, wb);

		RootPanel.get(HTML_PROJECT_CONTAINER_DIV).add(disp);

		// Add all the notecards
		if( notecards != null ) {
			for( Notecard nc : notecards ) {
				addNotecard( nc );
			}
		}
	}

	@Override
	public void saveProjectState() {
		dragCon_notecard.cancelDrag();
	}

	@Override
	public void persistUserStory( final Notecard nc, int index ) {
		if( index != -1 ) {
			index += usp.count();
		}

		super.persistUserStory(nc, index);
	}

	@Override
	public int getNotecardIndex(Notecard nc) {
		int index = notecards.indexOf(nc);
		
		for(int i=(index-1); i>=0; i--) {
			if( notecards.get(i).getCondition() == UserStoryCondition.WB ) 
				index--;
		}
		index -= usp.count();

		return index;
	}
	
	@Override
	public int unusedNotecardCount() {
		return usp.count();
	}


	/* 
	 * Implementations for HasProjectPermissions
	 */
	@Override
	public void genPermissionsLevel() {
		if( permissionsPopup == null ) {
			permissionsPopup = new AgilePermissionsPopup(this);
		}

		permissionsPopup.center();
	}

	@Override
	public boolean moveIsPermissable(DragContext context) {
		boolean isPermissable = false;

		if( context.draggable instanceof Notecard) {
			Notecard nc = (Notecard) context.draggable;
			DropController dropCon = context.dropController;

			switch(permissionsLevel) {
			case SCRUM_MASTER:
				/*
				 * SCRUM_MASTER is allowed to move Notecard objects from the USP to 
				 * the BL, from the BL to the USP, and move things around in the BL
				 * and USP.
				 */
				if( (nc.getCondition() == UserStoryCondition.USP 
				&& dropCon.getClass() == BacklogDropController.class)

				|| (nc.getCondition() == UserStoryCondition.USP
				&& dropCon.getClass() == UserStoryPileDropController.class)

				|| (nc.getCondition() == UserStoryCondition.BL
				&& dropCon.getClass() == BacklogDropController.class)

				|| (nc.getCondition() == UserStoryCondition.BL
				&& dropCon.getClass() == UserStoryPileDropController.class) 
						) {
					isPermissable = true;
				}

				break;

			case PRODUCT_OWNER:
				/*
				 * PRODUCT_OWNER is allowed to move a Notecard from the Backlog to
				 * the WhiteBoard, move a Notecard around the WhiteBoard, and move
				 * the Notecard back to the Backlog; 
				 */
				if( (nc.getCondition() == UserStoryCondition.BL 
				&& dropCon.getClass() == WhiteBoardDropController.class)

				|| (nc.getCondition() == UserStoryCondition.WB 
				&& dropCon.getClass() == WhiteBoardDropController.class)

				|| (nc.getCondition() == UserStoryCondition.WB 
				&& dropCon.getClass() == BacklogDropController.class)
						) {

					isPermissable = true;
				}

				break;

			case DEVELOPER:
				/*
				 * DEVELOPER is only allowed to move Notecards and Postits around
				 * the WhiteBoard.
				 */
				if( (nc.getCondition() == UserStoryCondition.WB 
				&& dropCon.getClass() == WhiteBoardDropController.class)
						) {

					isPermissable = true;
				}

				break;
			}
		} else {
			isPermissable = true;
		}

		return isPermissable;
	}


	/*
	 * GETTERS & SETTERS
	 */
	public void setPermissionsLevel(PermissionType_Agile permissionsLevel) {
		this.permissionsLevel = permissionsLevel;
	}

}
