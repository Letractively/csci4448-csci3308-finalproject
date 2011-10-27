package com.csci.finalproject.agileassistant.client;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.drop.AbsolutePositionDropController;
import com.google.gwt.user.client.ui.AbsolutePanel;

public class WhiteBoardDropController extends AbsolutePositionDropController {

	private OnDropBehavior onDropBehavior;

	/**
	 * Constructs a drop controller for the different columns on the White
	 * Board. The decision as to what behavior should be executed when a
	 * widget is dropped inside of a panel controlled by this drop
	 * controller is actually made within this class. That decision is
	 * made based on the title of the WhiteBoardColumn that is passed in
	 * and there is no need for you to specify further.
	 * 
	 * @param dropTarget
	 * @param column
	 */
	public WhiteBoardDropController(AbsolutePanel dropTarget, WhiteBoardColumn column) {
		super(dropTarget);
		determineOnDropBehavior(column.getColumnTitle());
	}

	@Override
	public void onDrop( DragContext context ) {
		super.onDrop(context);
		
		if( onDropBehavior != null ){
			onDropBehavior.onDrop(context);
		}
	}

	/*
	 * Helper Methods
	 */
	private void determineOnDropBehavior( String columnName ) {
		AbsolutePanel dropTarget = (AbsolutePanel) this.getDropTarget();
		
		switch( columnName.toLowerCase() ) {
			case "user stories":
				this.onDropBehavior = new OnDropBehavior_UserStories(dropTarget);
				break;
				
			case "to do":
				this.onDropBehavior = new OnDropBehavior_ToDo(dropTarget);
				break;
				
			case "in progress":
				this.onDropBehavior = new OnDropBehavior_InProgress(dropTarget);
				break;
				
			case "in verification":
				this.onDropBehavior = new OnDropBehavior_InVerification(dropTarget);
				break;
				
			case "complete":
				this.onDropBehavior = new OnDropBehavior_Complete(dropTarget);
				break;
				
			default:
				this.onDropBehavior = null;
		}
	}
}
