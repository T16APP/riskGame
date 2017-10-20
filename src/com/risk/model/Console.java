package com.risk.model;

import com.risk.utility.*;
import com.risk.view.applicationWindow;

import java.io.IOException;

public class Console {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		applicationWindow appWindow = new applicationWindow();
		System.out.println("Game started");
		appWindow.open();
		//Demo_addcountry();
		
	}
	/*public static void Demo_addcountry() throws Exception
	{
		GameBoard gameBoard = GameBoard.GetGameBoard();
		gameBoard.LoadMap("Earth.map");
		gameBoard.map.AddCountry("ctest", 1, 20, 20);
		System.out.println("this the new country: "+gameBoard.map.GetCountryIdByName("ctest"));
		System.out.println("The list of countries including the new"+gameBoard.map.getCountryListStringForCombobox(1));
	}*/
}
