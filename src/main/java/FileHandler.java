import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandler {
    File f;
    String path;


    public FileHandler() {
        this.f = new File("data/FenixStorage.txt");
        this.path = this.f.getAbsolutePath();
    }

    public String loadFileContents() throws FileNotFoundException {
        Scanner s = new Scanner(this.f);
        StringBuilder fileContent = new StringBuilder();
        while (s.hasNext()) {
            fileContent.append(s.nextLine());
            fileContent.append("\n");
        }
        return fileContent.toString();
    }

    public void writeToFile(String textToWrite) throws IOException {
        ensureFileExists();
        FileWriter fw = new FileWriter(this.path);
        fw.write(textToWrite);
        fw.close();
    }

    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(this.path, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    // Method to ensure the file and parent directory exist
    private void ensureFileExists() throws IOException {
        if (!f.exists()) {
            f.getParentFile().mkdirs();  // Create the parent directory if it doesn't exist
            f.createNewFile();           // Create the file if it doesn't exist
        }
    }
}
