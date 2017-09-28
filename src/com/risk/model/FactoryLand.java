package com.risk.model;



public class FactoryLand {
	
	 public static Land GetLand(String type,int new_id, String new_name)
	 {
		 switch(type)
		 {
		 case "Country":
			 return new Country(new_id,new_name);
			 
		 case "Continent":
			 return new Continent(new_id,new_name);
			 
			 default:
				 return null;
				 
		 
		 }
	 }

}
