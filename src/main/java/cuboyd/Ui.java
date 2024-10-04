package cuboyd;

import cuboyd.tasks.Task;
import cuboyd.tasks.TaskList;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Handles UI/ Input Output of the program.
 *  - Intro Text
 *  - Main Command Loop (using its parser
 *  - Display Methods for the various operations
 */
public class Ui {
    public Parser parser;
    public Ui() {
        parser = new Parser(); // Ui influences the parser
        // TaskList not an attribute: Wanted to decouple program state (TaskList, Storage) from Ui
    }

    /**
     * Displays an Introductory message to the user.
     */
    public void displayIntroMessage(){
        final String NAME = "Cuboyd";
        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");
    }

    /**
     * Displays List of all tasks.
     * @param taskList Task List
     */
    public void displayAllTasks(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        System.out.print(taskList.listAllTasks());
    }

    /**
     * Displays List of found tasks.
     * @param taskList Task List to find tasks from
     */
    public void displayFoundTasks(TaskList taskList, String keyword) throws CuboydException {
        System.out.println("Here are the matching tasks in your list:");
        System.out.print(taskList.listFoundTasks(keyword));
    }

    /**
     * Displays task that was added and new task list size.
     * @param task Added Task
     */
    public void displayAddedTask(TaskList taskList, Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
    }

    /**
     * Displays task that was removed and new task list size.
     * @param task Removed Task
     */
    public void displayRemovedTask(TaskList taskList, Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.toString());
        System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
    }

    /**
     * Displays task that was modified.
     * @param task Modified Task
     */
    public void displayModifiedTask(Task task, String action) {
        System.out.println("Nice! I've " + action + " this task:");
        System.out.println("  " + task.toString());
    }

    /**
     * Displays a Goodbye message to the user.
     */
    public void displayByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays an Invalid Command message to the user, along with a list of valid commands.
     */
    public void displayInvalidCommandMessage() {
        System.out.println("No valid command given! Valid Commands are: \n" +
                "  - list\n" +
                "  - todo\n" +
                "  - deadline\n" +
                "  - event\n" +
                "  - mark\n" +
                "  - unmark\n" +
                "  - delete\n" +
                "  - bye"
        );
    }

    /**
     * Displays Cuboyd Exception.
     * @param exception CuboydException to print
     */
    public void displayCuboydException(CuboydException exception) {
        System.out.println(exception.getMessage());
    }

    /**
     * Runs a loop where the user can enter commands, until they exit.
     */
    public void commandEntryLoop(TaskList taskList, Storage storage) {
        // Command Entry
        String line;
        HashMap<String, String> argumentsMap;
        Scanner scanner = new Scanner(System.in);
        boolean isAskingInput = true;
        while (isAskingInput){
            System.out.print("> ");
            line = scanner.nextLine();
            argumentsMap = parser.parseCommandToArgumentsMap(line);
            try {
                isAskingInput = parser.commandMatching(this, taskList, storage, argumentsMap);
            } catch (CuboydException e){
                displayCuboydException(e);
            }
        }
    }

    /**
     * Runs the entire program.
     */
    public void run(TaskList taskList, Storage storage){
        displayIntroMessage();
        commandEntryLoop(taskList, storage);
    }
}