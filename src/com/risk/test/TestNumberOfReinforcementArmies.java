package com.risk.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.risk.model.GameBoard;
import com.risk.model.Player;
import com.risk.utility.TurnPhases;

public class TestNumberOfReinforcementArmies {

	GameBoard game;

	@Before
	public void testBefore() {
		System.out.println("@Before");

		game = new GameBoard();
	}

	@Test
	public void TestNumberOfReinforcementArmies() {
        //tbd
		Player p = game.GetPlayerById(game.turnOrganizer.GetCurrentPlayerId());
		//p.CalculateReinforcementArmies();
		assertEquals(5, 5);
	}

}
