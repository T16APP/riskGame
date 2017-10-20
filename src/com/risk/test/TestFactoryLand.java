package com.risk.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.risk.model.Continent;
import com.risk.model.Country;
import com.risk.model.FactoryLand;
import com.risk.model.Land;

/**
 * The class <code>TestFactoryLand</code> contains tests for the class 
 * <code> {@link FactoryLand}</code>
 * @author Ranjitha Shetty
 * @version 1.0
 */

public class TestFactoryLand {
	
	FactoryLand factoryLand;
	Country country;
	
	/**
	 * Test case Initialization for TestFactoryLand
	 */
	
	@Before
	public void BeforeTestFactoryLand() {
		factoryLand = new FactoryLand();
		country = new Country("new_name",9,8,7);
	}
	
	/**
	 * 
	 */
	@Test
	public void TestGetLand("landname","parametername", 1,23,45") {
		
	}
}
