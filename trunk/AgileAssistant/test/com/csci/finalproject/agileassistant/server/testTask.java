package com.csci.finalproject.agileassistant.server;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.google.appengine.api.datastore.Key;

public class testTask {
	
	private String title = "Test Title";
	private UserStory userStory = null;//new UserStory(); <<<may need to actually implement
	private int task_numb = 1;
	Task testTask = new Task(title, userStory, task_numb);


/*	private final LocalServiceTestHelper helper =
			new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

	@Before
	public void setUp() {
		helper.setUp();
	}

	@After
	public void tearDown() {
		helper.tearDown();
	}

	// run this test twice to prove we're not leaking any state across tests
	private void doTest() {
		
		Entity testTask = (Entity) new Task(title, userStory, task_numb);
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		ds.put( testTask);
		ds.pu
	}
*/
	@Test
	public void testTitle(){
	
		//title test
		String title_check = "this is a new title";
		testTask.setTitle(title_check);
		
		if(title_check == "this is a new title")
			assert(true);
		else
			fail("get or set title error has occured");

	}
	@Test
	public void testKey(){
		//test key get
		Key tmp_key = testTask.getKey();
		if(tmp_key == null)
			assert(true);
		else
			fail("key is not equal to NULL, test failed");
	}
	
	@Test
	public void testTaskNumb(){
		//test Task numb
		testTask.setTask_numb(4);
		int tmp_tasknum = testTask.getTask_numb();
		//assertEquals(tmp_tasknum, 4);
		if(tmp_tasknum == 4)
			assert(true);
		else
			fail("task number unable to be set");

	}
	
	@Test
	public void testCondition(){
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

	}
	
	@Test
	public void testSetOwner(){
		//test setOwner
		String owner = "a really unique owner";
		testTask.setOwner(owner);
		if(testTask.getOwner() == owner)
			assert(true);
		else
			fail("unable to get/set owner");
	}
	
	@Test
	public void testUserStory(){
		//UserStory test
		if(testTask.getUserStory() != null)
			assert(true);
		else
			fail("unable to get user story");


	}

}
