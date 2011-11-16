package com.csci.finalproject.agileassistant.client;

import java.util.List;

import com.google.gwt.junit.client.GWTTestCase;
import org.junit.Test;

public class Test_UserStoryData extends GWTTestCase {

	public String getModuleName(){
		return "com.csci.finalproject.agileassistant.AgileAssistant";
	}
	
	@Test
	public void testUserStoryData() {
		Long ID = (long) 1988;
		String title = "UserStoryDataTesting";
		int points = 28;
		List<TaskData> taskDataList = null;
		UserStoryData UserStoryTest = new UserStoryData(ID, title, taskDataList, points, UserStoryCondition.USP);
		
		// Test getting and setting title
		assertEquals(title, UserStoryTest.getTitle());

		UserStoryTest.setTitle("IMPULSE");
		assertEquals("IMPULSE", UserStoryTest.getTitle());

		// Test getting and setting points
		assertEquals(points, UserStoryTest.getPoints());
		
		UserStoryTest.setPoints(15);
		assertEquals(15, UserStoryTest.getPoints());

		// Test getting and setting condtiion
		assertEquals(UserStoryCondition.USP, UserStoryTest.getCondition());
		
		UserStoryTest.setCondition(UserStoryCondition.BL);
		assertEquals(UserStoryCondition.BL, UserStoryTest.getCondition());
		
		UserStoryTest.setCondition(UserStoryCondition.WB);
		assertEquals(UserStoryCondition.WB, UserStoryTest.getCondition());
		
		// Test getting ID
		assertEquals(ID, UserStoryTest.getID());

		// Test getting TaskDataList
		List<TaskData> taskTest = UserStoryTest.getTaskDataList();
		// assertNull(taskTest.get(0)); ---> all functions keep returning null pointer exception?
	}

}
