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

public class tars {
    private static final String logo =
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

    public static void main(String[] args) {
        System.out.println(logo);
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

    // Process user input and return true if "bye" is entered to exit
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
                String taskDescription = input.substring(5).trim();
                if (taskDescription.isEmpty()) {
                    throw new TarsException("Oops! Your todo needs a description. Please try again.");
                }
                addTask(taskList, "todo", taskDescription, storage);
            } else if (input.startsWith("deadline")) {
                if (!input.contains("/by")) {
                    throw new TarsException("A deadline needs a due date. Format it like this: 'deadline <task> /by <due date>'.");
                }
                String deadlineDescription = input.substring(9).trim();
                addTask(taskList, "deadline", deadlineDescription, storage);
            } else if (input.startsWith("event")) {
                if (!input.contains("/from") || !input.contains("/to")) {
                    throw new TarsException("An event needs both start and end times. Format it like this: 'event <task> /from <start> /to <end>'.");
                }
                String eventDescription = input.substring(6).trim();
                addTask(taskList, "event", eventDescription, storage);
            } else if (input.startsWith("mark")) {
                markTask(input, taskList, ui, storage);
            } else if (input.startsWith("unmark")) {
                unmarkTask(input, taskList, ui, storage);
            } else if (input.startsWith("delete")) {
                deleteTask(input, taskList, ui, storage);
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

    // Add a task and save it
    private static void addTask(List<Task> tasks, String taskType, String input, Storage storage) {
        UserInterface ui = new UserInterface();

        try {
            switch (taskType) {
                case "todo":
                    tasks.add(new Todo(input));
                    break;
                case "deadline":
                    String[] deadlineParts = input.split("/by");
                    tasks.add(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
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

    // Mark a task as completed
    private static void markTask(String input, List<Task> taskList, UserInterface ui, Storage storage) throws TarsException {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
            if (taskNumber < 0 || taskNumber >= taskList.size()) {
                throw new TarsException("That task number is out of range.");
            }

            taskList.get(taskNumber).markAsDone();
            ui.printSeparator();
            System.out.println("    Great! Task marked as complete: ");
            System.out.println("    " + taskList.get(taskNumber));
            ui.printSeparator();

            storage.saveTasks(taskList);
        } catch (NumberFormatException | IOException e) {
            throw new TarsException("Error: " + e.getMessage());
        }
    }

    // Unmark a task as incomplete
    private static void unmarkTask(String input, List<Task> taskList, UserInterface ui, Storage storage) throws TarsException {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
            if (taskNumber < 0 || taskNumber >= taskList.size()) {
                throw new TarsException("That task number is out of range.");
            }

            taskList.get(taskNumber).markAsNotDone();
            ui.printSeparator();
            System.out.println("    Task has been unmarked: ");
            System.out.println("    " + taskList.get(taskNumber));
            ui.printSeparator();

            storage.saveTasks(taskList);
        } catch (NumberFormatException | IOException e) {
            throw new TarsException("Error: " + e.getMessage());
        }
    }

    // Delete a task
    private static void deleteTask(String input, List<Task> taskList, UserInterface ui, Storage storage) throws TarsException {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
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
    private static void printTaskCount(int taskCount) {
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
