import task.Task;
import static method.Command.*;

import java.util.Scanner;
import java.util.ArrayList;

public class Iris {
    static ArrayList<Task> tasks;

    private final static String DIVIDER = "---------------------------------------------";
    private final static String EMPTY_COMMAND_MESSAGE = "Tell me your needs! I'm here to help!";
    private final static String INVALID_COMMAND_MESSAGE = "HUH?!? I don't recognize this command :(";


    public static void printDivider() {
        System.out.println(DIVIDER);
    }

    public static boolean chat(String text) {
        String command = text.split(" ")[0].toLowerCase();
        printDivider();
        switch (command) {
        case "":
            System.out.println(EMPTY_COMMAND_MESSAGE);
            break;
        case "bye":
            saveAndLeave(tasks);
            return true;
        case "list":
            listTasks(tasks);
            break;
        case "delete":
            deleteTask(tasks, text);
            break;
        case "mark":
            changeTaskStatus(tasks, text, true);
            break;
        case "unmark":
            changeTaskStatus(tasks, text, false);
            break;
        case "deadline", "todo", "event":
            addTask(tasks, text);
            break;
        default:
            System.out.println(INVALID_COMMAND_MESSAGE);
        }
        printDivider();
        return false;
    }

    public static void main(String[] args) {
        tasks = loadAndStart();
        printDivider();
        boolean isEnded = false;
        Scanner in = new Scanner(System.in);
        while (!isEnded) {
            String line = in.nextLine();
            isEnded = chat(line);
        }
    }
}
