package com.csci.finalproject.agileassistant.client;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

public class WhiteBoardColumn extends Composite {

	private AbstractWhiteBoard wb;
	private WhiteBoardDropController dropController;
	private OnDropBehavior dropBehavior;
	
	private String title;
	private AbsolutePanel dragDropPanel;
	private FlowPanel columnWrapper;
	
	public WhiteBoardColumn( String title, AbstractWhiteBoard whiteBoard, OnDropBehavior dropBehavior ) {
		this.title = title;
		this.wb = whiteBoard;
		this.dropBehavior = dropBehavior;
		
		columnWrapper = new FlowPanel();
		columnWrapper.setStyleName("WhiteBoardColumn-Wrapper");
		initWidget(columnWrapper);
		columnWrapper.setSize("225px", "600px");
		
		Label lblNewLabel = new Label(this.title);
		lblNewLabel.setStyleName("WhiteBoardColumn-title");
		columnWrapper.add(lblNewLabel);
		
		dragDropPanel = new AbsolutePanel();
		dragDropPanel.setStyleName("WhiteBoardColumn-DragDropPanel");
		columnWrapper.add(dragDropPanel);
		
		this.dropController = new WhiteBoardDropController(dragDropPanel, this.dropBehavior);
	}

	/*
	 * GETTERS & SETTERS
	 */
	public WhiteBoardDropController getDropController() {
		return dropController;
	}

	public AbstractWhiteBoard getWb() {
		return wb;
	}

	public AbsolutePanel getDragDropPanel() {
		return dragDropPanel;
	}

	public void setDropController(WhiteBoardDropController dropController) {
		this.dropController = dropController;
	}

	protected FlowPanel getColumnWrapper() {
		return columnWrapper;
	}
}
