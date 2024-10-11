import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The FileHandler class is responsible for handling file operations, such as reading from and writing to files.
 * It ensures that the storage file and its parent directories are created if they do not exist.
 */
public class FileHandler {
    private File f;
    private String path;

    /**
     * Constructs a FileHandler instance and initializes the storage file path.
     * The file is located at "data/FenixStorage.txt".
     */
    public FileHandler() {
        this.f = new File("data/FenixStorage.txt");
        this.path = this.f.getAbsolutePath();
    }

    /**
     * Loads the content of the file and returns it as a string.
     *
     * @return The content of the file as a single string.
     * @throws FileNotFoundException if the file does not exist.
     */
    public String loadFileContents() throws FileNotFoundException {
        Scanner s = new Scanner(this.f);
        StringBuilder fileContent = new StringBuilder();
        while (s.hasNext()) {
            fileContent.append(s.nextLine());
            fileContent.append("\n");
        }
        return fileContent.toString();
    }

    /**
     * Writes the provided text to the file, overwriting any existing content.
     * If the file does not exist, it is created.
     *
     * @param textToWrite The text to write to the file.
     * @throws IOException if an I/O error occurs.
     */
    public void writeToFile(String textToWrite) throws IOException {
        ensureFileExists();
        FileWriter fw = new FileWriter(this.path);
        fw.write(textToWrite);
        fw.close();
    }

    /**
     * Appends the provided text to the file, keeping the existing content intact.
     * If the file does not exist, it is created.
     *
     * @param textToAppend The text to append to the file.
     * @throws IOException if an I/O error occurs.
     */
    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(this.path, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Ensures that the file and its parent directories exist. If the file or its parent directories
     * do not exist, they are created.
     *
     * @throws IOException if an I/O error occurs when creating the file or directories.
     */
    private void ensureFileExists() throws IOException {
        if (!f.exists()) {
            f.getParentFile().mkdirs();  // Create the parent directory if it doesn't exist
            f.createNewFile();           // Create the file if it doesn't exist
        }
    }
}
