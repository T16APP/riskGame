package com.risk.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import com.risk.model.Player;
import com.risk.model.TurnOrganizer;

public class TestTurnOrganizer {
	
	TurnOrganizer turnOrganizer;

	
	@Before
	public void testBefore() {
		System.out.println("@BeforeClass");
		turnOrganizer = new TurnOrganizer();
	}
		/*@Test
	public void testGetCurrentPhase() {
		
	}
		fail("Not yet implemented");
	}*/
		

		/*@Test
		public void testGetNextPhase() {
			
		}
			fail("Not yet implemented");
		}*/
		
		@Test
		public void testGetCurrentPlayerId() {
			
		}
		
}


