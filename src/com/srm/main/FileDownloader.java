package com.srm.main;

import com.srm.interfaces.DownloadManager;
import com.srm.loggingfiles.UtilLogger;
import com.srm.urlconnection.DownloadInitializerImpl;

public class FileDownloader {
	public static void main(String[] args) {
		System.out.println("Download application starts");
		
		//String urlsample = "https://docs.google.com/document/d/1jlpLFni73KwqWJGG6tvGHbh-kENWNYxP4eqZNeL1Hqc/export?format=pdf";
		//String urlsample = "https://ontheline.trincoll.edu/images/bookdown/sample-local-pdf.pdf";
		  String urlsample = "https://picsum.photos/200/300";
		//boolean output;
		DownloadInitializerImpl DII = new DownloadInitializerImpl();
		try {
			UtilLogger.logInfo(urlsample);
			 DII.validateURL(urlsample);
			 long filesize = DII.fetchFileSize(urlsample);
			 System.out.println("File size is: "+ filesize);
			 
			 int numberOfThreads; // You can tweak this
			 
			 if(filesize < 10000) {
				 numberOfThreads = 2;
			 }
			 else {
				 numberOfThreads = 3;
			 }
			 System.out.println("since filesize is "+ filesize + " Number of threads are : "+ numberOfThreads);
			 
	         DownloadManager manager = new DownloadManagerImpl();
	         manager.startDownload(urlsample, numberOfThreads);

	         System.out.println("Download initiated with " + numberOfThreads + " threads");
			 
		}catch (Exception e) {
			System.out.println("Main validation Exception" + e);
			UtilLogger.simpleLogErrorObj(e);			
		}
		
		System.out.println("output is above");
		
	}

}
