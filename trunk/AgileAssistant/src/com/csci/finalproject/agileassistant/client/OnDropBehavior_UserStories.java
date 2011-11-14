package com.csci.finalproject.agileassistant.client;

import com.allen_sauer.gwt.dnd.client.DragContext;

/**
 * The {@link OnDropBehavior} for the "User Stories" 
 * {@link WhiteBoardColumn} in an {@link AgileWhiteBoard} 
 * @param whiteBoard
 */
public class OnDropBehavior_UserStories extends OnDropBehavior {

	public OnDropBehavior_UserStories(AbstractWhiteBoard whiteBoard) {
		super(whiteBoard);
	}

	@Override
	public void onDrop(DragContext context) {
		Notecard nc = (Notecard) context.draggable;
		
		if( nc.getCondition() != UserStoryCondition.WB ) {
			nc.setCondition(UserStoryCondition.WB);
			
			for( Postit pst : nc.getPostits() ) {
				wb.add(pst);
			}
			
			wb.getProject().persistUserStory(nc);
		}
	}
}
