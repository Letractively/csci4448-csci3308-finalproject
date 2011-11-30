package com.csci.finalproject.agileassistant.client;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.HorizontalPanel;
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
	private final VerticalPanel projectContainer = new VerticalPanel();
	private PermissionType_Agile permissionsLevel;

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
		HorizontalPanel horizPan = new HorizontalPanel();

		projectContainer.add(usp);
		projectContainer.add(wb);

		horizPan.add(bl);
		horizPan.add(projectContainer);

		RootPanel.get("projectDiv").add(horizPan);

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
		Notecard nc = (Notecard) context.draggable;
		DropController dropCon = context.dropController;

		switch(permissionsLevel) {
		case SCRUM_MASTER:
			/*
			 * SCRUM_MASTER is allowed to move Notecard objects from the USP to 
			 * the BL, from the BL to the USP, and move things around in the BL.
			 */
			if( (nc.getCondition() == UserStoryCondition.USP 
			&& dropCon.getClass() == BacklogDropController.class)

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

		return isPermissable;
	}


	/*
	 * GETTERS & SETTERS
	 */
	public void setPermissionsLevel(PermissionType_Agile permissionsLevel) {
		this.permissionsLevel = permissionsLevel;
	}

}
