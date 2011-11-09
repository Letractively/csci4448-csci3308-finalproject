package com.csci.finalproject.agileassistant.client;

import com.allen_sauer.gwt.dnd.client.DragContext;

public class OnDropBehavior_UserStories extends OnDropBehavior {

	/**
	 * Constructs the OnDropBehavior for a UserStoryColumn
	 * 
	 * @param whiteBoard
	 */
	public OnDropBehavior_UserStories(AbstractWhiteBoard whiteBoard) {
		super(whiteBoard);
	}

	@Override
	public void onDrop(DragContext context) {
		Notecard nc = (Notecard) context.draggable;
		
		if( nc.getCondition() != 2 ) {
			nc.setCondition(2);
			
			for( Postit pst : nc.getPostits() ) {
				wb.add(pst);
			}
		}
	}
}
