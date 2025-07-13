package com.srm.main;

import com.srm.loggingfiles.UtilLogger;
import com.srm.urlconnection.DownloadInitializerImpl;

public class FileDownloader {
	public static void main(String[] args) {
		System.out.println("Download application starts");
		UtilLogger.logInfo("Application logging starts");
		UtilLogger.logWarning("Warning Logging starts");
		UtilLogger.simpleLogError("Error logging starts");
		
		String urlsample = "https//example.com/file.zip";
		boolean output;
		DownloadInitializerImpl DII = new DownloadInitializerImpl();
		output = DII.validateURL(urlsample);
		System.out.println(output);
		
	}

}
