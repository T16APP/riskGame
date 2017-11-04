package com.risk.test;

import static org.junit.Assert.*;

/**
 * This is Testclass for Player. It tests if player get name and Id succesfully and  he is able to Add or Remove land.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.risk.model.GameBoard;
import com.risk.model.Land;
import com.risk.model.Player;

/**
 * The class <code>TestPlayer</code> contains tests for the class
 * <code> {@link Player}</code>
 * 
 * @author Ranjitha Shetty
 * @version 1.0
 */

public class TestPlayer {

	Player player1;
	Player player2;

	/**
	 * Test case Initialization for TestFactoryLand
	 */

	@Before
	public void BeforeTestPlayer() {
		System.out.println("@BeforeClass");
		player1 = new Player(6, "sammy",null, null, null);
		player2 = new Player(7, "dammy",null, null, null);
	}

	/**
	 * testPlayerGetName shows GetName method in Player class works fine
	 */

	@Test
	public void TestGetName() {
		System.out.println("testGetName");
		String name = player1.GetName();
		System.out.println(name);
		assertEquals("sammy", name);

	}

	/**
	 * testPlayerGetName shows GetId method in Player class works fine
	 */

	@Test
	public void TestGetId() {
		System.out.println("testGetId");
		int id = player1.GetId();
		System.out.println(id);
		assertEquals(6, id);
	}

	/**
	 * This method deletes and sets variables to null after each test case
	 */

	@After
	public void TestAfter() {
		System.out.println("@AfterClass");

		// Add additional tear down code here
		System.out.println("@AfterClass - oneTimeTearDown");
		player1 = null;
		player2 = null;
		assertNull(player1);
		assertNull(player2);
	}
}
