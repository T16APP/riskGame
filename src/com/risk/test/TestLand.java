package com.risk.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.risk.model.GameBoard;
import com.risk.model.Land;
import com.risk.model.Player;

/**
 * The class <code>TestLand</code> contains tests for the class 
 * <code> {@link Land}</code>
 * @author Ranjitha Shetty
 * @version 1.0
 */

public class TestLand {
	
	Land land;
	
	/**
	 * Test case Initialization for TestLand
	 */
	
	@Before
	public void testBefore() {
		System.out.println("@BeforeClass");
		land = new Land("new_name");
		
	}
	
	/**
	 * 
	 */

	@Test
	public void testGetName() {
		System.out.println("testGetName");
		String landName = land.GetName();
		System.out.println(landName);
		assertEquals("new_name",landName);
	}
	
	/**
	 * 
	 */
	@Test
	public void testGetId() {
		System.out.println("testGetId");
		int landId = land.GetId();
		System.out.println(landId);
		assertEquals(1,landId);

	}
}
