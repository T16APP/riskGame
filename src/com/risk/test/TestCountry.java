package com.risk.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.risk.model.Country;
import com.risk.model.Land;

public class TestCountry {
	/**
	 * country object is created to Country class to test --
	 * land object is created for Land Class to test the land
	 */
	
	Country country;
//	Land land;

	@Before
	public void testBefore()
	{
		country = new Country("Canada", 10, 11, 12);
		//land = new Land("newName");
	}

	@Test
	public void testGetPlayerId() {
		System.out.println("testGetPlayerId");
		int playerid = country.GetPlayerId();
		System.out.println(playerid);
		assertEquals(1, country.GetPlayerId());
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetX() {
		System.out.println("testGetX");
		int x = country.GetX();
		System.out.println(x);
		assertEquals(11,x);
	
	}
	
	@Test
	public void testGetY() {
		System.out.println("testGetY");
		int y = country.GetY();
		System.out.println(y);
		assertEquals(11,y);
	
	}
	
	@Test
	public void testGetContinentId() {
		System.out.println("testGetContinentId");
		int continentid = country.GetContinentId();
		System.out.println(continentid);
		assertEquals(10,continentid);
		
		/*@Test
		public void testGetArmies(){
			System.out.println("testGetArmies");
			int army = country.GetArmies();
			System.out.println(army);
			assertEquals(12,army);
		}
		*/
		
}
}

