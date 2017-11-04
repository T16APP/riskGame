package com.risk.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a country it maintains name, id,army, and coordinates
 * of the country it has different methods to change the state and returns the
 * state of the object
 * 
 * @author Kourosh Aziz-Nejad
 * @version 1.0.0.0
 */
public class Country extends Land {
	private int armies;
	private int continentId;
	private int x;
	private int y;
	private List<Country> neighbors;
	public boolean visited;

	/**
	 * This constructor initialize the object
	 * 
	 * @param prm_name
	 *            which is string will be the name of the country
	 * @param prm_continentId
	 *            ,which is integer, is the id of the continent to which the country
	 *            belongs
	 * @param prm_x
	 *            , which is integer, is the x coordinate of the country
	 * @param prm_y
	 *            , which is integer, is the y coordinate of the country
	 */
	public Country(String prm_name, int prm_continentId, int prm_x, int prm_y) {
		super(prm_name);
		this.continentId = prm_continentId;
		this.x = prm_x;
		this.y = prm_y;
		neighbors = new ArrayList<Country>();
		visited = false;
	}

	/**
	 * This method returns the id of the player to which the country was assigned
	 * 
	 * @return playerId, which is integer, is the id of the player who owns the
	 *         country
	 */
	public int GetPlayerId() {
		return this.playerId;
	}

	/**
	 * this method sets the id of the player who will own the country
	 * 
	 * @param prm_playerId,
	 *            which is integer, The id of the player who will own the country
	 */
	public void SetPlayerId(int prm_playerId) {
		this.playerId = prm_playerId;
	}

	/**
	 * This method returns x coordinate of the country's position
	 * 
	 * @return x, which is integer, The x coordinate of the country
	 */
	public int GetX() {
		return this.x;
	}

	/**
	 * This method returns y coordinate of the country's position
	 * 
	 * @return y, which is integer, The y coordinate of the country
	 */
	public int GetY() {
		return this.y;
	}

	/**
	 * This method returns the id of the continent to which the country belongs
	 * 
	 * @return continentId, which is integer, The id of the continent to which the
	 *         country belongs
	 */
	public int GetContinentId() {
		return this.continentId;
	}

	/**
	 * this method returns army of a given country
	 * 
	 * @param prm_countryId,
	 *            which is the id of the given country
	 * @return , which its type is integer, is the number of army of the country
	 */
	public int GetArmies() {
		return this.armies;
	}

	/**
	 * this method sets the number of armies
	 * 
	 * @param prm_armies
	 *            is the number of new armies
	 */
	public void SetArmies(int prm_armies) {
		this.armies = prm_armies;
	}
	/**this method adds a country to the list of neighbors
	 * 
	 * @param prm_country is the neighbor country
	 */
	public void AddNeighbor(Country prm_country){
		this.neighbors.add(prm_country);
	}
	/**this method returns the neighbor country by id
	 * 
	 * @param prm_neighborId is the id of the neighbor country
	 * @return the neighbor country
	 */
	public Country GetNeighborById(int prm_neighborId){
		for(Country c : this.neighbors){
			if(c.GetId()==prm_neighborId) return c;
		}
		return null;
	}
	/**this method adds or subtract from the armies that the country has
	 * 
	 * @param prm_addedArmies the number of armies that is added or subtracted from the country's armies
	 */
	public void AddArmies(int prm_addedArmies){
		this.armies+=prm_addedArmies;
	}
	/**this method returns neighbors of a country
	 * 
	 * @return list of neighbor countries
	 */
	public List<Country> GetNeighbors(){
		return this.neighbors;
	}
	/**this method returns the list of neighbors that exist in the same continent
	 * 
	 * @return
	 */
	public List<Country> GetNeighborsInContinent(){
		List<Country> neighborsInContinent = new ArrayList<Country>();
		for(Country neighbor : neighbors){
			if(this.GetContinentId()==neighbor.GetContinentId()){
				neighborsInContinent.add(neighbor);
			}
		}
		return neighborsInContinent;
	}

}
