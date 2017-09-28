package com.risk.model;

import com.risk.utility.*;



import java.io.IOException;

public class Console {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		GameBoard gameboard = GameBoard.GetGameBoard();
		//String s = ParserHeader.Map.toString();
	     //System.out.println(s);
	     MapParser mp = new MapParser();
	     //Map map = mp.testParser("Earth.map");
	     gameboard.map = mp.MapParser("Earth.map");
	     /*for(Object land:gameboard.map.lands)
	     {
	    	 if(land instanceof Continent )
	    	 {
	    	 System.out.println(((Continent)land).GetName());
	    	 }
	    	 else if(land instanceof Country)
	    	 {
	    		 System.out.println(((Country)land).GetName());
	    		 System.out.println("_________List Of Neighbors______________");
	    		 for(Country neighbor:((Country)land).neighbers)
	    		 {
	    			 System.out.println(((Country)neighbor).GetName());
	    		 }
	    	 }
	     }
	     System.out.println("_________List Of Edges______________");
		 for(Edge e : gameboard.map.edges)
	     {
	    	 System.out.println(e.GetId() + "   " + e.GetCountry1()+"   " + e.GetCountry2());
	     }
		 */
		 gameboard.SetupPlayers();
		 gameboard.AssignCountriesRandom();
		 /*
		 for(Land l : gameboard.map.lands)
		 {
			 if(l instanceof Country)
			 {
			 System.out.println(((Country)l).GetPlayerId());
			 }
			 
		 }
		 */
		 for(int i =1;i<20;i++)
		 {
			 System.out.println(gameboard.GetNextPlayerId()); 
		 }
		}
	}


