import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import task.Todo; // Import the Todo class
import task.Deadline; // Import the Deadline class
import task.Event; // Import the Event class

public class Mel {

    public static final String DRAW_HORIZONTAL_LINE = "\t________________________________________";
    public static final int MARK_WORD_LEN = 4;
    public static final int UNMARK_WORD_LEN = 6;
    public static final int INPUT_SPACE_BUFFER = 2;
    private static final int DELETE_WORD_LEN = 6;
    private static final String LIST_FILE_PATH = "C:\\Users\\YK Personal\\Desktop\\Y2S1\\CS2113\\indiv_proj\\ip\\data\\Mel.txt";

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void main(String[] args) throws IOException {
        printIntroMessage();
        
        // Set up scanner for user input
        Scanner in = new Scanner(System.in);
        List userList = new List();

        try {
            writerSetUp(LIST_FILE_PATH);
            loadDataFromFile(LIST_FILE_PATH, userList); // Load saved tasks
        } catch (IOException e) {
            System.out.println("An error occurred when setting up writer.");
        }

        getUserInput(in, userList);
    }

    private static void writerSetUp(String filePath) throws IOException {
        File listFile = new File(filePath);
        if (!listFile.exists()) {
            File directory = listFile.getParentFile();
            if (!directory.exists()) {
                directory.mkdirs(); // Create directory if it doesn't exist
            }
            listFile.createNewFile(); // Create file if it doesn't exist
        }
    }

    public static void loadDataFromFile(String filePath, List userList) {
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                parseAndAddItem(line, userList);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        }
    }

    private static void parseAndAddItem(String line, List userList) {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0]; // T, D, E
        boolean isDone = parts[1].equals("X");
        String taskDescription = parts[2];


        switch (taskType) {
        case "T":
            Todo todoTask = new Todo(taskDescription);
            if (isDone) {
                todoTask.markAsDone();
            }
            userList.itemArrayList.add(todoTask);
            break;

        case "D":
            String deadlineDate = parts[3]; // Ignore remaining parts
            Deadline deadlineTask = new Deadline(taskDescription, deadlineDate);
            if (isDone) {
                deadlineTask.markAsDone();
            }
            userList.itemArrayList.add(deadlineTask);
            break;

        case "E":
            String eventStart = parts[3]; // Start date/time
            String eventEnd = parts[4];   // End date/time
            Event eventTask = new Event(taskDescription, eventStart, eventEnd);
            if (isDone) {
                eventTask.markAsDone();
            }
            userList.itemArrayList.add(eventTask);
            break;

        default:
            System.out.println("Invalid task type in file: " + taskType);
            break;
        }
    }


    private static void saveListToFile(List userList) {
        try {
            writeToFile(LIST_FILE_PATH, userList.getFormattedTasks()); // getFormattedTasks returns a formatted String
        } catch (IOException e) {
            System.out.println("An error occurred while saving the list.");
        }
    }

    private static void getUserInput(Scanner in, List userList) {
        String line;
        while (true) {
            line = getLine(in);

            if (isBye(line)) {
                printByeMessage();
                printHorizontalLine();
                break;

            } else if (isList(line)) {
                userList.printList();
                printHorizontalLine();

            } else if (isMark(line)) {
                userList.markItem(line);
                printHorizontalLine();
                saveListToFile(userList);

            } else if (isUnmark(line)) {
                userList.unmarkItem(line);
                printHorizontalLine();
                saveListToFile(userList);

            } else if (isDelete(line)) {
                userList.deleteItem(line);
                printHorizontalLine();
                saveListToFile(userList);

            } else {
                userList.addItem(line);
                printHorizontalLine();
                saveListToFile(userList);

            }
        }
    }

    private static void printByeMessage() {
        System.out.println("\tBye. Hope to see you again soon!");
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

    private static void printHorizontalLine() {
        System.out.println(DRAW_HORIZONTAL_LINE);
    }

    private static String getLine(Scanner in) {
        String line;
        System.out.print(System.lineSeparator());
        line = in.nextLine();
        printHorizontalLine();
        return line;
    }

    private static void printIntroMessage() {
        printHorizontalLine();
        System.out.println("\tHello! I'm");
        System.out.println("\t.___  ___.  _______  __      \n" +
                "\t|   \\/   | |   ____||  |     \n" +
                "\t|  \\  /  | |  |__   |  |     \n" +
                "\t|  |\\/|  | |   __|  |  |     \n" +
                "\t|  |  |  | |  |____ |  `----.\n" +
                "\t|__|  |__| |_______||_______|\n" +
                "\t                             ");
        System.out.println("\tWhat can I do for you?");
        printHorizontalLine();
    }
}
