package com.csci.finalproject.agileassistant.server;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({testPersistentProject.class, testTask.class,
		testUserStory.class })
public class ServerTests {

}
