package Tars;

import Tars.Task.Deadline;
import Tars.Task.Event;
import Tars.Task.Task;
import Tars.Task.Todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Tars {
    public static void main(String[] args) {
        String logo = " _____                        \n"
                + "|_   _|__  _ __ __ _ _ __ ___  \n"
                + "  | |/ _ \\| '__/ _` | '_ ` _ \\ \n"
                + "  | | (_) | | | (_| | | | | | |\n"
                + "  |_|\\___/|_|  \\__,_|_| |_| |_|\n";

        UserInterface ui = new UserInterface();

        // Initialize storage and load tasks from file
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
        System.out.println("    Hello! I'm Tars.Tars.");
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
                    throw new TarsException("Oops! Your todo needs a description. Please try again.");
                }
                addTask(taskList, "todo", taskDescription, storage);
            }
            // Handle "deadline" command
            else if (input.startsWith("deadline")) {
                if (!input.contains("/by")) {
                    throw new TarsException("A deadline needs a due date. Format it like this: 'deadline <task> /by <due date>'.");
                }
                String deadlineDescription = input.substring(9).trim();
                addTask(taskList, "deadline", deadlineDescription, storage);
            }
            // Handle "event" command
            else if (input.startsWith("event")) {
                if (!input.contains("/from") || !input.contains("/to")) {
                    throw new TarsException("An event needs both start and end times. Format it like this: 'event <task> /from <start> /to <end>'.");
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

                throw new TarsException(unknownCommandMessages[randomIndex]);
            }
        } catch (TarsException e) {
            ui.printSeparator();
            System.out.println(e.getMessage());
            ui.printSeparator();
        }
        return false;  // Continue processing user input
    }

    // Add a task to the list based on the task type and save it
    public static void addTask(List<Task> tasks, String taskType, String input, Storage storage) {
        UserInterface ui = new UserInterface();

        // Switch between task types: todo, deadline, event
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

        // Save tasks after adding
        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    // Mark a task as done and save the updated task list
    public static void markTask(String input, List<Task> taskList, UserInterface ui, Storage storage) throws TarsException {
        try {
            if (!input.contains(" ")) {
                throw new TarsException("Oops! The correct format is 'mark <task number>'.");
            }

            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;

            if (taskNumber < 0 || taskNumber >= taskList.size()) {
                throw new TarsException("That task number is out of range.");
            }

            taskList.get(taskNumber).markAsDone();
            ui.printSeparator();
            System.out.println("    Great! Task marked as complete: ");
            System.out.println("    " + taskList.get(taskNumber));
            ui.printSeparator();

            // Save tasks after marking
            storage.saveTasks(taskList);
        } catch (NumberFormatException | IOException e) {
            throw new TarsException("Error: " + e.getMessage());
        }
    }

    // Unmark a task as not done and save the updated task list
    public static void unmarkTask(String input, List<Task> taskList, UserInterface ui, Storage storage) throws TarsException {
        try {
            if (!input.contains(" ")) {
                throw new TarsException("Oops! The correct format is 'unmark <task number>'.");
            }

            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;

            if (taskNumber < 0 || taskNumber >= taskList.size()) {
                throw new TarsException("That task number is out of range.");
            }

            taskList.get(taskNumber).markAsNotDone();
            ui.printSeparator();
            System.out.println("    Task has been unmarked: ");
            System.out.println("    " + taskList.get(taskNumber));
            ui.printSeparator();

            // Save tasks after unmarking
            storage.saveTasks(taskList);
        } catch (NumberFormatException | IOException e) {
            throw new TarsException("Error: " + e.getMessage());
        }
    }

    // Print the total number of tasks in the list
    public static void printTaskCount(int taskCount) {
        System.out.println("    Now you have " + taskCount + " tasks in the list.");
    }
}
