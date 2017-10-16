package com.risk.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.risk.model.Edge;

public class TestEdge {
	
	Edge edge;
	
	@Before
	public void testBefore()
	{
		edge = new Edge(0, 0);
	}
	
	@Test
	public void testGetCountryId1() {
		{
			System.out.println("testGetCountryId1");
			int countryid1 = edge.GetCountryId1();
			System.out.println(countryid1);
			assertEquals(0,countryid1);
		}
		fail("Not yet implemented");
	}
	@Test
	public void testGetCountryId2() {
		{
			System.out.println("testGetCountryId2");
			int countryid2 = edge.GetCountryId2();
			System.out.println(countryid2);
			assertEquals(0,countryid2);
		}
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetId() {
		{
			System.out.println("testGetId");
			int id = edge.GetId();
			System.out.println(id);
			assertEquals(0,id);
		}
		fail("Not yet implemented");
	}
	


	@Test
	public void testCompareTo() {
		
	}
	
	@Test
	public void testDoesExistCountry()
	{
		
	}

	@Test
	public void testGetNeighborId()
	{
		
	}


	@Test
	public void testDoesContainCountry() {
	}
}




