package com.srm.downloadthread;

import com.srm.interfaces.DownloadStrategy;
import com.srm.loggingfiles.UtilLogger;

import java.io.RandomAccessFile;

public class DownloadThread extends Thread {
    private final int chunkId;
    private final long startByte, endByte;
    private final String url;
    private final RandomAccessFile file;
    private final DownloadStrategy strategy;

    public DownloadThread(int chunkId, long startByte, long endByte, String url,
                          RandomAccessFile file, DownloadStrategy strategy) {
        this.chunkId = chunkId;
        this.startByte = startByte;
        this.endByte = endByte;
        this.url = url;
        this.file = file;
        this.strategy = strategy;
        setName("DL-" + chunkId);
    }

    @Override
    public void run() {
        try {
            strategy.downloadChunk(chunkId, startByte, endByte, url, file);
        } catch (Exception e) {
            UtilLogger.simpleLogErrorObj(e);
        } finally {
            try { file.close(); } catch (Exception ignore) {}
        }
    }
}
