package com.csci.finalproject.agileassistant.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class Test_Notecard extends GWTTestCase {
//	*Description of tests:*
//	A Notecard should be able to:
//	1. be constructed
//	2. getTitle() and setTitle()
//	3. getPoints() and setPoints()
//	4. getCondition() and setCondition()
//	5. getID()
//	6. getPostits() // gets all the postits owned by that Notecard
//	7. addPostit()
//	8. removePostit()
//
//	*List of classes involved in tests:*
//	Notecard.java
	
	public String getModuleName() {
		return "com.csci.finalproject.agileassistant.AgileAssistant";
	}
	
// all test functions need to start with "test" in same case	
	public void testConstruction() {
		UserStoryServiceAsync usrStryServ = GWT.create(UserStoryService.class);
		
	}
	
	public void testTitle() {
	// get and set the post-it title
		
	}
	
	public void testPoints() {
	// get and set the post-it points
		
	}
	
	public void testCondition() {
	// get and set the post-it conidtion
		
	}
	
	public void testID() {
	// get the post-it ID
		
	}
	
	public void testPostits() {
	// Get post-its (all post-its owned by that notecard
	// add post-its, remove post-its
		
	}
	
}
