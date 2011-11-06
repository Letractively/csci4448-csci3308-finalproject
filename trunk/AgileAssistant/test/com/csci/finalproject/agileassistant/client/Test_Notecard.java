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
		AgileAssistant project = new AgileAssistant();
		Notecard NcTest1 = new Notecard(Id, "Test1", 13, 0, project);
		// need to verify that this is right
		if (NcTest1 == null) {
			fail("Notecard construction failed");
		}	
		
		// Test 2 - should be able to get and set title
		assertEquals("Test1", NcTest1.getStoryTitle());
		
		NcTest1.setStoryTitle("Test2");
		assertEquals("Test2", NcTest1.getStoryTitle());
		
		// Test 3 - should be able to get and set points
		assertEquals(13, NcTest1.getPoints());
		
		NcTest1.setPoints(5);
		assertEquals(5, NcTest1.getPoints());
		
		// Test 4 - should be able to get and set condition
		assertEquals(0, NcTest1.getCondition());
		
		NcTest1.setCondition(1);
		assertEquals(1, NcTest1.getCondition());
		
		// Test 5 - should be able to get ID
		assertEquals(Id, NcTest1.getID());
 
		// Test 6 - should be able to add a post-it
		Long Id6 = (long) 787;
		Notecard NcTest6 = new Notecard(Id6, "Test6", 5, 1, project);
		Long userStoryID = (long) 67711;
		Long ID6 = (long) 6771;
		Postit PostItTest6Add = new Postit(userStoryID, ID6, "It-1", 1, 0, "Test6");
		NcTest6.addPostit(PostItTest6Add);
		List<Postit> PostItTest6 = NcTest6.getPostits();
		assertEquals("It-1", PostItTest6.get(0).getTitle());
		assertEquals(userStoryID, PostItTest6.get(0).getUserStoryID());
		assertEquals(0, PostItTest6.get(0).getCondition());
		assertEquals(1, PostItTest6.get(0).getTask_numb());
		assertEquals("Test6", PostItTest6.get(0).getOwner());
		
		// Test 7 - should be able to get all post-its owned by that notecard
		Long ID7 = (long) 666;
		userStoryID = (long) 67712;
		Notecard NcTest7 = new Notecard(ID7, "Test7", 3, 0, project);
		Postit PostItTest71 = new Postit(userStoryID, ID7, "It-1", 1, 0, "Test7");
		Postit PostItTest72 = new Postit(userStoryID, ID7, "It-2", 2, 0, "Test7");
		Postit PostItTest73 = new Postit(userStoryID, ID7, "It-3", 3, 0, "Test7");
		Postit PostItTest74 = new Postit(userStoryID, ID7, "It-4", 4, 0, "Test7");
		NcTest7.addPostit(PostItTest71);
		NcTest7.addPostit(PostItTest72);
		NcTest7.addPostit(PostItTest73);
		NcTest7.addPostit(PostItTest74);
		List<Postit> PostItTest7 = NcTest7.getPostits();
			// Check First Task
			assertEquals("It-1", PostItTest7.get(0).getTitle());
			assertEquals(0, PostItTest7.get(0).getCondition(), 0);
			assertEquals(userStoryID, PostItTest7.get(0).getUserStoryID());
			assertEquals("Test7", PostItTest7.get(0).getOwner());
			assertEquals(1, PostItTest7.get(0).getTask_numb());
			// Check Second Task
			assertEquals("It-2", PostItTest7.get(1).getTitle());
			assertEquals(0, PostItTest7.get(1).getCondition());
			assertEquals(userStoryID, PostItTest7.get(1).getUserStoryID());
			assertEquals("Test7", PostItTest7.get(1).getOwner());
			assertEquals(2, PostItTest7.get(1).getTask_numb());
			// Check Third Task
			assertEquals("It-3", PostItTest7.get(2).getTitle());
			assertEquals(0, PostItTest7.get(2).getCondition());
			assertEquals(userStoryID, PostItTest7.get(2).getUserStoryID());
			assertEquals("Test7", PostItTest7.get(2).getOwner());
			assertEquals(3, PostItTest7.get(2).getTask_numb());
			// Check Fourth Task
			assertEquals("It-4", PostItTest7.get(3).getTitle());
			assertEquals(0, PostItTest7.get(3).getCondition());
			assertEquals(userStoryID, PostItTest7.get(3).getUserStoryID());
			assertEquals("Test7", PostItTest7.get(3).getOwner());
			assertEquals(4, PostItTest7.get(3).getTask_numb());
			// Check to make sure no post-its were added to another notecard
			List<Postit> PostItTest7Check = NcTest1.getPostits();
			assertEquals(0, PostItTest7Check.size());
		
		// Test 8 - should be able to remove a post-it
		userStoryID = (long) 67718;
		Long ID8 = (long) 1944;
		Postit PostItTest8Add = new Postit(userStoryID, ID8, "It-1", 1, 0, "Test8");
		NcTest1.addPostit(PostItTest8Add);
		NcTest1.removePostit(1);
		List<Postit> PostItTest8 = NcTest1.getPostits();
		assertEquals(0, PostItTest8.size());
		
		PostItTest8Add = new Postit(userStoryID, ID8, "It-1", 1, 0, "Test8");
		NcTest1.addPostit(PostItTest8Add);
		PostItTest8Add = new Postit(userStoryID, ID8, "It-2", 2, 0, "Test8");
		NcTest1.addPostit(PostItTest8Add);
		PostItTest8Add = new Postit(userStoryID, ID8, "It-3", 3, 0, "Test8");
		NcTest1.addPostit(PostItTest8Add);
		PostItTest8Add = new Postit(userStoryID, ID8, "It-4", 4, 0, "Test8");
		NcTest1.addPostit(PostItTest8Add);
		NcTest1.removePostit(3);
		PostItTest8 = NcTest1.getPostits();
		assertEquals(PostItTest8.size(), 3);
		assertEquals("It-1", PostItTest8.get(0).getTitle());
		assertEquals("It-2", PostItTest8.get(1).getTitle());
		assertEquals("It-4", PostItTest8.get(2).getTitle());
	}
}
