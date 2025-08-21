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
---

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

---

sequenceDiagram
    participant C as Client (Downloader)
    participant S as Server

    C->>S: HTTP GET /file.zip
    S-->>C: 200 OK (Full file stream)

    Note over C,S: Single-thread fallback when <br> server does not support ranges

    C->>S: HTTP GET /file.zip Range: bytes=0-1023
    S-->>C: 206 Partial Content (Chunk 0)

    C->>S: HTTP GET /file.zip Range: bytes=1024-2047
    S-->>C: 206 Partial Content (Chunk 1)

    C->>S: HTTP GET /file.zip Range: bytes=2048-3071
    S-->>C: 206 Partial Content (Chunk 2)

    Note over C: Client merges all chunks into final file
    C-->>S: Download Complete ✅


