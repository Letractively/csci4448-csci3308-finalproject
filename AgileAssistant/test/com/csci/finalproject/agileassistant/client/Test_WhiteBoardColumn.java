package com.csci.finalproject.agileassistant.client;

import com.google.gwt.junit.client.GWTTestCase;
import org.junit.Test;

public class Test_WhiteBoardColumn extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "com.csci.finalproject.agileassistant.AgileAssistant";
	}
	
	@Test
	public void testWhiteBoardColumn() {
		// Construction
		AgileAssistant Agile = new AgileAssistant();
		WhiteBoard WB = new WhiteBoard(Agile);
		WhiteBoardColumn UserStories = new UserStoryColumn(WB);
		WhiteBoardColumn ToDo = new ToDoColumn(WB);
		WhiteBoardColumn InProgress = new InProgressColumn(WB);
		WhiteBoardColumn InVerification = new InVerificationColumn(WB);
		WhiteBoardColumn Complete = new CompleteColumn(WB);
		
		// Get and set each column drop controller
		
		
		// Get each column's whiteboard it resides in
		
		
		// Get each column's drag panel
		
		
		// Get each column's drop panel
		
		
		// Get each panel that wraps the column
	}
}
