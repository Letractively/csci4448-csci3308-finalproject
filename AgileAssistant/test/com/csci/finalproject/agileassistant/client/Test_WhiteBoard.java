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
		// --- add postit for respective column ---
		assertEquals(null, WBTest.getUserStoryColumn());
		assertNotNull(WBTest.getUserStoryColumn());
		
		// Test 3 -- check toDoColumn
		// --- add postit for respective column ---
		assertNotNull(WBTest.getToDoColumn());
		
		// Test 4 -- check inProgressColumn
		// --- add postit for respective column ---
		assertNotNull(WBTest.getInProgressColumn());
		
		// Test 5 -- check inVerificationColumn
		// --- add postit for respective column ---
		assertNotNull(WBTest.getInVerificationColumn());
		
		// Test 6 -- check completeColumn
		// --- add postit for respective column ---
		assertNotNull(WBTest.getCompleteColumn());
	}

}
