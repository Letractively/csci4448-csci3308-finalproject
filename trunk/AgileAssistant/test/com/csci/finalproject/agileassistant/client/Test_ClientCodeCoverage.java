package com.csci.finalproject.agileassistant.client;

//import junit.framework.TestSuite;
//
//import com.google.gwt.junit.client.GWTTestCase;
//import com.google.gwt.junit.tools.GWTTestSuite;
//import org.junit.Test;
//
//public class Test_ClientCodeCoverage extends GWTTestSuite {
//
//	public static Test testClientCoverage() {
//		TestSuite suite = new TestSuite("Test Code Coverage.");
//		suite.addTestSuite(Test_PostIts.class);
////		suite.addTestSuite(Test_Notecard.class);
////		suite.addTestSuite(Test_WhiteBoard.class);
////		suite.addTestSuite(Test_WhiteBoardColumn.class);
//		return suite;
//	}
//
//}
//
import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
import junit.framework.TestSuite;
//import com.google.appengine.repackaged.org.json.Test;
import com.google.gwt.junit.client.GWTTestCase;

public class Test_ClientCodeCoverage extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "com.csci.finalproject.agileassistant.AgileAssistant";
	}
	
   public static Test suite() {
       final TestSuite suite = new TestSuite("Client Code Coverage Test Suite");
       
       // The following tests will not run do to server/communication problems
	   suite.addTestSuite(Test_Notecard.class);
	   suite.addTestSuite(Test_AbstractProject.class);
	   suite.addTestSuite(Test_WhiteBoard.class);
	   suite.addTestSuite(Test_WhiteBoardColumn.class);
	   // The following tests are known to run without server/communication problems
	   suite.addTestSuite(Test_PostIts.class);
	   suite.addTestSuite(Test_TaskCondition.class);
	   suite.addTestSuite(Test_LoginInfo.class);
	   suite.addTestSuite(Test_TaskData.class);
	   suite.addTestSuite(Test_UserStoryData.class);
	   suite.addTestSuite(Test_UserStoryService.class);
	   suite.addTestSuite(Test_UserStoryServiceAsync.class);
       
       return suite;
   }
}