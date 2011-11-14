package com.csci.finalproject.agileassistant.client;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.drop.AbsolutePositionDropController;
import com.google.gwt.user.client.ui.AbsolutePanel;

public class UserStoryPileDropController extends AbsolutePositionDropController {
	private AbstractUserStoryPile usp;

	public UserStoryPileDropController(AbsolutePanel dropTarget, 
			AbstractUserStoryPile usp) {
		
		super(dropTarget);
		this.usp = usp;
	}
	
	@Override
	public void onDrop(DragContext context) {
		Notecard nc = (Notecard) context.draggable;
		
		if( nc.getCondition() != UserStoryCondition.USP ) {
			nc.setCondition(UserStoryCondition.USP);
			usp.getProject().persistUserStory(nc);
			
			for( Postit p : nc.getPostits() ) {
				p.removeFromParent();
			}
		}
		
		super.onDrop(context);
	}

}
