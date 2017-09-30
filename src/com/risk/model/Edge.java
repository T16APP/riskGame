package com.risk.model;



public class Edge implements Comparable{
	private static int counter=0;
	private  int id;
	private int countryId1;
	private int countryId2;
	
	public Edge(int new_countryId1, int new_countryId2)
	{
		counter++;
		this.id=this.counter;
		this.countryId1=new_countryId1;
		this.countryId2=new_countryId2;
	}
    
	public int GetCountryId1()
	{
		return this.countryId1;
	}
	public int GetCountryId2()
	{
		return this.countryId2;
	}
	public int GetId()
	{
		return this.id;
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		Edge edge = (Edge)o;
		if((this.GetCountryId1()==edge.GetCountryId1() && this.GetCountryId2()==edge.GetCountryId2())
		|| (this.GetCountryId1()==edge.GetCountryId2() && this.GetCountryId2()==edge.GetCountryId1()))
		{
			return 0;
		}
		else return -1;
				
		
	}
	public boolean DoesExistCountry(int new_countryId)
	{
		boolean result = false;
		if(this.GetCountryId1()==new_countryId || this.GetCountryId2()==new_countryId  )
		{
			return true;
		}
		return result;
	}
	public int GetNeighborId(int new_countryId)
	{
		if(this.countryId1==new_countryId)
		{
			return this.countryId2;
		}
		else if(this.countryId2==new_countryId)
		{
			return this.countryId1;
		}
		else
		{
		return -1;	
		}
		
	}

}
