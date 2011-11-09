package com.csci.finalproject.agileassistant.client;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ProjectData implements Serializable {
	/*
	 * FIELDS
	 */
	private Long ID;
	private String type;
	private String title;
	private List<UserStoryData> usdList;
	
	/*
	 * CONSTRUCTORS
	 */
	public ProjectData(){}
	public ProjectData(Long iD, String type, String title, List<UserStoryData> usdList) {
		super();
		ID = iD;
		this.type = type;
		this.title = title;
		this.usdList = usdList;
	}
	
	
	/*
	 * PUBLIC METHODS
	 */
	public List<Notecard> genNotecardList( AbstractProject project ) {
		List<Notecard> ncList = null;
		
		if( project != null ) {
			ncList = new LinkedList<Notecard>();
			for( UserStoryData usd : usdList ) {
				ncList.add( usd.genNotecard(project) );
			}
		}
		
		return ncList;
	}


	/*
	 * GETTERS & SETTERS
	 */
	public Long getID() {
		return ID;
	}


	public String getType() {
		return type;
	}


	public String getTitle() {
		return title;
	}


	public List<UserStoryData> getUsdList() {
		return usdList;
	}

}
