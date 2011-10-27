package com.csci.finalproject.agileassistant.client;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.google.gwt.user.client.ui.AbsolutePanel;

public abstract class OnDropBehavior {
	
	protected AbsolutePanel dropTarget;
	
	/**
	 * This is the constructor for the abstract super class
	 * for all the OnDropBehaviors. You cannot instantiate
	 * this directely, you must instantiate one of this
	 * classes subclasses.
	 * 
	 * @param dropTarget
	 */
	public OnDropBehavior(AbsolutePanel dropTarget) {
		this.dropTarget = dropTarget;
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
