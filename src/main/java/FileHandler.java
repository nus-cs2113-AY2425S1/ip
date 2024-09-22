import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandler {
    File f;
    String path;


    public FileHandler() {
        this.f = new File("./src/main/java/FenixStorage");
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

    public void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(this.path);
        fw.write(textToAdd);
        fw.close();
    }

    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(this.path, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }
}
