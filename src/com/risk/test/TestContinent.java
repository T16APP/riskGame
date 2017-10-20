package com.risk.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.risk.model.Continent;
import com.risk.model.Land;

/**
 * The class <code>TestContinent</code> contains tests for the class 
 * <code> {@link Continent}</code>
 * @author Ranjitha Shetty
 * @version 1.0
 */

public class TestContinent {
	
	Continent continent;
	
	/**
	 * Test case Initialization for TestContinent
	 */
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println("Entered TestContinent Class");
	}

	/**
	 * This method runs after all test cases were ran
	 */
	@AfterClass
	public static void afterClass() {
		System.out.println("Left TestContinent Class");
	}
	
	/**
	 * This method initiate the variable before each test case
	 */
	
	@Before
	public void beforeTestContinent() {
		continent = new Continent("Canada",5);
	}
	
	/**
	 * 
	 */
	@Test
	public void testGetContinentId() {
	
		int id = continent.GetContinentId();
		System.out.println(id);
		assertEquals(1,id);
	}
	/**
	 * 
	 * 
	 */
	
	@Test
	public void testGetControl() {
		int continentcontrol = continent.GetControl();
		assertEquals(5,continentcontrol);	
	}
}
