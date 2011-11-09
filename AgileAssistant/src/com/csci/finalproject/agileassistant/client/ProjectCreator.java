package com.csci.finalproject.agileassistant.client;

public class ProjectCreator {
	
	public ProjectCreator(){}
	
	public AbstractProject getProject( ProjectData pd, LoginInfo loginInfo ) {
		AbstractProject project = null;
		
		/*
		 * TODO:
		 * 	This is where we need to put the logic for deciding which type
		 * of project is being created. For right now, we only have Agile
		 * Projects, so it doesn't matter, but in the name of expansion,
		 * we need to add the logic here anyway.
		 */
		project = new AgileProject(pd.getTitle(), pd.getID(), loginInfo);
		
		project.setNotecards(pd.genNotecardList( project ));
		
		return project;
	}
}
