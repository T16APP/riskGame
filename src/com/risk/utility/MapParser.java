package com.risk.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.risk.model.Edge;
import com.risk.model.FactoryLand;
import com.risk.model.Land;
import com.risk.model.Map;

/**
 * This class parses the input map files and creates the map it also generate a
 * map file from map object
 * 
 * @author Kourosh Aziz-Nejad
 * @version 1.0.0.0
 */

public class MapParser {
	private static Map map;

	public MapParser() {
	}

	public static boolean MapValidator(String input) throws Exception {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(input));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			String header = "";
			ArrayList arrMap = new ArrayList();
			ArrayList arrContinents = new ArrayList();
			ArrayList arrCountries = new ArrayList();
			boolean existsMap = false;
			boolean existsContinents = false;
			boolean existsTerritories = false;
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				if (line.startsWith("[Map]")) {
					header = "Map";
					existsMap = true;
				} else if (line.startsWith("[Continents]")) {
					header = "Continents";
					existsContinents = true;
				} else if (line.startsWith("[Territories]")) {
					header = "Territories";
					existsTerritories = true;
				} else if (!line.isEmpty()) {
					switch (header) {
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
			// validate headers: [Map],[Continents],[Territories]
			if (existsMap == false || existsContinents == false || existsTerritories == false) {
				throw new Exception("Header validator failed");

			}
			// continents validator: fails if there is no continent
			if (arrContinents.size() < 1) {
				throw new Exception("Map contains no continent!");
			}
			// countries validator: fails if there are less than 5 countries
			if (arrCountries.size() < 5) {
				throw new Exception("Map contains less than 5 countries!");
			}

		} catch (Exception ex) {

			throw ex;

		}
		return true;
	}

	/**
	 * this method parse a map file and returns a map object
	 * 
	 * @param input
	 *            is a file
	 * @return a map object contains continents and countries
	 * @throws Exception
	 */
	public static Map MapParser(String input) throws Exception {
		map = new Map("map1");
		BufferedReader br = null;
		try {
			// tbd
			if (!MapValidator(input))
				throw new Exception("NotValidMapFile");
			br = new BufferedReader(new FileReader(input));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			String header = "";
			ArrayList arrMap = new ArrayList();
			ArrayList arrContinents = new ArrayList();
			ArrayList arrCountries = new ArrayList();
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				if (line.startsWith("[Map]")) {
					header = "Map";
				} else if (line.startsWith("[Continents]")) {
					header = "Continents";
				} else if (line.startsWith("[Territories]")) {
					header = "Territories";
				} else if (!line.isEmpty()) {
					switch (header) {
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
			// building map
			// set map properties
			for (Object o : arrMap) {
				ParseMap((String) o);
			}
			int continentCount = 1;
			for (Object o : arrContinents) {
				map.lands.add(ParseContinents((String) o));
				continentCount++;
			}
			// building countries without neighbers
			int countryCount = 1;
			for (Object o : arrCountries) {
				map.lands.add(ParseCountries((String) o));
				countryCount++;
			}
			// add country to continent
			String[] strLines;
			for (Object o : arrCountries) {
				strLines = ((String) o).split(",");
				if (strLines.length > 4) {
					for (int i = 4; i < strLines.length; i++) {
						map.AddEdge(new Edge(map.GetCountryIdByName(strLines[0]), map.GetCountryIdByName(strLines[i])));
					}
				}

			}
			return map;
		} finally {

		}
	}

	/**
	 * this method extract continent from a line of map file
	 * 
	 * @param line
	 *            a line of map file
	 * @return a continent object
	 */
	public static Land ParseContinents(String line) {
		if (!line.isEmpty()) {
			String[] lines = line.split("=");
			String strName = lines[0];
			int control = Integer.parseInt(lines[1]);
			return FactoryLand.GetLand("Continent", strName, -1, -1, -1, control);
		}
		return null;
	}

	/**
	 * this method parses the Map header
	 * 
	 * @param line
	 *            is a line of map file
	 */
	public static void ParseMap(String line) {
		if (!line.isEmpty()) {
			String[] lines = line.split("=");
			String strName = lines[0];
			String strValue = lines[1];
			switch (strName.toLowerCase()) {
			case "author":
				map.SetAuthor(strValue);
				break;
			case "image":
				map.SetImage(strValue);
				break;
			}
		}

	}

	/**
	 * this method extract a country from a line of a map
	 * 
	 * @param line
	 *            is the line of a map
	 * @param prm_id
	 *            is the id of the country to be created
	 * @return
	 */
	public static Land ParseCountries(String line) {
		if (!line.isEmpty()) {
			String[] lines = line.split(",");
			String strName = lines[0];
			int x = Integer.parseInt(lines[1]);
			int y = Integer.parseInt(lines[2]);
			String strContinent = lines[3];
			return FactoryLand.GetLand("Country", strName, map.GetContinentIdByName(strContinent), x, y, -1);
		} else
			return null;
	}

	/**
	 * this method convert a map object to a map file
	 * 
	 * @param map
	 *            is the map to be converted
	 * @param file
	 *            is the output file
	 * @throws IOException
	 *             if the output file has issue to be written
	 */
	public static void WriteMapToFile(Map map, String file) throws IOException {
		PrintWriter f0 = new PrintWriter(new FileWriter(file));
		for (String line : map.MapToLines()) {
			f0.println(line);
		}
		f0.close();
	}
}
