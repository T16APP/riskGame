package com.risk.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.risk.model.GameBoard;
import com.risk.model.Land;
import com.risk.model.Player;

/**
 * The class <code>TestLand</code> contains tests for the class
 * <code> {@link Land}</code>
 * 
 * @author Ranjitha Shetty
 * @version 1.0
 */

public class TestLand {

	Land land;

	/**
	 * Test case Initialization for TestLand
	 */

	@Before
	public void TestBefore() {
		System.out.println("@BeforeClass");
		land = new Land("new_name");

	}

	/**
	 * This Test case test the Name of the land is returned correctly
	 */

	@Test
	public void TestGetName() {
		System.out.println("testGetName");
		String landName = land.GetName();
		System.out.println(landName);
		assertEquals("new_name", landName);
	}

	/**
	 * This Test case test the Land playerId is returned correctly
	 */
	@Test
	public void TestGetId() {
		System.out.println("testGetLandId");
		int playerId = land.GetPlayerId();
		System.out.println(playerId);
		assertEquals(-1, playerId);

	}

	/**
	 * Perform post-test clean-up.
	 * 
	 * @throws Exception
	 *             if the clean-up fails for some reason
	 */
	@After
	public void TearDown() throws Exception {
		System.out.println("");
		land = null;
		assertNull(land);
	}
}
