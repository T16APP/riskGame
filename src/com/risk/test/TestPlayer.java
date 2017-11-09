package com.risk.test;

import static org.junit.Assert.*;

/**
 * This is Testclass for Player. It tests if player get name and Id succesfully and  he is able to Add or Remove land.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.risk.model.Country;
import com.risk.model.GameBoard;
import com.risk.model.Land;
import com.risk.model.Player;
import com.risk.utility.TurnPhases;

/**
 * The class <code>TestPlayer</code> contains tests for the class
 * <code> {@link Player}</code>
 * 
 * @author Ranjitha Shetty
 * @version 1.0
 */

public class TestPlayer {

	Player player1;
	Player player2;
	GameBoard gameBoard;

	/**
	 * Test case Initialization for TestFactoryLand
	 * 
	 * @throws Exception
	 *             Exception if the logging window file does not exist
	 */

	@Before
	public void BeforeTestPlayer() throws Exception {
		System.out.println("@BeforeClass");
		player1 = new Player(6, "sammy", null, null, null);
		player2 = new Player(7, "dammy", null, null, null);
		gameBoard = GameBoard.GetGameBoard();
		gameBoard.LoadMap("Earth.map");
	}

	/**
	 * testPlayerGetName shows GetName method in Player class works fine
	 */

	@Test
	public void TestGetName() {
		System.out.println("testGetName");
		String name = player1.GetName();
		System.out.println(name);
		assertEquals("sammy", name);

	}

	/**
	 * testPlayerGetName shows GetId method in Player class works fine
	 */

	@Test
	public void TestGetId() {
		System.out.println("testGetId");
		int id = player1.GetId();
		System.out.println(id);
		assertEquals(6, id);
	}

	/**
	 * this method verifies the reinforcement calculation
	 * 
	 * @throws Exception
	 *             Exception if the logging window file does not exist
	 * 
	 */
	@Test
	public void TestReinforcementCalculation() throws Exception {
		gameBoard.StartupGame(3);
		gameBoard.players.get(0).CalculateReinforcementArmies();
		int armies = gameBoard.players.get(0).GetArmies();
		System.out.println("Current player armies:" + armies);
		assertEquals(11, armies);
	}

	/**
	 * this test verifies conformity of fortification to the rules
	 * 
	 * @throws Exception
	 *             Exception if the logging window file does not exist
	 * 
	 */
	@Test
	public void TestFortification() throws Exception {
		gameBoard.StartupGame(3);
		Player currentPlayer = gameBoard.turnOrganizer.GetCurrentPlayer();
		Country countryS = gameBoard.map.GetCountryByName("Cockpit01");
		Country countryD = gameBoard.map.GetCountryByName("Cockpit02");
		countryS.SetArmies(5);
		countryD.SetArmies(5);
		int armiesD = countryD.GetArmies();
		gameBoard.map.GetCountryByName("Cockpit01").SetPlayerId(currentPlayer.GetId());
		gameBoard.map.GetCountryByName("Cockpit02").SetPlayerId(currentPlayer.GetId());
		gameBoard.turnOrganizer.GetCurrentPlayer().MoveArmiesToCountryFromCountry(countryS.GetId(), countryD.GetId(),
				1);
		assertEquals("PhaseNotValid", gameBoard.turnOrganizer.GetCurrentPlayer()
				.MoveArmiesToCountryFromCountry(countryS.GetId(), countryD.GetId(), 1));
	}

	/**
	 * this method verifies attack conformity to the rules /**this applies
	 * conditions after test
	 * 
	 * @throws Exception
	 *             Exception if the logging window file does not exist
	 * 
	 */
	@Test
	public void TestAttackValidphase() throws Exception {
		gameBoard.StartupGame(3);
		Player currentPlayer = gameBoard.turnOrganizer.GetCurrentPlayer();
		Country countryS = gameBoard.map.GetCountryByName("Cockpit01");
		Country countryD = gameBoard.map.GetCountryByName("Cockpit02");
		countryS.SetArmies(5);
		countryD.SetArmies(5);
		int armiesD = countryD.GetArmies();
		gameBoard.map.GetCountryByName("Cockpit01").SetPlayerId(currentPlayer.GetId());
		gameBoard.map.GetCountryByName("Cockpit02").SetPlayerId(currentPlayer.GetId() + 1);
		gameBoard.turnOrganizer.GetCurrentPlayer().Attack(2, 2);
		assertEquals("PhaseNotValid", gameBoard.turnOrganizer.GetCurrentPlayer()
				.MoveArmiesToCountryFromCountry(countryS.GetId(), countryD.GetId(), 1));
	}

	/**
	 * this method verifies attack declaration conformity to the rules /**this
	 * applies conditions after test
	 * 
	 * @throws Exception
	 *             Exception if the logging window file does not exist
	 * 
	 */
	@Test
	public void TestDeclareAttackValidphase() throws Exception {
		gameBoard.StartupGame(3);
		Player currentPlayer = gameBoard.turnOrganizer.GetCurrentPlayer();
		Player defenderPlayer = gameBoard.GetPlayerById(gameBoard.turnOrganizer.GetCurrentPlayer().GetId() + 1);
		Country countryS = gameBoard.map.GetCountryByName("Cockpit01");
		Country countryD = gameBoard.map.GetCountryByName("Cockpit02");
		countryS.SetArmies(5);
		countryD.SetArmies(5);
		int armiesD = countryD.GetArmies();
		gameBoard.map.GetCountryByName("Cockpit01").SetPlayerId(currentPlayer.GetId());
		gameBoard.map.GetCountryByName("Cockpit02").SetPlayerId(currentPlayer.GetId() + 1);
		gameBoard.turnOrganizer.GetCurrentPlayer().DeclareAttack(countryS.GetId(), countryD.GetId(), defenderPlayer);
		assertEquals("PhaseNotValid", gameBoard.turnOrganizer.GetCurrentPlayer()
				.MoveArmiesToCountryFromCountry(countryS.GetId(), countryD.GetId(), 1));
	}

	/**
	 * this methodes verifies the end of the game method
	 * 
	 * @throws Exception
	 *             Exception if the logging window file does not exist
	 * 
	 */
	@Test
	public void TestEndOfGame() throws Exception {
		gameBoard.StartupGame(3);
		gameBoard.turnOrganizer.GetCurrentPlayer().EndGame();
		assertEquals(gameBoard.turnOrganizer.GetCurrentPhase(), TurnPhases.GameOver);
	}

	@After
	public void TestAfter() {
		System.out.println("@AfterClass");

		// Add additional tear down code here
		System.out.println("@AfterClass - oneTimeTearDown");
		player1 = null;
		player2 = null;
		assertNull(player1);
		assertNull(player2);
	}
}
