import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import task.Todo; // Import the Todo class
import task.Deadline; // Import the Deadline class
import task.Event; // Import the Event class

public class Mel {

    private static final String LIST_FILE_PATH = ".\\data\\Mel.txt";

    public static void main(String[] args) throws IOException {
        Ui.printIntroMessage();
        
        // Set up scanner for user input
        Scanner in = new Scanner(System.in);
        List userList = new List();

        try {
            Storage.writerSetUp(LIST_FILE_PATH);
            Storage.loadDataFromFile(LIST_FILE_PATH, userList); // Load saved tasks
        } catch (IOException e) {
            System.out.println("An error occurred when setting up writer.");
        }

        Parser.getUserInput(in, LIST_FILE_PATH, userList);
    }
}
