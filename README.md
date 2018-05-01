# multithreading_java_compressor
A simple demonstration of multi-threading in java by compressing a file having non-numeric characters.

RandomFileGenerator : generates a random file with the name random.txt
(Suggested to use size less than 1MB)

SimpleCompressionWithoutThreads.java : A very basic compression based on counting the number of repeated characters.

SimpleCompressionWithThreads.java : The same file, now split in 2 simultaneously running threads.
