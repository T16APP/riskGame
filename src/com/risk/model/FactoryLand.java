package com.risk.model;

/**
 * This class constructs objects of continent and country classes
 * 
 * @author Kourosh Aziz-Nejad
 * @version 1.0.0.0
 */

public class FactoryLand {

	public static Land GetLand(String type, String prm_name, int prm_continentId, int prm_x, int prm_y,
			int prm_control) {
		switch (type) {
		case "Country":
			return new Country(prm_name, prm_continentId, prm_x, prm_y);

		case "Continent":
			return new Continent(prm_name, prm_control);

		default:
			return null;

		}
	}

}
