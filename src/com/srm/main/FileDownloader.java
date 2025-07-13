package com.srm.main;

import com.srm.loggingfiles.UtilLogger;
import com.srm.urlconnection.DownloadInitializerImpl;

public class FileDownloader {
	public static void main(String[] args) {
		System.out.println("Download application starts");
		UtilLogger.logInfo("Application logging starts");
		UtilLogger.logWarning("Warning Logging starts");
		UtilLogger.simpleLogError("Error logging starts");
		
		String urlsample = "https://ipv4.download.thinkbroadband.com/5MB.zip";
		//boolean output;
		DownloadInitializerImpl DII = new DownloadInitializerImpl();
		try {
			 DII.validateURL(urlsample);
		}catch (Exception e) {
			System.out.println("Main validation Exception" + e);
			UtilLogger.simpleLogErrorObj(e);			
		}
		
		System.out.println("output is above");
		
	}

}
