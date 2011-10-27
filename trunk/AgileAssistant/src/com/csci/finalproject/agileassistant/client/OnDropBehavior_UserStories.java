package com.csci.finalproject.agileassistant.client;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.google.gwt.user.client.ui.AbsolutePanel;

public class OnDropBehavior_UserStories extends OnDropBehavior {

	public OnDropBehavior_UserStories(AbsolutePanel drpTrg) {
		super(drpTrg);
	}

	@Override
	public void onDrop(DragContext context) {
		Notecard nc = (Notecard) context.draggable;
		
		if( nc.getCondition() != 2 ) {
			nc.setCondition(2);
			
			for( Postit pst : nc.getPostits() ) {
				/*
				 * TODO: need to figure out a way to get all
				 * of the Postits for the Notecard into the
				 * "To Do" column when a Notecard is dropped
				 *  into the User Story column
				 */
			}
		}
	}

}
