package com.csci.finalproject.agileassistant.client.WhiteBoard;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.csci.finalproject.agileassistant.client.Postit;
import com.csci.finalproject.agileassistant.client.TaskCondition;

/**
 * The {@link OnDropBehavior} for the "Complete" 
 * {@link WhiteBoardColumn} in an {@link AgileWhiteBoard} 
 * @param whiteBoard
 */
public class OnDropBehavior_Complete extends OnDropBehavior {

	public OnDropBehavior_Complete(AbstractWhiteBoard whiteBoard) {
		super(whiteBoard);
	}

	@Override
	public void onDrop(DragContext context) {
		Postit pst = (Postit) context.draggable;
		
		if( pst.getCondition() != TaskCondition.COMPLETE ) {
			pst.setCondition(TaskCondition.COMPLETE);
			wb.getProject().persistTask(pst);
		}
	}

}
