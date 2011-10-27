package com.csci.finalproject.agileassistant.client;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;

public class WhiteBoardColumn extends Composite {
	
	// "User Stories", "To Do", "In Progress", "In Verification", or "Complete"
	private String columnTitle;
	
	// This is the absolute panel that serves as the drag and drop container for Notecards/Postits
	private AbsolutePanel dndPanel;

	public WhiteBoardColumn() {
	}

	
	/*
	 * GETTERS & SETTERS
	 */
	public String getColumnTitle() {
		return columnTitle;
	}

	public AbsolutePanel getDndPanel() {
		return dndPanel;
	}

}
