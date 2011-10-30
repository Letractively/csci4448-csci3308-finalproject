package com.csci.finalproject.agileassistant.client;

public class CompleteColumn extends WhiteBoardColumn {

	public CompleteColumn( WhiteBoard whiteBoard ) {
		super("Complete", whiteBoard);
		
		OnDropBehavior dropBehav = new OnDropBehavior_Complete( super.getDragDropPanel(), super.getWb() );
		WhiteBoardDropController dropCon = 
				new WhiteBoardDropController( super.getDragDropPanel(), dropBehav );
		
		super.setDropController(dropCon);
		super.getColumnWrapper().setStyleName("WhiteBoardColumn-WrapperRight");
	}

}
