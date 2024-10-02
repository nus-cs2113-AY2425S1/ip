import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import UserInteraction.ChatBot;

import static File.FileFunctions.printFileContents;

public class Main {
    Scanner sc = new Scanner(System.in);




    public static void main(String[] args) {
        File f = new File("data/tasks.txt");
        String absolutePath = f.getAbsolutePath();
        try {
            printFileContents(absolutePath, f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        //System.out.println("file exists?: " + f.exists());
        //System.out.println("is Directory?: " + f.isDirectory());
        ChatBot edith = new ChatBot("Edith");
        edith.giveIntroduction();
        edith.interactWithUser();
        edith.sayGoodbye();

    }
}
