package com.risk.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
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

	/*public GameBoard() {
		if (this.instance == null) {
			players = new ArrayList<Player>();
			map = new Map("map");
			turnOrganizer = new TurnOrganizer();
			deck = new ArrayList<Card>();

		}

	}*/
	public GameBoard() {
		    players = new ArrayList<Player>();
			map = new Map("map");
			turnOrganizer = new TurnOrganizer();
			deck = new ArrayList<Card>();

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
	/**this method is entrance of the application
	 * @throws Exception file not exist
	 * 
	 */
	public void GameEntry() throws Exception{
		Scanner in = new Scanner(System.in); 
	    System.out.println("Enter that mode: 0 for SingleMode, 1 for Tournament");
		int mode = in.nextInt();
		if(mode==0){
			SingleModePlay();
		}
		else if(mode==1){
			TournamentPlay();
		}
	}
	/**this method selects the mode
	 * @param mode the mode
	 * @throws Exception file not exist
	 * @return name of mode
	 */
	public String GameEntry(int mode) throws Exception{
		if(mode==0){
			return "SingleMode";
		}
		else if(mode==1){
			return "Tournament";
		}
		else return "";
	}

	/**this method organizes the single mode play
	 * @throws Exception file not exist
	 * 
	 */
	public void SingleModePlay() throws Exception{
		Scanner inInteger = new Scanner(System.in); 
		Scanner inString = new Scanner(System.in); 
	    System.out.println("New game or load a game from file: 0 for new game, 1 for already saved game");
		int newOrLoad = inInteger.nextInt();
		if(newOrLoad==0){
			System.out.println("Enter map file:");
			String file = inString.next();
			LoadMap(file);
			inInteger = new Scanner(System.in);
			int numPlayers=0;
			while(numPlayers<2 || numPlayers>5){
				System.out.println("Enter number of players between 2 and 5:");
				numPlayers = inInteger.nextInt();
			}
			int numStrategy=0;
			List<String> strategyPlayers = new ArrayList<String>();
			for(int i = 1;i<=numPlayers;i++){
				System.out.println("Enter strategy player for player: "+i+"0:HumanPlayer, 1:AggressivePlayer, 2:BenevolentPlayer, 3:RandomPlayer, 4:CheaterPlayer");
				numStrategy = inInteger.nextInt();
				strategyPlayers.add(StrategyDic(numStrategy));
			}
			StartupStrategyGame(strategyPlayers);
			System.out.println("Quit or save the game: 0 for quit, 1 for save the game");
			int quitSave = inInteger.nextInt();
			if(quitSave==1){
				System.out.println("Enter the file to save the game into it:");
				String fileOut = inString.next();
				SaveGameToFile(fileOut);
			}
			
		}
		else if(newOrLoad==2){
			inInteger = new Scanner(System.in);
			System.out.println("Enter name of the file in which a game already saved:");
			String file = inString.next();
			instance = LoadGameFromFile(file);
			switch(turnOrganizer.GetCurrentPhase()){
				case Reinforcement:
					turnOrganizer.GetCurrentPlayer().Reinforcement();
					break;
				case Attack:
					turnOrganizer.GetCurrentPlayer().Attack();
					break;
				case Fortification:
					turnOrganizer.GetCurrentPlayer().Fortification();
					break;
					
			}
		}
	}
	/**this method translate the number to name of strategy
	 * 
	 * @param prm_strategyKey the number
	 * @return the name of strategy
	 */
	public String StrategyDic(int prm_strategyKey){
		switch(prm_strategyKey){
		case 0:
			return "Human";
		case 1:
			return "Aggressive";
		case 2:
			return "Benevolent";
		case 3:
			return "Random";
		case 4:
			return "Cheater";
		default:
			return "Cheater";
		}
	}
	/**this method organizes the tournament games
	 * @throws Exception file not exist
	 * 
	 */
	public void TournamentPlay() throws Exception{
		Scanner inInteger = new Scanner(System.in); 
		Scanner inString = new Scanner(System.in); 
	    //set the map for tounament
		System.out.println("Enter the number of maps:");
		int numMaps = inInteger.nextInt();
		while(numMaps<1 || numMaps>5){
			System.out.println("Enter the number of maps between 1 and 5:");
			numMaps = inInteger.nextInt();
		}
		//take map files one by one
		String mapFile="";
		List<String> mapFiles = new ArrayList<String>();
		for(int i = 0;i<numMaps;i++){
			System.out.println("Enter map file for map: "+i);
			mapFile = inString.next();
			mapFiles.add(mapFile);
		}
		//take number of players
		int numPlayers=0;
		while(numPlayers<2 || numPlayers>4){
			System.out.println("Enter number of computer players between 2 and 4:");
			numPlayers = inInteger.nextInt();
		}
		//take strategy players
		int numStrategy=0;
		List<String> strategyPlayers = new ArrayList<String>();
		for(int i =1 ;i<=numPlayers;i++){
			System.out.println("Enter computer strategy player for player: "+i+" 1:AggressivePlayer, 2:BenevolentPlayer, 3:RandomPlayer, 4:CheaterPlayer");
			numStrategy = inInteger.nextInt();
			strategyPlayers.add(StrategyDic(numStrategy));
		}
		//take the number of games
		int numGame=0;
		while(numGame<1 || numPlayers>6){
			System.out.println("Enter number of games on each map between 1 and 5:");
			numGame = inInteger.nextInt();
		}
		//take the number of turns
		int numTurn=0;
		while(numTurn<9 || numTurn>51){
			System.out.println("Enter number of turns for each game  between 10 and 50:");
			numTurn = inInteger.nextInt();
		}
		String[][] tournemantResult = new String[numMaps][numGame];
		int mapIterator=0;
		for(String map: mapFiles){
			LoggingWindow.Log("Map: "+map+" is loaded");
			for(int i=0;i<numGame;i++ ){
				LoggingWindow.Log("Game: "+i+" is started on map: "+map);
				this.LoadMap(map);
				this.turnOrganizer = new TurnOrganizer(numTurn);
				StartupStrategyGame(strategyPlayers);
				tournemantResult[mapIterator][i]=this.turnOrganizer.winner;
				LoggingWindow.Log("Game: "+i+" is finished on map: "+map);
				}
			mapIterator++;
		}
		LoggingWindow.Log("Tournament is finished");
		for(int i=0;i<numMaps;i++){
			System.out.print(mapFiles.get(i)+"|");
			for(int j=0;j<numGame;j++){
				System.out.print(tournemantResult[i][j]+"|");
			}
			System.out.println("");
		}
	}
	/**this method sets up the player based on the strategies
	 * 
	 * @param prm_playersStrategy the list of strategies
	 */
	public void SetupStrategyPlayers(List<String> prm_playersStrategy) {
		players = new ArrayList<Player>();
		int i = 1;
		for(String strategy : prm_playersStrategy){
			players.add(new Player(i, strategy, this.map, this.deck, this.turnOrganizer,strategy));
			i++;
		}
		roundRobin = new ArrayList<Integer>(players.size());
		turnOrganizer.roundRobin = new ArrayList<Integer>(players.size());
		InitRoundRobin();
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
	/**this method starts up the game according the strategy players
	 * 
	 * @param prm_strategyPlayer number of players
	 * @return successful message
	 * @throws Exception file not exist
	 */
	public String StartupStrategyGame(List<String> prm_strategyPlayer) throws Exception {
		if (prm_strategyPlayer.size() < 2 || prm_strategyPlayer.size() > 5) {
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
		SetupStrategyPlayers(prm_strategyPlayer);
		turnOrganizer.players = this.players;
		AssignCountriesRandom();
		LoggingWindow.Log("Startup Phase: countries were allocated to the players randomly and evenly");
		AllocateInitialArmies();
		LoggingWindow.Log("Startup Phase: armies were allocated to players based on risk rules");
		PlaceOneByOneArmies();
		LoggingWindow.Log("Startup Phase: all armies placed one by one on countries in round-robin fashion");
		turnOrganizer.GameStarted();
		turnOrganizer.GetNextPlayerId();
		turnOrganizer.GetCurrentPlayer().Reinforcement();
		return "Game started successfully";
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
	/**this method saves the game as json
	 * 
	 * @param file is the file in which the game is saved
	 * @throws FileNotFoundException if the file is not valid
	 * @return successful message
	 */
	public String SaveGameToFile(String file) throws FileNotFoundException{
		//prepare game to save, this acivities should be reverse when load the game
		for(Country c : this.map.GetCountries()) c.neighbors= new ArrayList<Country>();
		for(Player p : this.players){
			//p.map=null;
			//p.deck=null;
			p.turnOrganizer= new TurnOrganizer();
			p.map = new Map();
			p.deck = new ArrayList<Card>();
			p.strategy = null;
		}
		
		this.map.deleteObservers();
		this.turnOrganizer.deleteObservers();
		this.map.countries = this.map.GetCountries();
		this.map.continents = this.map.GetContinents();
		Gson _gson = new Gson();
		String request = _gson.toJson(this);
		try(  PrintWriter out = new PrintWriter(file)  ){
			out.println( request );
		}
		return "Successful";
	}
	/**this method loads the game from a file
	 * 
	 * @param file the file to save the game
	 * @throws JsonIOException exception of json 
	 * @throws JsonSyntaxException exception of json
	 * @throws FileNotFoundException exception of file not existing
	 * @return an instance of game board
	 */
	public static GameBoard LoadGameFromFile(String file) throws JsonIOException, JsonSyntaxException, FileNotFoundException{
		JsonParser parser = new JsonParser();
		Object obj = parser.parse(new FileReader(file));
		JsonObject jsonObject = (JsonObject)obj;
		Gson gson = new Gson();
		GameBoard gameBoardNew = gson.fromJson(jsonObject, GameBoard.class);
		gameBoardNew.map.lands= new ArrayList<Land>();
		for(Continent c:gameBoardNew.map.continents){
			gameBoardNew.map.lands.add(c);
		}
		for(Country c:gameBoardNew.map.countries){
			gameBoardNew.map.lands.add(c);
		}
		gameBoardNew.turnOrganizer.players=gameBoardNew.players;
		WorldDominationObserver worldDomination = new WorldDominationObserver();
		// attach world domination observer
		gameBoardNew.map.addObserver(worldDomination);
		PhaseViewObserver phaseViewObserver = new PhaseViewObserver();
		//reattach phase view
		gameBoardNew.turnOrganizer.addObserver(phaseViewObserver);
		for(Player p : gameBoardNew.players){
			//p.map=null;
			//p.deck=null;
			p.turnOrganizer=gameBoardNew.turnOrganizer;
			p.map=gameBoardNew.map;
			p.deck=gameBoardNew.deck;
			p.setStrategy(p.GetName());
		}
		return gameBoardNew;
	}

}
