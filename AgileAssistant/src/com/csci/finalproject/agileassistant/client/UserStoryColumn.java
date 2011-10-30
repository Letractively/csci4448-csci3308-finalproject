package com.csci.finalproject.agileassistant.client;

public class UserStoryColumn extends WhiteBoardColumn {

	public UserStoryColumn( WhiteBoard whiteBoard ) {
		super("User Story", whiteBoard);
		
		OnDropBehavior dropBehav = new OnDropBehavior_UserStories( super.getDragDropPanel(), super.getWb() );
		WhiteBoardDropController dropCon = 
				new WhiteBoardDropController( super.getDragDropPanel(), dropBehav );
		
		super.setDropController(dropCon);
		super.getColumnWrapper().setWidth("175px");
	}

}
