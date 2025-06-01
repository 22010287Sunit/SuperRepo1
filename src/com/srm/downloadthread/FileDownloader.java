
package com.srm.downloadthread;

import com.srm.loggingfiles.UtilLogger;

public class FileDownloader {
	public static void main(String[] args) {
		System.out.println("Download application starts");
		UtilLogger.logInfo("Application logging starts");
		UtilLogger.logWarning("Warning Logging starts");
		UtilLogger.simpleLogError("Error logging starts");
	}

}
