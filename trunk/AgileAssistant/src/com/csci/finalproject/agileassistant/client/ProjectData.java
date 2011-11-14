package com.csci.finalproject.agileassistant.client;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ProjectData implements Serializable {
	/*
	 * FIELDS
	 */
	private Long ID;
	private ProjectType projectType;
	private String title;
	private List<UserStoryData> usdList;

	/*
	 * CONSTRUCTORS
	 */
	public ProjectData(){}
	public ProjectData(Long iD, ProjectType projectType, String title, List<UserStoryData> usdList) {
		super();

		ID = iD;
		this.projectType = projectType;
		this.title = title;
		this.usdList = usdList;
	}
	
	
	/*
	 * PUBLIC METHODS
	 */
	public AbstractProject genAbstractProject(LoginInfo loginInfo) {
		AbstractProject project = null;

		switch( projectType ) {
		case AGILE:
			project = new AgileProject(title, ID, loginInfo);
			break;
		case AGILE_MOBILE:
			break;
		}

		project.setNotecards(genNotecardList(project));
		return project;
	}

	public List<Notecard> genNotecardList( AbstractProject project ) {
		List<Notecard> ncList = new LinkedList<Notecard>();

		if( project != null ) {
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


	public ProjectType getProjectType() {
		return projectType;
	}


	public String getTitle() {
		return title;
	}


	public List<UserStoryData> getUsdList() {
		return usdList;
	}

}
