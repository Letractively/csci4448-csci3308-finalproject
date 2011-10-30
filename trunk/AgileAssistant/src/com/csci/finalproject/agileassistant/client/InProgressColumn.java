package com.csci.finalproject.agileassistant.client;

public class InProgressColumn extends WhiteBoardColumn {

	public InProgressColumn( WhiteBoard whiteBoard ) {
		super("In Progress", whiteBoard);
		
		OnDropBehavior dropBehav = new OnDropBehavior_InProgress( super.getDragDropPanel(), super.getWb() );
		WhiteBoardDropController dropCon = 
				new WhiteBoardDropController( super.getDragDropPanel(), dropBehav );
		
		super.setDropController(dropCon);
	}

}
