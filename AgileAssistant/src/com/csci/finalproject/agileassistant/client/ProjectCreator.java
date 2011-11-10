package com.csci.finalproject.agileassistant.client;

import java.util.List;

public class ProjectCreator {	
	public ProjectCreator(){}
	
	public AbstractProject getProject( ProjectData pd, LoginInfo loginInfo ) {
		AbstractProject project = new AgileProject(pd.getTitle(), pd.getID(), loginInfo);
		project.setNotecards(pd.genNotecardList( project ));
		
		return project;
	}
}
