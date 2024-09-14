package esme.Storage;

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

    public void writeToFile(ArrayList<String> textToAdd) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (String text : textToAdd) {
            writer.write(text + System.lineSeparator());
        }
        writer.close();
    }

    public ArrayList<String> loadFileContents() throws FileNotFoundException {
        ArrayList<String> content = new ArrayList<>();
        Scanner s = new Scanner(inputFile);
        while (s.hasNext()) {
            content.add(s.nextLine());
        }
        return content;
    }
}
