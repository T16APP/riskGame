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
	public static void BeforeClass() {
		System.out.println("Entered TestContinent Class");
	}

	/**
	 * This method runs after all test cases were ran
	 */
	@AfterClass
	public static void AfterClass() {
		System.out.println("Left TestContinent Class");
	}
	
	/**
	 * This method initiate the variable before each test case
	 */
	
	@Before
	public void BeforeTestContinent() {
		continent = new Continent("Canada",5);
	}
	
	/**
	 * This testcase tests the continent Id in continent test class
	 * and shows that the id is equal to continent id
	 */
	@Test
	public void TestGetContinentId() {
	
		int id = continent.GetContinentId();
		System.out.println(id);
		assertEquals(1,id);
	}
	/**
	 * This testcase tests the continent control value in continent test class 
	 * and shows that the control value is 
	 * 
	 */
	
	@Test
	public void TestGetControl() {
		int continentcontrol = continent.GetControl();
		assertEquals(5,continentcontrol);	
	}
}
