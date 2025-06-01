package com.srm.interfaces;

public interface DownloadInitializer {
	boolean validateURL(String url);
	long fetchFileSize(String url) throws Exception;
}
