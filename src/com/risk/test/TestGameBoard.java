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
import com.risk.utility.MapParser;
import com.risk.utility.staticApplicationVariables;

/**
 * The class <code>TestGameBoard</code> contains tests for the class 
 * <code> {@link GameBoard}</code>
 * @author Ranjitha Shetty
 * @version 1.0
 */

public class TestGameBoard {
	GameBoard gameboard;
	
	/**
	 * Test case Initialization for TestGameBoard
	 */
	
	@Before
	public void BeforeTestGameBoard() {
		gameboard = new GameBoard();
	}
/**
 * This testGetGameBoard checks the single instance creation (singleton)
 */
	@Test
	public void TestGetGameBoard() {
		
		assertNotNull(gameboard.GetGameBoard()); 
	}
	
}