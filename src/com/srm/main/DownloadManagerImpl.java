package com.srm.main;

import com.srm.interfaces.DownloadManager;
import com.srm.interfaces.DownloadStrategy;
import com.srm.interfaces.DownloadInitializer;
import com.srm.downloadthread.DownloadThread;
import com.srm.downloadthread.MultiThreadDownloadStrategy;
import com.srm.urlconnection.DownloadInitializerImpl;
import com.srm.loggingfiles.UtilLogger;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class DownloadManagerImpl implements DownloadManager {
    private final DownloadInitializer initializer = new DownloadInitializerImpl();
    private final DownloadStrategy strategy = new MultiThreadDownloadStrategy();
    private static final int FIRST_CHUNK_BYTES = 2000;

    @Override
    public void startDownload(String url, int numberOfThreads) throws Exception {
        UtilLogger.logInfo("Starting download: " + url + " with " + numberOfThreads + " threads");

        initializer.validateURL(url);
        long size = -1;
        try {
            size = initializer.fetchFileSize(url); // may be -1 for dynamic sites
            System.out.println("Filesize DownloadManager: " + size);
        } catch (Exception e) {
            UtilLogger.logWarning("Size unknown, proceeding with open-ended last chunk. Reason: " + e.getMessage());
        }

        String out = "C:\\Users\\Admin\\Pictures\\Camera Roll\\Output1.png";
        if (size > 0) {
            try (RandomAccessFile pre = new RandomAccessFile(out, "rw")) {
                pre.setLength(size); // preallocate
            }
        }

        List<Thread> threads = new ArrayList<>();

        if (size <= 0) {
            // Unknown size → your picsum plan: first 2000 bytes, rest open-ended
            if (numberOfThreads < 2) numberOfThreads = 2;
            long firstEnd = FIRST_CHUNK_BYTES - 1;

            RandomAccessFile f1 = new RandomAccessFile(out, "rw");
            RandomAccessFile f2 = new RandomAccessFile(out, "rw");

            threads.add(new DownloadThread(1, 0, firstEnd, url, f1, strategy));
            threads.add(new DownloadThread(2, FIRST_CHUNK_BYTES, -1, url, f2, strategy));
        } else {
            long part = (long) Math.ceil((double) size / numberOfThreads);
            for (int i = 0; i < numberOfThreads; i++) {
                long start = i * part;
                long end = Math.min(size - 1, start + part - 1);
                RandomAccessFile f = new RandomAccessFile(out, "rw");
                threads.add(new DownloadThread(i + 1, start, end, url, f, strategy));
                if (end >= size - 1) break;
            }
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        UtilLogger.logInfo("Download finished: " + out);
    }
}