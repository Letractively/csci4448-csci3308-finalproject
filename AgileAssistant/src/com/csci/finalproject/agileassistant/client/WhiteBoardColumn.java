package com.csci.finalproject.agileassistant.client;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

public abstract class WhiteBoardColumn extends Composite {
	
	protected String title;
	protected WhiteBoard wb;
	protected WhiteBoardDropController dropController;
	protected AbsolutePanel dragDropPanel;
	private FlowPanel columnWrapper;
	
	public WhiteBoardColumn( String title, WhiteBoard whiteBoard ) {
		this.title = title;
		this.wb = whiteBoard;
		
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
	}

	/*
	 * GETTERS & SETTERS
	 */
	public WhiteBoardDropController getDropController() {
		return dropController;
	}

	public WhiteBoard getWb() {
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
