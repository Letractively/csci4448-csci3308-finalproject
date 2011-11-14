package com.csci.finalproject.agileassistant.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.csci.finalproject.agileassistant.client.ProjectType;

public class testPersistentProject {

	@Test
	public void testSimple() {
		assertTrue( true );
	}
	
	@Test
	public void testConstructor() {
		String title = "Test Project";
		
		PersistentProject pp = new PersistentProject(null, title, ProjectType.AGILE);
		assertTrue( pp.getClass() == PersistentProject.class );
		
		assertEquals( pp.getTitle(), title );
		assertEquals( pp.getProjectType(), ProjectType.AGILE );
	}
}
