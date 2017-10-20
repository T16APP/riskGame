package com.risk.model;

import com.risk.utility.*;
import com.risk.view.applicationWindow;

import java.io.IOException;

public class Console {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		applicationWindow appWindow = new applicationWindow();
		System.out.println("Game started");
		appWindow.open();

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