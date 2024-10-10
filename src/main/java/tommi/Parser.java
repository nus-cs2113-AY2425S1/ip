package tommi;

import tommi.Task.Deadline;
import tommi.Task.Event;
import tommi.Task.ToDo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents Parser of all chat input from the user
 */
public class Parser {

    public static final String BYE_STRING = "bye";
    public static final String LIST_STRING = "list";
    public static final String MARK_STRING = "mark";
    public static final String UNMARK_STRING = "unmark";
    public static final String SEARCH_STRING = "search";
    public static final String DELETE_STRING = "delete";
    public static final String TODO_STRING = "todo";
    public static final String DEADLINE_STRING = "deadline";
    public static final String EVENT_STRING = "event";

    /**
     * Process input and ask for next as long as input is not bye command
     *
     * @param scanner scanner used for chat
     */
    public static void readInputStrings(Scanner scanner) {
        String input = scanner.nextLine();
        while (!input.equals(BYE_STRING)) {
            processInputCases(input);
            input = scanner.nextLine();
        }
    }

    /**
     * Switch statements for all possibilities of input from the user
     *
     * @param input String input from user
     * @throws IllegalArgumentException If user does not use any of the commands
     * @throws RuntimeException If search fails to find save file
     */
    public static void processInputCases(String input) {
        try {
            if (input.equals(LIST_STRING)) {
                TaskList.listTasks();
                return;
            }

            String[] words = input.split(" ");
            checkCommandHasContent(words);

            switch (words[0]) {
            case MARK_STRING:
                tryMarkTask(words);
                trySaveTaskData();
                break;
            case UNMARK_STRING:
                tryUnmarkTask(words);
                trySaveTaskData();
                break;
            case SEARCH_STRING:
                ArrayList<String> foundResults = Storage.search(words[1]);
                Ui.printSearchResults(foundResults);
                break;
            case DELETE_STRING:
                tryDeleteTask(words);
                trySaveTaskData();
                break;
            case TODO_STRING:
                TaskList.addTask(new ToDo(false, input.substring(5)));
                trySaveTaskData();
                break;
            case DEADLINE_STRING:
                String deadlineContent = input.substring(9);
                checkIllegalDeadlineUsage(deadlineContent);
                String[] deadlineParts = deadlineContent.split(" /by ", 2);
                TaskList.addTask(new Deadline(false, deadlineParts[0], deadlineParts[1]));
                trySaveTaskData();
                break;
            case EVENT_STRING:
                String eventContent = input.substring(6);
                checkIllegalEventUsage(eventContent);
                String[] eventParts = input.split(" /from ", 2);
                String[] timeParts = eventParts[1].split(" /to ", 2);
                TaskList.addTask(new Event(false, eventParts[0].substring(6), timeParts[0], timeParts[1]));
                trySaveTaskData();
                break;
            default:
                throw new IllegalArgumentException(
                        """
                                ____________________________________________________________
                                ERROR: Please use a command as shown in the user guide.
                                ____________________________________________________________"""
                );
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Throw IllegalArgumentException if "event" command is used without /from or /to
     *
     * @param eventContent String of input after keyword "event"
     * @throws IllegalArgumentException If "event" command is used without /from or /to
     */
    private static void checkIllegalEventUsage(String eventContent) {
        if (!eventContent.contains(" /from ") || !eventContent.contains(" /to ")) {
            throw new IllegalArgumentException(
                    """
                            ____________________________________________________________
                            ERROR: Please use keyword "/from" and "/to" in the event.
                            ____________________________________________________________""");
        }
    }

    /**
     * Throw IllegalArgumentException if "deadline" command is used without /by
     *
     * @param deadlineContent String of input after keyword "deadline"
     * @throws IllegalArgumentException If "deadline" command is used without /by
     */
    private static void checkIllegalDeadlineUsage(String deadlineContent) {
        if (!deadlineContent.contains(" /by ")) {
            throw new IllegalArgumentException(
                    """
                            ____________________________________________________________
                            ERROR: Please use keyword "/by" in the deadline.
                            ____________________________________________________________""");
        }
    }

    /**
     * Try and catch for deleting task
     *
     * @param words User input split into strings separated by " "
     * @throws IllegalArgumentException If index is not in TaskList
     * @throws NumberFormatException If the input is not an integer
     */
    private static void tryDeleteTask(String[] words) {
        try {
            int taskIndex = Integer.parseInt(words[1]) - 1;
            TaskList.deleteTask(taskIndex);
        } catch (NumberFormatException e) {
            Ui.printLine();
            System.out.println("ERROR: Please use an integer index to delete");
            Ui.printLine();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Try and catch for unmarking task
     *
     * @param words User input split into strings separated by " "
     * @throws IllegalArgumentException If index is not in TaskList
     * @throws NumberFormatException If the input is not an integer
     */
    private static void tryUnmarkTask(String[] words) {
        try {
            int taskIndex = Integer.parseInt(words[1]) - 1;
            TaskList.unmarkTask(taskIndex);
        } catch (NumberFormatException e) {
            Ui.printLine();
            System.out.println("ERROR: Please use an integer index to unmark");
            Ui.printLine();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Try and catch for marking task
     *
     * @param words User input split into strings separated by " "
     * @throws IllegalArgumentException If index is not in TaskList
     * @throws NumberFormatException If the input is not an integer
     */
    private static void tryMarkTask(String[] words) {
        try {
            int taskIndex = Integer.parseInt(words[1]) - 1;
            TaskList.markTask(taskIndex);
        } catch (NumberFormatException e) {
            Ui.printLine();
            System.out.println("ERROR: Please use an integer index to mark");
            Ui.printLine();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Check if command is used with any content
     *
     * @param words User input split into strings separated by " "
     * @throws IllegalArgumentException If the command has no information
     */
    private static void checkCommandHasContent(String[] words) {
        if (words.length <= 1 || words[1].isEmpty()) {
            throw new IllegalArgumentException(
                    """
                            ____________________________________________________________
                            ERROR: Please provide necessary information for the command used.
                            ____________________________________________________________"""
            );
        }
    }

    // Try and save the updated TaskList
    private static void trySaveTaskData() {
        try {
            Storage.saveTaskData(TaskList.getTaskList());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
