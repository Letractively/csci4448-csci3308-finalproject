package com.csci.finalproject.agileassistant.client;


import com.google.gwt.junit.client.GWTTestCase;
import org.junit.Test;

public class Test_LoginInfo extends GWTTestCase{
	public String getModuleName(){
		return "com.csci.finalproject.agileassistant.AgileAssistant";
	}

	public void testLoginInfo() {
		LoginInfo LoginTest = new LoginInfo();
		
		// Set and check logged-in status
		assertEquals(false, LoginTest.isLoggedIn());
		
		LoginTest.setLoggedIn(true);
		assertEquals(true, LoginTest.isLoggedIn());
		
		// Set and check logged-in url
		LoginTest.setLoginUrl("www.cs.colorado.edu");
		assertEquals("www.cs.colorado.edu", LoginTest.getLoginUrl());

		// Set and check logged-out url
		LoginTest.setLogoutUrl("www.colorado.edu/aerospace");
		assertEquals("www.colorado.edu/aerospace", LoginTest.getLogoutUrl());
		
		// Set and get email address
		LoginTest.setEmailAddress("LogInTest@AgileProject.com");
		assertEquals("LogInTest@AgileProject.com", LoginTest.getEmailAddress());
		
		// Set and get nickname
		LoginTest.setNickname("DawnSucks");
		assertEquals("DawnSucks", LoginTest.getNickname());
	}
}
