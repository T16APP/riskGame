package com.risk.model;

public class Land implements ILand{
	private static int counter=0;
	protected int id;
	protected String name;
	protected int playerId;
	
	public Land(String new_name)
	{
		counter++;
		this.id=this.counter;
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
