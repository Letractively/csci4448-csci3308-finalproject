package com.csci.finalproject.agileassistant.client;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
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

	@Override
	protected void restoreSelectedWidgetsLocation() {
		if( context.draggable instanceof Notecard ) {
			Notecard nc = (Notecard) context.draggable;
			if(nc.getCondition() == UserStoryCondition.BL) {
				project.restoreNotecard(nc);
			} else {
				super.restoreSelectedWidgetsLocation();
			}
		}
	}
	
	public void cancelDrag() {
/*		if( current_draggable_nc != null ) {
			project.persistUserStory(current_draggable_nc, -1);
		}
		
		dragEnd();
*/
	}
}
