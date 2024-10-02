package wildpeace.task;

import wildpeace.exceptions.EmptyCommandException;
import wildpeace.exceptions.InvalidInputException;
import wildpeace.task.Parser;
import wildpeace.Storage.Storage;
import wildpeace.task.Ui;

/**
 * Manages the high-level routine of Ui, Storage, TaskList, and Parser classes.
 * The routine involves showing the user guide, parsing user commands and arguments,
 * handling task operations (such as adding, marking, deleting tasks), and saving tasks.
 */
public class TaskManager {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    /**
     * Constructor for TaskManager class.
     * Initializes the user interface (Ui), task storage, task list, and command parser.
     */
    public TaskManager() {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList(storage.load());
        parser = new Parser();
    }

    /**
     * Starts the main loop for managing tasks.
     * The loop continually reads user commands, processes them, and updates the task list
     * until the user exits the program by typing "bye".
     */
    public void run() {
        ui.showGuide();
        boolean isExit = false;

        while (!isExit) {
            String fullCommand = ui.readCommand();
            try {
                String[] parsedInput = parser.parse(fullCommand);
                String command = parser.getCommand(parsedInput);
                String arguments = parser.getArguments(parsedInput);
                isExit = handleCommand(command, arguments);
            } catch (EmptyCommandException e) {
                ui.showError("The description cannot be empty: " + e.getMessage());
            }
        }

        storage.save(taskList.getTasks());
        ui.showLine("Goodbye!");
        ui.close();
    }

    /**
     * Processes user commands and performs corresponding task operations.
     * This includes listing tasks, adding todo/deadline/event tasks, marking/unmarking tasks,
     * and deleting tasks. The tasks are saved to the storage after each operation.
     *
     * @param command   The command parsed from user input.
     * @param arguments The arguments associated with the command.
     * @return true if the user chooses to exit the program, false otherwise.
     */
    private boolean handleCommand(String command, String arguments) {
        try {
            switch (command) {
            case "list":
                taskList.listTasks();
                break;
            case "todo":
                if (arguments.isEmpty()) {
                    throw new EmptyCommandException("The description of a todo cannot be empty.");
                }
                taskList.addTask(new Task(arguments, Task.TaskType.TODO));
                storage.save(taskList.getTasks());
                break;
            case "deadline":
                if (!arguments.contains("/by")) {
                    throw new InvalidInputException("Deadline tasks must include '/by <deadline>'.");
                }
                String[] deadlineParts = arguments.split("/by", 2);
                taskList.addTask(new Task(deadlineParts[0].trim(), deadlineParts[1].trim(), Task.TaskType.DEADLINE));
                storage.save(taskList.getTasks());
                break;
            case "event":
                if (!arguments.contains("/at")) {
                    throw new InvalidInputException("Event tasks must include '/at <event time>'.");
                }
                String[] eventParts = arguments.split("/at", 2);
                taskList.addTask(new Task(eventParts[0].trim(), eventParts[1].trim(), Task.TaskType.EVENT));
                storage.save(taskList.getTasks());
                break;
            case "mark":
                taskList.markTask(Integer.parseInt(arguments) - 1);
                storage.save(taskList.getTasks());
                break;
            case "unmark":
                taskList.unmarkTask(Integer.parseInt(arguments) - 1);
                storage.save(taskList.getTasks());
                break;
            case "delete":
                taskList.deleteTask(Integer.parseInt(arguments) - 1);
                storage.save(taskList.getTasks());
                break;
            case "bye":
                return true;
            case "q":
                ui.showGuide();
                break;
            default:
                ui.showError("Unknown command.");
            }
        } catch (InvalidInputException e) {
            ui.showError("Invalid input: " + e.getMessage());
        } catch (EmptyCommandException e) {
            ui.showError("Empty command: " + e.getMessage());
        } catch (NumberFormatException e) {
            ui.showError("Please enter a valid number for the task index.");
        }
        return false;
    }
}
