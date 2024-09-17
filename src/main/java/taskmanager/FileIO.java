package taskmanager;
import java.io.*;

public class FileIO {
    public static void main(String[] args) {

        try {
        BufferedWriter writer = new BufferedWriter(new java.io.FileWriter("ryan.txt"));
        writer.write("writing to a file");
        writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
