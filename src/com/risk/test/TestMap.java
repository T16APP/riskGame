package com.risk.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
 * @author Ranjitha Shetty
 * @version 1.0
 */

public class TestMap {
	Map map;
	Edge edge;
	
	/**
	 * Test case Initialization for TestMap
	 */
	
	@Before
	public void beforeTestMap() {
		System.out.println("@BeforeClass");
		map = new Map("new_name");	
		edge = new Edge(1,1);
	}
	/**
	 * 
	 */

	@Test
	public void testGetAuthor() {
		
		String author = map.GetAuthor();
		System.out.println(author);
		assertEquals("new_name",author);
	}
/**
 * 
 */

@Test
public void testGetImage() {
	String image = map.GetImage();
	System.out.println(image);
	assertEquals("new_name",image);
	fail("Not yet implemented");
}

/**
 * 
 */
		
@Test
public void testLandByName() {
	Land landname = map.GetLandByName("landname");
	System.out.println(landname);
	assertEquals("landname1",landname);
	assertEqual(true);

}

/**
 * 
 */
@Test
public void testRemoveContinent() {
	
}
/**
 * 
 */

@Test
public void testDoesExistCountry() {
	
}
/**
 * 
 */
@Test
public void testDoesExistContinent() {
	
}
/**
 * 
 */
@Test
public void testAddEdge() {
	
}
/**
 * 
 */
@Test
public void testAddCountry() {
	
}

@Test
public void testDoesExistEdge() {
	
}
/**
 * 
 */
@Test
public void testGetCountriesNotAssigned() {
	
}
/**
 * 
 */


@Test
public void testGetCountryIdByName() {
	
}

@Test
public void testGetCountryByName() {
	
}
}


/*@Test
public void testAddEdge() {
	System.out.println("testAddEdge");

	int edgeresult1 = map.AddEdge(edge);
	System.out.println(edgeresult1);
	
	int edgeresult2 = map.AddEdge(edge);
	System.out.println(edgeresult2);
	
	assertEquals(1, edgeresult1);
	assertEquals(0, edgeresult2);
	
	fail("Not yet implemented");
}



@Test
public void testDoesExistEdge() {
	System.out.println("testDoesExistEdge");
	
	boolean edgeresult1 = false;
	System.out.println(edgeresult1);
	
	int edgeresult2 = map.AddEdge(edge);
	System.out.println(edgeresult2);
	
	assertTrue(true);
	assertFalse(true);
	
	fail("Not yet implemented");

}
*/

