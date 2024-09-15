package esme.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File inputFile;
    private String filePath;

    public Storage(String filePath) {
        inputFile = new File(filePath);
        this.filePath = filePath;
    }

    public void createFile() throws IOException {
        inputFile.createNewFile();
    }

    public void writeToFile(ArrayList<String> textToAdd) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (String text : textToAdd) {
            writer.write(text + System.lineSeparator());
        }
        writer.close();
    }

    public ArrayList<String> loadFileContents() throws FileNotFoundException {
        ArrayList<String> content = new ArrayList<>();
        Scanner reader = new Scanner(inputFile);
        while (reader.hasNext()) {
            content.add(reader.nextLine());
        }
        return content;
    }
}
