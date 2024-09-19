package Glendon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Files{

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write("textToAdd");
        fw.close();
    }

    public static void main(String[] args) {
        String file2 = "temp/lines.txt";
        try {
            writeToFile(file2, "first line" + System.lineSeparator() + "second line");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}