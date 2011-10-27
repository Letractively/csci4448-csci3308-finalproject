package com.csci.finalproject.agileassistant.client;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.google.gwt.user.client.ui.AbsolutePanel;

public class OnDropBehavior_InProgress extends OnDropBehavior {

	public OnDropBehavior_InProgress(AbsolutePanel drpTrg) {
		super(drpTrg);
	}

	@Override
	public void onDrop(DragContext context) {
		Postit pst = (Postit) context.draggable;
		
		/*
		 * TODO: once the postit class has been implemented
		 * the following line needs to be uncommented and the
		 * int passed into setCondition needs to be checked
		 * pst.setCondition(1);
		 */
	}

}
