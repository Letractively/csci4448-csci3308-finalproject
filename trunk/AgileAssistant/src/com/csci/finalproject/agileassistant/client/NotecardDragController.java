package com.csci.finalproject.agileassistant.client;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.allen_sauer.gwt.dnd.client.VetoDragException;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;

public class NotecardDragController extends PickupDragController {
	private static AbstractProject project;

	private Notecard current_draggable_nc;

	public NotecardDragController(AbsolutePanel boundaryPanel,
			boolean allowDroppingOnBoundaryPanel, AbstractProject project) {

		super(boundaryPanel, allowDroppingOnBoundaryPanel);
		NotecardDragController.project = project;
	}

	@Override
	public void dragStart() {
		if( context.draggable.getClass() != Notecard.class ) {
			Window.alert("You are using a NotecardDragController to drag an " +
					"object that is not of type Notecard! Please fix this!");
			return;
		}
		
		super.dragStart();

		current_draggable_nc = (Notecard) context.draggable;
		project.removeNotecard(current_draggable_nc);
	}

	@Override
	public void dragEnd() {

		/*
		 * Check that the current move is permissible by the projects standards
		 */
		if( context.vetoException == null && !project.moveIsPermissable(context) ) {
			context.vetoException = new VetoDragException();
			Window.alert("You do not have permission to do this!");
		}
		
		/* 
		 * A user story cannot go straight from the user story pile onto the 
		 * white board. It must first be sorted into the backlog.
		 */
		if( current_draggable_nc.getCondition() == UserStoryCondition.USP && 
				context.dropController.getClass() == WhiteBoardDropController.class ) {

			context.vetoException = new VetoDragException();
			Window.alert("You must first sort this User Story into the " +
					"Backlog before you can assign it to the White Board!");
		}
		
		/*
		 * This protects a user story from being permanently deleted in the case
		 * that it was dropped outside of all drop controllers, if it was 
		 * dropped straight into the white board from the user story pile, or 
		 * if the user didn't have permission to do this.
		 */
		if( context.vetoException != null ) {
			project.persistUserStory(current_draggable_nc, -1);
		}
		
		current_draggable_nc = null;
		
		super.dragEnd();
	}
	
	public void cancelDrag() {
		if( current_draggable_nc != null ) {
			project.persistUserStory(current_draggable_nc, -1);

			//context.vetoException = new VetoDragException();
			//this.dragEnd();
		}
	}
}
