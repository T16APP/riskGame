package com.risk.model;



public class FactoryLand {
	
	 public static Land GetLand(String type, String new_name, int new_continentId, double new_db1, double new_db2)
	 {
		 switch(type)
		 {
		 case "Country":
			 return new Country(new_name, new_continentId, new_db1, new_db2);
			 
		 case "Continent":
			 return new Continent(new_name, new_db1);
			 
			 default:
				 return null;
				 
		 
		 }
	 }

}
