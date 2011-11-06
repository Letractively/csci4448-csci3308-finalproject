package com.csci.finalproject.agileassistant.client;

import static org.junit.Assert.*;
import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

public class Test_WhiteBoard extends GWTTestCase {

	public String getModuleName() {
		return "com.csci.finalproject.agileassistant.AgileAssistant";
	}
	
	@Test
	public void testWhiteBoardClass() {
		// All checks accessible through respective getters
		// Test 1 -- test Construction
		AgileAssistant agileAssistant = new AgileAssistant();
		WhiteBoard WBTest = new WhiteBoard(agileAssistant);
		// somehow check??
		
		// Test 2 -- check userStoryColumn
		assertNotNull(WBTest.getUserStoryColumn());
		
		// Test 3 -- check toDoColumn
		assertNotNull(WBTest.getToDoColumn());
		
		// Test 4 -- check inProgressColumn
		assertNotNull(WBTest.getInProgressColumn());
		
		// Test 5 -- check inVerificationColumn
		assertNotNull(WBTest.getInVerificationColumn());
		
		// Test 6 -- check completeColumn
		assertNotNull(WBTest.getCompleteColumn());
	}

}
