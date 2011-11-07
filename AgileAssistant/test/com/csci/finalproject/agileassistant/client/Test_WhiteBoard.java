package com.csci.finalproject.agileassistant.client;

import org.junit.Test;
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
		Postit PostItToDo = new Postit(userStoryID, ID, title, task_numb, 0, owner);
		Postit PostItInProgress = new Postit(userStoryID, ID, title, task_numb, 1, owner);
		Postit PostItInVerify = new Postit(userStoryID, ID, title, task_numb, 2, owner);
		Postit PostItComplete = new Postit(userStoryID, ID, title, task_numb, 3, owner);
	
		
		// All checks accessible through respective getters
		// Test 1 -- test Construction
		AgileAssistant agileAssistant = new AgileAssistant();
		WhiteBoard WBTest = new WhiteBoard(agileAssistant);
		// somehow check??
		
		// Test 2 -- check userStoryColumn
		assertNotNull(WBTest.getUserStoryColumn());
		
		// Test 3 -- check toDoColumn
		WBTest.addPostit(PostItToDo);
		assertNotNull(WBTest.getToDoColumn());
		
		// Test 4 -- check inProgressColumn
		WBTest.addPostit(PostItInProgress);
		assertNotNull(WBTest.getInProgressColumn());
		
		// Test 5 -- check inVerificationColumn
		WBTest.addPostit(PostItInVerify);
		assertNotNull(WBTest.getInVerificationColumn());
		
		// Test 6 -- check completeColumn
		WBTest.addPostit(PostItComplete);
		assertNotNull(WBTest.getCompleteColumn());
	}

}
