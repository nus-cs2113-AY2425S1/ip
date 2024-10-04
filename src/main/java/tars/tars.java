package tars;

import tars.storage.Storage;
import tars.tarsexception.TarsException;
import tars.task.Deadline;
import tars.task.Event;
import tars.task.Task;
import tars.task.Todo;
import tars.userinterface.UserInterface;
import tars.command.FindCommand;
import tars.tasklist.TaskList;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * The main class of the TARS application.
 * TARS helps users manage their tasks, deadlines, and events.
 */
public class tars {

    private static final String LOGO =
            "       [][][][][][][][]       \n"
                    + "       []   |    |   []        \n"
                    + "       []   [    ]   []        \n"
                    + "       []   |____|   []        \n"
                    + "       [][][][][][][][]        \n"
                    + "       []   |    |   []        \n"
                    + "       []   |____|   []        \n"
                    + "       []   |TARS|   []        \n"
                    + "       []   |____|   []        \n"
                    + "       []__  ____  __[]        \n";

    /**
     * The main entry point for the TARS application.
     * Initializes the application, loads tasks, and processes user input.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        System.out.println(LOGO);
        UserInterface ui = new UserInterface();

        // Initialize Storage and load tasks from file
        Storage storage = new Storage("./data/tasks.txt");
        List<Task> taskList = loadTasks(storage);

        // Opening speech and prompt for help
        ui.printSeparator();
        System.out.println("    Hello! I'm tars.tars.");
        System.out.println("    Ready to conquer the world? Or maybe just help with something smaller? What can I do for you?");
        ui.printSeparator();

        // Provide help prompt
        System.out.println("    If you need to see the tutorial, type 'help' to view available commands.");
        ui.printSeparator();

        // Main loop to process user input
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            input = scanner.nextLine();
            if (processUserInput(input, taskList, ui, storage)) {
                break;  // Exit loop if "bye" is entered
            }
        }
    }

    // Load tasks from file and handle any errors
    private static List<Task> loadTasks(Storage storage) {
        List<Task> taskList = new ArrayList<>();
        try {
            taskList = storage.loadTasks();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return taskList;
    }

    /**
     * Processes the user's input and executes the appropriate command.
     *
     * @param input The user's input.
     * @param taskList The list of tasks.
     * @param ui The user interface for interacting with the user.
     * @param storage The storage to save and load tasks.
     * @return true if the input is "bye" and the program should exit; false otherwise.
     */
    public static boolean processUserInput(String input, List<Task> taskList, UserInterface ui, Storage storage) {
        try {
            if (input.equals("bye")) {
                ui.showGoodbyeMessage();
                return true;
            } else if (input.equals("list")) {
                ui.showTasks(taskList);
            } else if (input.equals("help")) {
                showHelp();
            } else if (input.startsWith("find")) {
                handleFindCommand(input, taskList, ui, storage);
            } else if (input.startsWith("todo")) {
                handleAddTask("todo", input, taskList, storage);
            } else if (input.startsWith("deadline")) {
                handleAddTask("deadline", input, taskList, storage);
            } else if (input.startsWith("event")) {
                handleAddTask("event", input, taskList, storage);
            } else if (input.startsWith("mark")) {
                handleMarkTask(input, taskList, ui, storage);
            } else if (input.startsWith("unmark")) {
                handleUnmarkTask(input, taskList, ui, storage);
            } else if (input.startsWith("delete")) {
                handleDeleteTask(input, taskList, ui, storage);
            } else {
                handleUnknownCommand();
            }
        } catch (TarsException e) {
            ui.printSeparator();
            System.out.println(e.getMessage());
            ui.printSeparator();
        }
        return false;
    }

    // Handle the "find" command
    private static void handleFindCommand(String input, List<Task> taskList, UserInterface ui, Storage storage) throws TarsException {
        if (input.length() <= 5 || !input.substring(4, 5).equals(" ")) {
            throw new TarsException("The correct format is: find <keyword>. Please make sure to include a space after 'find' followed by a keyword.");
        }

        String keyword = input.substring(5).trim();
        if (keyword.isEmpty()) {
            throw new TarsException("The find command requires a keyword.");
        }

        FindCommand findCommand = new FindCommand(keyword);
        findCommand.execute(new TaskList(taskList), ui, storage);
    }

    // Display available commands
    private static void showHelp() {
        System.out.println("Here are the available commands:");
        System.out.println("1. todo <description> - Adds a todo task.");
        System.out.println("2. deadline <description> /by <date> - Adds a deadline task.");
        System.out.println("3. event <description> /from <start> /to <end> - Adds an event task.");
        System.out.println("4. list - Lists all tasks.");
        System.out.println("5. mark <task number> - Marks a task as completed.");
        System.out.println("6. unmark <task number> - Marks a task as not completed.");
        System.out.println("7. delete <task number> - Deletes a task.");
        System.out.println("8. find <keyword> - Finds tasks containing the keyword.");
        System.out.println("9. bye - Exits the program.");
        System.out.println("10. help - Shows this help message.");
        System.out.println("------------------------------------------------------------");
    }

    // Handle adding a task
    private static void handleAddTask(String taskType, String input, List<Task> tasks, Storage storage) throws TarsException {
        String description;
        switch (taskType) {
            case "todo":
                description = input.substring(5).trim();
                if (description.isEmpty()) {
                    throw new TarsException("Oops! Your todo needs a description. Please try again.");
                }
                addTask(tasks, "todo", description, storage);
                break;
            case "deadline":
                if (!input.contains("/by")) {
                    throw new TarsException("A deadline needs a due date. Format it like this: 'deadline <task> /by <due date>'.");
                }
                description = input.substring(9).trim();
                addTask(tasks, "deadline", description, storage);
                break;
            case "event":
                if (!input.contains("/from") || !input.contains("/to")) {
                    throw new TarsException("An event needs both start and end times. Format it like this: 'event <task> /from <start> /to <end>'.");
                }
                description = input.substring(6).trim();
                addTask(tasks, "event", description, storage);
                break;
            default:
                throw new TarsException("Unknown task type: " + taskType);
        }
    }

    // Add a task and save it
    public static void addTask(List<Task> tasks, String taskType, String input, Storage storage) {
        UserInterface ui = new UserInterface();

        try {
            switch (taskType) {
                case "todo":
                    tasks.add(new Todo(input));
                    break;
                case "deadline":
                    String[] deadlineParts = input.split("/by");
                    tasks.add(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));  // Parse date
                    break;
                case "event":
                    String[] eventParts = input.split("/from|/to");
                    tasks.add(new Event(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim()));
                    break;
                default:
                    break;
            }

            ui.printSeparator();
            System.out.println("    Got it. I've added this task: ");
            System.out.println("    " + tasks.get(tasks.size() - 1));
            printTaskCount(tasks.size());
            ui.printSeparator();

            storage.saveTasks(tasks);
        } catch (DateTimeParseException e) {
            ui.printSeparator();
            System.out.println("Invalid date format. Please use yyyy-mm-dd.");
            ui.printSeparator();
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            ui.printSeparator();
            System.out.println("An error occurred while processing your task. Please try again.");
            ui.printSeparator();
        }
    }

    // Handle marking a task as completed
    private static void handleMarkTask(String input, List<Task> taskList, UserInterface ui, Storage storage) throws TarsException {
        String[] parts = input.split(" ");
        if (parts.length != 2) {
            throw new TarsException("The mark command requires a task number. Format it like this: 'mark <task number>'.");
        }
        try {
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            if (taskNumber < 0 || taskNumber >= taskList.size()) {
                throw new TarsException("That task number is out of range.");
            }

            Task task = taskList.get(taskNumber);

            if (task.isDone()) {
                ui.printSeparator();
                System.out.println("    It seems this task is already done, like checking the airlock twice. Mission accomplished!");
                ui.printSeparator();
            } else {
                task.markAsDone();
                ui.printSeparator();
                System.out.println("    Ding! Another task bites the dust. Mission accomplished: ");
                System.out.println("    " + task);
                System.out.println("    Keep this up, and you'll conquer the galaxy in no time.");
                ui.printSeparator();
                storage.saveTasks(taskList);
            }
        } catch (NumberFormatException | IOException e) {
            throw new TarsException("Error: " + e.getMessage());
        }
    }

    // Handle unmarking a task
    private static void handleUnmarkTask(String input, List<Task> taskList, UserInterface ui, Storage storage) throws TarsException {
        String[] parts = input.split(" ");
        if (parts.length != 2) {
            throw new TarsException("The unmark command requires a task number. Format it like this: 'unmark <task number>'.");
        }
        try {
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            if (taskNumber < 0 || taskNumber >= taskList.size()) {
                throw new TarsException("That task number is out of range.");
            }

            Task task = taskList.get(taskNumber);

            if (!task.isDone()) {
                ui.printSeparator();
                System.out.println("    Hmm, it looks like this task was never completed. Can't undo what's not been done.");
                ui.printSeparator();
            } else {
                task.markAsNotDone();
                ui.printSeparator();
                System.out.println("    Task unmarked. Looks like you changed your mind, no worries: ");
                System.out.println("    " + task);
                System.out.println("    I'll be here when you're ready to finish the job. Calculating probability of completion... low.");
                ui.printSeparator();
                storage.saveTasks(taskList);
            }
        } catch (NumberFormatException | IOException e) {
            throw new TarsException("Error: " + e.getMessage());
        }
    }


    // Handle deleting a task
    private static void handleDeleteTask(String input, List<Task> taskList, UserInterface ui, Storage storage) throws TarsException {
        String[] parts = input.split(" ");
        if (parts.length != 2) {
            throw new TarsException("The delete command requires a task number. Format it like this: 'delete <task number>'.");
        }
        try {
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            if (taskNumber < 0 || taskNumber >= taskList.size()) {
                throw new TarsException("That task number is out of range.");
            }

            Task removedTask = taskList.remove(taskNumber);
            ui.printSeparator();
            System.out.println("    Noted. I've successfully removed this task: ");
            System.out.println("    " + removedTask);
            System.out.println("    Now you have " + taskList.size() + " tasks left in your list.");
            ui.printSeparator();

            storage.saveTasks(taskList);
        } catch (NumberFormatException | IOException e) {
            throw new TarsException("Error: " + e.getMessage());
        }
    }

    // Print the total number of tasks
    public static void printTaskCount(int taskCount) {
        System.out.println("    Now you have " + taskCount + " tasks in the list.");
    }

    // Handle unknown commands
    private static void handleUnknownCommand() throws TarsException {
        String[] unknownCommandMessages = {
                "Hmm, that doesn't sound like a command I recognize. Maybe try again?",
                "I think you're speaking a language I don't understand. Try something else!",
                "Oops! That didn't quite compute. Please enter a valid command.",
                "Are you sure that's a command? Double-check and try again.",
                "This doesn't seem like a command I know. How about we try something else?"
        };

        Random rand = new Random();
        int randomIndex = rand.nextInt(unknownCommandMessages.length);
        throw new TarsException(unknownCommandMessages[randomIndex]);
    }
}
