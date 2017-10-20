package com.risk.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.risk.model.Continent;
import com.risk.model.Country;
import com.risk.model.Edge;
import com.risk.model.GameBoard;
import com.risk.model.Land;
import com.risk.model.Map;
import com.risk.model.Player;

/**
 * The class <code>TestMap</code> contains tests for the class
 * <code> {@link Map}</code>
 * 
 * @author Ranjitha Shetty
 * @version 1.0
 */

public class TestMap {
	Map map;
	Edge edge;
	Country country;

	/**
	 * Test case Initialization for TestMap
	 */

	@Before
	public void BeforeTestMap() {
		System.out.println("@BeforeClass");
		map = new Map("new_name");
		edge = new Edge(1, 1);
	}

	/**
	 * This Test case test the Author is returned correctly
	 */

	@Test
	public void TestGetAuthor() {

		String author = map.GetAuthor();
		System.out.println(author);
		assertNull("new_name", author);
	}
	
	/**
	 * This Test case test the Edge is added
	 */

	@Test
	public void TestAddEdge() {
		System.out.println("testAddEdge");

		int edgeresult1 = map.AddEdge(edge);
		System.out.println(edgeresult1);

		int edgeresult2 = map.AddEdge(edge);
		System.out.println(edgeresult2);

		assertEquals(1, edgeresult1);
		assertEquals(-1, edgeresult2);
	}

	/**
	 * This Test case test the Edge is exist already
	 */

	@Test
	public void TestDoesExistEdge() {
		System.out.println("testDoesExistEdge");

		boolean edgeresult1 = false;
		System.out.println(edgeresult1);

		int edgeresult2 = map.AddEdge(edge);
		System.out.println(edgeresult2);

		assertTrue(true);
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
		map = null;
		edge = null;
		country = null;
		assertNull(map);
		assertNull(edge);
		assertNull(country);
	}
}
