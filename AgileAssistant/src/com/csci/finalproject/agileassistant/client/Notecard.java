package com.csci.finalproject.agileassistant.client;

import java.io.Serializable;
import java.util.List;

import com.google.gwt.user.client.ui.Composite;

public class Notecard extends Composite implements Serializable {

	private Long ID;
	private String title;
	private List<Postit> postits;
	private int points;
	private int condition; // 0=UserStoryPile 1=Backlog 2=Whiteboard
	
	public Notecard() {}

	public Notecard( Long id, String ttl, List<Postit> postitList, int pts, int cond ) {
		this.ID = id;
		this.title = ttl;
		this.postits = postitList;
		this.points = pts;
		this.condition = cond;
	}

	/*
	 * GETTERS & SETTERS
	 */
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
	
	

}
