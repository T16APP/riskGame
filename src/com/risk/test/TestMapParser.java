package com.risk.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.risk.model.Continent;
import com.risk.model.Country;
import com.risk.model.FactoryLand;
import com.risk.model.GameBoard;
import com.risk.model.Land;
import com.risk.model.Map;
import com.risk.utility.MapParser;

import org.junit.Test;

/**
 * The class <code>TestMapParser</code> contains tests for the class
 * <code> {@link MapParser}</code>
 * 
 * @author Ranjitha Shetty
 * @version 1.0
 */

public class TestMapParser {
	/**
	 * Test case Initialization for TestMapParser
	 */

	@Before
	public void BeforeTestMapMarser() {

	}

	/**
	 * 
	 * @throws IOException
	 */

	@Test
	public void TestInvalidHeader() {
		try {
			assertEquals("Legal Values: Head validator should be [Map]",
					MapParser.MapValidator("InvalidHeaderValidator.map"));
			Assert.fail("Should have thrown an exception");
		} catch (Exception e) {
			String expectedMessage = "Header validator failed";
			Assert.assertEquals("Exception message must be correct", expectedMessage, e.getMessage());
		}
	}

	@Test
	public void TestLessThan5countries() {

		try {
			assertEquals("Legal Values: Countries should be more than 5",
					MapParser.MapValidator("lessthan5countries.map"));
			Assert.fail("Should have thrown an exception");
		} catch (Exception e) {
			String expectedMessage = "Map contains less than 5 countries!";
			Assert.assertEquals("Exception message must be correct", expectedMessage, e.getMessage());
		}
	}

	@Test
	public void TestMapMustContainContinents() {

		try {
			assertEquals("Legal Values: Map must contain continents", MapParser.MapValidator("Nocontinents.map"));
			Assert.fail("Should have thrown an exception");
		} catch (Exception e) {
			String expectedMessage = "Map contains no continent!";
			Assert.assertEquals("Exception message must be correct", expectedMessage, e.getMessage());
		}
	}

	@Test
	public void TestInvalidMapFile() {
		try {
			assertEquals("Legal Values: Map must contain continents", MapParser.MapValidator("invalidFile.pdf"));
			Assert.fail("Should have thrown an exception");
		} catch (Exception e) {
			String expectedMessage = "Invalid Filr selected";
			Assert.assertEquals("Exception message must be correct", expectedMessage, e.getMessage());
		}
	}

}
