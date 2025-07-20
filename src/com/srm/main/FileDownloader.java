package com.srm.main;

import com.srm.loggingfiles.UtilLogger;
import com.srm.urlconnection.DownloadInitializerImpl;

public class FileDownloader {
	public static void main(String[] args) {
		System.out.println("Download application starts");
		//UtilLogger.logInfo("Application logging starts");
		//UtilLogger.logWarning("Warning Logging starts");
		//UtilLogger.simpleLogError("Error logging starts");
		
		String urlsample = "https://picsum.photos/200/300";
		//String urlsample = "https://file-examples.com/wp-content/uploads/2017/10/file_example_PNG_500kB.png";
		//boolean output;
		DownloadInitializerImpl DII = new DownloadInitializerImpl();
		try {
			UtilLogger.logInfo(urlsample);
			 DII.validateURL(urlsample);
			 long filesize = DII.fetchFileSize(urlsample);
			 System.out.println("File size is: "+ filesize);
		}catch (Exception e) {
			System.out.println("Main validation Exception" + e);
			UtilLogger.simpleLogErrorObj(e);			
		}
		
		System.out.println("output is above");
		
	}

}
