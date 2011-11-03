package com.csci.finalproject.agileassistant.client;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.google.gwt.user.client.ui.AbsolutePanel;

public class OnDropBehavior_UserStories extends OnDropBehavior {

	public OnDropBehavior_UserStories(AbsolutePanel drpTrg, WhiteBoard whiteBoard) {
		super(drpTrg, whiteBoard);
	}

	@Override
	public void onDrop(DragContext context) {
		Notecard nc = (Notecard) context.draggable;
		
		if( nc.getCondition() != 2 ) {
			nc.setCondition(2);
			
			for( Postit pst : nc.getPostits() ) {
				switch( pst.getCondition() ) {
					case 0:
						super.wb.getToDoColumn().getDragDropPanel().add(pst);
					case 1:
						super.wb.getInProgressColumn().getDragDropPanel().add(pst);
					case 2:
						super.wb.getInVerificationColumn().getDragDropPanel().add(pst);
					case 3:
						super.wb.getCompleteColumn().getDragDropPanel().add(pst);							
				}
			}
		}
	}
}
