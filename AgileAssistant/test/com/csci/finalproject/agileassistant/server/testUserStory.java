package com.csci.finalproject.agileassistant.server;
import java.util.Collections;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;
import com.csci.finalproject.agileassistant.client.ProjectType;
import com.csci.finalproject.agileassistant.client.TaskCondition;
import com.csci.finalproject.agileassistant.client.UserStoryCondition;
import com.csci.finalproject.agileassistant.client.UserStoryData;
import com.csci.finalproject.agileassistant.client.TaskData;

public class testUserStory{

	private String title = "user story test title";

	PersistentProject pp = new PersistentProject( null, "PP title", ProjectType.AGILE );
	UserStory testUserStory = new UserStory( pp, title);


	@Test
	public void  testgetKey() {
		//test key get
		Key tmp_key = testUserStory.getKey();
		if(tmp_key == null)
			assert(true);
		else
			fail("key is not equal NULL, test failed");
	}

	@Test
	public void testTitle() {
		String title_check = "this is a new title";
		testUserStory.setTitle(title_check);
		if(title_check == testUserStory.getTitle())
			assert(true);
		else
			fail("get or set title error has occured");
	}

	@Test
	public void testCondition() {
		testUserStory.setCondition(UserStoryCondition.WB);
		if(testUserStory.getCondition()==UserStoryCondition.WB)
			assert(true);
		else
			fail("unable to set condition");
	}

	@Test
	public void testgetTasks() {
		List<Task> tasks = testUserStory.getTasks();
		if(tasks!=null)
			assert(true);
		else
			fail("tasks not set yet");
	}

	@Test
	public void testPoints() {
		int points = 10;
		testUserStory.setPoints(points);
		if(testUserStory.getPoints()==10)
			assert(true);
		else
			fail("unable to set Points");
	}
	
	@Test
	public void testAddTask(){
		Task task = testUserStory.addTask("this is a new task");
		String taskTitle = task.getTitle();
		if(taskTitle == "this is a new task") //we need to get the title of the task
			assert(true);
		else
			fail("did not add task correctly");
		
	}
	
	//RemoveTask and genUserStoryData are not fully implemented/functional
	//cannot test until they are
	@Test
	public void testRemoveTask(){
		long numb = 0;
		if(testUserStory.removeTask(numb) == numb)
			assert(true);
		else
			fail("did not remove task correctly");
		
	}
	
	@Test
	public void testUserStoryData(){
		testUserStory.genUserStoryData();
		assert(true);
	}
	
	@Test
	public void testUpDateTask(){
		Long id = 1;
		Long userStoryID = 1001;
		TaskData taskdata = new TaskData(id, userStoryID, "this is the title", 5, TaskCondition.IN_PROGRESS, "Rico Suavy");
		
	}
	/*
	/*
	 * HELPER METHODS
	 */
	/*	private User getUser() {
		UserService userService = UserServiceFactory.getUserService();
		return userService.getCurrentUser();
	}
	 */
}
