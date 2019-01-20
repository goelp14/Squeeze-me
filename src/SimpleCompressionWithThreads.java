import java.io.*;

class SimpleCompressionWithThreads {
    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();

        BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter a fileName(file should be non-numeric):");
        String fileName = consoleInput.readLine();

        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
        // Writer initialised to prevent calculations in case of error here.
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter("compressed_"+fileName));

        String fileText = "", line = fileReader.readLine();
        while(line != null) {
            fileText += line + "\n";
            line = fileReader.readLine();
        }

        System.out.println("Reading completed.");

        Compressor firstHalf = new Compressor(fileText.substring(0,fileText.length()/2));
        Compressor secondHalf = new Compressor(fileText.substring(fileText.length()/2));
        try {
            System.out.println("Waiting for threads to join...");
            firstHalf.t.join();
            secondHalf.t.join();
            System.out.println("Threads joined Successfully.");
        } catch(Exception e) {
            System.out.println(e);
        }

        fileWriter.write(firstHalf.compressedText);
        fileWriter.write(secondHalf.compressedText);

        System.out.println((double)(firstHalf.compressedText.length()+secondHalf.compressedText.length())/fileText.length());
        fileWriter.close();
        fileReader.close();

        long endTime = System.nanoTime();
        System.out.println("Time Taken : "+ (endTime-startTime)/1000000000 + " seconds");
    }
}

class Compressor implements Runnable {
    Thread t;
    String text;
    String compressedText;

    Compressor(String text) {
        t = new Thread(this);
        this.text = text;
        compressedText = "";
        System.out.println("Started Thread.");
        t.start();

    }

    public void run() {
        int count = 1;
        for(int i=1;i<text.length();i++) {
            if(text.charAt(i) == text.charAt(i-1)) {
                count++;
                continue;
            }
            else {
                if(count>1) {
                    compressedText += text.charAt(i-1) + "" + count;
                    count = 1;
                }
                else compressedText += text.charAt(i-1);
            }
        }
    }
}
