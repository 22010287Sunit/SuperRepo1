package com.srm.loggingfiles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UtilLogger {
	private static final String LOG_DIR = "D:\\GitProjects\\MultiThreadedDownloader\\Logs";
	private static final String LOG_FILE_1 = "Console.log";
	private static final String LOG_FILE_2 = "Error.log";
	private static final File LOG_PATH_1 = new File(LOG_DIR, LOG_FILE_1);
	private static final File LOG_PATH_2 = new File(LOG_DIR, LOG_FILE_2);
	// We are adding static block, since we want to create log files directories before execution of main methos
	static {
		try {
			File directory = new File(LOG_DIR);
			if(!directory.exists()) {
				directory.mkdirs();
			}
			else {
				System.out.println("Path already exists");
			}
			if(!LOG_PATH_1.exists() && !LOG_PATH_2.exists()) {
				LOG_PATH_1.createNewFile();
				LOG_PATH_2.createNewFile();
				
			}
		}catch(IOException e) {
			System.out.println("IOException during log file creation: "+ e);
		}
	}
	
	private static synchronized void writeLog1(String level, String message) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_PATH_1, true))){
			String timeStamp = LocalDateTime.now().toString();
			writer.write("["+ timeStamp + "]" + message);
			writer.newLine();
		}catch(IOException e) {
			System.out.println("writeLog Method Exception: " + e);
			UtilLogger.exceptionLogError("Exception in writeLog Method", e);
		}
	}
	
	private static synchronized void writeLog2(String level, String message) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_PATH_2, true))){
			String timeStamp = LocalDateTime.now().toString();
			writer.write("["+ timeStamp + "]" + message);
			writer.newLine();
		}catch(IOException e) {
			System.out.println("writeLog Method Exception: " + e);
		}
	}
	
	public static void logInfo(String message) {
        writeLog1("INFO", message);
    }

    public static void exceptionLogError(String message, Exception ex) {
        writeLog2("ERROR", message + " | Exception: " + ex.getMessage());
    }
    public static void simpleLogError(String message) {
    	writeLog2("RuntimeError", message);
    }

    public static void logWarning(String message) {
        writeLog1("WARNING", message);
    }

}
