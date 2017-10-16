package com.risk.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.risk.model.Land;
import com.risk.model.Player;

public class TestLand {
	
	Land land;
	
	
	@Before
	public void testBefore() {
		System.out.println("@BeforeClass");
		land = new Land("new_name");
		
	}

	@Test
	public void testGetName() {
		System.out.println("testGetName");
		String landName = land.GetName();
		System.out.println(landName);
		assertEquals("new_name",landName);
		
		fail("Not yet implemented");
	}
	@Test
	public void testGetId() {
		System.out.println("testGetId");
		String landId = land.GetName();
		System.out.println(landId);
		assertEquals(1,landId);
		
		fail("Not yet implemented");
	}

}
