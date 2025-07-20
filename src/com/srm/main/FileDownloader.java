package com.srm.main;

import com.srm.loggingfiles.UtilLogger;
import com.srm.urlconnection.DownloadInitializerImpl;

public class FileDownloader {
	public static void main(String[] args) {
		System.out.println("Download application starts");
		//UtilLogger.logInfo("Application logging starts");
		//UtilLogger.logWarning("Warning Logging starts");
		//UtilLogger.simpleLogError("Error logging starts");
		
		//String urlsample = "https://download.samplelib.com/";
		String urlsample = "https://kaido.to";
		//boolean output;
		DownloadInitializerImpl DII = new DownloadInitializerImpl();
		try {
			UtilLogger.logInfo(urlsample);
			 DII.validateURL(urlsample);
		}catch (Exception e) {
			System.out.println("Main validation Exception" + e);
			UtilLogger.simpleLogErrorObj(e);			
		}
		
		System.out.println("output is above");
		
	}

}
