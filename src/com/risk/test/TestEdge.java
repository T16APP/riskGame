package com.risk.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.risk.model.Edge;

/**
 * The class <code>TestEdge</code> contains tests for the class 
 * <code> {@link Edge}</code>
 * @author Ranjitha Shetty
 * @version 1.0
 */

public class TestEdge {
	
	Edge edge;
	
	/**
	 * Test case Initialization for TestEdge
	 */
	
	@Before
	public void BeforeTestEdge() {
		edge = new Edge(0, 0);
	}
	
	/**
	 * Test case to get Player Id
	 */
	
	@Test
	public void TestGetCountryId1() {
			System.out.println("testGetCountryId1");
			int countryid1 = edge.GetCountryId1();
			System.out.println(countryid1);
			assertEquals(0,countryid1);
		}
	
	/**
	 * Test case to get Player Id
	 */
	
	@Test
	public void TestGetCountryId2() {
			System.out.println("testGetCountryId2");
			int countryid2 = edge.GetCountryId2();
			System.out.println(countryid2);
			assertEquals(0,countryid2);
		}

}




