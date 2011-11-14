package com.csci.finalproject.agileassistant.client;

import com.allen_sauer.gwt.dnd.client.DragContext;

/**
 * The {@link OnDropBehavior} for the "In Progress" 
 * {@link WhiteBoardColumn} in an {@link AgileWhiteBoard} 
 * @param whiteBoard
 */
public class OnDropBehavior_InProgress extends OnDropBehavior {

	public OnDropBehavior_InProgress(AbstractWhiteBoard whiteBoard) {
		super(whiteBoard);
	}

	@Override
	public void onDrop(DragContext context) {
		Postit pst = (Postit) context.draggable;

		if( pst.getCondition() != TaskCondition.IN_PROGRESS ) {
			pst.setCondition(TaskCondition.IN_PROGRESS);
			wb.getProject().persistTask(pst);
		}
	}

}
