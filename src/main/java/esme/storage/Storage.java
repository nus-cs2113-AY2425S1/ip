package esme.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Provides methods for interacting with a file, such as creating a new file or
 * loading its contents.
 */
public class Storage {
    /**
     * The file to interact with.
     */
    private File inputFile;

    /**
     * The path of the file to interact with.
     */
    private String filePath;

    /**
     * Creates a new Storage object, which can be used to interact with a file.
     *
     * @param filePath The path of the file to interact with.
     */
    public Storage(String filePath) {
        inputFile = new File(filePath);
        this.filePath = filePath;
    }

    /**
     * Creates a new file if it does not exist already.
     *
     * @throws IOException If the file cannot be created.
     */
    public void createFile() throws IOException {
        inputFile.createNewFile();
    }

    /**
     * Writes the given text to the file.
     *
     * @param textToAdd The text to write to the file.
     * @throws IOException If the file cannot be written to.
     */
    public void writeToFile(ArrayList<String> textToAdd) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (String text : textToAdd) {
            writer.write(text + System.lineSeparator());
        }
        writer.close();
    }

    /**
     * Loads the contents of the file into an ArrayList.
     *
     * @return An ArrayList containing the lines of the file.
     * @throws FileNotFoundException If the file does not exist.
     */
    public ArrayList<String> loadFileContents() throws FileNotFoundException {
        ArrayList<String> content = new ArrayList<>();
        Scanner reader = new Scanner(inputFile);
        while (reader.hasNext()) {
            content.add(reader.nextLine());
        }
        reader.close();
        return content;
    }
}
