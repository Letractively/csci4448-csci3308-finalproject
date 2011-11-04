package com.csci.finalproject.agileassistant.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class testPersistentProject {

	@Test
	public void testSimple() {
		assertTrue( true );
	}
	
	@Test
	public void testConstructor() {
		String title = "Test Project";
		String type = "agile";
		
		PersistentProject pp = new PersistentProject(null, title, type);
		assertTrue( pp.getClass() == PersistentProject.class );
		
		assertEquals( pp.getTitle(), title );
		assertEquals( pp.getType(), type );
	}
}