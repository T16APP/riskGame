package com.risk.test;

import static org.junit.Assert.*;

/**
 * This is Testclass for Player. It tests if player get name and Id succesfully and  he is able to Add or Remove land.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.risk.model.GameBoard;
import com.risk.model.Land;
import com.risk.model.Player;
/**
 * The class <code>TestPlayer</code> contains tests for the class 
 * <code> {@link Player}</code>
 * @author Ranjitha Shetty
 * @version 1.0
 */

public class TestPlayer {
	
		Player player1;
		Player player2;
		
		/**
		 * Test case Initialization for TestFactoryLand
		 */

		@Before
		public void beforeTestFactoryLand() {
			System.out.println("@BeforeClass");
			player1 = new Player(6, "sammy");
			player2 = new Player(7, "dammy");
		}
		
		/**
		 * testPlayerGetName shows GetName method in Player class works fine
		 */
		
		@Test
		public void testGetName(){
		System.out.println("testGetName");
		String name = player1.GetName();
		System.out.println(name);
		assertEquals("sammy",name);
		
		}
		
		/**
		 * testPlayerGetName shows GetId method in Player class works fine
		 */
		
		@Test
		public void testGetId(){
			System.out.println("testGetId");
			int id = player1.GetId();
			System.out.println(id);
			assertEquals(6,id);
		}
		
		/**
		 * testAddLand method shows player is able to add Land in Player class without any issue
		 */
		
		@Test
		public void testAddLand() {
		
			System.out.println("Test addLand()");
			//System.out.println(player1.GetName());
			Land land1 = new Land("Canada");
			int result1 = player1.AddLand(land1);
			System.out.println(result1);
			//trying to add same country to the player again
			int result2 = player1.AddLand(land1);
			System.out.println(result2);
			
			//0 for failure, player already has this country, 1 for success
			assertEquals(1, result1);
			assertEquals(0, result2);
		
		}
		/**
		 * testRemoveLand method shows player is able to Remove Land in Player class without any issue
		 */
		
		@Test
		public void testRemoveLand(){
		System.out.println("testRemoveLand");
		Land land1 = new Land("Canada");
		player1.AddLand(land1);
		int resultOfRemove = player1.RemoveLand(land1);
		System.out.println("bbb "+resultOfRemove);
		assertEquals(1, resultOfRemove);
		int resultOfRemove2 = player1.RemoveLand(land1);
		System.out.println("asd "+resultOfRemove2);
		assertEquals(0, resultOfRemove2);
		
		}
		
		/**
		 * testDoesOwnLand method shows player is able to test DoesOwnLand Land in Player class without any issue
		 */
		
		@Test
		public void testDoesOwnLand(){
			System.out.print("testOwnLand");
			Land own = new Land("canada");
			player1.AddLand(own);
			boolean resultOfDoesOwnLand = player1.DoesOwnLand(own);
			System.out.print(resultOfDoesOwnLand);
			assertTrue(resultOfDoesOwnLand); //first
			Land notown = new Land("india");
			boolean resultOfDoesOwnLand2 = player1.DoesOwnLand(notown);
			System.out.print(resultOfDoesOwnLand2);
			assertFalse(resultOfDoesOwnLand2); //second
		}
	/**
	 * This method deletes and sets variables to null after each test case
	 */

		@After
		public void testAfter() {
			System.out.println("@AfterClass");
			
			// Add additional tear down code here
			System.out.println("@AfterClass - oneTimeTearDown");
			player1 = null;
			player2 = null;
			assertNull(player1);
			assertNull(player2);
		}
}
