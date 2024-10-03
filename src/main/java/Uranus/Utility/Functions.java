package Uranus.Utility;

import java.util.Scanner;
import java.util.ArrayList;
import Uranus.Tasks.Task;

/**
 * Provides base functionality for managing tasks and handling user input.
 * This abstract class contains methods and fields that support task management,
 * such as handling input from the user and managing a list of tasks.
 */
public abstract class Functions {

    protected static final Scanner in = new Scanner(System.in);
    protected static final String LINE_SEPARATOR = "_________________________________________________________";
    protected static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Manages the tasks by continuously reading user input and processing commands.
     * This method loads tasks from the file upon start and saves tasks after every user command.
     * It runs in a loop, waiting for user input and processing it until the program is terminated.
     * Only upon the user's input "bye", will the app close itself.
     */
    public static void manageTasks() {
        FileManagement.load();
        while (true) {
            String input = in.nextLine();
            Parser.processCommand(input);
            FileManagement.saveFile();
        }
    }
}
