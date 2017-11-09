package com.risk.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.risk.model.Continent;
import com.risk.model.Country;
import com.risk.model.Land;
import com.risk.model.Player;

/**
 * The class <code>TestCountry</code> contains tests for the class
 * <code> {@link Country}</code>
 * 
 * @author Ranjitha Shetty
 * @version 1.0
 */

public class TestCountry {

	Country country;
	Player player;
	Continent continent;

	/**
	 * Test case Initialization for TestCountry
	 */

	@Before
	public void RiskGameTestCase() {
		country = new Country("Canada", 10, 11, 12);
	}

	/**
	 * Test case to tests x value is correct
	 */

	@Test
	public void TestGetX() {
		System.out.println("testGetX");
		int x = country.GetX();
		System.out.println(x);
		assertEquals(11, x);
	}

	/**
	 * Test case to tests x value is correct
	 */

	@Test
	public void TestGetY() {
		System.out.println("testGetY");
		int y = country.GetY();
		System.out.println(y);
		assertEquals(12, y);
	}

	/**
	 * returning in int
	 */
	@Test
	public void TestGetArmies() {
		System.out.println("testGetArmies");
		int army = country.GetArmies();
		System.out.println(army);
		assertEquals(0, army);
	}

	@Test
	public void TestGetPlayerId() {
		System.out.println("testPlayerId");
		int id = country.GetPlayerId();
		System.out.println(id);
		assertEquals(-1, id);
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
		country = null;
		assertNull(country);
	}
}
