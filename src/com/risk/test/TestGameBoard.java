package com.risk.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.risk.model.Country;
import com.risk.model.FactoryLand;
import com.risk.model.GameBoard;
import com.risk.model.Map;
import com.risk.model.Player;
import com.risk.utility.LoggingWindow;
import com.risk.utility.MapParser;
import com.risk.utility.TurnPhases;
import com.risk.utility.staticApplicationVariables;

/**
 * The class <code>TestGameBoard</code> contains tests for the class
 * <code> {@link GameBoard}</code>
 * 
 * @author Ranjitha Shetty
 * @version 1.0
 */

public class TestGameBoard {
	GameBoard gameBoard;

	/**
	 * Test case Initialization for TestGameBoard
	 */

	@Before
	public void BeforeTestGameBoard() {
		gameBoard = new GameBoard();
	}

	/**
	 * This testGetGameBoard checks the single instance creation (singleton)
	 */
	@Test
	public void TestGetGameBoard() {

		assertNotNull(gameBoard.GetGameBoard());
	}

	/**
	 * this method verfies the game startup by checking the number of armies
	 * 
	 * @throws Exception
	 *             if the logging window file does not exist
	 * 
	 */
	@Test
	public void TestStartupPhase() throws Exception {
		gameBoard = GameBoard.GetGameBoard();
		gameBoard.LoadMap("Earth.map");
		gameBoard.turnOrganizer.SetCurrentPhase(TurnPhases.Startup);
		;
		gameBoard.BuildDeck(gameBoard.map.GetCountries().size());
		gameBoard.SetupPlayers(3);
		gameBoard.turnOrganizer.players = gameBoard.players;
		gameBoard.AssignCountriesRandom();
		gameBoard.AllocateInitialArmies();
		int armies = gameBoard.players.get(0).GetArmies();
		System.out.println("Current player armies:" + armies);
		assertEquals(35, armies);
	}

}