package com.risk.model;
/**
 * This is a class that belongs to a continent
 * @author Kourosh Aziz-Nejad
 *
 */

import java.util.ArrayList;
import java.util.List;

public class Country extends Land {
	private Double army;
	private int continentId;
	private double db1;
	private double db2;
	
	public Country(String new_name, int new_continentId, double new_db1, double new_db2)
	{
		super(new_name);
		this.continentId = new_continentId;
		this.db1 = new_db1;
		this.db2= new_db2;
	}
	public int GetPlayerId()
	{
		return this.playerId;
	}
	public void SetPlayerId(int new_playerId)
	{
		this.playerId=new_playerId;
	}
	public double GetDb1()
	{
		return this.db1;
	}
	public double GetDb2()
	{
		return this.db2;
	}
	public int GetContinentId()
	{
		return this.continentId;
	}
	

}
