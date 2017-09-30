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
private String author;
private String image;
public List<Land> lands;
public List<Edge> edges;

public Map(String new_name)
{
	this.name=new_name;
	lands = new ArrayList<Land>();
	edges= new ArrayList<Edge>();
	}
//methods
public void SetAuthor(String new_author)
{
	this.author=new_author;
	}
public void SetImage(String new_image)
{
	this.image=new_image;
	}
public String GetAuthor()
{
	return this.author;
	}
public String GetImage()
{
	return this.image;
	}

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


public int GetContinentIdByName(String new_name)
{
	int id=-1;
	for(Land l : this.lands)
	{
		if(l instanceof Continent && l.GetName().equals(new_name))
		{
			return l.GetId();
		}
	}
	return id;
}
public int GetCountryIdByName(String new_name)
{
	int id=-1;
	for(Land l : this.lands)
	{
		if(l instanceof Country && l.GetName().equals(new_name))
		{
			return l.GetId();
		}
	}
	return id;
}
public String GetContinentNameById(int new_id)
{
	String name="";
	for(Land l : this.lands)
	{
		if(l instanceof Continent && l.GetId()==new_id)
		{
			return l.GetName();
		}
	}
	return name;
}
public String GetCountryNameById(int new_id)
{
	String name="";
	for(Land l : this.lands)
	{
		if(l instanceof Country && l.GetId()==new_id)
		{
			return l.GetName();
		}
	}
	return name;
}
public List<Continent> GetContinents()
{
	List<Continent> continents = new ArrayList<Continent>();
	for(Land l : this.lands)
	{
		if(l instanceof Continent) 
		{
			continents.add((Continent)l);
		}
	}
	return continents;
	}
public List<Country> GetCountries()
{
	List<Country> continents = new ArrayList<Country>();
	for(Land l : this.lands)
	{
		if(l instanceof Country) 
		{
			continents.add((Country)l);
		}
	}
	return continents;
	}
public List<String> GetNeihborsName(Country new_country)
{
	List<String> neighborsName = new ArrayList<String>();
	for(Edge e : this.edges)
	{
		if(e.DoesExistCountry(new_country.GetId()))
		{
			neighborsName.add(this.GetCountryNameById(e.GetNeighborId(new_country.GetId())));
		}
	}
	return neighborsName;
	
	}
public List<String> CountriesToLine()
{
	List<String> lines = new ArrayList<String>();
	String line;
	for(Country c : this.GetCountries())
	{
		line = new String();
		line = c.GetName()+","+c.GetDb1()+","+c.GetDb2()+","+this.GetContinentNameById(c.GetContinentId());
		for(String neighborName : this.GetNeihborsName(c))
        {
			line+=","+neighborName;
        }
		lines.add(line);
	}
	return lines;
	}
public List<String> MapToLines()
{
	List<String> lines = new ArrayList<String>();
	lines.add("[Map]");
	lines.add("author="+this.GetAuthor());
	lines.add("image="+this.GetImage());
	lines.add("wrap=yes");
	lines.add("scroll=yes");
	lines.add("warn=yes");
	lines.add("");
	lines.add("[Continents]");
	for(Continent c : this.GetContinents())
	{
		lines.add(c.GetName()+"="+c.GetDb1());
	}
	lines.add("");
	lines.add("[Territories]");
	for(String l : CountriesToLine())
	{
		lines.add(l);
	}
	return lines;
	}
}
