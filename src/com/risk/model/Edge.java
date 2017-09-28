package com.risk.model;



public class Edge implements Comparable{
	private static int counter=0;
	private  int id;
	private String country1;
	private String country2;
	
	public Edge(String new_country1, String new_country2)
	{
		counter++;
		this.id=this.counter;
		this.country1=new_country1;
		this.country2=new_country2;
	}
    
	public String GetCountry1()
	{
		return this.country1;
	}
	public String GetCountry2()
	{
		return this.country2;
	}
	public int GetId()
	{
		return this.id;
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		Edge edge = (Edge)o;
		if((this.GetCountry1().equals(edge.GetCountry1()) && this.GetCountry2().equals(edge.GetCountry2()))
		|| (this.GetCountry1().equals(edge.GetCountry2()) && this.GetCountry2().equals(edge.GetCountry1())))
		{
			return 0;
		}
		else return -1;
				
		
	}

}
