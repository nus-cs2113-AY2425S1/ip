package tars;

import tars.storage.Storage;
import tars.tarsexception.tarsException;
import tars.task.Deadline;
import tars.task.Event;
import tars.task.Task;
import tars.task.Todo;
import tars.userinterface.UserInterface;

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
        List<Task> taskList = null;
        try {
            taskList = storage.loadTasks();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
            taskList = new ArrayList<>();  // Initialize an empty task list
        }

        // Opening speech
        ui.printSeparator();
        System.out.println("    Hello! I'm tars.tars.");
        System.out.println("    Ready to conquer the world? Or maybe just help with something smaller? What can I do for you?");
        ui.printSeparator();

        // Waiting for user input
        Scanner scanner = new Scanner(System.in);
        String input;

        // Main loop to process user input
        while (true) {
            input = scanner.nextLine();
            if (processUserInput(input, taskList, ui, storage)) {
                break;  // Exit loop if "bye" is entered
            }
        }
    }

    // Process user input and return true if "bye" is entered to exit
    public static boolean processUserInput(String input, List<Task> taskList, UserInterface ui, Storage storage) {
        try {
            // Handle "bye" command
            if (input.equals("bye")) {
                ui.showGoodbyeMessage();
                return true;  // Exit loop
            }
            // Handle "list" command
            else if (input.equals("list")) {
                ui.showTasks(taskList);
            }
            // Handle "todo" command
            else if (input.startsWith("todo")) {
                String taskDescription = input.substring(5).trim();
                if (taskDescription.isEmpty()) {
                    throw new tarsException("Oops! Your todo needs a description. Please try again.");
                }
                addTask(taskList, "todo", taskDescription, storage);
            }
            // Handle "deadline" command
            else if (input.startsWith("deadline")) {
                if (!input.contains("/by")) {
                    throw new tarsException("A deadline needs a due date. Format it like this: 'deadline <task> /by <due date>'.");
                }
                String deadlineDescription = input.substring(9).trim();
                addTask(taskList, "deadline", deadlineDescription, storage);
            }
            // Handle "event" command
            else if (input.startsWith("event")) {
                if (!input.contains("/from") || !input.contains("/to")) {
                    throw new tarsException("An event needs both start and end times. Format it like this: 'event <task> /from <start> /to <end>'.");
                }
                String eventDescription = input.substring(6).trim();
                addTask(taskList, "event", eventDescription, storage);
            }
            // Handle "mark" and "unmark" commands
            else if (input.startsWith("mark")) {
                markTask(input, taskList, ui, storage);
            } else if (input.startsWith("unmark")) {
                unmarkTask(input, taskList, ui, storage);
            }
            // Handle "delete" command
            else if (input.startsWith("delete")) {
                deleteTask(input, taskList, ui, storage);
            }
            // Handle unrecognized commands with humor
            else {
                String[] unknownCommandMessages = {
                        "Hmm, that doesn't sound like a command I recognize. Maybe try again?",
                        "I think you're speaking a language I don't understand. Try something else!",
                        "Oops! That didn't quite compute. Please enter a valid command.",
                        "Are you sure that's a command? Double-check and try again.",
                        "This doesn't seem like a command I know. How about we try something else?"
                };

                Random rand = new Random();
                int randomIndex = rand.nextInt(unknownCommandMessages.length);

                throw new tarsException(unknownCommandMessages[randomIndex]);
            }
        } catch (tarsException e) {
            ui.printSeparator();
            System.out.println(e.getMessage());
            ui.printSeparator();
        }
        return false;  // Continue processing user input
    }

    public static void addTask(List<Task> tasks, String taskType, String input, Storage storage) {
        UserInterface ui = new UserInterface();

        try {
            // Switch between task types: todo, deadline, event
            switch (taskType) {
                case "todo":
                    tasks.add(new Todo(input));
                    break;
                case "deadline":
                    String[] deadlineParts = input.split("/by");
                    tasks.add(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));  // 解析日期
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

            // Save tasks after adding
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

    // Mark a task as done and save the updated task list
    public static void markTask(String input, List<Task> taskList, UserInterface ui, Storage storage) throws tarsException {
        try {
            if (!input.contains(" ")) {
                throw new tarsException("Oops! The correct format is 'mark <task number>'.");
            }

            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;

            if (taskNumber < 0 || taskNumber >= taskList.size()) {
                throw new tarsException("That task number is out of range.");
            }

            taskList.get(taskNumber).markAsDone();
            ui.printSeparator();
            System.out.println("    Great! Task marked as complete: ");
            System.out.println("    " + taskList.get(taskNumber));
            ui.printSeparator();

            // Save tasks after marking
            storage.saveTasks(taskList);
        } catch (NumberFormatException | IOException e) {
            throw new tarsException("Error: " + e.getMessage());
        }
    }

    // Unmark a task as not done and save the updated task list
    public static void unmarkTask(String input, List<Task> taskList, UserInterface ui, Storage storage) throws tarsException {
        try {
            if (!input.contains(" ")) {
                throw new tarsException("Oops! The correct format is 'unmark <task number>'.");
            }

            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;

            if (taskNumber < 0 || taskNumber >= taskList.size()) {
                throw new tarsException("That task number is out of range.");
            }

            taskList.get(taskNumber).markAsNotDone();
            ui.printSeparator();
            System.out.println("    Task has been unmarked: ");
            System.out.println("    " + taskList.get(taskNumber));
            ui.printSeparator();

            // Save tasks after unmarking
            storage.saveTasks(taskList);
        } catch (NumberFormatException | IOException e) {
            throw new tarsException("Error: " + e.getMessage());
        }
    }

    // Function to delete tasks and save the updated task list
    public static void deleteTask(String input, List<Task> taskList, UserInterface ui, Storage storage) throws tarsException {
        try {
            String[] inputs = input.split(" ");

            if (inputs.length == 2 && inputs[1].equalsIgnoreCase("all")) {
                if (taskList.isEmpty()) {
                    throw new tarsException("Your task list is already empty. Nothing to delete!");
                }

                taskList.clear();
                ui.printSeparator();
                System.out.println("All tasks have been successfully deleted.");
                ui.printSeparator();

                storage.saveTasks(taskList);
                return;
            }

            if (inputs.length != 2) {
                throw new tarsException("Oops! The correct format is 'delete <task number>' or 'delete all'. Please try again.");
            }

            int taskNumber = Integer.parseInt(inputs[1]) - 1;

            if (taskNumber < 0 || taskNumber >= taskList.size()) {
                throw new tarsException("Oops! That task number is out of range. Please choose a number between 1 and " + taskList.size() + ".");
            }

            Task removedTask = taskList.remove(taskNumber);
            ui.printSeparator();
            System.out.println("Noted. I've successfully removed this task: ");
            System.out.println("  " + removedTask);
            System.out.println("Now you have " + taskList.size() + " tasks left in your list. Keep going!");
            ui.printSeparator();

            storage.saveTasks(taskList);
        } catch (NumberFormatException e) {
            throw new tarsException("Uh-oh! That doesn't look like a valid number. Please enter 'delete' followed by the task number.");
        } catch (IOException e) {
            throw new tarsException("Error saving tasks: " + e.getMessage());
        }
    }

    // Print the total number of tasks in the list
    public static void printTaskCount(int taskCount) {
        System.out.println("    Now you have " + taskCount + " tasks in the list.");
    }
}
