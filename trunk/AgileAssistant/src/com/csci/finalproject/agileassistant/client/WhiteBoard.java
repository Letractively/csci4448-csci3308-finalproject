package com.csci.finalproject.agileassistant.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class WhiteBoard extends Composite {
	
	// Columns
	private WhiteBoardColumn userStoryColumn;
	private WhiteBoardColumn toDoColumn;
	private WhiteBoardColumn inProgressColumn;
	private WhiteBoardColumn inVerificationColumn;
	private WhiteBoardColumn completeColumn;

	public WhiteBoard() {
		
		HorizontalPanel WhiteBoardWrapperPanel = new HorizontalPanel();
		WhiteBoardWrapperPanel.setStyleName("WhiteBoard-Wrapper");
		initWidget(WhiteBoardWrapperPanel);
		
		this.userStoryColumn = new UserStoryColumn(this);
		this.toDoColumn = new ToDoColumn(this);
		this.inProgressColumn = new InProgressColumn(this);
		this.inVerificationColumn = new InVerificationColumn(this);
		this.completeColumn = new CompleteColumn(this);
		
		WhiteBoardWrapperPanel.add(userStoryColumn);
		WhiteBoardWrapperPanel.add(toDoColumn);
		WhiteBoardWrapperPanel.add(inProgressColumn);
		WhiteBoardWrapperPanel.add(inVerificationColumn);
		WhiteBoardWrapperPanel.add(completeColumn);
		
	}

	/*
	 * GETTERS & SETTERS
	 */
	public WhiteBoardColumn getUserStoryColumn() {
		return userStoryColumn;
	}

	public WhiteBoardColumn getToDoColumn() {
		return toDoColumn;
	}

	public WhiteBoardColumn getInProgressColumn() {
		return inProgressColumn;
	}

	public WhiteBoardColumn getInVerificationColumn() {
		return inVerificationColumn;
	}

	public WhiteBoardColumn getCompleteColumn() {
		return completeColumn;
	}
	
}
