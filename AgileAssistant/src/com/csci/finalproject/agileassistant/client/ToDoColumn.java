package com.csci.finalproject.agileassistant.client;

public class ToDoColumn extends WhiteBoardColumn {

	public ToDoColumn( WhiteBoard whiteBoard ) {
		super("To Do", whiteBoard);
		
		OnDropBehavior dropBehav = new OnDropBehavior_ToDo( super.getDragDropPanel(), super.getWb() );
		WhiteBoardDropController dropCon = 
				new WhiteBoardDropController( super.getDragDropPanel(), dropBehav );
		
		super.setDropController(dropCon);
	}

}
