package com.csci.finalproject.agileassistant.client;

import java.util.List;

import com.google.gwt.junit.client.GWTTestCase;

public class Test_Notecard extends GWTTestCase {

	public String getModuleName() {
		return "com.csci.finalproject.agileassistant.AgileAssistant";
	}
	
	@SuppressWarnings("unused")
	public void testNotecards() {
		// Test 1 - must be capable of being constructed
		Long Id = (long) 1776;
		Notecard NcTest = new Notecard(Id, "Test1", 13, 0);
		// need to verify that this is right
		if (NcTest == null) {
			fail("Notecard construction failed");
		}	
		
		// Test 2 - should be able to get and set title
		assertEquals(NcTest.getTitle(), "Test1");
		
		NcTest.setTitle("Test2");
		assertEquals(NcTest.getTitle(), "Test2");
		
		// Test 3 - should be able to get and set points
		assertEquals(NcTest.getPoints(), 13);
		
		NcTest.setPoints(5);
		assertEquals(NcTest.getPoints(), 5);
		
		// Test 4 - should be able to get and set condition
		assertEquals(NcTest.getCondition(), 0);
		
		NcTest.setCondition(1);
		assertEquals(NcTest.getCondition(), 1);
		
		// Test 5 - should be able to get ID
		assertEquals(NcTest.getID(), Id);
 
		// Test 6 - should be able to add a post-it
		Long Id6 = (long) 787;
		Notecard NcTest6 = new Notecard(Id6, "Test6", 5, 1);
		NcTest6.addPostit("It-1", 1, 0);
		List<Postit> PostItTest6 = NcTest6.getPostits();
		assertEquals(PostItTest6.get(0).getTitle(), "It-1");
		assertEquals(PostItTest6.get(0).getUserStoryID(), Id6);
		assertEquals(PostItTest6.get(0).getCondition(), 0);
		assertEquals(PostItTest6.get(0).getTask_numb(), 1);
		assertEquals(PostItTest6.get(0).getOwner(), null);
		
		// Test 7 - should be able to get all post-its owned by that notecard
		Long Id7 = (long) 666;
		Notecard NcTest7 = new Notecard(Id7, "Test7", 3, 0);
		NcTest7.addPostit("It-1", 1, 0);
		NcTest7.addPostit("It-2", 2, 0);
		NcTest7.addPostit("It-3", 3, 0);
		NcTest7.addPostit("It-4", 4, 0);
		List<Postit> PostItTest7 = NcTest7.getPostits();
			// Check First Task
			assertEquals(PostItTest7.get(0).getTitle(), "It-1");
			assertEquals(PostItTest7.get(0).getCondition(), 0);
			assertEquals(PostItTest7.get(0).getUserStoryID(), Id7);
			assertEquals(PostItTest7.get(0).getOwner(), null);
			assertEquals(PostItTest7.get(0).getTask_numb(), 1);
			// Check Second Task
			assertEquals(PostItTest7.get(1).getTitle(), "It-2");
			assertEquals(PostItTest7.get(1).getCondition(), 0);
			assertEquals(PostItTest7.get(1).getUserStoryID(), Id7);
			assertEquals(PostItTest7.get(1).getOwner(), null);
			assertEquals(PostItTest7.get(1).getTask_numb(), 2);
			// Check Third Task
			assertEquals(PostItTest7.get(2).getTitle(), "It-3");
			assertEquals(PostItTest7.get(2).getCondition(), 0);
			assertEquals(PostItTest7.get(2).getUserStoryID(), Id7);
			assertEquals(PostItTest7.get(2).getOwner(), null);
			assertEquals(PostItTest7.get(2).getTask_numb(), 3);
			// Check Fourth Task
			assertEquals(PostItTest7.get(3).getTitle(), "It-4");
			assertEquals(PostItTest7.get(3).getCondition(), 0);
			assertEquals(PostItTest7.get(3).getUserStoryID(), Id7);
			assertEquals(PostItTest7.get(3).getOwner(), null);
			assertEquals(PostItTest7.get(3).getTask_numb(), 4);
			// Check to make sure no post-its were added to another notecard
			List<Postit> PostItTest7Check = NcTest.getPostits();
			assertEquals(PostItTest7Check.size(), 0);
		
		// Test 8 - should be able to remove a post-it		
		List<Postit> PostItTest8 = NcTest.getPostits();
		NcTest.addPostit("It-1", 1, 0);
		NcTest.removePostit(1);
		PostItTest8 = NcTest.getPostits();
		assertEquals(PostItTest8.size(), 0);
		
		NcTest.addPostit("It-1", 1, 0);
		NcTest.addPostit("It-2", 2, 0);
		NcTest.addPostit("It-3", 3, 0);
		NcTest.addPostit("It-4", 4, 0);
		NcTest.removePostit(3);
		PostItTest8 = NcTest.getPostits();
		assertEquals(PostItTest8.size(), 3);
		assertEquals(PostItTest8.get(0).getTitle(), "It-1");
		assertEquals(PostItTest8.get(1).getTitle(), "It-2");
		assertEquals(PostItTest8.get(2).getTitle(), "It-4");
	}
}
