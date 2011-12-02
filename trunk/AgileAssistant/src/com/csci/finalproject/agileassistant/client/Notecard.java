package com.csci.finalproject.agileassistant.client;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasAllMouseHandlers;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

/**
 * A {@link com.google.gwt.user.client.ui.Composite} widget that
 * serves as a client side representation of a 
 * {@link com.csci.finalproject.agileassistant.server.UserStory}.
 * A Notecard displays a title and how many points it is worth.
 * Notecards are meant to be made draggable by an {@link AbstractProject}'s
 * drag controllers.
 * 
 * @author Jacob
 */
public class Notecard extends Composite implements HasAllMouseHandlers {
	/*
	 * User Story Fields
	 */
	private Long ID;
	private String storyTitle;
	private List<Postit> postits;
	private int points;
	private UserStoryCondition condition;
	private AbstractProject project;


	/*
	 * GUI Fields
	 */
	private Button dragHandleButton = new Button("");
	private Label pointsLabel;
	
	
	/*
	 * CSS Style Names
	 */
	private static final String CSS_NOTECARD = "Notecard";
	private static final String CSS_NOTECARD_WRAPPER = "Notecard-Wrapper";
	private static final String CSS_NOTECARD_TITLE = "Notecard-TitleLabel";
	private static final String CSS_NOTECARD_POINTS = "Notecard-PointsLabel";
	private static final String CSS_NOTECARD_DRAGHANDLE = "Notecard-DragHandleButton";
	private static final String CSS_NOTECARD_ADDTASKBUTTON = "Notecard-AddTaskButton";


	/*
	 * Constructor
	 */
	public Notecard( Long Id, String ttl, int pts, UserStoryCondition condition,
			AbstractProject project ) {
		
		this.ID = Id;
		this.storyTitle = ttl;
		this.postits = new LinkedList<Postit>();
		this.points = pts;
		this.condition = condition;
		this.project = project;

		// Initialize Components
		AbsolutePanel wrapper = new AbsolutePanel();
		initWidget(wrapper);
		Label titleLabel = new Label(storyTitle);
		pointsLabel = new Label("" + points);
		Button addTaskButton = new Button("+");
		addTaskButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				popupAddTaskPopup();
			}
		});

		// CSS Properties
		addStyleName(CSS_NOTECARD);
		wrapper.addStyleName(CSS_NOTECARD_WRAPPER);
		titleLabel.addStyleName(CSS_NOTECARD_TITLE);
		pointsLabel.addStyleName(CSS_NOTECARD_POINTS);
		dragHandleButton.addStyleName(CSS_NOTECARD_DRAGHANDLE);
		addTaskButton.addStyleName(CSS_NOTECARD_ADDTASKBUTTON);

		// Set Sizes
		wrapper.setSize("100px", "60px");
		titleLabel.setSize("96px", "28px");
		pointsLabel.setSize("100px", "20px");
		dragHandleButton.setSize("100px", "60px");
		addTaskButton.setSize("20px", "20px");

		// Assemble Components
		wrapper.add(titleLabel, 0, 0);
		wrapper.add(pointsLabel, 0, 35);
		wrapper.add(dragHandleButton, 0, 0);
		wrapper.add(addTaskButton, 0, 40);
	}


	/*
	 * HELPER METHODS
	 */
	public void popupAddTaskPopup() {
		project.popupAddTaskPopupPanel( ID );
	}


	/*
	 * PUBLIC METHODS
	 */
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
		pointsLabel.setText(""+points);
	}

	public UserStoryCondition getCondition() {
		return condition;
	}

	public void setCondition(UserStoryCondition condition) {
		this.condition = condition;
	}

	public Long getID() {
		return ID;
	}
	
	public void setID(Long id) {
		this.ID = id;
	}

	public List<Postit> getPostits() {
		return postits;
	}

	public Button getDragHandle() {
		return dragHandleButton;
	}
	public AbstractProject getProject() {
		return project;
	}

	/*
	 * Implementation for HasAllMouseHandlers
	 */
	@Override
	public HandlerRegistration addMouseDownHandler(MouseDownHandler handler) {
		return dragHandleButton.addMouseDownHandler(handler);
	}
	@Override
	public HandlerRegistration addMouseUpHandler(MouseUpHandler handler) {
		return dragHandleButton.addMouseUpHandler(handler);
	}
	@Override
	public HandlerRegistration addMouseOutHandler(MouseOutHandler handler) {
		return dragHandleButton.addMouseOutHandler(handler);
	}
	@Override
	public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
		return dragHandleButton.addMouseOverHandler(handler);
	}
	@Override
	public HandlerRegistration addMouseMoveHandler(MouseMoveHandler handler) {
		return dragHandleButton.addMouseMoveHandler(handler);
	}
	@Override
	public HandlerRegistration addMouseWheelHandler(MouseWheelHandler handler) {
		return dragHandleButton.addMouseWheelHandler(handler);
	}
}
