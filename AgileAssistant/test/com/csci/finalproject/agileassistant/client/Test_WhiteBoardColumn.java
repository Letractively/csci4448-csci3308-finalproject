package com.csci.finalproject.agileassistant.client;

import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.csci.finalproject.agileassistant.client.WhiteBoard.AgileWhiteBoard;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.ui.AbsolutePanel;
import org.junit.Test;

public class Test_WhiteBoardColumn extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "com.csci.finalproject.agileassistant.AgileAssistant";
	}
	
	@Test
	public void testWhiteBoardColumn() {
		// Construction
		Long Id = (long) 1787;
		AbstractProject project = new AgileProject("White Board Column Test File", Id, new LoginInfo());
		AgileWhiteBoard WBCTest = new AgileWhiteBoard(project);
		
		// Check project getting
		assertEquals(project, WBCTest.getProject());

//		// Get each column's drag panel
//		AbsolutePanel WBCDrag = WBCTest.getDragDropPanel();
//		
//		// Get each column's drop panel
//		DropController WBCDrop = WBCTest.getDropController();
		
	}
}
