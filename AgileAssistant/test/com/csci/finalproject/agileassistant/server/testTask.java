package com.csci.finalproject.agileassistant.server;

import static org.junit.Assert.*;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.junit.Before;
import org.junit.Test;

import com.csci.finalproject.agileassistant.client.TaskData;
import com.google.appengine.api.datastore.Key;

public class testTask {

	
	@Test
	public void test(){
		String title = "Test Title";
		UserStory userStory = null;//new UserStory();
		int task_numb = 1;
		Task testTask = new Task(title, userStory, task_numb);
		
		//title test
		String title_check = "this is a new title";
		testTask.setTitle(title_check);
		if(title_check == "this is a new title")
			assert(true);
		else
			fail("get or set title error has occured");
		
		//test key get
		Key tmp_key = testTask.getKey();
		if(tmp_key != null)
			assert(true);
		else
			fail("key is equal to NULL, test failed");
		
		//test Task numb
		testTask.setTask_numb(4);
		int tmp_tasknum = testTask.getTask_numb();
		//assertEquals(tmp_tasknum, 4);
		if(tmp_tasknum == 4)
			assert(true);
		else
			fail("task number unable to be set");
		
		//test Condition setting
		int condition = 0;
		int tmp_condition;
		tmp_condition = testTask.getCondition();
		if(tmp_condition == condition)
			testTask.setCondition(condition+1);
		else
			testTask.setCondition(condition);
		if(testTask.getCondition() == tmp_condition)
			assert(true);
		else
			fail("was unable to set condition correctly");
			
		
		//test setOwner
		String owner = "a really unique owner";
		testTask.setOwner(owner);
		if(testTask.getOwner() == owner)
			assert(true);
		else
			fail("unable to get/set owner");
		
		//UserStory test
		if(testTask.getUserStory() != null)
			assert(true);
		else
			fail("unable to get user story");
		
		
	}
	
}
