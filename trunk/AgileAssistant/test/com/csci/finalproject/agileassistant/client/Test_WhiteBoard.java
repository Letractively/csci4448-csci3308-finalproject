package com.csci.finalproject.agileassistant.client;

import org.junit.Test;

import com.csci.finalproject.agileassistant.client.WhiteBoard.AgileWhiteBoard;
import com.google.gwt.junit.client.GWTTestCase;

public class Test_WhiteBoard extends GWTTestCase {

	public String getModuleName() {
		return "com.csci.finalproject.agileassistant.AgileAssistant";
	}
	
	@Test
	public void testWhiteBoardClass() {
		// Initialization
		Long userStoryID = (long) 1987;
		Long ID = (long) 1941;
		String title = "Whiteboard-PostIt Testing";
		int task_numb = 5;
		String owner = "Aerospace Engineering";
//		Postit PostItToDo = new Postit(userStoryID, ID, title, task_numb, TaskCondition.TO_DO, owner);
//		Postit PostItInProgress = new Postit(userStoryID, ID, title, task_numb, TaskCondition.IN_PROGRESS, owner);
//		Postit PostItInVerify = new Postit(userStoryID, ID, title, task_numb, TaskCondition.IN_VERIFICATION, owner);
//		Postit PostItComplete = new Postit(userStoryID, ID, title, task_numb, TaskCondition.COMPLETE, owner);
	
		
		// All checks accessible through respective getters
		// Test 1 -- test Construction
		Long Id = (long) 1787;
		AbstractProject project = new AgileProject("White Board Test File", Id, new LoginInfo());
		AgileWhiteBoard WBTest = new AgileWhiteBoard(project);
		// somehow check??
		
		// Check project in WBTest is what it should be
		assertEquals(project, WBTest.getProject());
		
	}

}
