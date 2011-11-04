package com.csci.finalproject.agileassistant.server;

import com.google.gwt.junit.client.GWTTestCase;

public class UserStoryTest extends GWTTestCase {

	public String getModuleName() {
		return "com.csci.finalproject.agileassistant.AgileAssistant";
	}
	
	public void testInfrastructure() {
		assertTrue(true);
	}
	
	/*
	public void testInstantiation(){
		PersistentProject pp = new PersistentProject( getUser(), "test project", "agile" );
		UserStory testUserStory = new UserStory( pp, "test User Story");
		assertTrue( testUserStory.getClass() == UserStory.class);
	}
	
	public void  testgetKey() {
	}

	public void testsetKey(Key key) {
		//once again setting a primary key is a no-no
	}

	public void testgetTitle() {
	}

	public void testgetUser() {
	}

	public void testsetTitle(String title) {
	}

	public void testgetCondition() {
	}

	public void testsetCondition() {
	}

	public void testgetTasks() {
	}

	public void testgetPoints() {
	}

	public void testsetPoints() {
	}
/*
	/*
	 * HELPER METHODS
	 */
/*	private User getUser() {
		UserService userService = UserServiceFactory.getUserService();
		return userService.getCurrentUser();
	}
*/
}
