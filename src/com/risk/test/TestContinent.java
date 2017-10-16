package com.risk.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.risk.model.Continent;
import com.risk.model.Land;

/**
 * 
 * @author Ranjitha Shetty
 * This is the Class to Test Continent
 */

public class TestContinent {
	/**
	 * object continent is created to access Continent Class  
	 */
	
	Continent continent;
	
	@Before
	public void testBefore()
	{
		System.out.println("@BeforeClass");
		continent = new Continent("Canada",5);
	}

	@Test
	public void testGetContinentId() {
	
		//System.out.println(continent.GetContinentId());	
		int id = continent.GetContinentId();
		System.out.println(id);
		assertEquals(continent,id);
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetControl()
	{
		int continentcontrol = continent.GetControl();
		assertEquals(continent,continentcontrol);
		fail("Not yet implemented");
	}

}
