package com.risk.utility;

import java.io.FileWriter;
import java.io.IOException;
/**this class provides a static method accesible everywhere to
 * write into loggong window
 * @author Kourosh
 * @version 1.0.0.0
 */
public class LoggingWindow {
	/*this is a static method that takes the text and writes it
	 * into loggingwindow file
	 * @param is the text to be written 
	 */
	public static void Log(String line) throws IOException{
		String filename= "LoggingWindow.txt";
	    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
	    fw.write(line+"\n");//appends the string to the file
	    fw.close();
	}

}
