package com.csci.finalproject.agileassistant.client;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.Timer;

public class Test_PostIts extends GWTTestCase {

	public String getModuleName() {
		return "com.csci.finalproject.agileassistant.AgileAssistant";
	}
	
	public void testPostIts() 
	{
		// Test 1 - test construction
		Long StoryID = (long) 1776;
		Long userStoryID = (long) 1767;
		int TaskNum = 5;
		Postit PostTest = new Postit(userStoryID, StoryID, "PostTest", TaskNum, TaskCondition.TO_DO, "PostItClassTest");
		
		// Test 2 - test getting a setting title
		assertEquals("PostTest", PostTest.getTitle());
		
		PostTest.setTitle("PostTestChange");
		assertEquals("PostTestChange", PostTest.getTitle());
		
		// Test 3 - test getting and setting task number
		assertEquals(TaskNum, PostTest.getTask_numb());
		
		PostTest.setTask_numb(1);
		assertEquals(1, PostTest.getTask_numb());
		
		// Test 4 - test getting and setting condition
		assertEquals(TaskCondition.TO_DO, PostTest.getCondition());
		
		PostTest.setCondition(TaskCondition.IN_PROGRESS);
		assertEquals(TaskCondition.IN_PROGRESS, PostTest.getCondition());
		
		PostTest.setCondition(TaskCondition.IN_VERIFICATION);
		assertEquals(TaskCondition.IN_VERIFICATION, PostTest.getCondition());
		
		PostTest.setCondition(TaskCondition.COMPLETE);
		assertEquals(TaskCondition.COMPLETE, PostTest.getCondition());
		
		// Test 5 - test getting and setting owner
		assertEquals("PostItClassTest", PostTest.getOwner());
		
		PostTest.setOwner("Agile Assistant Postit testing");
		assertEquals("Agile Assistant Postit testing", PostTest.getOwner());
		
		// Test 6 - test getting user story id
		assertEquals(userStoryID, PostTest.getUserStoryID());
		
		// Test 7 - test getting story id
		assertEquals(StoryID, PostTest.getID());
	}
}
