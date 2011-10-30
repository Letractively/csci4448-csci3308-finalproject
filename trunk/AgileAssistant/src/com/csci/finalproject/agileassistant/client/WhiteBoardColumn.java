package com.csci.finalproject.agileassistant.client;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public abstract class WhiteBoardColumn extends Composite {
	
	protected String title;
	protected WhiteBoard wb;
	protected WhiteBoardDropController dropController;
	protected AbsolutePanel dragDropPanel;
	private VerticalPanel columnWrapper;
	
	public WhiteBoardColumn( String title, WhiteBoard whiteBoard ) {
		this.title = title;
		this.wb = whiteBoard;
		
		columnWrapper = new VerticalPanel();
		columnWrapper.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		columnWrapper.setStyleName("WhiteBoardColumn-Wrapper");
		initWidget(columnWrapper);
		columnWrapper.setSize("225px", "600px");
		
		Label lblNewLabel = new Label(this.title);
		lblNewLabel.setStyleName("WhiteBoardColumn-title");
		columnWrapper.add(lblNewLabel);
		
		dragDropPanel = new AbsolutePanel();
		dragDropPanel.setStyleName("WhiteBoardColumn-DragDropPanel");
		columnWrapper.add(dragDropPanel);
		dragDropPanel.setSize("", "");
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

	protected VerticalPanel getColumnWrapper() {
		return columnWrapper;
	}
}
