package com.risk.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.risk.model.GameBoard;
import com.risk.model.Player;
import com.risk.model.TurnOrganizer;

/**
 * The class <code>TestTurnOrganizer</code> contains tests for the class 
 * <code> {@link GameBoard}</code>
 * @author Ranjitha Shetty
 * @version 1.0
 */

public class TestTurnOrganizer {
	
	TurnOrganizer turnOrganizer;

	/**
	 * Test case Initialization for TestTurnOrganizer
	 */
	@Before
	public void beforeTestTurnOrganizer() {
		System.out.println("@BeforeClass");
		turnOrganizer = new TurnOrganizer();
	}
		/*@Test
	public void testGetCurrentPhase() {
		
	}
		
	}*/
		

		/*@Test
		public void testGetNextPhase() {
			
		}
			
		}*/
		
		@Test
		public void testGetCurrentPlayerId() {
			
		}
		
}


