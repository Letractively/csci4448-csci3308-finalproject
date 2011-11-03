package com.csci.finalproject.agileassistant.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class WhiteBoard extends Composite {
	/*
	 * FIELDS
	 */
	private AgileAssistant agileAssistant;
	
	// Columns
	private WhiteBoardColumn userStoryColumn;
	private WhiteBoardColumn toDoColumn;
	private WhiteBoardColumn inProgressColumn;
	private WhiteBoardColumn inVerificationColumn;
	private WhiteBoardColumn completeColumn;

	public WhiteBoard( AgileAssistant agileAssistant) {
		this.agileAssistant = agileAssistant;
		
		HorizontalPanel WhiteBoardWrapperPanel = new HorizontalPanel();
		WhiteBoardWrapperPanel.setStyleName("WhiteBoard-Wrapper");
		initWidget(WhiteBoardWrapperPanel);
		
		userStoryColumn = new UserStoryColumn(this);
		toDoColumn = new ToDoColumn(this);
		inProgressColumn = new InProgressColumn(this);
		inVerificationColumn = new InVerificationColumn(this);
		completeColumn = new CompleteColumn(this);
		
		WhiteBoardWrapperPanel.add(userStoryColumn);
		WhiteBoardWrapperPanel.add(toDoColumn);
		WhiteBoardWrapperPanel.add(inProgressColumn);
		WhiteBoardWrapperPanel.add(inVerificationColumn);
		WhiteBoardWrapperPanel.add(completeColumn);
		
		registerDragDropControllers();
	}
	
	/*
	 * METHODS
	 */
	private void registerDragDropControllers() {
		agileAssistant.getDragCon_notecard().registerDropController(userStoryColumn.getDropController());
		
		agileAssistant.getDragCon_postit().registerDropController(toDoColumn.getDropController());
		agileAssistant.getDragCon_postit().registerDropController(inProgressColumn.getDropController());
		agileAssistant.getDragCon_postit().registerDropController(inVerificationColumn.getDropController());
		agileAssistant.getDragCon_postit().registerDropController(completeColumn.getDropController());
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
