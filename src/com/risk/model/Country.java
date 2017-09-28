package com.risk.model;
/**
 * This is a class that belongs to a continent
 * @author Kourosh Aziz-Nejad
 *
 */

import java.util.ArrayList;
import java.util.List;

public class Country extends Land {
	private int id;
	private Double army;
	private int playerId;
	public List<Country> neighbers;
	
	public Country(int new_id,String new_name)
	{
		super(new_id,new_name);
		neighbers = new ArrayList<Country>();
		playerId=-1;
	}
	public int UpdatePlayer(int new_playerId)
	{
		int result=0;
		int old_playerId = this.playerId;
		this.playerId=new_playerId;
		return result;
	}
	public int GetPlayerId()
	{
		return this.playerId;
	}
	public void SetPlayerId(int new_playerId)
	{
		this.playerId=new_playerId;
	}
	

}
