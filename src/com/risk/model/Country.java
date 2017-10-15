package com.risk.model;
/**
 * This is a class that belongs to a continent
 * @author Kourosh Aziz-Nejad
 *
 */

/**
 * This class represents a country
 * it maintains name, id,army, and coordinates of the country 
 * it has different methods to change the state and returns the state of the object 
 * @author Kourosh Aziz-Nejad
 * @version 1.0.0.0
 */
public class Country extends Land {
	private Double army;
	private int continentId;
	private int x;
	private int y;
	
	/**
	 * This constructor initialize the object
	 * @param new_name which is string will be the name of the country
	 * @param new_continentId ,which is integer, is the id of the continent to which the country belongs
	 * @param prm_x , which is integer, is the x coordinate of the country
	 * @param prm_y , which is integer, is the y coordinate of the country
	 */
	public Country(String prm_name, int prm_continentId, int prm_x, int prm_y)
	{
		super(prm_name);
		this.continentId = prm_continentId;
		this.x = prm_x;
		this.y= prm_y;
	}

	/**
	 * This method returns the id of the player to which the country was assigned
	 * @return playerId, which is integer, is the id of the player who owns the country
	 */
	public int GetPlayerId() {
               return this.playerId;
	}

	/**
	 * this method sets the id of the player who will own the country
	 * @param new_playerId, which is integer, The id of the player who will own the country
	 */
	public void SetPlayerId(int prm_playerId)
	{
		this.playerId=prm_playerId;
    }
     /**
	 * This method returns x coordinate of  the country's position
	 * @return x, which is integer, The x coordinate of the country
	 */
	public int GetX()
	{
		return this.x;
    }
     /**
	 * This method returns y coordinate of the country's position
	 * @return y, which is integer, The y coordinate of the country
	 */
	public int GetY()
	{
		return this.y;
     }
     /**
	 * This method returns the id of the continent to which the country belongs
	 * @return continentId, which is integer,  The id of the continent to which the country belongs
	 */
	public int GetContinentId()
	{
      return this.continentId;
	}

}
