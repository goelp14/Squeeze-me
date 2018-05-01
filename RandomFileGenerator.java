import java.util.*;
import java.io.*;

public class RandomFileGenerator {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter writer = new BufferedWriter(new FileWriter("random.txt"));
        System.out.println("Enter size of file(MB):");
        double size = sc.nextDouble()*1000000;

        Random r = new Random();
        for(int i=0;i<size;i++) {
            writer.write(r.nextInt(26)+'a');
        }
        writer.close();
        System.out.println("File created with name 'random.txt'");
    }
}
