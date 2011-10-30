package com.csci.finalproject.agileassistant.client;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.google.gwt.user.client.ui.AbsolutePanel;

public class OnDropBehavior_Complete extends OnDropBehavior {

	public OnDropBehavior_Complete(AbsolutePanel drpTrg, WhiteBoard whiteBoard) {
		super(drpTrg, whiteBoard);
	}

	@Override
	public void onDrop(DragContext context) {
		Postit pst = (Postit) context.draggable;
		pst.setCondition(3);
	}

}
