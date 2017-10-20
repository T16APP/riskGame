package com.risk.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * This class is a test suite for all the riskGame test cases
 * 
 * @author Ranjitha Shetty
 * @version 1.0
 */
@RunWith(Suite.class)
@SuiteClasses({ TestContinent.class, TestCountry.class, TestEdge.class, TestGameBoard.class,
		TestLand.class, TestMap.class, TestMapParser.class, TestNumberOfReinforcementArmies.class, TestPlayer.class,
		})
public class TestSuite {

}
