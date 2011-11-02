package com.csci.finalproject.agileassistant.server;

import com.google.appengine.api.datastore.Key;
import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;

public class UserStoryTest extends GWTTestCase {

	public String getModuleName() {
		return "com.csci.finalproject.agileassistant.AgileAssistant";
	}
	
	public void testInstantiation(){
		@SuppressWarnings("unused")
		UserStory _UserStory = GWT.create(Task.class);
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


}
