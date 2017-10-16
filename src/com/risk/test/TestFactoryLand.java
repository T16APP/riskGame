package com.risk.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.risk.model.Continent;
import com.risk.model.Country;
import com.risk.model.FactoryLand;
import com.risk.model.Land;

public class TestFactoryLand {
	
	FactoryLand factoryLand;
	Country country;
	
	@Before
	public void testBefore()
	{
		factoryLand = new FactoryLand();
		country = new Country("new_name",9,8,7);
	}

	@Test
	public void testFactoryLand() {
	
		fail("Not yet implemented");
	}

}
