package com.srm.urlconnection;

import java.net.MalformedURLException;
import java.net.URL;

import com.srm.interfaces.DownloadInitializer;
import com.srm.loggingfiles.UtilLogger;

public class DownloadInitializerImpl implements DownloadInitializer{

	@Override
	public boolean validateURL(String url) {
		try {
			URL urlString = new URL(url);
			return true;
		} catch (MalformedURLException e) {
			System.out.println("Invalid URL exception: " + e);
			UtilLogger.simpleLogError("Invalid URL exception: " + e);
			UtilLogger.simpleLogErrorObj(e);
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public long fetchFileSize(String url) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
