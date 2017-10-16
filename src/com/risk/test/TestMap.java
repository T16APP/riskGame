package com.risk.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.risk.model.Continent;
import com.risk.model.Country;
import com.risk.model.Edge;
import com.risk.model.Land;
import com.risk.model.Map;
import com.risk.model.Player;

public class TestMap {
	Map map;
	Edge edge;
	
	@Before
	public void testBefore() {
		System.out.println("@BeforeClass");
		map = new Map("new_name");	
		edge = new Edge(1,1);
	}

	@Test
	public void testGetAuthor() {
		
		String author = map.GetAuthor();
		System.out.println(author);
		assertEquals("new_name",author);
		
		fail("Not yet implemented");
	}



@Test
public void testGetImage() {
	String image = map.GetImage();
	System.out.println(image);
	assertEquals("new_name",image);
	fail("Not yet implemented");
}

@Test
public void testLandByName() {
	Land landname = map.GetLandByName("landname");
	System.out.println(landname);
	assertEquals("landname1",landname);
	assertNull(0);
	
	fail("Not yet implemented");
}

@Test
public void testRemoveContinent() {
	
}

@Test
public void testDoesExistCountry() {
	
}

@Test
public void testDoesExistContinent() {
	
}

@Test
public void testAddEdge() {
	
}

@Test
public void testAddCountry() {
	
}

@Test
public void testDoesExistEdge() {
	
}

@Test
public void testGetCountriesNotAssigned() {
	
}

@Test
public void testGetCountriesByPlayerId() {
	
}

@Test
public void testGetCountryById() {
	
}

@Test
public void testGetContinentIdByName() {
	
}

@Test
public void testGetNeighbors() {
	
}


@Test
public void testGetCountryIdByName() {
	
}

@Test
public void testGetCountryByName() {
	
}
}




/*@Test
public void testAddEdge() {
	System.out.println("testAddEdge");

	int edgeresult1 = map.AddEdge(edge);
	System.out.println(edgeresult1);
	
	int edgeresult2 = map.AddEdge(edge);
	System.out.println(edgeresult2);
	
	assertEquals(1, edgeresult1);
	assertEquals(0, edgeresult2);
	
	fail("Not yet implemented");
}



@Test
public void testDoesExistEdge() {
	System.out.println("testDoesExistEdge");
	
	boolean edgeresult1 = false;
	System.out.println(edgeresult1);
	
	int edgeresult2 = map.AddEdge(edge);
	System.out.println(edgeresult2);
	
	assertTrue(true);
	assertFalse(true);
	
	fail("Not yet implemented");

}

@Test
public void testGetNotAssignedCountryRandom() {
	fail("Not yet implemented");
} 

public Country GetNotAssignedCountryRandom() {
List<Country> countries = GetCountriesNotAssigned();
Country randCountry = countries.get(new Random().nextInt(countries.size()));
return randCountry;
}


@Test
public void test() {
	fail("Not yet implemented");
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


@Test
public void test() {
	fail("Not yet implemented");
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


@Test
public void test() {
	fail("Not yet implemented");
}
public int GetCountryIdByName(String new_name) {
int id = -1;

for (Land l : this.lands) {
	if (l instanceof Country && l.GetName().equals(new_name)) {
		return l.GetId();
	}
}
return id;
}

@Test
public void test() {
	fail("Not yet implemented");
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

@Test
public void test() {
	fail("Not yet implemented");
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

@Test
public void test() {
	fail("Not yet implemented");
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

@Test
public void test() {
	fail("Not yet implemented");
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

@Test
public void test() {
	fail("Not yet implemented");
}


public List<String> GetNeihborsName(Country new_country) {
List<String> neighborsName = new ArrayList<String>();
for (Edge e : this.edges) {
	if (e.DoesExistCountry(new_country.GetId())) {
		neighborsName.add(this.GetCountryNameById(e.GetNeighborId(new_country.GetId())));
	}
}
return neighborsName;

}

@Test
public void test() {
	fail("Not yet implemented");
}


public List<String> CountriesToLine() {
List<String> lines = new ArrayList<String>();
String line;
for (Country c : this.GetCountries()) {
	line = new String();
	line = c.GetName() + "," + c.GetDb1() + "," + c.GetDb2() + ","
			+ this.GetContinentNameById(c.GetContinentId());
	for (String neighborName : this.GetNeihborsName(c)) {
		line += "," + neighborName;
	}
	lines.add(line);
}
return lines;
}


@Test
public void test() {
	fail("Not yet implemented");
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
	lines.add(c.GetName() + "=" + c.GetDb1());
}
lines.add("");
lines.add("[Territories]");
for (String l : CountriesToLine()) {
	lines.add(l);
}
return lines;
}
}

@Test
public void test() {
	fail("Not yet implemented");
}
*/
