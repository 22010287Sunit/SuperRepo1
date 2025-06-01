package com.srm.interfaces;

public interface ProgressObserver {
    void updateProgress(int chunkId, long bytesDownloaded);
    void reportCompletion();
}
