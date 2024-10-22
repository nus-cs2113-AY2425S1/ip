import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of tasks.
 *
 * Provides methods for adding, deleting, marking tasks, and finding tasks based on keywords or due dates.
 */
public class TaskList {

    private List<Task> tasks;
    private Ui ui;

    /**
     * Constructor for TaskList.
     * Initializes a new TaskList with an empty task collection and sets up the Ui component.
     */
    public TaskList() {
        this.ui = new Ui();
        this.tasks = new ArrayList<>();
    }

    /**
     * Gets the list of tasks.
     *
     * @return The current list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Prints the entire task list.
     * The method prints each task in the task list along with its index.
     */
    public void printList() {
        ui.printEnclosure();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + ". " + task.toString());
        }
        ui.printEnclosure();
    }

    /**
     * Finds tasks that contain a specific keyword in their description.
     *
     * @param keyword The keyword to search for.
     */
    public void findTasks(String keyword) {
        int index = 1;
        System.out.println("Here are the matching tasks in your list: ");
        ui.printEnclosure();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                System.out.println(index++ + ". " + task.toString());
            }
        }
        ui.printEnclosure();

        if (index == 1) {
            System.out.println("No tasks found.");
        }
    }

    /**
     * Finds tasks that are due on a specific date.
     *
     * @param input The input containing the target date.
     * @throws DianaException If the date format is invalid.
     */
    public void findDueDate(String input) throws DianaException {
        String dueDate = input.substring("date".length()).trim();
        LocalDate targetDate;
        try {
            targetDate = LocalDate.parse(dueDate, DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            throw new DianaException("Invalid date format, please use MMM dd yyyy.");
        }

        ui.printEnclosure();
        int i = 1;
        for (Task task : tasks) {
            if (task.getDueDate().toLocalDate().equals(targetDate)) {
                System.out.println((i++) + ". " + task.toString());
            }
        }

        if (i == 1) {
            System.out.println("No tasks found on: " + dueDate);
        }
        ui.printEnclosure();
    }

    /**
     * Marks or unmarks a task based on user input.
     *
     * @param input      The user input specifying the task number.
     * @param shouldMark Whether to mark or unmark the task.
     * @throws DianaException If the task number is invalid or the task is already marked/unmarked.
     */
    public void toMark(String input, boolean shouldMark) throws DianaException {
        try {
            String substring = input.substring(input.indexOf(" ") + 1);
            int taskNum = Integer.parseInt(substring) - 1;

            if (taskNum == -1) {
                throw new DianaException("Task number cannot be zero.");
            }

            if (taskNum < -1) {
                throw new DianaException("Task number cannot be negative.");
            }

            if (taskNum >= tasks.size()) {
                throw new DianaException("Task number must be less than " + tasks.size());
            }

            Task task = tasks.get(taskNum);
            if (shouldMark) {
                if (task.getStatusIcon().equals("X")) {
                    throw new DianaException("Task is already marked.");
                }
                task.markAsDone();
                ui.printEnclosure();
                System.out.println("Nice! I've marked this task as done\n" + task.toString());
                ui.printEnclosure();
            } else {
                if (task.getStatusIcon().equals(" ")) {
                    throw new DianaException("You cannot unmark a task that is already unmarked.");
                }
                task.markAsNotDone();
                ui.printEnclosure();
                System.out.println("Okay, I've marked this task as not done yet\n" + task.toString());
                ui.printEnclosure();
            }
        } catch (NumberFormatException e) {
            System.out.println("Number specified must be an integer.");
        }
    }

    /**
     * Prints a message confirming that a task was added and displays the updated task list.
     *
     * @param description The description of the added task.
     */
    public void printAddedTask(String description) {
        ui.printEnclosure();
        System.out.println("Got it! I've added this task:");
        System.out.println(description);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        printList();
    }

    /**
     * Adds a new Todo task based on the user's input.
     *
     * @param input The user input specifying the todo task.
     * @throws DianaException If the todo description is empty.
     */
    public void addTodo(String input) throws DianaException {
        String description = input.substring("todo".length()).trim();
        if (description.isEmpty()) {
            throw new DianaException("Description of todo task cannot be empty.");
        }
        Todo todo = new Todo(description);
        tasks.add(todo);
        printAddedTask(description);
    }

    /**
     * Adds a new Deadline task based on the user's input.
     *
     * @param input The user input specifying the deadline task.
     * @throws DianaException If the description or format is invalid.
     */
    public void addDeadline(String input) throws DianaException {
        if (input.substring("deadline".length()).trim().isEmpty()) {
            throw new DianaException("Description of deadline task cannot be empty.");
        }
        if (input.contains("/by")) {
            String[] parts = input.split("/by", 2);
            String description = parts[0].substring("deadline".length()).trim();
            String by = parts[1].trim();
            Task deadline = new Deadline(description, by);
            tasks.add(deadline);
            printAddedTask(description);
        } else {
            throw new DianaException("Format Invalid. Please use the following format: deadline <description> /by <time>");
        }
    }

    /**
     * Adds a new Event task based on the user's input.
     *
     * @param input The user input specifying the event task.
     * @throws DianaException If the description or format is invalid.
     */
    public void addEvent(String input) throws DianaException {
        if (input.substring("event".length()).trim().isEmpty()) {
            throw new DianaException("Description of event task cannot be empty.");
        }
        if (input.contains("/from") && input.contains("/to")) {
            String[] parts = input.split("/from", 2);
            String description = parts[0].substring("event".length()).trim();
            String[] fromto = parts[1].split("/to", 2);
            String from = fromto[0].trim();
            String to = fromto[1].trim();
            Task event = new Event(description, from, to);
            tasks.add(event);
            printAddedTask(description);
        } else {
            throw new DianaException("Format Invalid. Please use the following format: event <description> /from <start time> /to <end time>");
        }
    }

    /**
     * Deletes a task from the task list based on the user's input.
     *
     * @param input The user input specifying the task to be deleted.
     * @throws DianaException If the task number is invalid.
     */
    public void deleteTask(String input) throws DianaException {

        if (tasks.isEmpty()) {
            throw new DianaException("Task list is empty.");
        }

        try {
            String substring = input.substring(input.indexOf(" ") + 1);
            int taskNum = Integer.parseInt(substring) - 1;

            if (taskNum == -1) {
                throw new DianaException("Task number cannot be zero.");
            }

            if (taskNum < -1) {
                throw new DianaException("Task number cannot be negative.");
            }

            if (taskNum >= tasks.size()) {
                throw new DianaException("Task number must be less than " + tasks.size());
            }

            System.out.println("Task: " + tasks.get(taskNum).toString() + " has been deleted.");
            tasks.remove(taskNum);

        } catch (NumberFormatException e) {
            System.out.println("Number specified must be an integer.");
        }
    }
}
