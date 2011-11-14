package com.csci.finalproject.agileassistant.client;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

public class Test_TaskData extends GWTTestCase {

	public String getModuleName() {
		return "com.csci.finalproject.agileassistant.AgileAssistant";
	}

	Long id = (long) 1;
	Long userStoryID = (long) 1001;
	TaskData taskdata = new TaskData(id, userStoryID, "this is the title", 1, TaskCondition.IN_PROGRESS, "Rico Suavy");
	
	@Test
	public void testGenPostIt(){
		assertNotNull(taskdata.genPostit());
		TaskData taskdata_2 = new TaskData(taskdata.genPostit());
		if(taskdata_2.getUserStoryID() == taskdata.getUserStoryID())
			assert(true);
		else
			fail("constructor created two different classes with same data");
	}
	
	@Test
	public void testTitle(){
		taskdata.setTitle("this is the new title");
		if(taskdata.getTitle()=="this is the new title")
			assert(true);
		else
			fail("the title was not changed");
		
	}
	
	@Test
	public void testTask(){
		taskdata.setTask_numb(3);//initially set to 1
		assertEquals(taskdata.getTask_numb(),3);
		
	}
	
	@Test
	public void testCondition(){
		taskdata.setCondition(TaskCondition.IN_VERIFICATION); //originally set to IN_PROGRESS
		assertEquals(taskdata.getCondition(),TaskCondition.IN_VERIFICATION);
	}
	
	@Test
	public void testOwner(){
		taskdata.setOwner("Ricky Ricardo");//Mr.Suavy is disappointed
		assertEquals(taskdata.getOwner(),"Ricky Ricardo");
		
	}
	
	@Test
	public void testGetID(){
		assertEquals(taskdata.getID(),id); //id was originally set to 1
		
	}


}
