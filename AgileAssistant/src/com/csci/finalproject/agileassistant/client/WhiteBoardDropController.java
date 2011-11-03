package com.csci.finalproject.agileassistant.client;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.drop.AbsolutePositionDropController;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Widget;

public class WhiteBoardDropController extends AbsolutePositionDropController {

	private OnDropBehavior onDropBehavior;
	final AbsolutePanel dropTarget;

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
	public WhiteBoardDropController(AbsolutePanel dropTarget, OnDropBehavior dropBehav) {
		super(dropTarget);
		this.dropTarget = dropTarget;
		this.onDropBehavior = dropBehav;
	}

	@Override
	public void onDrop( DragContext context ) {
		if( onDropBehavior != null ){
			onDropBehavior.onDrop(context);
		}
		super.onDrop(context);
	}
}
