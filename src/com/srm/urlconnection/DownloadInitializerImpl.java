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
				connection.setConnectTimeout(5000); // 5 sec to conncetion
				connection.setReadTimeout(5000); // 5 sec to wait for response
			}catch(Exception e) {
				UtilLogger.simpleLogError("Connection Catch: " + e);
				UtilLogger.simpleLogErrorObj(e);
				e.printStackTrace();
			}
			
			
			int responseCode = connection.getResponseCode();
			
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
	public long fetchFileSize(String url) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
