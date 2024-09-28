import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import task.Todo; // Import the Todo class
import task.Deadline; // Import the Deadline class
import task.Event; // Import the Event class

public class Mel {

    public static final int MARK_WORD_LEN = 4;
    public static final int UNMARK_WORD_LEN = 6;
    public static final int INPUT_SPACE_BUFFER = 2;
    private static final int DELETE_WORD_LEN = 6;
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

        getUserInput(in, userList);
    }

    private static void getUserInput(Scanner in, List userList) {
        String line;
        while (true) {
            line = getLine(in);

            if (isBye(line)) {
                Ui.printByeMessage();
                Ui.printHorizontalLine();
                break;

            } else if (isList(line)) {
                userList.printList();
                Ui.printHorizontalLine();

            } else if (isMark(line)) {
                userList.markItem(line);
                Ui.printHorizontalLine();
                Storage.saveListToFile(LIST_FILE_PATH, userList);

            } else if (isUnmark(line)) {
                userList.unmarkItem(line);
                Ui.printHorizontalLine();
                Storage.saveListToFile(LIST_FILE_PATH, userList);

            } else if (isDelete(line)) {
                userList.deleteItem(line);
                Ui.printHorizontalLine();
                Storage.saveListToFile(LIST_FILE_PATH, userList);

            } else {
                userList.addItem(line);
                Ui.printHorizontalLine();
                Storage.saveListToFile(LIST_FILE_PATH, userList);

            }
        }
    }

    private static boolean isList(String line) {
        return line.equals("list");
    }

    private static boolean isBye(String line) {
        return line.equals("bye");
    }

    private static boolean isMark(String line) {
        return line.length() >= (MARK_WORD_LEN + INPUT_SPACE_BUFFER) && line.substring(0, 4).equals("mark");
    }

    private static boolean isUnmark(String line) {
        return line.length() >= (UNMARK_WORD_LEN + INPUT_SPACE_BUFFER) && line.substring(0, 6).equals("unmark");
    }

    private static boolean isDelete(String line) {
        return line.length() >= (DELETE_WORD_LEN + INPUT_SPACE_BUFFER) && line.substring(0, 6).equals("delete");
    }

    private static String getLine(Scanner in) {
        String line;
        System.out.print(System.lineSeparator());
        line = in.nextLine();
        Ui.printHorizontalLine();
        return line;
    }

}
