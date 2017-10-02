package com.risk.model;

import com.risk.utility.*;
import com.risk.view.applicationWindow;

import java.io.IOException;

public class Console {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Game started");	
		applicationWindow appWindow = new applicationWindow();
        appWindow.open();
		GameBoard gameboard = GameBoard.GetGameBoard();
		MapParser mp = new MapParser();
	    gameboard.map = mp.MapParser("Earth.map");
	    //print map properties
	    System.out.println("Author = "+gameboard.map.GetAuthor());
	    System.out.println("Image = "+gameboard.map.GetImage());
	    for(String l : gameboard.map.MapToLines())
	    {
	    	System.out.println(l);
	    }
	    mp.WriteMapToFile( gameboard.map,"e:output.txt");
	    for(Object land:gameboard.map.lands)
	     {
	    	 if(land instanceof Continent )
	    	 {
	    	 System.out.println(((Continent)land).GetName());
	    	 }
	    	 else if(land instanceof Country)
	    	 {
	    		 System.out.println(((Country)land).GetName());
	    		 System.out.println("_________List Of Neighbors______________");
	         }
	     }
	     System.out.println("_________List Of Edges______________");
		 for(Edge e : gameboard.map.edges)  
	     {
	    	 System.out.println(e.GetId() + "   " + gameboard.map.GetCountryNameById(e.GetCountryId1())+"   " 
	                                               + gameboard.map.GetCountryNameById(e.GetCountryId2()));
	     }
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


