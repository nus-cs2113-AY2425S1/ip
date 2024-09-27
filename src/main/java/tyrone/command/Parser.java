package tyrone.command;

import tyrone.Ui;
import tyrone.command.exceptions.EmptyFieldException;
import tyrone.storage.Storage;
import tyrone.task.Deadline;
import tyrone.task.Event;
import tyrone.task.TaskList;
import tyrone.task.Todo;

/**
 * Class to parse user inputs to chatbot through Ui
 */
public class Parser {

    // Constants for handleInput function
    public static final int START_INDEX_OFFSET_DESC = 1;
    public static final int START_INDEX_OFFSET_START = 6;
    public static final int START_INDEX_OFFSET_END = 4;
    public static final int START_INDEX_OFFSET_DEADLINE = 4;

    /**
     * Returns true if user inputs exit command, false otherwise.
     *
     * @param input User input to chatbot.
     * @return True if user inputs exit command, false otherwise.
     */
    public static boolean isExitCommand (String input) {
        return input.equals("bye");
    }

    /**
     * Handles user input according to input type.
     *
     * @param input User input to chatbot.
     */
    public static void handleInput(String input) {
        String[] dissectedInput = input.split(" ");
        String command = dissectedInput[0];

        switch (command) {
        case "list":
            handleList(dissectedInput);
            break;
        case "mark":
            handleMark(dissectedInput);
            break;
        case "unmark":
            handleUnmark(dissectedInput);
            break;
        case "todo":
            handleTodo(input);
            break;
        case "deadline":
            handleDeadline(input);
            break;
        case "event":
            handleEvent(input);
            break;
        case "delete":
            handleDelete(dissectedInput);
            break;
        default:
            handleUnknown();
            break;
        }

        Storage.updateSaveFile();
    }

    /**
     * Handles "list" command when input by user.
     *
     * @param dissectedInput User input split by ' ' character.
     */
    private static void handleList(String[] dissectedInput) {
        if (dissectedInput.length > 1) {
            Ui.println("Unrecognized command.");
        } else {
            Ui.println("Here are the tasks in your list:");
            Ui.println(TaskList.getAllTaskDetails());
        }
    }

    /**
     * Handles user inputs which do not fall under the
     * chatbot's recognized types.
     */
    private static void handleUnknown() {
        Ui.println("Unrecognized command.");
    }

    /**
     * Handles "event" command when input by user
     *
     * @param command User input to chatbot.
     */
    private static void handleEvent(String command) {
        try {
            String description = command.substring(command.indexOf(" ") + START_INDEX_OFFSET_DESC, 
                    command.indexOf(" /from"));
            String start = command.substring(command.indexOf("/from") + START_INDEX_OFFSET_START,
                    command.indexOf(" /to"));
            String end = command.substring(command.indexOf("/to") + START_INDEX_OFFSET_END);
            if (description.isBlank() || start.isBlank() || end.isBlank()) {
                throw new EmptyFieldException();
            }
            Event newEvent = new Event(description, start, end);
            TaskList.addTask(newEvent);
            Ui.println("added: " + newEvent.getNameWithStatus());
        } catch (EmptyFieldException e) {
            Ui.println("Description/Start time/End time cannot be empty.");
        } catch (StringIndexOutOfBoundsException e) {
            Ui.println("Please input Event tasks using the following format:\n" +
                    "event <description> /from <start> /to <end>");
        }
    }

    /**
     * Handles "deadline" command when input by user
     *
     * @param command User input to chatbot.
     */
    private static void handleDeadline(String command) {
        try {
            String description = command.substring(command.indexOf(" ") + START_INDEX_OFFSET_DESC,
                    command.indexOf(" /by"));
            String deadline = command.substring(command.indexOf("/by") + START_INDEX_OFFSET_DEADLINE);
            if (description.isBlank() || deadline.isBlank()) {
                throw new EmptyFieldException();
            }
            Deadline newDeadline = new Deadline(description, deadline);
            TaskList.addTask(newDeadline);
            Ui.println("added: " + newDeadline.getNameWithStatus());
        } catch (EmptyFieldException e) {
            Ui.println("Description/Deadline cannot be empty.");
        } catch (StringIndexOutOfBoundsException e) {
            Ui.println("Please input Deadline tasks using the following format:\n" +
                    "deadline <description> /by <deadline>");
        }
    }

    /**
     * Handles "todo" command when input by user
     *
     * @param command User input to chatbot.
     */
    private static void handleTodo(String command) {
        try {
            String description = command.substring(command.indexOf(" ") + START_INDEX_OFFSET_DESC);
            if (!command.contains(" ") || description.isBlank()) {
                throw new EmptyFieldException();
            }
            Todo newTodo = new Todo(description);
            TaskList.addTask(newTodo);
            Ui.println("added: " + newTodo.getNameWithStatus());
        } catch (EmptyFieldException e) {
            Ui.println("Description cannot be empty.");
        }
    }

    /**
     * Handles "unmark" command when input by user.
     *
     * @param dissectedInput User input split by ' ' character.
     */
    private static void handleUnmark(String[] dissectedInput) {
        try {
            int taskId = Integer.parseInt(dissectedInput[1]) - 1;
            if (TaskList.isValidTaskId(taskId)) {
                TaskList.markTaskAsUndone(taskId);
                Ui.println("Ok, I've marked this task as not done yet:");
                Ui.println("  " + TaskList.getSingleTaskDetails(taskId));
            } else {
                Ui.println("Invalid task ID.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.println("Please input the index of the task you wish to unmark.");
        } catch (NumberFormatException e) {
            Ui.println("Index to unmark should be a number.");
        }
    }

    /**
     * Handles "mark" command when input by user.
     *
     * @param dissectedInput User input split by ' ' character.
     */
    private static void handleMark(String[] dissectedInput) {
        try {
            int taskId = Integer.parseInt(dissectedInput[1]) - 1;
            if (TaskList.isValidTaskId(taskId)) {
                TaskList.markTaskAsDone(taskId);
                Ui.println("Nice! I've marked this task as done:");
                Ui.println("  " + TaskList.getSingleTaskDetails(taskId));
            } else {
                Ui.println("Invalid task ID.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.println("Please input the index of the task you wish to mark.");
        } catch (NumberFormatException e) {
            Ui.println("Index to mark should be a number.");
        }
    }

    /**
     * Handles "delete" command when input by user.
     *
     * @param dissectedInput User input split by ' ' character.
     */
    private static void handleDelete (String[] dissectedInput) {
        try {
            int taskId = Integer.parseInt(dissectedInput[1]) - 1;
            if (TaskList.isValidTaskId(taskId)) {
                Ui.println("Ok, I've removed this task:");
                Ui.println("  " + TaskList.getSingleTaskDetails(taskId));
                TaskList.deleteTask(taskId);
            } else {
                Ui.println("Invalid task ID.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.println("Please input the index of the task you wish to delete.");
        } catch (NumberFormatException e) {
            Ui.println("Index to unmark should be a number.");
        }
    }
}
