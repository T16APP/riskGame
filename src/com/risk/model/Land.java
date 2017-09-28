package com.risk.model;

public class Land implements ILand{
	private int id;
	private String name;
	private int playerId;
	
	public Land(int new_id,String new_name)
	{
		this.id=new_id;
		this.name=new_name;
		this.playerId=-1;
	}
	public String GetName()
	{
		return this.name;
	}
	public int GetId()
	{
		return this.id;
	}

}
