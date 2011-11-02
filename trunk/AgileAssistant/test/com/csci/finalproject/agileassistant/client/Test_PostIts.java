package com.csci.finalproject.agileassistant.client;

import com.google.gwt.junit.client.GWTTestCase;

public class Test_PostIts extends GWTTestCase {

	public String getModuleName() {
		return "com.csci.finalproject.agileassistant.AgileAssistant";
	}
	
	public void testPostIts(){
		// Test 1 - test construction
		Long StoryID = (long) 1776;
		int TaskNum = 5;
		int Condition = 0;
		Postit PostTest = new Postit("PostTest", StoryID, TaskNum, Condition);
		
		// Test 2 - test getting a setting title
		assertEquals(PostTest.getTitle(), "PostTest");
		
		PostTest.setTitle("PostTestChange");
		assertEquals(PostTest.getTitle(), "PostTestChange");
		
		// Test 3 - test getting and setting task number
		assertEquals(PostTest.getTask_numb(), TaskNum);
		
		PostTest.setTask_numb(1);
		assertEquals(PostTest.getTask_numb(), 1);
		
		// Test 4 - test getting and setting condition
		assertEquals(PostTest.getCondition(), Condition);
		
		PostTest.setCondition(1);
		assertEquals(PostTest.getCondition(), 1);
		
		// Test 5 - test getting and setting owner
		assertNull(PostTest.getOwner());
		
		PostTest.setOwner("Agile Assistant Postit testing");
		assertEquals(PostTest.getOwner(), "Agile Assistant Postit testing");
		
		// Test 6 - test getting user story id
		assertEquals(PostTest.getUserStoryID(), StoryID);
	}
}
