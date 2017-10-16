package com.risk.test;

import static org.junit.Assert.*;

/**
 * This is Testclass for Player. It tests if player get name and Id succesfully and  he is able to Add or Remove land.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.risk.model.Land;
import com.risk.model.Player;

public class TestPlayer {
	
		Player player1;
		Player player2;
		
		/**
		 * testBefore method runs before each testcase run
		 */

		@Before
		public void testBefore() {
			System.out.println("@BeforeClass");
			player1 = new Player(6, "sammy");
			player2 = new Player(7, "dammy");
		}
		
		/**
		 * testPlayerGetName shows GetName method in Player class works fine
		 */
		
		@Test
		public void testGetName()
		{
		System.out.println("testGetName");
		String name = player1.GetName();
		System.out.println(name);
		assertEquals("sammy",name);
		
		}
		
		/**
		 * testPlayerGetName shows GetId method in Player class works fine
		 */
		
		@Test
		public void testGetId()
		{
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
			
			int result2 = player1.AddLand(land1);
			System.out.println(result2);
			
			//0 means failure, player already has this country
			//1 mean success 
			assertEquals(1, result1);
			assertEquals(0, result2);
		
	}
		/**
		 * testRemoveLand method shows player is able to Remove Land in Player class without any issue
		 */
		
		@Test
		public void testRemoveLand() 
		{
		System.out.println("testRemoveLand");
		Land land1 = new Land("Canada");
		int resultOfRemove = player1.RemoveLand(land1);
		System.out.println(resultOfRemove);
		assertEquals(1, resultOfRemove);
		//assertEquals(0, resultOfRemove)
		}
		/**public boolean DoesOwnLand(Land new_land) 
	{
		boolean result = false;
		for (Land c : this.lands) 
		{
			if (new_land.GetId() == c.GetId()) 
			{
				result = true;
				return result;
			}
		}
		return result;
	}
		*/
		
		/**
		 * testDoesOwnLand method shows player is able to test DoesOwnLand Land in Player class without any issue
		 */
		
		@Test
		public void testDoesOwnLand()
		{
			System.out.print("testOwnLand");
			Land own = new Land("canada");
			boolean resultOfDoesOwnLand = player1.DoesOwnLand(own);
			System.out.print(resultOfDoesOwnLand);
			//assertTrue(2, resultOfDoesOwnLand);
			//assertFalse();
			
		}
		/**
		 * testAfter method runs after each testcase run
		 */
		@After
		public void testAfter() {
			System.out.println("@AfterClass");
		}

}
