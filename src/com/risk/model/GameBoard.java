package com.risk.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.risk.utility.ECards;
import com.risk.utility.LoggingWindow;
import com.risk.utility.MapParser;
import com.risk.utility.TurnPhases;

/**
 * This class represents the game board as a singleton it maintains the list of
 * players as well as the map it provides all objects and methods needed for
 * presentation the game
 * 
 * @author Kourosh Aziz-Nejad
 * @version 1.0.0.0
 */

public class GameBoard {
	public List<Player> players;
	public Map map;
	public static GameBoard instance;
	public List<Integer> roundRobin;
	public TurnOrganizer turnOrganizer;
	public List<Card> deck;

	public GameBoard() {
		if (this.instance == null) {
			players = new ArrayList<Player>();
			map = new Map("map");
			turnOrganizer = new TurnOrganizer();
			deck = new ArrayList<Card>();

		}

	}

	/**
	 * this method returns an instance the object if the instance is created
	 * already, will be returned otherwise a new instance will be created and
	 * will be returned
	 * 
	 * @return an instance of GameBoard
	 */
	public static GameBoard GetGameBoard() {
		if (instance == null) {
			return instance = new GameBoard();
		} else
			return instance;
	}

	/**
	 * this method load the map
	 * 
	 * @param prm_input
	 *            , which its type is string is the map file that will be
	 *            imported into the game
	 * @return is successful message
	 * @throws Exception
	 *             if the map file does not exist
	 */
	public String LoadMap(String prm_input) throws Exception {
		// tbd
		String result = "Failed";
		if (!MapParser.MapValidator_Header(prm_input)) {
			LoggingWindow.Log("Load Map: Validation headers failed");
			return result = "FailedHeaderValidation";
		}
		if (!MapParser.MapValidator_MinContinents(prm_input)) {
			LoggingWindow.Log("Load Map: Validation min continents failed");
			return result = "FailedMinContinentsValidation";
		}
		if (!MapParser.MapValidator_MinCountries(prm_input)) {
			LoggingWindow.Log("Load Map: Validation min countries failed");
			return result = "FailedMinCountriesValidation";
		}
		this.map = MapParser.MapParser(prm_input);
		if (!this.map.ValidationMapConnectivity()) {
			LoggingWindow.Log("Load Map: Validation map connectivity failed");
			return "FailedMapConnectivityValidation";
		}
		if (!this.map.ValidateContinentsConnectivity()) {
			LoggingWindow.Log("Load Map: Validation continents connectivity failed");
			return "FailedCoonectivityValidation";
		}
		LoggingWindow.Log("Load Map: Map loaded as a connected graph successfully");
		turnOrganizer.MapLoaded();
		return result = "SuccessfullyMapLoaded";
	}

	/**
	 * this method start up the game it takes the number of players, then
	 * creates players and then assign countries evenly to players then builds
	 * the deck next it set the current player based on round-robin fashion next
	 * initializes the phase
	 * 
	 * @param prm_playerNum
	 *            is the number of the player
	 * @return is successful message
	 * @throws Exception
	 *             if the number of player is less than 2 or greater than 5
	 */
	public String StartupGame(int prm_playerNum) throws Exception {
		if (prm_playerNum < 2 || prm_playerNum > 5) {
			LoggingWindow.Log("Startup Phase: number of players is out of range (between 2 and 5)");
			return "PlayerNumberNotValid";
		}
		if (turnOrganizer.IsGameStarted()) {
			return "Game already started";
		}
		WorldDominationObserver worldDomination = new WorldDominationObserver();
		// attach world domination observer
		worldDomination.GetWorldDomination();
		PhaseViewObserver phaseViewObserver = new PhaseViewObserver();
		// attach phase view observer
		turnOrganizer.addObserver(phaseViewObserver);
		this.map.addObserver(worldDomination);
		turnOrganizer.SetCurrentPhase(TurnPhases.Startup);
		;
		BuildDeck(map.GetCountries().size());
		SetupPlayers(prm_playerNum);
		turnOrganizer.players = this.players;
		AssignCountriesRandom();
		LoggingWindow.Log("Startup Phase: countries were allocated to the players randomly and evenly");
		AllocateInitialArmies();
		LoggingWindow.Log("Startup Phase: armies were allocated to players based on risk rules");
		PlaceOneByOneArmies();
		LoggingWindow.Log("Startup Phase: all armies placed one by one on countries in round-robin fashion");
		turnOrganizer.GameStarted();
		turnOrganizer.GetNextPlayerId();
		return "Game started successfully";
	}

	/**
	 * this method allocate initial armies to the players according to the risk
	 * game rules in round-robin fashion
	 */
	public void AllocateInitialArmies() {
		int initialArmies = 0;
		switch (this.players.size()) {
		case 2:
			initialArmies = 40;
			break;
		case 3:
			initialArmies = 35;
			break;
		case 4:
			initialArmies = 30;
			break;
		case 5:
			initialArmies = 25;
			break;
		}
		for (Player p : this.players) {
			p.SetArmies(initialArmies);
		}
	}

	/**
	 * this method places armies on countries one by one in round-robin fashion
	 * 
	 * @throws Exception
	 *             if the countries not allocated armies
	 * 
	 */
	public void PlaceOneByOneArmies() throws Exception {
		int playerId = -2;
		int stopTurn = 0;
		while (stopTurn < players.size()) {
			playerId = turnOrganizer.GetNextPlayerIdForStartUp();
			PlaceArmiesOnCountry(GetCountryToPlaceInitialArmies(playerId).GetId(), 1);
			if (GetPlayerById(playerId).GetArmies() == 0) {
				stopTurn += 1;
			}
		}
	}

	public Country GetCountryToPlaceInitialArmies(int prm_playerId) {
		Country countryToBePlacedArmies = this.map.GetCountriesByPlayerId(prm_playerId).get(prm_playerId);
		for (Country c : this.map.GetCountriesByPlayerId(prm_playerId)) {
			if (c.GetArmies() < countryToBePlacedArmies.GetArmies()) {
				countryToBePlacedArmies = c;
			}
		}
		return countryToBePlacedArmies;
	}

	/**
	 * this method creates instance of players as many as the playerNum
	 * 
	 * @param prm_playerNum
	 *            , which is integer, is the number of players that user
	 *            spesifies at the beginning of the game
	 */
	public void SetupPlayers(int prm_playerNum) {
		for (int i = 1; i <= prm_playerNum; i++) {
			players.add(new Player(i, "player" + i, this.map, this.deck, this.turnOrganizer));
		}
		roundRobin = new ArrayList<Integer>(players.size());
		turnOrganizer.roundRobin = new ArrayList<Integer>(players.size());
		InitRoundRobin();
	}

	/**
	 * this method will assign the companies to the players evenly
	 */
	public void AssignCountriesRandom() {
		Country countryRandom;
		while (map.GetCountriesNotAssigned().size() > 0) {
			for (Player p : players) {
				if (map.GetCountriesNotAssigned().size() > 0) {
					countryRandom = map.GetNotAssignedCountryRandom();
					map.SetCountryPlayerId(countryRandom, p.GetId());
				} else
					break;
			}
		}
	}

	/**
	 * this method initialaizes the roundrobin objects it adds all players to
	 * the instance of roundrobin
	 */
	public void InitRoundRobin() {
		for (Player p : this.players) {
			roundRobin.add(p.GetId());
		}

	}

	/**
	 * this method calls the map file validator from MapParser
	 * 
	 * @param input,
	 *            which is string, is the name of the map file to be validated
	 * @return , which is string, is valid if the map file meets the validation
	 *         criteria otherwise returns invalid
	 * @throws Exception
	 *             if the map file does not exist
	 */
	public boolean MapValidator(String input) throws Exception {
		boolean result = false;
		try {
			result = MapParser.MapValidator(input);
		} catch (Exception ex) {
			throw ex;
		}
		return result;
	}

	/**
	 * this method will save the current map into a file in the format of
	 * Conquest rules
	 * 
	 * @param input,
	 *            which its type is string, is the file in which the map will be
	 *            saved
	 * @return , which its type is integer, is 1 if the save is successful and
	 *         is 0 if it is failed
	 * @throws Exception
	 *             if continents are less than 1 or countries are less than 5
	 *             the exception will be thrown
	 */
	public int SaveMapToFile(String input) throws Exception {
		int result = 0;
		// tbd
		if (this.map.GetContinents().size() < 1)
			throw new Exception("MapHasNoContinent");
		if (this.map.GetCountries().size() < 5)
			throw new Exception("LessThanFiveCountriesValidation");
		MapParser.WriteMapToFile(map, input);
		return 1;
	}

	/**
	 * this method reset the map and get it ready to build it
	 * 
	 */
	public void CreateMap() {
		this.map = new Map("map1");
	}

	/**
	 * this function returns the player from its id
	 * 
	 * @param prm_playerId
	 *            is the id of the player
	 * @return the player
	 */
	public Player GetPlayerById(int prm_playerId) {
		for (Player p : players) {
			if (p.GetId() == prm_playerId)
				return p;
		}
		return null;
	}

	/**
	 * this method places armies on a country
	 * 
	 * @param prm_countryId
	 *            is the id of the country on which armies are placed
	 * @param prm_armies
	 *            the number of armies to be placed
	 * @return 1 if it is succesful otherwise 0
	 */
	public String PlaceArmiesOnCountry(int prm_countryId, int prm_armies) {
		String result = "";
		// tbd
		if (turnOrganizer.GetCurrentPhase() != TurnPhases.Startup)
			return "PhaseNotValid";
		int countryArmies = map.GetCountryById(prm_countryId).GetArmies();
		int playerArmies = GetPlayerById(map.GetCountryById(prm_countryId).GetPlayerId()).GetArmies();
		if (playerArmies >= prm_armies) {
			map.GetCountryById(prm_countryId).SetArmies(countryArmies + prm_armies);
			GetPlayerById(map.GetCountryById(prm_countryId).GetPlayerId()).AddArmies(-prm_armies);
			System.out.println("country armies" + countryArmies + "player armies" + playerArmies);
			result = "SuccessfullReinforcement";
		} else
			return "FailedNotEnoughArmies";
		return result;
	}

	public void BuildDeck(int countryNum) {
		int cardsNum = (countryNum / 3 + 1) * 3;
		Card card;
		for (int i = 0; i < cardsNum / 3; i++) {
			deck.add(new Card(ECards.Artillery));
		}
		for (int i = cardsNum / 3; i < cardsNum * 2 / 3; i++) {
			deck.add(new Card(ECards.Cavalry));
		}
		for (int i = cardsNum * 2 / 3; i < cardsNum; i++) {
			deck.add(new Card(ECards.Infantry));
		}
	}

	/**
	 * this method shuffles the card
	 * 
	 */
	public void ShuffleDeck() {
		Collections.shuffle(deck);
	}

	/**
	 * this method calculates the number of armies from the continent control if
	 * the player has the whol continent
	 * 
	 * @param prm_playerId
	 *            is the id of the player under questio
	 * @return is the number of calulated srmies from continent controls
	 */
	public int Calculate_ArmiesFromContinentControl(int prm_playerId) {
		boolean doesBelong = true;
		;
		int totArmies = 0;
		for (Continent c : this.map.GetContinents()) {
			doesBelong = true;
			for (Country cy : this.map.GetCountriesByContinentId(c.GetContinentId())) {
				if (cy.GetPlayerId() != prm_playerId)
					doesBelong = false;
			}
			if (doesBelong)
				totArmies += c.GetControl();
		}
		return totArmies;
	}

}
