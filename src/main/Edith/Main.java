import java.io.File;
import java.io.FileNotFoundException;
import UserInteraction.ChatBot;
import static File.FileFunctions.*;


public class Main {
    public static void main(String[] args) {
        File f = new File("./data/tasks.txt");
        getFileProperties(f);
        String absolutePath = f.getAbsolutePath();
        try {
            printFileContents(absolutePath, f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        ChatBot edith = new ChatBot("Edith");
        edith.giveIntroduction();
        edith.interactWithUser();
        edith.sayGoodbye();

    }
}
