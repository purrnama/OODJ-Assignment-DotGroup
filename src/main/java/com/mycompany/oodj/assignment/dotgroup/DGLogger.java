package com.mycompany.oodj.assignment.dotgroup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;

import java.util.logging.*;

public class DGLogger {
	private static final Logger LOGGER = Logger.getLogger(DGLogger.class.getName());
	
	public static void main(String[] args) {
		try {
			LogManager.getLogManager().readConfiguration(new FileInputStream("mylogging.properties"));
		} catch (SecurityException | IOException e1) {
		      e1.printStackTrace();
		  }
		LOGGER.setLevel(Level.FINE);
		LOGGER.addHandler(new ConsoleHandler());
		  //adding custom handler
		LOGGER.addHandler(new MyHandler());
		  try {
		      //FileHandler file name with max size and number of log files limit
		  Handler fileHandler = new FileHandler("/Users/pankaj/temp/logger.log", 2000, 5);
		  fileHandler.setFormatter(new MyFormatter());
		  //setting custom filter for FileHandler
		  fileHandler.setFilter(new MyFilter());
		  LOGGER.addHandler(fileHandler);
		  
		  for(int i=0; i<1000; i++){
		      //logging messages
			  LOGGER.log(Level.INFO, "Msg"+i);
		  }
		  LOGGER.log(Level.CONFIG, "Config data");
		  } catch (SecurityException | IOException e) {
		      e.printStackTrace();
		  }
	}
}