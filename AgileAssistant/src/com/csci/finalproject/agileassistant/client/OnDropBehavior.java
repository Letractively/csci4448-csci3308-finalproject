package com.csci.finalproject.agileassistant.client;

import com.allen_sauer.gwt.dnd.client.DragContext;

/**
 * Defines the behavior to be executed when a widget
 * (specifically a {@link Notecard} or {@link Postit}
 * is dropped into a {@link WhiteBoardColumn} in an
 * {@link AgileWhiteBoard}.
 * @param whiteBoard
 */
public abstract class OnDropBehavior {
	
	protected AbstractWhiteBoard wb;

	public OnDropBehavior(AbstractWhiteBoard whiteBoard) {
		this.wb = whiteBoard;
	}

	/**
	 * Carries out the actions needed upon dropping a widget
	 * into a specific White Board column. This is polymorphic
	 * in nature. You call this function regardless of which
	 * column the widget is being dropped in, but you get the
	 * behavior specific to that column.
	 * 
	 * @param context
	 */
	abstract public void onDrop( DragContext context );
}
