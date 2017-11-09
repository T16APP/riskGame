package com.risk.model;

import com.risk.utility.*;
import com.risk.view.applicationWindow;

import java.io.IOException;

public class Console {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		// applicationWindow appWindow = new applicationWindow();
		// System.out.println("Game started");
		// appWindow.open();
		Demo_StartUp_Reinforcement();
	}

	public static void Demo_StartUp_Reinforcement() throws Exception {
		GameBoard gameBoard = GameBoard.GetGameBoard();
		gameBoard.LoadMap("Earth.map");
		gameBoard.StartupGame(3);
		Player currentPlayer = gameBoard.turnOrganizer.GetCurrentPlayer();
		Country defenderCountry = gameBoard.map.GetCountriesByPlayerId(currentPlayer.GetId()).get(0);
		Country countryAttacker = gameBoard.map.GetCountriesByPlayerId(currentPlayer.GetId()).get(0);
		Country countryNeighbor = gameBoard.map.GetNeighborsByCountryIdSamePlayer(countryAttacker.GetId()).get(0);
		Player defenderPlayer = gameBoard.GetPlayerById(defenderCountry.GetPlayerId());
		for (Country c : gameBoard.map.GetCountriesByPlayerId(currentPlayer.GetId())) {
			if (gameBoard.map.GetNeighborsByCountryIdOpponentPlayer(c.GetId()).size() > 0) {
				countryAttacker = c;
				defenderCountry = gameBoard.map.GetNeighborsByCountryIdOpponentPlayer(c.GetId()).get(0);
				break;
			}
		}
		System.out.println("_____Reinforcement place armies demo_____");
		System.out.println("Number of player armies befor reinforcement:" + currentPlayer.GetArmies());
		System.out.println("Number of country armies befor reinforcement:" + countryAttacker.GetArmies());
		currentPlayer.PlaceReinforcedArmiesOnCountry(countryAttacker.GetId(), 11);
		System.out.println("Number of player armies after reinforcement:" + currentPlayer.GetArmies());
		System.out.println("Number of country armies after reinforcement:" + countryAttacker.GetArmies());
		System.out.println("_____End of Reinforcement place armies demo_____");
		currentPlayer.EndReinforcementPhase();
		System.out.println("Attack  demo_____");
		System.out.println("Number of attacker country armies befor attck:" + countryAttacker.GetArmies());
		System.out.println("Number of defender country armies befor attck:" + defenderCountry.GetArmies());
		currentPlayer.DeclareAttack(countryAttacker.GetId(), defenderCountry.GetId(), defenderPlayer);
		currentPlayer.Attack(2, 2);
		System.out.println("Number of attacker country armies after attck:" + countryAttacker.GetArmies());
		System.out.println("Number of defender country armies after attck:" + defenderCountry.GetArmies());
		if (gameBoard.turnOrganizer.GetCurrentPlayer().attack.isCaptured) {
			System.out.println("Number of attacker country armies befor occupation:" + countryAttacker.GetArmies());
			System.out.println("Number of defender country armies befor occupation:" + defenderCountry.GetArmies());
			currentPlayer.OccupyCountry(3);
			System.out.println("Number of attacker country armies after occupation:" + countryAttacker.GetArmies());
			System.out.println("Number of defender country armies after occupation:" + defenderCountry.GetArmies());
		}
		currentPlayer.EndAttackPhase();
		System.out.println("End of Attack  demo_____");
		System.out.println("Fortification  demo_____");
		System.out.println("Number of source country armies befor fortification:" + countryAttacker.GetArmies());
		System.out.println("Number of destination country armies befor fortification:" + countryNeighbor.GetArmies());
		currentPlayer.MoveArmiesToCountryFromCountry(countryAttacker.GetId(), countryNeighbor.GetId(), 2);
		System.out.println("Number of source country armies after fortification:" + countryAttacker.GetArmies());
		System.out.println("Number of destination country armies after fortification:" + countryNeighbor.GetArmies());
		System.out.println("End of Fortification  demo_____");
	}

	public static void Demo_MapConnectivityValidation() throws Exception {
		GameBoard gameBoard = GameBoard.GetGameBoard(); // header validation
		gameBoard.LoadMap("Earth_Validation_MapConnectivity.map");
		// gameBoard.LoadMap("Earth_Validation_ContinentConnectivity.map");
		// gameBoard.LoadMap("Earth.map");
		boolean isConnectivityValid = gameBoard.map.ValidationMapConnectivity();
		// boolean isConnectivityValid =
		// gameBoard.map.ValidateContinentsConnectivity();
		System.out.println(isConnectivityValid);
	}

	public static void Demo_ContinentConnectivityValidation() throws Exception {
		GameBoard gameBoard = GameBoard.GetGameBoard(); // header validation
		// gameBoard.LoadMap("Earth_Validation_MapConnectivity.map");
		gameBoard.LoadMap("Earth_Validation_ContinentConnectivity.map");
		// gameBoard.LoadMap("Earth.map");
		// boolean isConnectivityValid =
		// gameBoard.map.ValidationMapConnectivity();
		boolean isConnectivityValid = gameBoard.map.ValidateContinentsConnectivity();
		System.out.println(isConnectivityValid);
	}
}
/*
 * public static void Demo_MapValidation() throws Exception { GameBoard
 * gameBoard = GameBoard.GetGameBoard(); //header validation
 * //gameBoard.MapValidator("Earth_Headers_NotValid.map"); //continent
 * validation //gameBoard.MapValidator("Earth_NumberOfContinents_NotValid.map");
 * //countries validation
 * gameBoard.MapValidator("Earth_NumberOfCountries_NotValid.map");
 * 
 * } public static void Demo_addingCountry() throws Exception { int
 * givenCountryId=-1; GameBoard gameBoard = GameBoard.GetGameBoard();
 * gameBoard.LoadMap("Earth.map"); gameBoard.map.AddCountry("testc", 1, 20, 20);
 * 
 * 
 * } public static void Demo_ShowingAddedCountry() throws Exception { int
 * givenCountryId=-1; GameBoard gameBoard = GameBoard.GetGameBoard(); for(int
 * i=0;i<gameBoard.map.GetCountriesByContinentId(1).size();i++) {
 * System.out.println("list of countrt for the continent"+gameBoard.map.
 * GetCountriesByContinentId(1).get(i).name); }
 * 
 * } public static void Demo_ChangingTurnAndPhase() throws Exception { int
 * playerNum=2; int currentPlayerId=-1; int givenCountryId=-1; GameBoard
 * gameBoard = GameBoard.GetGameBoard(); gameBoard.LoadMap("Earth.map");
 * gameBoard.StartupGame(playerNum);
 * currentPlayerId=gameBoard.turnOrganizer.GetCurrentPlayerId();
 * System.out.println("Current PlayerId: "+gameBoard.turnOrganizer.
 * GetCurrentPlayerId());
 * System.out.println("Current Phase: "+gameBoard.turnOrganizer.GetCurrentPhase(
 * )); System.out.println("Current player armies: "+gameBoard.GetPlayerById(
 * gameBoard.turnOrganizer.GetCurrentPlayerId()).GetArmies());
 * System.out.println("Current player cards: "+gameBoard.GetCardsByPlayerId(
 * currentPlayerId).size());
 * givenCountryId=gameBoard.map.GetCountriesByPlayerId(currentPlayerId).get(0).
 * GetId(); gameBoard.PlaceArmiesOnCountry(givenCountryId,5);
 * gameBoard.EndReinforcementPhase();
 * currentPlayerId=gameBoard.turnOrganizer.GetCurrentPlayerId();
 * System.out.println("Current PlayerId: "+gameBoard.turnOrganizer.
 * GetCurrentPlayerId());
 * System.out.println("Current Phase: "+gameBoard.turnOrganizer.GetCurrentPhase(
 * )); System.out.println("Current player armies: "+gameBoard.GetPlayerById(
 * gameBoard.turnOrganizer.GetCurrentPlayerId()).GetArmies());
 * System.out.println("Current player cards: "+gameBoard.GetCardsByPlayerId(
 * currentPlayerId).size());
 * givenCountryId=gameBoard.map.GetCountriesByPlayerId(currentPlayerId).get(0).
 * GetId(); //gameBoard.PlaceArmiesOnCountry(givenCountryId,5);
 * gameBoard.MoveArmiesToCountryFromCountry(givenCountryId,
 * gameBoard.map.GetCountriesByPlayerId(currentPlayerId).get(1).GetId(), 1);
 * gameBoard.EndFortificationPhase();
 * currentPlayerId=gameBoard.turnOrganizer.GetCurrentPlayerId();
 * System.out.println("Current PlayerId: "+gameBoard.turnOrganizer.
 * GetCurrentPlayerId());
 * System.out.println("Current Phase: "+gameBoard.turnOrganizer.GetCurrentPhase(
 * )); System.out.println("Current player armies: "+gameBoard.GetPlayerById(
 * gameBoard.turnOrganizer.GetCurrentPlayerId()).GetArmies());
 * System.out.println("Current player cards: "+gameBoard.GetCardsByPlayerId(
 * currentPlayerId).size());
 * givenCountryId=gameBoard.map.GetCountriesByPlayerId(currentPlayerId).get(0).
 * GetId(); gameBoard.PlaceArmiesOnCountry(givenCountryId,5);
 * gameBoard.EndReinforcementPhase();
 * currentPlayerId=gameBoard.turnOrganizer.GetCurrentPlayerId();
 * System.out.println("Current PlayerId: "+gameBoard.turnOrganizer.
 * GetCurrentPlayerId());
 * System.out.println("Current Phase: "+gameBoard.turnOrganizer.GetCurrentPhase(
 * )); System.out.println("Current player armies: "+gameBoard.GetPlayerById(
 * gameBoard.turnOrganizer.GetCurrentPlayerId()).GetArmies());
 * System.out.println("Current player cards: "+gameBoard.GetCardsByPlayerId(
 * currentPlayerId).size());
 * givenCountryId=gameBoard.map.GetCountriesByPlayerId(currentPlayerId).get(0).
 * GetId(); //gameBoard.PlaceArmiesOnCountry(givenCountryId,5);
 * gameBoard.EndFortificationPhase();
 * currentPlayerId=gameBoard.turnOrganizer.GetCurrentPlayerId();
 * System.out.println("Current PlayerId: "+gameBoard.turnOrganizer.
 * GetCurrentPlayerId());
 * System.out.println("Current Phase: "+gameBoard.turnOrganizer.GetCurrentPhase(
 * )); System.out.println("Current player armies: "+gameBoard.GetPlayerById(
 * gameBoard.turnOrganizer.GetCurrentPlayerId()).GetArmies());
 * System.out.println("Current player cards: "+gameBoard.GetCardsByPlayerId(
 * currentPlayerId).size());
 * givenCountryId=gameBoard.map.GetCountriesByPlayerId(currentPlayerId).get(0).
 * GetId(); gameBoard.PlaceArmiesOnCountry(givenCountryId,5);
 * gameBoard.EndReinforcementPhase();
 * currentPlayerId=gameBoard.turnOrganizer.GetCurrentPlayerId();
 * System.out.println("Current PlayerId: "+gameBoard.turnOrganizer.
 * GetCurrentPlayerId());
 * System.out.println("Current Phase: "+gameBoard.turnOrganizer.GetCurrentPhase(
 * )); System.out.println("Current player armies: "+gameBoard.GetPlayerById(
 * gameBoard.turnOrganizer.GetCurrentPlayerId()).GetArmies());
 * System.out.println("Current player cards: "+gameBoard.GetCardsByPlayerId(
 * currentPlayerId).size());
 * givenCountryId=gameBoard.map.GetCountriesByPlayerId(currentPlayerId).get(0).
 * GetId(); gameBoard.PlaceArmiesOnCountry(givenCountryId,5);
 * gameBoard.EndFortificationPhase(); } public static void
 * Demo_AssignCountriesRandomly() throws Exception { int playerNum=5; int
 * currentPlayerId=-1; int givenCountryId=-1; GameBoard gameBoard =
 * GameBoard.GetGameBoard(); gameBoard.LoadMap("Earth.map");
 * gameBoard.StartupGame(playerNum);
 * System.out.println("Size of map: "+gameBoard.map.GetCountries().size());
 * for(int i =1;i<playerNum*3;i++) {
 * System.out.println(gameBoard.turnOrganizer.GetCurrentPlayerId());
 * System.out.println(gameBoard.map.GetCountriesByPlayerId(gameBoard.
 * turnOrganizer.GetCurrentPlayerId()).size()); gameBoard.GetNextPlayerId(); }
 * System.out.println("State of map: "+gameBoard.turnOrganizer.IsMapLoaded());
 * System.out.println("State of game: "+gameBoard.turnOrganizer.IsGameStarted())
 * ; System.out.println("Current PlayerId: "+gameBoard.turnOrganizer.
 * GetCurrentPlayerId());
 * System.out.println("Current Phase: "+gameBoard.turnOrganizer.GetCurrentPhase(
 * )); System.out.println("Current player armies: "+gameBoard.GetPlayerById(
 * gameBoard.turnOrganizer.GetCurrentPlayerId()).GetArmies());
 * gameBoard.EndReinforcementPhase();
 * System.out.println("Current player armies: "+gameBoard.GetPlayerById(
 * gameBoard.turnOrganizer.GetCurrentPlayerId()).GetArmies());
 * System.out.println("change phase:");
 * System.out.println("Current PlayerId: "+gameBoard.turnOrganizer.
 * GetCurrentPlayerId());
 * System.out.println("Current Phase: "+gameBoard.turnOrganizer.GetCurrentPhase(
 * )); gameBoard.EndFortificationPhase();
 * currentPlayerId=gameBoard.turnOrganizer.GetCurrentPlayerId(); for(int i
 * =0;i<11;i++) { gameBoard.DrawACard(currentPlayerId);
 * System.out.println("Size of deck: "+gameBoard.GetUnassignedCards().size());
 * System.out.println("# of cards of player: "+currentPlayerId+"is :"+gameBoard.
 * GetCardsByPlayerId(currentPlayerId).size()); }
 * System.out.println("Current player armies before exchange: "+gameBoard.
 * GetPlayerById(gameBoard.turnOrganizer.GetCurrentPlayerId()).GetArmies());
 * gameBoard.ExchangeCards(currentPlayerId);
 * System.out.println("Size of deck: "+gameBoard.GetUnassignedCards().size());
 * System.out.println("# of cards of player: "+currentPlayerId+"is :"+gameBoard.
 * GetCardsByPlayerId(currentPlayerId).size());
 * System.out.println("Current player armies after exchange: "+gameBoard.
 * GetPlayerById(gameBoard.turnOrganizer.GetCurrentPlayerId()).GetArmies());
 * for(int i =1;i<playerNum*2;i++) {
 * givenCountryId=gameBoard.map.GetCountriesByPlayerId(currentPlayerId).get(i).
 * GetId(); gameBoard.PlaceArmiesOnCountry(givenCountryId,1);
 * System.out.println("Current player armies: "+gameBoard.GetPlayerById(
 * gameBoard.turnOrganizer.GetCurrentPlayerId()).GetArmies());
 * System.out.println("Country: "+givenCountryId+" player armies: "+gameBoard.
 * map.GetCountryById(givenCountryId).GetArmies());
 * 
 * } System.out.println("Current player armies: "+gameBoard.GetPlayerById(
 * gameBoard.turnOrganizer.GetCurrentPlayerId()).GetArmies());
 * System.out.println("change phase:");
 * System.out.println("Current PlayerId: "+gameBoard.turnOrganizer.
 * GetCurrentPlayerId());
 * System.out.println("Current Phase: "+gameBoard.turnOrganizer.GetCurrentPhase(
 * ));
 * 
 * } public static void Demo_BuildDeck() throws Exception { int playerNum=5;
 * GameBoard gameBoard = GameBoard.GetGameBoard();
 * gameBoard.LoadMap("Earth.map"); gameBoard.StartupGame(playerNum);
 * System.out.println(gameBoard.deck.size()); for(int i
 * =1;i<gameBoard.deck.size();i++) {
 * System.out.println(gameBoard.deck.get(i).GetType());
 * 
 * } gameBoard.ShuffleDeck(); for(int i =1;i<gameBoard.deck.size();i++) {
 * System.out.println(gameBoard.deck.get(i).GetType());
 * 
 * } for(int i =1;i<10;i++) {
 * gameBoard.DrawACard(gameBoard.turnOrganizer.GetCurrentPlayerId()); }
 * gameBoard.PlaceArmiesOnCountry(1, 1);
 * 
 * } }
 * 
 * public static void Demo_addcountry() throws Exception { GameBoard gameBoard =
 * GameBoard.GetGameBoard(); gameBoard.LoadMap("Earth.map");
 * gameBoard.map.AddCountry("ctest", 1, 20, 20);
 * System.out.println("this the new country: "+gameBoard.map.GetCountryIdByName(
 * "ctest"));
 * System.out.println("The list of countries including the new"+gameBoard.map.
 * getCountryListStringForCombobox(1)); }
 * 
 */