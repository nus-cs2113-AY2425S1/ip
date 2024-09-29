package tyrone.command;

import tyrone.ui.Ui;
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

    private TaskList taskList;
    private Storage storage;

    // Constants for handleInput function
    public static final int START_INDEX_OFFSET_DESCRIPTION = 1;
    public static final int START_INDEX_OFFSET_KEYWORD = 1;
    public static final int START_INDEX_OFFSET_START = 6;
    public static final int START_INDEX_OFFSET_END = 4;
    public static final int START_INDEX_OFFSET_DEADLINE = 4;
    public static final String TWO_SPACE_INDENT = "  ";

    /**
     * Constructor for Parser class.
     *
     * @param taskList Class containing list of tasks.
     * @param storage Class to handle writing data to save file.
     */
    public Parser(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Returns true if user inputs exit command, false otherwise.
     *
     * @param input User input to chatbot.
     * @return True if user inputs exit command, false otherwise.
     */
    public boolean isExitCommand (String input) {
        return input.equals("bye");
    }

    /**
     * Handles user input according to input type.
     *
     * @param input User input to chatbot.
     */
    public void handleInput(String input) {
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
        case "find":
            handleFind(input);
            break;
        default:
            handleUnknown();
            break;
        }

        storage.updateSaveFile(taskList);
    }

    /**
     * Handles "list" command when input by user.
     *
     * @param dissectedInput User input split by ' ' character.
     */
    private void handleList(String[] dissectedInput) {
        if (dissectedInput.length > 1) {
            Ui.println("Unrecognized command.");
        } else {
            Ui.println("Here are the tasks in your list:");
            Ui.println(taskList.getAllTaskDetails());
        }
    }

    /**
     * Handles user inputs which do not fall under the
     * chatbot's recognized types.
     */
    private void handleUnknown() {
        Ui.println("Unrecognized command.");
    }

    /**
     * Handles "event" command when input by user
     *
     * @param input User input to chatbot.
     */
    private void handleEvent(String input) {
        try {
            String description = input.substring(input.indexOf(" ") + START_INDEX_OFFSET_DESCRIPTION,
                    input.indexOf(" /from"));
            String start = input.substring(input.indexOf("/from") + START_INDEX_OFFSET_START,
                    input.indexOf(" /to"));
            String end = input.substring(input.indexOf("/to") + START_INDEX_OFFSET_END);
            if (description.isBlank() || start.isBlank() || end.isBlank()) {
                throw new EmptyFieldException();
            }
            Event newEvent = new Event(description, start, end);
            taskList.addTask(newEvent);
            Ui.println("added: " + newEvent.getNameWithStatus());
            Ui.println("Now you have " + taskList.getNumOfTasks() + " task(s) in the list.");
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
     * @param input User input to chatbot.
     */
    private void handleDeadline(String input) {
        try {
            String description = input.substring(input.indexOf(" ") + START_INDEX_OFFSET_DESCRIPTION,
                    input.indexOf(" /by"));
            String deadline = input.substring(input.indexOf("/by") + START_INDEX_OFFSET_DEADLINE);
            if (description.isBlank() || deadline.isBlank()) {
                throw new EmptyFieldException();
            }
            Deadline newDeadline = new Deadline(description, deadline);
            taskList.addTask(newDeadline);
            Ui.println("added: " + newDeadline.getNameWithStatus());
            Ui.println("Now you have " + taskList.getNumOfTasks() + " task(s) in the list.");
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
     * @param input User input to chatbot.
     */
    private void handleTodo(String input) {
        try {
            String description = input.substring(input.indexOf(" ") + START_INDEX_OFFSET_DESCRIPTION);
            if (!input.contains(" ") || description.isBlank()) {
                throw new EmptyFieldException();
            }
            Todo newTodo = new Todo(description);
            taskList.addTask(newTodo);
            Ui.println("added: " + newTodo.getNameWithStatus());
            Ui.println("Now you have " + taskList.getNumOfTasks() + " task(s) in the list.");
        } catch (EmptyFieldException e) {
            Ui.println("Description cannot be empty.");
        }
    }

    /**
     * Handles "unmark" command when input by user.
     *
     * @param dissectedInput User input split by ' ' character.
     */
    private void handleUnmark(String[] dissectedInput) {
        try {
            int taskId = Integer.parseInt(dissectedInput[1]) - 1;
            if (taskList.isValidTaskId(taskId)) {
                taskList.markTaskAsUndone(taskId);
                Ui.println("Ok, I've marked this task as not done yet:");
                Ui.println(TWO_SPACE_INDENT + taskList.getSingleTaskDetails(taskId));
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
    private void handleMark(String[] dissectedInput) {
        try {
            int taskId = Integer.parseInt(dissectedInput[1]) - 1;
            if (taskList.isValidTaskId(taskId)) {
                taskList.markTaskAsDone(taskId);
                Ui.println("Nice! I've marked this task as done:");
                Ui.println(TWO_SPACE_INDENT + taskList.getSingleTaskDetails(taskId));
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
    private void handleDelete (String[] dissectedInput) {
        try {
            int taskId = Integer.parseInt(dissectedInput[1]) - 1;
            if (taskList.isValidTaskId(taskId)) {
                Ui.println("Ok, I've removed this task:");
                Ui.println(TWO_SPACE_INDENT + taskList.getSingleTaskDetails(taskId));
                taskList.deleteTask(taskId);
                Ui.println("Now you have " + taskList.getNumOfTasks() + " task(s) in the list.");
            } else {
                Ui.println("Invalid task ID.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.println("Please input the index of the task you wish to delete.");
        } catch (NumberFormatException e) {
            Ui.println("Index to unmark should be a number.");
        }
    }

    /**
     * Handles "find" command when input by user.
     *
     * @param input User input to chatbot.
     */
    private void handleFind(String input){
        try {
            String keyword = input.substring(input.indexOf(" ") + START_INDEX_OFFSET_KEYWORD);
            if (!input.contains(" ") || keyword.isBlank()) {
                throw new EmptyFieldException();
            }
            Ui.println("Here are the matching tasks in your list:");
            Ui.println(taskList.listTasksWithKeyword(keyword));
        } catch (EmptyFieldException e) {
            Ui.println("Please enter a keyword to search for.");
        }
    }
}
