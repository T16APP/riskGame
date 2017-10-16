package com.risk.model;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.risk.utility.MapParser;



/**
 * This class represents the game board as a singleton
 * it maintains the list of players as well as the map
 * it provides all objects and methods needed for presentation the game
 * @author Kourosh Aziz-Nejad
 * @version 1.0.0.0
 */

public class GameBoard {
	public List<Player> players;
	public Map map;
	public static GameBoard instance;
	public List<Integer> roundRobin;
	public TurnOrganizer turnOrganizer;

	public GameBoard() {
		if (this.instance == null) {
			players = new ArrayList<Player>();
			map = new Map("map");
			turnOrganizer = new TurnOrganizer();
		}

	}

	/**
	 * this method returns an instance the object
	 * if the instance is created already, will be returned
	 * otherwise a new instance will be created and will be returned
	 * @return an instance of GameBoard
	 */
	public static GameBoard GetGameBoard()
	{
		if(instance==null)
		{
          return new GameBoard();
		} 
		else
			return new GameBoard();
	}

	/**this method load the map
	 * @param prm_input , which its type is string
	 * is the map file that will be imported into the game
	 * @throws IOException 
	 */
	public void LoadMap(String prm_input) throws IOException
	{
		MapParser.MapParser(prm_input);
		turnOrganizer.MapLoaded();
	}
	/** this method start up the game
	 * it takes the number of players, then creates players
	 * and then assign countries evenly to players
	 * next it set the current player based on round-robin fashion
	 * next initializes the phase
	 */
	public void StartupGame(int prm_playerNum)
	{
		SetupPlayers(prm_playerNum);
		AssignCountriesRandom();
		turnOrganizer.GameStarted();
		turnOrganizer.SetCurrentPlayerId(GetNextPlayerId());
		turnOrganizer.GetNextPhase();
	}
	/**
	 * this method creates 6 instance of players
	 */
	public void SetupPlayers()
	{
		for(int i=1;i<7;i++)
		{
			players.add(new Player(i,"player"+i));
        }
		roundRobin = new ArrayList<Integer>(players.size());
		InitRoundRobin();
	}
	/**
	 * this method creates instance of players
	 * as many as the playerNum
	 * @param playerNum , which is integer, is the number of 
	 * players that user spesifies at the beginning 
	 * of the game
	 */
	public void SetupPlayers(int prm_playerNum)
	{
		for(int i=1;i<prm_playerNum;i++)
		{
			players.add(new Player(i,"player"+i));
		}
		roundRobin = new ArrayList<Integer>(players.size());
		InitRoundRobin();
	}
	/**
	 * this method will assign the companies to 
	 * the players evenly
	 */
	public void AssignCountriesRandom()
	{
     Country countryRandom;
		while (map.GetCountriesNotAssigned().size() > 0) {
			for (Player p : players) {
				if (map.GetCountriesNotAssigned().size() > 0) {
					map.GetNotAssignedCountryRandom().SetPlayerId(p.GetId());
				} else
					break;
			}
		}
	}
	/**
	 * this method initialaizes the roundrobin objects
	 * it adds all players to the instance of roundrobin
	 */
	public void InitRoundRobin()
	{
		for(Player p : this.players)
		{
         roundRobin.add(p.GetId());
		}
	}
	/**
	 * this method returns the next player
	 * to play
	 * @return the id of the next player who should play
	 */
	public int GetNextPlayerId()
	{
		if(roundRobin.size()<1)
		{
         InitRoundRobin();
		}
		int indexNextPlayerId = new Random().nextInt(roundRobin.size());
		int nextPlayerId = roundRobin.get(indexNextPlayerId);
		roundRobin.remove(indexNextPlayerId);
		return nextPlayerId;
	}
	/**
	 * this method calls the map file validator from MapParser
	 * @param input, which is string, is the name of the map file 
	 * to be validated
	 * @return , which is string, is valid if the map file meets the validation criteria
	 * otherwise returns invalid
	 * @throws Exception
	 */
	public String MapValidator(String input) throws Exception
	{
		String result ="valid";
		try
		{
		result=MapParser.MapValidator(input);
		}
		catch(Exception ex)
		{
			throw ex;
		}
		return result;
	}
	/** this method colculate the number of
	 * armies that the player can have
	 * @param , which its type is playerId, is the id of the player
	 * 
	 */
	public void CalculateArmies(Player prm_player)
	{
		int armiesNum = map.GetCountriesByPlayerId(prm_player.GetId()).size();
		armiesNum /= 3; 
		prm_player.SetArmiesToplayer(armiesNum<=3?3:armiesNum);
		
		
	}
	/**this method will save the current map into a file in the format of Qonquest rules
	 * @param input, which its type is string, is the fale in which the map will be saved
	 * @return , which its type is integer, is 1 if the save is successful and is 0 if it is faled
	 * @throws IOException 
	 */
	public int SaveMapToFile(String input) throws IOException
	{
		int result = 0;
		MapParser.WriteMapToFile(map, input);
		return 1;
	}
    /**this methos reset the map and get it ready to build it
     * 
     */
	public void CreateMap()
    {
    	this.map=new Map("map1");
    }

}
