## Written By Sunit 
We will be implementing below Java Concepts in this Project
1. Multithreading
2. I/O Stream
3. Interface Implementation
4. Abstraction
5. Collections
6. Exception Handling
7. File Handling

## 💡 Java Concepts Used (Given By ChatGPT)

This project demonstrates several Java Core and Advanced concepts:

- Multithreading using `Runnable` and `Thread`
- Byte-level file writing using `RandomAccessFile`
- URL-based file downloading using `HttpURLConnection`
- Interface-driven architecture with 5+ modular interfaces
- Exception handling, I/O Streams, Collections
- Optional advanced features like thread pooling and logging
------------------------------------------------------------------------

# Multi-Threaded File Downloader – Logic Flow

```mermaid
flowchart TD
    A[Start Application] --> B[Take File URL and Destination Path]
    B --> C[Open Connection]
    C --> D{Server Supports Range?}
    
    D -- No --> E[Single Thread Download]
    E --> Z[Merge/Save File]
    Z --> Y[Download Complete ✅]

    D -- Yes --> F[Get File Size]
    F --> G[Decide Number of Threads]
    G --> H[Split File into Byte Ranges]
    
    H --> I[For Each Thread]
    I --> J[Open HTTP Connection with Range Header]
    J --> K[Download Chunk -> Buffer -> Temp File]
    K --> L{All Chunks Downloaded?}
    L -- No --> I
    L -- Yes --> M[Merge Chunks into Final File]
    M --> Y[Download Complete ✅]

