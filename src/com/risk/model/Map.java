package com.risk.model;
/**
 * This is a high level class which contains continents and countries
 * @author Kourosh Aziz-Nejad
 *
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {
private String name;
public List<Land> lands;
public List<Edge> edges;

public Map(String new_name)
{
	this.name=new_name;
	lands = new ArrayList<Land>();
	edges= new ArrayList<Edge>();
	}
//methods
public Land GetLandByName(String new_name)
{
	String tempName="";
	for(Land land : lands)
	{
		if(new_name.equals(land.GetName())) 
			{
              return land;
              
			}
		
	}
	return null;
	}
public int AddEdge(Edge new_edge)
{
	int result = -1;
	if(!DoesExistEdge(new_edge))
	{
		this.edges.add(new_edge);
	}
	return result;
	}
public boolean DoesExistEdge(Edge new_edge) 
{
	boolean result = false;
	for(Edge e : this.edges)
	{
		if(e.compareTo(new_edge)==0)
		{
			return true;
			
		}
		
	}
	return result;
	}
public Country GetNotAssignedCountryRandom()
{
	List<Country> countries = GetCountriesNotAssigned();
	Country randCountry = countries.get(new Random().nextInt(countries.size()));
	return randCountry;
	}
public List<Country> GetCountriesNotAssigned()
{
	List<Country> countriesNotAssigned = new ArrayList<Country>();
	for(Land c : lands)
	{
		if(c instanceof Country )
		{
			if(((Country)c).GetPlayerId()==-1)
			{
				countriesNotAssigned.add((Country)c);
			}
		}
	}
	return countriesNotAssigned;
	}

}
