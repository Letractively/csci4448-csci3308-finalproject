package com.csci.finalproject.agileassistant.client;

import java.util.List;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.junit.client.GWTTestCase;

public class Test_AbstractProject extends GWTTestCase{

	public String getModuleName() {
		return "com.csci.finalproject.agileassistant.AgileAssistant";
	}
	
	public void testAbstractProject() {
		Long Id = (long) 2004;
		LoginInfo AbstractLogin = new LoginInfo(); 
		AbstractProject project = new AgileProject("Notecard Test File", Id, AbstractLogin);
		
		// Check check get notecards
		List<Notecard> AbstractNotes = project.getNotecards();
		assertEquals(0, AbstractNotes.size());

		// Check check setnotecards
		AbstractNotes.set(0, new Notecard(Id, "Test1", 3, UserStoryCondition.USP, project));
		AbstractNotes.set(1, new Notecard(Id, "Test2", 5, UserStoryCondition.BL, project));
		AbstractNotes.set(2, new Notecard(Id, "Test3", 8, UserStoryCondition.WB, project));
		AbstractNotes.set(3, new Notecard(Id, "Test4", 13, UserStoryCondition.WB, project));
		
		List<Notecard> AbstractNotesCheck = project.getNotecards();
		assertEquals(4, AbstractNotesCheck.size());

		// Check Ids
		assertEquals(Id, AbstractNotesCheck.get(0).getID());
		assertEquals(Id, AbstractNotesCheck.get(1).getID());
		assertEquals(Id, AbstractNotesCheck.get(2).getID());
		assertEquals(Id, AbstractNotesCheck.get(3).getID());
		
		// Check Titles
		assertEquals("Test1", AbstractNotesCheck.get(0).getTitle());
		assertEquals("Test2", AbstractNotesCheck.get(1).getTitle());
		assertEquals("Test3", AbstractNotesCheck.get(2).getTitle());
		assertEquals("Test4", AbstractNotesCheck.get(3).getTitle());
		
		// Check Points
		assertEquals(3, AbstractNotesCheck.get(0).getPoints());
		assertEquals(5, AbstractNotesCheck.get(1).getPoints());
		assertEquals(8, AbstractNotesCheck.get(2).getPoints());
		assertEquals(13, AbstractNotesCheck.get(3).getPoints());
		
		// Check Conditions
		assertEquals(UserStoryCondition.USP, AbstractNotesCheck.get(0).getCondition());
		assertEquals(UserStoryCondition.BL, AbstractNotesCheck.get(0).getCondition());
		assertEquals(UserStoryCondition.WB, AbstractNotesCheck.get(0).getCondition());
		assertEquals(UserStoryCondition.WB, AbstractNotesCheck.get(0).getCondition());
		
		// Check gettitle
		assertEquals("Notecard Test File", project.getTitle());

		// Check getID
		assertEquals(Id, project.getID());

//		// Check getusrstryserv
//		public UserStoryServiceAsync getUsrStryServ() {
//			return usrStryServ;
//		}
//
//		// Check getdragcon_notecard
//		public PickupDragController getDragCon_notecard() {
//			return dragCon_notecard;
//		}
//
//		// Check getdragcon_postit
//		public PickupDragController getDragCon_postit() {
//			return dragCon_postit;
//		}
//
//		// Check getadduserstorypopup
//		public AddUserStoryPopupPanel getAddUserStoryPopup() {
//			return addUserStoryPopup;
//		}
//
//
//		// Check addTaskPopup
//		public AddTaskPopupPanel getAddTaskPopup() {
//			return addTaskPopup;
//		}

		// Set login info, and check Abstract can gets the right data
		LoginInfo LoginTest = new LoginInfo();
		LoginTest.setLoginUrl("www.cs.colorado.edu");
		LoginTest.setLoggedIn(true);
		LoginTest.setLogoutUrl("www.colorado.edu/aerospace");
		LoginTest.setEmailAddress("LogInTest@AgileProject.com");
		LoginTest.setNickname("DawnSucks");
		
		LoginInfo Check = project.getLoginInfo();
		assertEquals("www.cs.colorado.edu", Check.getLoginUrl());
		assertEquals(true, Check.isLoggedIn());
		assertEquals("www.colorado.edu/aerospace", Check.getLogoutUrl());
		assertEquals("AbstractProjectTest@AgileProject.com", Check.getEmailAddress());
		assertEquals("DawnSucks", Check.getNickname());
	}
}
