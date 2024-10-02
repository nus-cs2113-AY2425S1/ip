import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import UserInteraction.Ui;
import static File.FileFunctions.*;
import static UserInteraction.Storage.initializeArrayList;


public class Main {
    private static final String FILE_PATH = "./data/tasks.txt";
    public static void main(String[] args) {
        File f = new File(FILE_PATH);
        getFileProperties(f);
        String absolutePath = f.getAbsolutePath();

        try {
            printFileContents(absolutePath, f);
            Ui edith = new Ui("Edith");
            initializeArrayList(edith.getTasks(), f);
            edith.giveIntroduction();
            edith.interactWithUser();
            edith.sayGoodbye();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("I/O exception.");
        }


    }
}
