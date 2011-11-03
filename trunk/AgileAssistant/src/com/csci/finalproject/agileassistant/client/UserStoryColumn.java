package com.csci.finalproject.agileassistant.client;

public class UserStoryColumn extends WhiteBoardColumn {

	public UserStoryColumn( WhiteBoard whiteBoard ) {
		super("User Stories", whiteBoard);
		
		OnDropBehavior dropBehav = new OnDropBehavior_UserStories( super.dragDropPanel, super.getWb() );
		dropController = new WhiteBoardDropController( super.dragDropPanel, dropBehav );
		
		super.getColumnWrapper().setWidth("175px");
	}

}
