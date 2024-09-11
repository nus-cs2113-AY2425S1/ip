package aether;

import aether.task.Task;
import aether.task.Todo;
import aether.task.Deadline;
import aether.task.Event;
import aether.ui.Display;

import java.util.Scanner;

/**
 * Aether class represents the main logic for a simple task manager program.
 * It allows users to add, mark, unmark tasks, and display tasks.
 */
public class Aether {
    private boolean isExit = false;
    private static final int MAX_TASKS = 100;
    private Task[] tasks = new Task[MAX_TASKS];
    private int taskCount = 0;

    public static void main(String[] args) {
        Aether aether = new Aether();
        aether.run();
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
            throw new DukeException("Command cannot be empty. Please enter a valid command.");
        }

        String[] commandParts = command.split(" ", 2);
        String commandName = commandParts[0].toLowerCase();
        String arguments = commandParts.length > 1 ? commandParts[1] : "";

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
        default:
            throw new DukeException("Invalid command. Please try again.");
        }
        Display.printSeparator();
    }

    private void handleMarkCommand(String arguments, boolean isMark) throws DukeException {
        try {
            int index = Integer.parseInt(arguments.trim()) - 1;
            if (index < 0 || index >= taskCount) {
                throw new DukeException("Invalid task number. Please enter a valid task index.");
            }
            markTaskStatus(index, isMark);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid input. Please enter a valid task number.");
        }
    }

    private void handleTodoCommand(String arguments) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty. Please enter a valid description.");
        } else {
            addTask(new Todo(arguments));
        }
    }

    private void handleDeadlineCommand(String arguments) throws DukeException {
        String[] deadlineParts = arguments.split(" /by ", 2);
        if (arguments.isEmpty() || deadlineParts.length < 2) {
            throw new DukeException("Please enter a deadline in the format:\ndescription /by date.");
        } else {
            addTask(new Deadline(deadlineParts[0], deadlineParts[1]));
        }
    }

    private void handleEventCommand(String arguments) throws DukeException {
        String[] eventParts = arguments.split(" /from | /to ");
        if (arguments.isEmpty() || eventParts.length < 3) {
            throw new DukeException(
                    "Please enter an event in the format:\ndescription /from start_time /to end_time."
            );
        } else {
            addTask(new Event(eventParts[0], eventParts[1], eventParts[2]));
        }
    }

    private void addTask(Task task) throws DukeException {
        if (taskCount < tasks.length) {
            tasks[taskCount] = task;
            taskCount++;
            Display.response(
                    "Got it. I've added this task:\n  " + task + "\nNow you have " + taskCount + " tasks in the list."
            );
        } else {
            throw new DukeException("Task list is full. Sorry!");
        }
    }

    private void listTasks() {
        if (taskCount == 0) {
            Display.response("Task list is empty.");
        } else {
            Display.response("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + "." + tasks[i]);
            }
        }
    }

    private void markTaskStatus(int index, boolean isDone) {
        tasks[index].setDone(isDone);
        String message = isDone
                ? "Nice! I've marked this task as done:\n"
                : "OK, I've marked this task as not done yet:\n";
        Display.response(message + (index + 1) + "." + tasks[index]);
    }
}
