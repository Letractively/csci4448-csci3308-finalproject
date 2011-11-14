package com.csci.finalproject.agileassistant.client;

import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * A {@link com.google.gwt.user.client.ui.Composite} widget that has title
 * and a drag & drop panel. The title represents the name of the column 
 * and the AbsolutePanel is a panel where either a {@link Notecard} or a 
 * {@link Postit} can be drag and dropped into. The WhiteBoardColumn also 
 * has a {@link WhiteBoardDropController} to control the widgets that are 
 * dropped into it. The WhiteBoardColumn is to be placed inside of an 
 * {@link AbstractWhiteBoard}.
 * 
 * @author Jacob
 */
public class WhiteBoardColumn extends Composite {

	private AbsolutePanel dragDropPanel = new AbsolutePanel();
	private DropController wbDropController;
	private String title = "Title";
	
	/**
	 * Constructs a column that can be placed onto an {@link AbstractWhiteBoard}. It
	 * takes a title that will be displayed at the top of the column, and
	 * a {@link OnDropBehavior} that defines what happens when a widget is
	 * dropped on the column.
	 * 
	 * @param title
	 * @param dropBehavior
	 */
	public WhiteBoardColumn( String title, OnDropBehavior dropBehavior ) {
		this.title = title;
		
		FlowPanel columnWrapper = new FlowPanel();
		initWidget(columnWrapper);
		columnWrapper.setStyleName("WhiteBoardColumn-Wrapper");
		columnWrapper.setSize("225px", "600px");
		
		Label columnLabel = new Label(this.title);
		columnLabel.setStyleName("WhiteBoardColumn-title");
		dragDropPanel.setStyleName("WhiteBoardColumn-DragDropPanel");
		
		columnWrapper.add(columnLabel);
		columnWrapper.add(dragDropPanel);
		
		wbDropController = new WhiteBoardDropController(dragDropPanel, dropBehavior);
	}

	/*
	 * GETTERS & SETTERS
	 */
	public AbsolutePanel getDragDropPanel() {
		return dragDropPanel;
	}
	
	public DropController getDropController() {
		return wbDropController;
	}
}
