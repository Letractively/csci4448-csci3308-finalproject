package com.csci.finalproject.agileassistant.client;

public class InVerificationColumn extends WhiteBoardColumn {

	public InVerificationColumn( WhiteBoard whiteBoard ) {
		super("In Verification", whiteBoard);
		
		OnDropBehavior dropBehav = new OnDropBehavior_InVerification( super.getDragDropPanel(), super.getWb() );
		WhiteBoardDropController dropCon = 
				new WhiteBoardDropController( super.getDragDropPanel(), dropBehav );
		
		super.setDropController(dropCon);
	}

}
