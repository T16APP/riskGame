package com.risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import com.risk.utility.staticApplicationVariables;

public class CreateMapfromGUI implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		  
		
	       staticApplicationVariables.gb.CreateMap();
	       
	
	   try {
    		staticApplicationVariables.gb.SaveMapToFile("output.txt");
	    	} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		   } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
