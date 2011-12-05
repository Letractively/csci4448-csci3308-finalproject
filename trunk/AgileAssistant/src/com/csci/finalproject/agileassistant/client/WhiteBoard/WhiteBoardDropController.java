package com.csci.finalproject.agileassistant.client.WhiteBoard;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.VetoDragException;
import com.allen_sauer.gwt.dnd.client.drop.AbsolutePositionDropController;
import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.csci.finalproject.agileassistant.client.AbstractProject;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;

/**
 * A {@link DropController} which allows a draggable widget to be placed at 
 * specific (absolute) locations on an {@link WhiteBoardColumn} drop target.
 * 
 * @author Jacob
 */
public class WhiteBoardDropController extends AbsolutePositionDropController {

	private OnDropBehavior onDropBehavior;
	private static AbstractProject project;

	/**
	 * A {@link DropController} which allows a draggable widget to be placed at 
	 * specific (absolute) locations on an {@link WhiteBoardColumn} drop target.
	 * 
	 * @param dropTarget
	 * @param column
	 */
	public WhiteBoardDropController(AbsolutePanel dropTarget, OnDropBehavior dropBehav, AbstractProject project) {
		super(dropTarget);
		this.onDropBehavior = dropBehav;
		WhiteBoardDropController.project = project;
	}

	@Override
	public void onDrop( DragContext context ) {
		onDropBehavior.onDrop(context);
		super.onDrop(context);
	}
	
	@Override
	public void onPreviewDrop(DragContext context) throws VetoDragException {
		/*
		 * Check that the current move is permissible by the projects standards
		 */
		if( !project.moveIsPermissable(context) ) {
			Window.alert("You do not have permission to do this!");
			throw new VetoDragException();
		}
		
		super.onPreviewDrop(context);
	}
}
