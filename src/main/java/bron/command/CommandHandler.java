package bron.command;

import bron.exception.*;
import bron.task.*;
import bron.storage.FileStorage;
import bron.ui.TextUI;
import bron.parser.Parser;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Handles the execution of commands and interacts with the TaskList and FileStorage.
 * It provides methods for various commands such as adding tasks, marking tasks, and finding tasks.
 */
public class CommandHandler {
    private TaskList taskList;
    private FileStorage storage;

    /**
     * Constructs a CommandHandler with the given TaskList and FileStorage.
     *
     * @param taskList The TaskList containing tasks.
     * @param storage The FileStorage for saving and loading tasks.
     */
    public CommandHandler(TaskList taskList, FileStorage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Processes the given command and line. Delegates to the appropriate handler method based on the command.
     *
     * @param command The command to execute.
     * @param line The full user input line.
     */
    public void handleCommand(Command command, String line) {
        try {
            switch (command) {
            case BYE:
                printByeMessage();
                break;
            case LIST:
                handleList();
                break;
            case MARK:
                handleMark(parseIndex(line));
                storage.save(taskList);
                break;
            case DELETE:
                deleteTask(parseIndex(line));
                storage.save(taskList);
                break;
            case UNMARK:
                handleUnmark(parseIndex(line));
                storage.save(taskList);
                break;
            case TODO:
                handleTodo(line);
                storage.save(taskList);
                break;
            case DEADLINE:
                handleDeadline(line);
                storage.save(taskList);
                break;
            case EVENT:
                handleEvent(line);
                storage.save(taskList);
                break;
            case FIND:
                handleFind(line);
                break;
            case HELP:
                handleHelp();
                break;
            default:
                throw new InvalidCommandException();
            }
        } catch (BronException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles the help command by calling the TextUI to display help.
     */
    private void handleHelp() {
        TextUI.showHelp();
    }

    /**
     * Handles the "find" command, searching for tasks that match the given keyword.
     *
     * @param line The full user input line containing the keyword.
     * @throws EmptyKeywordException If no keyword is provided.
     */
    private void handleFind(String line) throws EmptyKeywordException {
        String[] parts = line.split(" ");
        if (parts.length < 2) {
            throw new EmptyKeywordException();
        }

        String keyword = parts[1];
        TaskList results = taskList.findTask(keyword);
        if (results.size() == 0) {
            System.out.println("No tasks found with the keyword: " + keyword);
        } else {
            System.out.println("Here are the tasks that have the keyword: " + keyword);
            results.printTasks();
        }
    }

    /**
     * Displays the goodbye message to the user.
     */
    private void printByeMessage() {
        TextUI.showByeMessage();
    }

    /**
     * Parses the index from the user input line.
     *
     * @param line The full user input line.
     * @return The parsed index (zero-based).
     */
    private int parseIndex(String line) {
        String[] parts = line.split(" ");
        return Integer.parseInt(parts[1]) - 1;
    }

    /**
     * Handles the "list" command, displaying the current tasks.
     */
    private void handleList() {
        System.out.printf("You have %d tasks in your list.%n", taskList.size());
        taskList.printTasks();
    }

    /**
     * Handles the "delete" command, deleting a task at the given index.
     *
     * @param index The index of the task to delete.
     * @throws TaskIndexOutOfBoundsException If the index is out of bounds.
     */
    private void deleteTask(int index) throws TaskIndexOutOfBoundsException {
        if (index < 0 || index >= taskList.size()) {
            throw new TaskIndexOutOfBoundsException();
        }

        taskList.deleteTask(index);
        System.out.println("You have " + taskList.size() + " task(s)");
    }

    /**
     * Handles the "mark" command, marking a task as done.
     *
     * @param index The index of the task to mark as done.
     * @throws TaskIndexOutOfBoundsException If the index is out of bounds.
     */
    private void handleMark(int index) throws TaskIndexOutOfBoundsException {
        if (index < 0 || index >= taskList.size()) {
            throw new TaskIndexOutOfBoundsException();
        }

        taskList.getTask(index).markAsDone();
        System.out.println("Good shit kid! I've marked this task as done:");
        System.out.println("  " + taskList.getTask(index));
    }

    /**
     * Handles the "unmark" command, marking a task as not done.
     *
     * @param index The index of the task to mark as not done.
     * @throws TaskIndexOutOfBoundsException If the index is out of bounds.
     */
    private void handleUnmark(int index) throws TaskIndexOutOfBoundsException {
        if (index < 0 || index >= taskList.size()) {
            throw new TaskIndexOutOfBoundsException();
        }

        taskList.getTask(index).markAsNotDone();
        System.out.println("Get yo shit together son, this task aint done yet:");
        System.out.println("  " + taskList.getTask(index));
    }

    /**
     * Handles the "todo" command, adding a ToDo task.
     *
     * @param line The full user input line containing the task description.
     * @throws EmptyTodoDescriptionException If the description is missing.
     */
    private void handleTodo(String line) throws EmptyTodoDescriptionException {
            if (!line.contains(" ") || line.substring(line.indexOf(" ") + 1).trim().isEmpty()) {
                throw new EmptyTodoDescriptionException();
            }

            String taskDescription = line.substring(line.indexOf(" ") + 1).trim();
            ToDo todo = new ToDo(taskDescription);
            taskList.addTask(todo);

            System.out.println("Got it. I've added this task:");
            System.out.println(todo);
            System.out.println("You got " + taskList.size() + " task(s)");
    }

    /**
     * Handles the "deadline" command, adding a Deadline task with a date.
     *
     * @param line The full user input line containing the task description and deadline.
     * @throws InvalidDeadlineFormatException If the format is invalid.
     */
    private void handleDeadline(String line) throws InvalidDeadlineFormatException {
            if (!line.contains("/by")) {
                throw new InvalidDeadlineFormatException("The deadline must include '/by' followed by the date.");
            }

            String[] deadlineParts = line.split("/by", 2);
            if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty()) {
                throw new InvalidDeadlineFormatException("Please provide a valid deadline after '/by'.");
            }

            String taskDescription = deadlineParts[0].trim();
            if (taskDescription.length() < 9) {
                throw new InvalidDeadlineFormatException("The description of a deadline cannot be empty.");
            }
            taskDescription = taskDescription.substring(9).trim();
            String byWhen = deadlineParts[1].trim();
            LocalDateTime byDateTime;
            try {
                byDateTime = Parser.parseDeadline(byWhen);
            } catch (DateTimeParseException e) {
                throw new InvalidDeadlineFormatException("Invalid date format! Please use yyyy-MM-dd HHmm.");
            }

            Deadline deadline = new Deadline(taskDescription, byDateTime);
            taskList.addTask(deadline);

            System.out.println("Got it. I've added this task:");
            System.out.println("  " + deadline);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Handles the "event" command, adding an Event task with a start and end time.
     *
     * @param line The full user input line containing the task description and event timings.
     * @throws InvalidEventFormatException If the format is invalid.
     */
    private void handleEvent(String line) throws InvalidEventFormatException {
            if (!line.contains("/from") || !line.contains("/to")) {
                throw new InvalidEventFormatException("The event must include '/from' and '/to' to specify the time.");
            }

            String[] eventParts = line.split("/from", 2);
            if (eventParts.length < 2 || eventParts[1].trim().isEmpty()) {
                throw new InvalidEventFormatException("Please provide valid event timings after '/from' and '/to'.");
            }

            String taskDescription = eventParts[0].trim();
            if (taskDescription.length() < 6) {
                throw new InvalidEventFormatException("The description of an event cannot be empty.");
            }

            taskDescription = taskDescription.substring(6).trim();
            String[] timeParts = eventParts[1].split("/to", 2);
            if (timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
                throw new InvalidEventFormatException("Please provide valid start and end times.");
            }

            String fromTime = timeParts[0].trim();
            String toTime = timeParts[1].trim();

            Event event = new Event(taskDescription, fromTime, toTime);
            taskList.addTask(event);

            System.out.println("Got it. I've added this task:");
            System.out.println("  " + event);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}
