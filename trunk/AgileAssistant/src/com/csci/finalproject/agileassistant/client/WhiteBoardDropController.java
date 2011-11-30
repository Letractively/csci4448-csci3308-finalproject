package com.csci.finalproject.agileassistant.client;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.drop.AbsolutePositionDropController;
import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.AbsolutePanel;

/**
 * A {@link DropController} which allows a draggable widget to be placed at 
 * specific (absolute) locations on an {@link WhiteBoardColumn} drop target.
 * 
 * @author Jacob
 */
public class WhiteBoardDropController extends AbsolutePositionDropController {

	private OnDropBehavior onDropBehavior;

	/**
	 * A {@link DropController} which allows a draggable widget to be placed at 
	 * specific (absolute) locations on an {@link WhiteBoardColumn} drop target.
	 * 
	 * @param dropTarget
	 * @param column
	 */
	public WhiteBoardDropController(AbsolutePanel dropTarget, OnDropBehavior dropBehav) {
		super(dropTarget);
		this.onDropBehavior = dropBehav;
	}

	@Override
	public void onDrop( DragContext context ) {
		onDropBehavior.onDrop(context);
		super.onDrop(context);
	}
}
