import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileHandler {
    File f;
    String path;

    public FileHandler() {
        this.f = new File("./src/main/java/FenixStorage");
        this.path = this.f.getAbsolutePath();
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
