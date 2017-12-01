package com.risk.utility;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * this class provides a static method accesible everywhere to write into
 * loggong window
 * 
 * @author Kourosh
 * @version 1.0.0.0
 */
public class LoggingWindow {
	/*
	 * this is a static method that takes the text and writes it into
	 * loggingwindow file
	 * 
	 * @param is the text to be written
	 */
	public static void Log(String line) throws IOException {
		String filename = "LoggingWindow.txt";
		FileWriter fw = new FileWriter(filename, true);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		// appends the string to the file
		fw.write(dateFormat.format(cal.getTime())+ "\n");
		// appends the string to the file
		fw.write(line + "\n");
		fw.close();
	}

}
