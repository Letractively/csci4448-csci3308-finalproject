package com.csci.finalproject.agileassistant.client;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

public class WhiteBoardColumn extends Composite {

	private AbstractWhiteBoard wb;
	private WhiteBoardDropController dropController;
	private String title = "Title";
	
	private AbsolutePanel dragDropPanel;
	
	public WhiteBoardColumn( String title, AbstractWhiteBoard whiteBoard, OnDropBehavior dropBehavior ) {
		this.title = title;
		this.wb = whiteBoard;
		
		FlowPanel columnWrapper = new FlowPanel();
		initWidget(columnWrapper);
		columnWrapper.setStyleName("WhiteBoardColumn-Wrapper");
		columnWrapper.setSize("225px", "600px");
		
		Label columnLabel = new Label(this.title);
		columnLabel.setStyleName("WhiteBoardColumn-title");
		
		dragDropPanel = new AbsolutePanel();
		dragDropPanel.setStyleName("WhiteBoardColumn-DragDropPanel");
		
		columnWrapper.add(columnLabel);
		columnWrapper.add(dragDropPanel);
		
		dropController = new WhiteBoardDropController(dragDropPanel, dropBehavior);
	}

	/*
	 * GETTERS & SETTERS
	 */
	public AbstractWhiteBoard getWhiteBoard() {
		return wb;
	}

	public AbsolutePanel getDragDropPanel() {
		return dragDropPanel;
	}
	
	public WhiteBoardDropController getDropController() {
		return dropController;
	}

	public void setDropController(WhiteBoardDropController dropController) {
		this.dropController = dropController;
	}

}
