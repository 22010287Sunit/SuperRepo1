package com.srm.urlconnection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.srm.interfaces.DownloadInitializer;
import com.srm.loggingfiles.UtilLogger;

public class DownloadInitializerImpl implements DownloadInitializer{

	@Override
	public boolean validateURL(String url) {
		try {
			URL urlObj = new URL(url);
			
			HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
			try {
				connection.setRequestMethod("HEAD");
				connection.setConnectTimeout(30000); // 30 sec to conncetion
				connection.setReadTimeout(30000); // 30 sec to wait for response
				System.out.println("Connection Successful");
				UtilLogger.logInfo("Connection Successful");
			}catch(Exception e) {
				UtilLogger.simpleLogError("Connection Catch: " + e);
				UtilLogger.simpleLogErrorObj(e);
				e.printStackTrace();
			}
			
			
			int responseCode = connection.getResponseCode();
			System.out.println(responseCode);
			UtilLogger.logInfo("ResponseCode " + responseCode);
			
			// Check if it's valid (200 = OK, 206 = Partial Content)
			return(responseCode == 200 || responseCode == 206);
			
		} catch (MalformedURLException e) {
			System.out.println("Invalid URL exception: " + e);
			UtilLogger.simpleLogError("Invalid URL exception: " + e);
			UtilLogger.simpleLogErrorObj(e);
			e.printStackTrace();
		} catch (IOException e) {
			UtilLogger.simpleLogErrorObj(e);
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
			UtilLogger.simpleLogErrorObj(e);
		}
		return false;
	}

	@Override
	//Connect to a given file URL and return the file size (in bytes) 
	//— so that the program knows how much to download.
	public long fetchFileSize(String url) throws Exception {
	    URL fileUrl = new URL(url);
	    HttpURLConnection connection = (HttpURLConnection) fileUrl.openConnection();

	    connection.setRequestMethod("GET");  // HEAD is used to get metadata only, not full file
	    connection.connect();

	    int responseCode = connection.getResponseCode();
	    if (responseCode >= 200 && responseCode < 300) {
	        return connection.getContentLengthLong();  // returns file size in bytes
	    } else {
	        throw new IOException("Failed to fetch file size. Response code: " + responseCode);
	    }
	}

}
