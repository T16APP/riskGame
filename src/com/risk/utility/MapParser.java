package com.risk.utility;
/**
 * This is a class that reads map from a text file and extracts continents and countries
 * @author Kourosh Aziz-Nejad
 *
 */
import com.risk.model.Map;
import com.risk.model.Continent;
import com.risk.model.Country;
import com.risk.model.Edge;
import com.risk.model.Land;
import com.risk.model.FactoryLand;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

 public class MapParser {
	 private Map map;
	public MapParser(){}
	public Map MapParser(String input) throws IOException
	{
	map = new Map("map1");
	BufferedReader br=null;
	try {
		  br = new BufferedReader(new FileReader(input));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
	    StringBuilder sb = new StringBuilder();
	    String line = br.readLine();
        String header="";
        ArrayList arrMap = new ArrayList();
	    ArrayList arrContinents = new ArrayList();
	    ArrayList arrCountries = new ArrayList();
        while (line != null) {
	        sb.append(line);
	        sb.append(System.lineSeparator());
	        if(line.startsWith("[Map]"))
	        {
	        	header = "Map";
	        }
	        else if(line.startsWith("[Continents]"))
	        {
	        	header = "Continents";
	        }
	        else if(line.startsWith("[Territories]"))
	        {
	        	header = "Territories";
	        }
	        else if(!line.isEmpty())
	        {
	        	switch(header)
	        	{
	        	case "Map":
	        		arrMap.add(line);
	        		break;
	        	case "Continents":
	        		arrContinents.add(line);
	        		break;
	        	case "Territories":
	        		arrCountries.add(line);
	        		break;
	        	}
	        }
	        line = br.readLine();
	    }
        br.close();
        //building map
        //building continents
        int continentCount=1;
        for(Object o:arrContinents)
        {
        	map.lands.add(ParseContinents((String) o,continentCount));
        	continentCount++;
        }
        //building countries without neighbers
        int countryCount=1;
        for(Object o : arrCountries)
        {
        	map.lands.add(ParseCountries((String)o,countryCount ));
        	countryCount++;
        }
        //add country to continent
        String[] strLines;
        for(Object o : arrCountries)
        {
        	strLines = ((String)o).split(",");
        	((Continent)map.GetLandByName(strLines[3])).countries.add((Country)map.GetLandByName(strLines[0]));
        	if(strLines.length>4)
        	{
        		for(int i=4;i<strLines.length;i++)
        		{
        			((Country)map.GetLandByName(strLines[0])).neighbers.add(((Country)map.GetLandByName(strLines[i])));
        			map.AddEdge(new Edge(strLines[0],strLines[i]));
        		}
        	}
        	
        }
        return map;
	} finally {
	    
	}
	}
	public void ParseMap(String line){}
	public Land ParseContinents(String line, int new_id){
		if(!line.isEmpty())
		{
		String[] lines = line.split("=");
		String strName = lines[0];
		int int1 = Integer.parseInt(lines[1]);
		return FactoryLand.GetLand("Continent",new_id, strName);
		}
		else return null;
	}
	public Land ParseCountries(String line,int new_id)
	{
		if(!line.isEmpty())
		{
		String[] lines = line.split(",");
		String strName = lines[0];
		int int1 = Integer.parseInt(lines[1]);
		int int2 = Integer.parseInt(lines[2]);
		String strContinent = lines[3];
		return FactoryLand.GetLand("Country",new_id, strName);
		}
		else return null;
	}
}
