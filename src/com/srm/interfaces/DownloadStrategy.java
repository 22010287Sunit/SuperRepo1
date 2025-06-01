package com.srm.interfaces;

import java.io.RandomAccessFile;

public interface DownloadStrategy {
    void downloadChunk(int chunkId, long startByte, long endByte, String url, RandomAccessFile file) throws Exception;
}
