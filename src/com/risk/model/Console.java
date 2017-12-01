package com.risk.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.risk.utility.*;
import com.risk.view.applicationWindow;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		//applicationWindow appWindow = new applicationWindow();
		//System.out.println("Game started");
		//appWindow.open();
		//Demo_StartUp_Reinforcement();
		//Demo_LoadGameFromFile();
		//Demo_StartUp_SaveToFile();
	    Demo_Entrance();
		
	}
	/**demo for entry
	 * 
	 * @throws Exception not found file
	 */
	public static void Demo_Entrance() throws Exception{
		Scanner inInteger = new Scanner(System.in); 
		Scanner inString = new Scanner(System.in); 
	    GameBoard gameBoard = GameBoard.GetGameBoard();
		gameBoard.GameEntry();
		System.out.println("Quit or load a game from file: 0 for Quit, 1 for already saved game");
		int newOrLoad = inInteger.nextInt();
		if(newOrLoad==1){
			inInteger = new Scanner(System.in);
			System.out.println("Enter name of the file in which a game already saved:");
			String file = inString.next();
			gameBoard = GameBoard.LoadGameFromFile(file);
			switch(gameBoard.turnOrganizer.GetCurrentPhase()){
				case Reinforcement:
					gameBoard.turnOrganizer.GetCurrentPlayer().Reinforcement();
					break;
				case Attack:
					gameBoard.turnOrganizer.GetCurrentPlayer().Attack();
					break;
				case Fortification:
					gameBoard.turnOrganizer.GetCurrentPlayer().Fortification();
					break;
					
			}
		}
	}
	
	

}
