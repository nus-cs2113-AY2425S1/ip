package tommi;

import tommi.Task.Deadline;
import tommi.Task.Event;
import tommi.Task.ToDo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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

    public static void readInputStrings(Scanner scanner) {
        String input = scanner.nextLine();
        while (!input.equals(BYE_STRING)) {
            processInputCases(input);
            input = scanner.nextLine();
        }
    }

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

    private static void checkIllegalEventUsage(String eventContent) {
        if (!eventContent.contains(" /from ") || !eventContent.contains(" /to ")) {
            throw new IllegalArgumentException(
                    """
                            ____________________________________________________________
                            ERROR: Please use keyword "/from" and "/to" in the event.
                            ____________________________________________________________""");
        }
    }

    private static void checkIllegalDeadlineUsage(String deadlineContent) {
        if (!deadlineContent.contains(" /by ")) {
            throw new IllegalArgumentException(
                    """
                            ____________________________________________________________
                            ERROR: Please use keyword "/by" in the deadline.
                            ____________________________________________________________""");
        }
    }

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

    private static void trySaveTaskData() {
        try {
            Storage.saveTaskData(TaskList.getTaskList());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
