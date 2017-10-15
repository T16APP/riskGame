package com.risk.model;
/**
 * This is a high level class which contains continents and countries
 * @author Kourosh Aziz-Nejad
 *
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Random;

public class Map {
	private String name;
	private String author;
	private String image;
	public List<Land> lands;
	public List<Edge> edges;

	public Map(String prm_name)
	{
		this.name=prm_name;
		lands = new ArrayList<Land>();
		edges= new ArrayList<Edge>();
		}

	// methods
	public void SetAuthor(String prm_author)
	{
		this.author=prm_author;
		}

	public void SetImage(String prm_image)
	{
		this.image=prm_image;
		}

	public String GetAuthor()
	{
		return this.author;
		}

	public String GetImage()
	{
		return this.image;
	}

	public Land GetLandByName(String prm_name)
	{
		String tempName="";
		for(Land land : lands)
		{
			if(prm_name.equals(land.GetName())) 
				{
	              return land;
				}
		}
		return null;
		
	}
	
	public String RemoveContinent(Continent prm_continent)
	{
		if(DoesExistContinent(prm_continent.GetId()))
		{
			for(Country c : this.GetCountries())
			{
				if(c.GetContinentId()==prm_continent.GetContinentId())
				{
					this.RemoveCountry(c);
				}
			}
			this.lands.remove(prm_continent);
			return "successful";
		}
		else
		{
			return "duplicate";
		}
		
		}
	
/*	public int AddEdge(Edge prm_edge)
	{
		int result = -1;
		if(!DoesExistEdge(prm_edge))
		{
			this.edges.add(prm_edge);
		}
		return result;
		}*/
/*	public boolean DoesExistEdge(Edge prm_edge) 
	{
		for(Edge e : this.edges)
		{
			if(e.compareTo(prm_edge)==0)
			{
				return true;
			}
			
		}
		return false;
	}*/
/*	public Country GetNotAssignedCountryRandom() {
		List<Country> countries = GetCountriesNotAssigned();
		Country randCountry = countries.get(new Random().nextInt(countries.size()));
		return randCountry;
	}

	public List<Country> GetCountriesNotAssigned() {
		List<Country> countriesNotAssigned = new ArrayList<Country>();
		for (Land c : lands) {
			if (c instanceof Country) {
				if (((Country) c).GetPlayerId() == -1) {
					countriesNotAssigned.add((Country) c);
				}
			}
		}
		return countriesNotAssigned;
	}*/
	
/*	public int GetContinentIdByName(String prm_name)
	{
		int id=-1;
		for(Land l : this.lands)
		{
			if(l instanceof Continent && l.GetName().equals(prm_name))
			{
				return l.GetId();
	        }
		}
			return id;
	}*/
	
	/*public int GetCountryIdByName(String prm_name)
	{
		int id=-1;
		for(Land l : this.lands)
		{
			if(l instanceof Country && l.GetName().equals(prm_name))
			{
				return l.GetId();
			}
		}
		return id;
	}*/

/*public String GetContinentNameById(int prm_id)
{
	String name="";
	for(Land l : this.lands)
	{
		if(l instanceof Continent && l.GetId()==prm_id)
		{
			return l.GetName();
		}
	}
	return name;
}*/
	
/*	public String GetCountryNameById(int prm_id)
	{
		String name="";
		for(Land l : this.lands)
		{
			if(l instanceof Country && l.GetId()==prm_id)
			{
				return l.GetName();
			}
		}
		return name;
	}*/
	
/*	public List<Continent> GetContinents() {
		List<Continent> continents = new ArrayList<Continent>();
		for (Land l : this.lands) {
			if (l instanceof Continent) {
				continents.add((Continent) l);
			}
		}
		return continents;
	}*/
	public boolean DoesExistCountry(String prm_name)
	{
		boolean result=false;
		for(Country c : this.GetCountries())
		{
			if(c.GetName().equals(prm_name))
			{
			  return true;	
			}
		}
		return result;
		}	
	public boolean DoesExistContinent(int prm_continentId)
	{
		boolean result=false;
		for(Continent c : this.GetContinents())
		{
			if(c.GetId()==prm_continentId)
			{
			  return true;	
			}
		}
		return result;
		}
	public int AddEdge(Edge new_edge) {
		int result = -1;
		if (!DoesExistEdge(new_edge)) {
			this.edges.add(new_edge);
		}
		return result;
	}
	public String AddCountry(String prm_name, int prm_continentId, int prm_x, int prm_y)
	{
		if(!DoesExistCountry(prm_name))
		{
			this.lands.add(FactoryLand.GetLand("Country", prm_name, prm_continentId, prm_x, prm_y,-1));
			return "successful";
		}
		else
		{
			return "duplicate";
		}
		
		}
	
	
	public boolean DoesExistEdge(Edge new_edge) {
		boolean result = false;
		for (Edge e : this.edges) {
			if (e.compareTo(new_edge) == 0) {
				return true;

			}

		}
		return result;
	}
	public boolean DoesExistEdge(int prm_countryId1, int prm_countryId2)
	{
		boolean result=false;
		for(Edge e : this.edges)
		{
			if((e.GetCountryId1()==prm_countryId1 && e.GetCountryId2()==prm_countryId2)
					|| (e.GetCountryId1()==prm_countryId2 && e.GetCountryId2()==prm_countryId1))
					{
						return true;
					}
		}
		return result;
		}
	public String AddEdge(int prm_countryId1, int prm_countryId2)
	{
		if(!DoesExistEdge( prm_countryId1,  prm_countryId2))
		{
			this.edges.add(new Edge(prm_countryId1,  prm_countryId2));
			return "successful";
		}
		else
		{
			return "duplicate";
		}
		
		}
	/*public String RemoveEdge(int prm_countryId)
	{
		Iterator it = this.edges.iterator(); 
		while(it.hasNext()){
		    Object o = it.next();
		    if(((Edge)o).DoesContainCountry(prm_countryId)){
		        it.remove();
		    }
		}
		return "successful";
		
		}*/
	public boolean DoesExistContinent(String prm_name)
	{
		boolean result=false;
		for(Continent c : this.GetContinents())
		{
			if(c.GetName().equals(prm_name))
			{
			  return true;	
			}
		}
		return result;
		}
	
	
	public Country GetNotAssignedCountryRandom() {
		List<Country> countries = GetCountriesNotAssigned();
		Country randCountry = countries.get(new Random().nextInt(countries.size()));
		return randCountry;
	}
	public String AddContinent(String prm_name,  int prm_control)
	{
		if(!DoesExistCountry(prm_name))
		{
			this.lands.add(FactoryLand.GetLand("Continent", prm_name, -1, -1, -1,prm_control));
			return "successful";
		}
		else
		{
			return "duplicate";
		}
		
		}
	
	
	public List<Country> GetCountriesNotAssigned() {
		List<Country> countriesNotAssigned = new ArrayList<Country>();
		for (Land c : lands) {
			if (c instanceof Country) {
				if (((Country) c).GetPlayerId() == -1) {
					countriesNotAssigned.add((Country) c);
				}
			}
		}
		return countriesNotAssigned;
	}

	 public List<Country> GetCountriesByPlayerId(int prm_playerId)
	 {
		 List<Country> countries = new ArrayList<Country>();
		 for(Country c : GetCountries())
		 {
			 if(c.GetPlayerId()==prm_playerId)
			 {
				 countries.add(c);
			 }
		 }
		 return countries;
	 }
	 
	 public Country GetCountryById(int prm_countryId)
	 {
		 Country country = null;
		 for(Country c:GetCountries())
		 {
			 if(c.GetId()==prm_countryId)
				 return c;
		 }
		 return country;
	 }
	public int GetContinentIdByName(String new_name) {
		int id = -1;
		for (Land l : this.lands) {
			if (l instanceof Continent && l.GetName().equals(new_name)) {
				return l.GetId();
			}
		}
		return id;
	}
	  public List<Country> GetNeighbors(int prm_countryId)
	  {
	    List<Country> neighbors = new ArrayList<Country>();
	    for(Edge e:edges)
	    {
	 	   if(e.DoesContainCountry(prm_countryId))
	 	   {
	 		  neighbors.add(GetCountryById(e.GetNeighborId(prm_countryId)));
	 	   }
	    }
	    return neighbors;
	  
	  }
	/*public int GetContinentCByName(String new_name) {
		int id = -1;
		for (Land l : this.lands) {
			if (l instanceof Continent && l.GetName().equals(new_name)) {
				return l.GetId();
			}
		}
		return id;
	}*/
	public int GetCountryIdByName(String new_name) {
		int id = -1;
		for (Land l : this.lands) {
			if (l instanceof Country && l.GetName().equals(new_name)) {
				return l.GetId();
			}
		}
		return id;
	}
	public Country GetCountryByName(String new_name) {
		for (Land l : this.lands) {
			if (l instanceof Country && l.GetName().equals(new_name)) {
				return (Country)l;
			}
		}
		return null;
	}
	public boolean DoesExistCountry(int prm_countryId)
	{
		boolean result=false;
		for(Country c : this.GetCountries())
		{
			if(c.GetId()== prm_countryId)
			{
			  return true;	
			}
		}
		return result;
		}
	
	public boolean DoesExistContinent(Continent cont)
	{
	boolean result=false;
		for (Continent c : this.GetContinents()) {
			if (c instanceof Continent && c.GetName().equals(cont.GetName())) 
			 {
				return true;
			 }
	}
		return result;
	}
	/*public Continent GetContinentByName(String new_cont) {
		int id = -1;
		for (Continent c : this.GetContinents()) {
			if (c instanceof Continent && c.GetName().equals(new_cont.GetName())) 
				return c.GetName();
			}
		}
		return id;
	}*/
	public String RemoveCountry(Country prm_country)
	{
		if(DoesExistCountry(prm_country.GetId()))
		{
			this.RemoveEdge(prm_country.GetId());
			this.lands.remove(prm_country);
			return "successful";
		}
		else
		{
			return "duplicate";
		}
		
	}
	public String RemoveEdge(int prm_countryId)
	{
		Iterator it = this.edges.iterator(); 
		while(it.hasNext()){
		    Object o = it.next();
		    if(((Edge)o).DoesContainCountry(prm_countryId)){
		        it.remove();
		    }
		}
		return "successful";
		
		}
	public String GetContinentNameById(int new_id) {
		String name = "";
		for (Land l : this.lands) {
			if (l instanceof Continent && l.GetId() == new_id) {
				return l.GetName();
			}
		}
		return name;
	}

	public String GetCountryNameById(int new_id) {
		String name = "";
		for (Land l : this.lands) {
			if (l instanceof Country && l.GetId() == new_id) {
				return l.GetName();
			}
		}
		return name;
	}

	public List<Continent> GetContinents() {
		List<Continent> continents = new ArrayList<Continent>();
		for (Land l : this.lands) {
			if (l instanceof Continent) {
				continents.add((Continent) l);
			}
		}
		return continents;
	}
	
	
	public List<Country> GetCountries() {
		List<Country> continents = new ArrayList<Country>();
		for (Land l : this.lands) {
			if (l instanceof Country) {
				continents.add((Country) l);
			}
		}
		return continents;
	}

	public List<String> GetNeighhboursName(Country new_country) {
		List<String> neighborsName = new ArrayList<String>();
		for (Edge e : this.edges) {
			if (e.DoesExistCountry(new_country.GetId())) {
				neighborsName.add(this.GetCountryNameById(e.GetNeighborId(new_country.GetId())));
			}
		}
		return neighborsName;

	}
	
	public List<String> CountriesToLine() {
		List<String> lines = new ArrayList<String>();
		String line;
		for (Country c : this.GetCountries()) {
			line = new String();
			line = c.GetName() + "," + c.GetX() + "," + c.GetY() + ","
					+ this.GetContinentNameById(c.GetContinentId());
			for (String neighborName : this.GetNeighhboursName(c)) {
				line += "," + neighborName;
			}
			lines.add(line);
		}
		return lines;
	}

	public List<String> MapToLines() {
		List<String> lines = new ArrayList<String>();
		lines.add("[Map]");
		lines.add("author=" + this.GetAuthor());
		lines.add("image=" + this.GetImage());
		lines.add("wrap=yes");
		lines.add("scroll=yes");
		lines.add("warn=yes");
		lines.add("");
		lines.add("[Continents]");
		for (Continent c : this.GetContinents()) {
			lines.add(c.GetName() + "=" + c.GetContinentId());
		}
		lines.add("");
		lines.add("[Territories]");
		for (String l : CountriesToLine()) {
			lines.add(l);
		}
		return lines;
	}
	
	
	public List<Country> GetCountriesByContinentId(int prm_continentId)
	{
		List<Country> countries = new ArrayList<Country>();
		for(Country c : GetCountries())
		{
			if(c.GetContinentId()==prm_continentId) 
				countries.add(c);
		}
		return countries;
		}
}
