package com.csci.finalproject.agileassistant.server;

import com.csci.finalproject.agileassistant.client.UserStoryService;
import com.csci.finalproject.agileassistant.client.UserStoryServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;

public class taskTest extends GWTTestCase {

	public String getModuleName() {
		return "com.csci.finalproject.agileassistant.AgileAssistant";
	}
	
	public void testClassInstantiation(){
		@SuppressWarnings("unused")
		Task _Task = GWT.create(Task.class);
	}

	public void testkeyget(){
		
	}
	
	public void testkeySET(){
		//I don't think we want to be able to set the Primary key
	}
	
	public void testgetTitle(){
		
	}
	
	public void testsetTask_numb(){
		
	}
	
	public void testgetCondition() {
	}

	public void testsetCondition() {
	}

	public void testgetOwner() {
	}

	public void testsetOwner() {
	}

	public void testgetUserStory() {
	}
}
