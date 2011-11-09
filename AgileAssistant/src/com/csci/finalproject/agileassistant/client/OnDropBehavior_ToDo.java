package com.csci.finalproject.agileassistant.client;

import com.allen_sauer.gwt.dnd.client.DragContext;

public class OnDropBehavior_ToDo extends OnDropBehavior {

	public OnDropBehavior_ToDo(AbstractWhiteBoard whiteBoard) {
		super(whiteBoard);
	}

	@Override
	public void onDrop(DragContext context) {
		Postit pst = (Postit) context.draggable;
		pst.setCondition(0);
	}

}
