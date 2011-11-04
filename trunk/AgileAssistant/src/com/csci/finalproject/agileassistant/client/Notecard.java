package com.csci.finalproject.agileassistant.client;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.event.dom.client.ClickEvent;

public class Notecard extends Composite {

	private Long ID;
	private String storyTitle;
	private List<Postit> postits;
	private int points;
	private int condition; // 0=UserStoryPile 1=Backlog 2=Whiteboard
	private Button dragHandleButton;
	
	private AgileAssistant project;
	
	/*
	 * Constructors
	 */
	// For the sake of serialize we need an empty constructor
	public Notecard() {}
	/**
	 * @wbp.parser.constructor
	 */
	public Notecard( Long Id, String ttl, int pts, int cond, AgileAssistant project ) {
		this.ID = Id;
		this.storyTitle = ttl;
		this.postits = new LinkedList<Postit>();
		this.points = pts;
		this.condition = cond;
		this.project = project;
		
		AbsolutePanel notecardWrapper = new AbsolutePanel();
		notecardWrapper.setStyleName("Notecard-Wrapper");
		initWidget(notecardWrapper);
		notecardWrapper.setSize("100px", "60px");
		
		Label titleLabel = new Label(storyTitle);
		titleLabel.setStyleName("Notecard-TitleLabel");
		notecardWrapper.add(titleLabel, 0, 0);
		titleLabel.setSize("96px", "28px");
		
		Label pointsLabel = new Label("" + points);
		pointsLabel.setStyleName("Notecard-PointsLabel");
		notecardWrapper.add(pointsLabel, 0, 35);
		pointsLabel.setSize("100px", "20px");
		
		dragHandleButton = new Button("");
		dragHandleButton.setText("");
		dragHandleButton.setStyleName("dragHandleButton");
		notecardWrapper.add(dragHandleButton, 0, 0);
		dragHandleButton.setSize("100px", "60px");
		
		Button addTaskButton = new Button("+");
		addTaskButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				popupAddTaskPopup();
			}
		});
		addTaskButton.setStyleName("Notecard-AddTaskButton");
		addTaskButton.setText("+");
		notecardWrapper.add(addTaskButton, 0, 40);
		addTaskButton.setSize("20px", "20px");
	}
	
	public void addPostit( Postit postit ) {
		postits.add( postit );
		
	} 
	
	public void removePostit( int taskNum ) {
		for( Postit p : postits ) {
			if( p.getTask_numb() == taskNum ) {
				postits.remove( postits.indexOf(p) );
			}
		}
	}
	
	public void popupAddTaskPopup() {
		project.popupAddTaskPopupPanel( ID );
	}

	/*
	 * GETTERS & SETTERS
	 */
	public String getStoryTitle() {
		return storyTitle;
	}

	public void setStoryTitle(String title) {
		this.storyTitle = title;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getCondition() {
		return condition;
	}

	public void setCondition(int condition) {
		this.condition = condition;
	}

	public Long getID() {
		return ID;
	}

	public List<Postit> getPostits() {
		return postits;
	}
	public Button getDragHandle() {
		return dragHandleButton;
	}
}
