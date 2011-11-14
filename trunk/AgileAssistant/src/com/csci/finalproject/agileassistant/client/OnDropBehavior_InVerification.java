package com.csci.finalproject.agileassistant.client;

import com.allen_sauer.gwt.dnd.client.DragContext;

/**
 * The {@link OnDropBehavior} for the "In Verification" 
 * {@link WhiteBoardColumn} in an {@link AgileWhiteBoard} 
 * @param whiteBoard
 */
public class OnDropBehavior_InVerification extends OnDropBehavior {

	public OnDropBehavior_InVerification(AbstractWhiteBoard whiteBoard) {
		super(whiteBoard);
	}

	@Override
	public void onDrop(DragContext context) {
		Postit pst = (Postit) context.draggable;

		if( pst.getCondition() != TaskCondition.IN_VERIFICATION ) {
			pst.setCondition(TaskCondition.IN_VERIFICATION);
			wb.getProject().persistTask(pst);
		}
	}

}
