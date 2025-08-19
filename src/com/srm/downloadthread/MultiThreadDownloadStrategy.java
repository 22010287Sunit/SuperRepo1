package com.srm.downloadthread;

import com.srm.interfaces.DownloadStrategy;
import com.srm.loggingfiles.UtilLogger;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Concrete implementation of DownloadStrategy.
 * Handles downloading of a specific chunk (byte range) from a URL
 * and writing it into the correct position in a RandomAccessFile.
 */
public class MultiThreadDownloadStrategy implements DownloadStrategy {

    /**
     * Downloads a chunk of the file from the given URL and writes it into the RandomAccessFile.
     *
     * @param chunkId   Identifier of the chunk (for logging/debugging).
     * @param startByte The starting byte position of this chunk.
     * @param endByte   The ending byte position of this chunk (-1 if till EOF).
     * @param url       The file URL to download from.
     * @param file      The RandomAccessFile representing the output file to write into.
     */
    @Override
    public void downloadChunk(int chunkId, long startByte, long endByte, String url, RandomAccessFile file) throws Exception {
        
        // Open a new HTTP connection for this chunk
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

        // Set HTTP "Range" header to request only this chunk of bytes
        // e.g. "bytes=0-1999" for first 2000 bytes
        String range = "bytes=" + startByte + "-" + (endByte >= 0 ? endByte : "");
        conn.setRequestProperty("Range", range);

        // Set User-Agent to mimic a real browser (helps bypass some servers that block bots)
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");

        // Get server's response code after requesting the chunk
        int code = conn.getResponseCode();

        // If the server does not return "Partial Content (206)" or "OK (200)", fail the chunk download
        if (code != HttpURLConnection.HTTP_PARTIAL && code != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("HTTP " + code + " for chunk " + chunkId + " range " + range);
        }

        // Log the successful initiation of the chunk download
        System.out.println("Chunk " + chunkId + " -> " + range + " (" + code + ")");
        UtilLogger.logInfo("Chunk " + chunkId + " -> " + range + " (" + code + ")");

        // Open input stream to read the chunk data from server
        try (InputStream in = conn.getInputStream()) {
            
            // Buffer for reading data from the stream
            byte[] buf = new byte[8192];  

            // Current write position inside the file (start at the chunk's start byte)
            long pos = startByte;  

            int r; // number of bytes read in one iteration

            // Read until end of stream
            while ((r = in.read(buf)) != -1) {
                // Move file pointer to correct position for this chunk
                file.seek(pos);

                // Write the buffer content into file at the correct offset
                file.write(buf, 0, r);

                // Update the current position pointer
                pos += r;
            }
        }
    }
}
