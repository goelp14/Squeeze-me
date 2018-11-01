# multithreading_java_compressor
A simple demonstration of multi-threading in java by compressing a file having non-numeric characters with run-length encoding.

RandomFileGenerator : generates a random file with the name random.txt
(Suggested to use size less than 1MB)

SimpleCompressionWithoutThreads.java : Simple compression

SimpleCompressionWithThreads.java : The same file, now split in 2 simultaneously running threads
