package com.csci.finalproject.agileassistant.client;

import com.allen_sauer.gwt.dnd.client.DragContext;

/**
 * The {@link OnDropBehavior} for the "To Do" 
 * {@link WhiteBoardColumn} in an {@link AgileWhiteBoard} 
 * @param whiteBoard
 */
public class OnDropBehavior_ToDo extends OnDropBehavior {
	
	public OnDropBehavior_ToDo(AbstractWhiteBoard whiteBoard) {
		super(whiteBoard);
	}

	@Override
	public void onDrop(DragContext context) {
		Postit pst = (Postit) context.draggable;
		
		if( pst.getCondition() != TaskCondition.TO_DO ) {
			pst.setCondition(TaskCondition.TO_DO);
			wb.getProject().persistTask(pst);
		}
	}

}
