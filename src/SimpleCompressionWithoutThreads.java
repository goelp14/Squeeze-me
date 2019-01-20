import java.io.*;

public class SimpleCompressionWithoutThreads {
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
        fileReader.close();
        System.out.println("Reading completed.");

        String newText = compress(fileText);
        fileWriter.write(newText);
        fileWriter.close();
        long endTime = System.nanoTime();

        System.out.println("Time Taken : " + (endTime-startTime)/1000000000 + " seconds");

    }


    public static String compress(String initial) {
        String newText = "";
        int count = 1;
        for(int i=1;i<initial.length();i++) {
            if(initial.charAt(i) == initial.charAt(i-1)) {
                count++;
                continue;
            }
            else {
                if(count>1) {
                    newText += initial.charAt(i-1) + "" + count;
                    count = 1;
                }
                else newText += initial.charAt(i-1);
            }
        }
        return newText;
    }
}
