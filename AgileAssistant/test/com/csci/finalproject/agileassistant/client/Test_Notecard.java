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
 
		// Test 6 - should be able to get all post-its owned by that notecard
//		List<Postit> PostItTest = NcTest.getPostits();
//		assertEquals(PostItTest.)
//		assertEquals(PostItTest.getTitle(), null);
//		assertEquals(PostItTest.getCondition(), null);
//		assertEquals(PostItTest.getUserStoryID(), null);
//		assertEquals(PostItTest.getOwner(), null);
//		assertEquals(PostItTest.getTask_numb(), null);
		
		// Test 7 - should be able to add a post-it
		NcTest.addPostit("It-1", 1, 0);
//		PostItTest = (Postit) NcTest.getPostits();
//		assertEquals(PostItTest.getTitle(), "It-1");
//		assertEquals(PostItTest.getUserStoryID(), Id);
//		assertEquals(PostItTest.getCondition(), 0);
//		assertEquals(PostItTest.getTask_numb(), 1);
//		assertEquals(PostItTest.getOwner(), null);
		
		// Test 8 - should be able to remove a post-it		
		List<Postit> PostItTest8 = NcTest.getPostits();
		int length = PostItTest8.size();
		NcTest.removePostit(1);
		PostItTest8 = NcTest.getPostits();
		assertEquals(PostItTest8.size(), length - 1);
	}
}
