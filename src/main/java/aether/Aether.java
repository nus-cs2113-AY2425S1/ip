package aether;

import aether.task.Task;
import aether.task.Todo;
import aether.task.Deadline;
import aether.task.Event;
import aether.ui.Display;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Aether class represents the main logic for a simple task manager program.
 * It allows users to add, delete, mark, unmark tasks, and display tasks.
 */
public class Aether {
    private boolean isExit = false;
    private ArrayList<Task> tasks = new ArrayList<>();
    private Storage storage;

    public static void main(String[] args) {
        Aether aether = new Aether();
        aether.run();
    }

    public Aether() {
        storage = new Storage("data/aether.txt");
        try {
            tasks = storage.load(); // Load tasks from storage
        } catch (IOException e) {
            Display.response("Error loading tasks: " + e.getMessage());
        }
    }

    public void run() {
        Display.showStartScreen();
        Scanner scanner = new Scanner(System.in);

        while (!isExit) {
            System.out.println("You:");
            String command = scanner.nextLine();
            Display.printSeparator();
            try {
                handleCommand(command);
            } catch (DukeException e) {
                Display.response(e.getMessage());
                Display.printSeparator();
            }
        }
        scanner.close();
    }

    private void handleCommand(String command) throws DukeException {
        command = command.trim();
        if (command.isEmpty()) {
            throw new DukeException("Error: Command cannot be empty. Please enter a valid command.");
        }

        String[] commandParts = command.split("\\s+", 2);
        String commandName = commandParts[0].toLowerCase();
        String arguments = commandParts.length > 1 ? commandParts[1].trim() : "";

        switch (commandName) {
        case "bye":
            isExit = true;
            Display.showEndScreen();
            break;
        case "list":
            listTasks();
            break;
        case "mark":
            handleMarkCommand(arguments, true);
            break;
        case "unmark":
            handleMarkCommand(arguments, false);
            break;
        case "todo":
            handleTodoCommand(arguments);
            break;
        case "deadline":
            handleDeadlineCommand(arguments);
            break;
        case "event":
            handleEventCommand(arguments);
            break;
        case "delete":
            handleDeleteCommand(arguments);
            break;
        default:
            throw new DukeException("Error: Invalid command. Please enter a valid command.");
        }
        Display.printSeparator();
    }

    private void handleMarkCommand(String arguments, boolean isMark) throws DukeException {
        if (tasks.isEmpty()) {
            throw new DukeException("Error: The task list is empty. There are no tasks to mark.");
        }
        try {
            int index = Integer.parseInt(arguments.trim()) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new DukeException("Error: Invalid task number. Please enter a valid task number.");
            }
            markTaskStatus(index, isMark);
            saveTasks(); // Save after marking task
        } catch (NumberFormatException e) {
            throw new DukeException("Error: Invalid input. Please enter a valid task number.");
        }
    }

    private void handleTodoCommand(String arguments) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException("Error: The description of a todo cannot be empty.");
        } else {
            addTask(new Todo(arguments));
        }
    }

    private void handleDeadlineCommand(String arguments) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException("Error: The description of a deadline cannot be empty.");
        }
        String[] deadlineParts = arguments.split(" /by ", 2);
        if (deadlineParts.length < 2 || deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
            throw new DukeException("Error: Invalid deadline format. Please enter in the format:"
                    + "\n'deadline <description> /by <date>'.");
        }
        String description = deadlineParts[0].trim();
        String by = deadlineParts[1].trim();
        addTask(new Deadline(description, by));
    }

    private void handleEventCommand(String arguments) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException("Error: The description of an event cannot be empty.");
        }
        String[] eventParts = arguments.split(" /from ", 2);
        if (eventParts.length < 2 || eventParts[0].trim().isEmpty()) {
            throw new DukeException("Error: Invalid event format. Please enter in the format:"
                    + "\n'event <description> /from <start_time> /to <end_time>'.");
        }
        String description = eventParts[0].trim();
        String[] timeParts = eventParts[1].split(" /to ", 2);
        if (timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
            throw new DukeException("Error: Invalid event time format. Please enter in the format:"
                    + "\n'event <description> /from <start_time> /to <end_time>'.");
        }
        String from = timeParts[0].trim();
        String to = timeParts[1].trim();
        addTask(new Event(description, from, to));
    }

    private void handleDeleteCommand(String arguments) throws DukeException {
        if (tasks.isEmpty()) {
            throw new DukeException("Error: The task list is empty. There are no tasks to delete.");
        }
        try {
            int index = Integer.parseInt(arguments.trim()) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new DukeException("Error: Invalid task number. Please enter a valid task number.");
            }
            Task removedTask = tasks.remove(index);
            Display.response("Noted. I've removed this task:\n  " + removedTask);
            Display.response("Now you have " + tasks.size() + " tasks in the list.");
            saveTasks(); // Save after deletion
        } catch (NumberFormatException e) {
            throw new DukeException("Error: Invalid input. Please enter a valid task number.");
        }
    }

    private void addTask(Task task) {
        tasks.add(task);
        Display.response(
                "Got it. I've added this task:\n  " + task + "\nNow you have " + tasks.size() + " tasks in the list."
        );
        saveTasks(); // Save after adding a task
    }

    private void listTasks() {
        if (tasks.isEmpty()) {
            Display.response("Task list is empty.");
        } else {
            Display.response("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }

    private void markTaskStatus(int index, boolean isDone) {
        tasks.get(index).setDone(isDone);
        String message = isDone
                ? "Nice! I've marked this task as done:\n"
                : "OK, I've marked this task as not done yet:\n";
        Display.response(message + (index + 1) + "." + tasks.get(index));
    }

    private void saveTasks() {
        try {
            storage.save(tasks); // Save the updated tasks
        } catch (IOException e) {
            Display.response("Error saving tasks: " + e.getMessage());
        }
    }
}
