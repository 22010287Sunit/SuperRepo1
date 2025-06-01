package com.srm.interfaces;

public interface DownloadManager {
    void startDownload(String url, int numberOfThreads) throws Exception;
}
