package com.csci.finalproject.agileassistant.client;

import com.allen_sauer.gwt.dnd.client.DragContext;

public abstract class OnDropBehavior {
	
	protected AbstractWhiteBoard wb;
	
	/**
	 * This is the constructor for the abstract super class
	 * for all the OnDropBehaviors. You cannot instantiate
	 * this directely, you must instantiate one of this
	 * classes subclasses.
	 * 
	 * @param whiteBoard
	 */
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
