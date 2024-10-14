package eva.tasks;

import eva.exception.EvaException;
import eva.storage.Storage;
import eva.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a manager for handling tasks in the task list.
 * The TaskManager class is responsible for managing tasks, including adding,
 * deleting, marking, unmarking, and finding tasks. It provides methods to interact
 * with the user interface (UI) and stores tasks in a file using the Storage class.
 */
public class TaskManager {

    private final static Ui ui = new Ui();
    public static final String HORIZONTAL_LINE = "---------------------------------------------------------------";
    private static final String FILE_PATH = "data/eva.txt";

    private Storage storage;
    private ArrayList<Task> tasks;
    private int count;

    public TaskManager() {
        tasks = new ArrayList<>();
        count = 0;
        storage = new Storage(FILE_PATH);
        loadTasks();
    }

    /**
     * Prints out all the tasks in the list to the user.
     */
    public void printTaskList() {
        ui.printTaskList(tasks);
    }

    /**
     * Marks the task as done based on the provided task number.
     * If the task number is out of range, a {@link EvaException} is thrown.
     *
     * @param line A string representing the task number to be marked as done.
     * @throws EvaException If number given is out of bounds (< 0 or > count).
     */
    public void markTask(String line) throws EvaException {
        int taskNumber = Integer.parseInt(line) - 1;
        EvaException.validateTaskNumber(taskNumber, count);

        tasks.get(taskNumber).setMarkAsDone();

        String description = tasks.get(taskNumber).toString();
        ui.showTaskAsDone(description);

        saveTasks();
    }

    /**
     * Unmarks the task as done based on the provided task number.
     * If the task number is out of range, a {@link EvaException} is thrown.
     *
     * @param line A string representing the task number to be unmarked as done.
     * @throws EvaException If number given is out of bounds (< 0 or > count).
     */
    public void unmarkTask(String line) throws EvaException {
        int taskNumber = Integer.parseInt(line) - 1;
        EvaException.validateTaskNumber(taskNumber, count);

        tasks.get(taskNumber).setMarkAsNotDone();

        String description = tasks.get(taskNumber).toString();
        ui.showTaskAsNotDone(description);

        saveTasks();
    }

    /**
     * Deletes the task as specified by the user.
     * If the task number is out of range, a {@link EvaException} is thrown.
     *
     * @param line A string representing the task number to be deleted.
     * @throws EvaException If number given is out of bounds (< 0 or > count).
     */
    public void deleteTask(String line) throws EvaException {
        int taskNumber = Integer.parseInt(line) - 1;
        EvaException.validateTaskNumber(taskNumber, count);

        String taskDescription = tasks.get(taskNumber).toString();

        tasks.remove(taskNumber);
        count --;

        ui.showDeleteTask(taskDescription, taskNumber, count);

        saveTasks();
    }

    /**
     * Adds a new Todo task to the task list.
     * Creates a new {@code Todo} object using the provided task description.
     * and adds it to the list of tasks.
     * After adding the task, a confirmation message is displayed,
     * the total number of tasks is printed, and the updated list is saved.
     *
     * @param line The description of the Todo task to be added.
     */
    public void addTodo(String line) {
        tasks.add(new Todo(line));
        count++;

        String todoDescription = tasks.get(count - 1).toString();
        ui.showAddTodo(todoDescription, count);

        saveTasks();
    }

    /**
     * Adds a new Deadline task to the task list.
     * Creates a new {@code Deadline} object using the provided task description
     * and adds it to the list of tasks.
     * After adding the task, a confirmation message is displayed,
     * the total number of tasks is printed, and updated list is saved.
     *
     * @param description The description of the Deadline task to be added.
     * @param by The time by which the Deadline task needs to be completed.
     */
    public void addDeadline(String description, String by) {
        tasks.add(new Deadline(description, by));
        count++;

        String deadlineDescription = tasks.get(count - 1).toString();
        ui.showAddDeadline(deadlineDescription, count);

        saveTasks();
    }

    /**
     * Adds a new Event task to the task list.
     * Creates a new {@code Event} object using the provided task description
     * and adds it to the list of tasks.
     * After adding the task, a confirmation message is displayed,
     * the total number of tasks is printed, and updated list is saved.
     *
     * @param description The description of the Event task to be added
     * @param from The time when the Event task starts
     * @param to The time when the Event task ends
     */
    public void addEvent(String description, String from, String to) {
        tasks.add(new Event(description, from, to));
        count++;

        String eventDescription = tasks.get(count - 1).toString();
        ui.showAddEvent(eventDescription, count);

        saveTasks();
    }

    /**
     * Finds and displays the tasks that match the given keyword
     * It searches the task list for tasks whose description matches
     * the specific keyword.
     * If matching tasks are found, they are displayed to the user,
     * If no matching tasks are found, a message indicating no matches will show.
     *
     * @param subject The keyword used to search for matching tasks.
     */
    public void findTasks(String subject) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isContain(subject)) {
                foundTasks.add(task);
            }
        }

        if (foundTasks.isEmpty()) {
            ui.showMessage("No tasks found!");
        } else {
            ui.showMessage("Here are the matching tasks in your list:");
            for (int i = 0; i < foundTasks.size(); i++) {
                ui.showMessage((i + 1) + ". " + foundTasks.get(i).toString());
            }
        }
        ui.showMessage(HORIZONTAL_LINE);
    }

    private void saveTasks() {
        try {
            storage.saveTasksToFile(tasks);
        } catch (EvaException e) {
            ui.showErrorMessage("Failed to save tasks: " + e.getMessage());
        }
    }

    private void loadTasks() {
        tasks = storage.loadTasksFromFile();
        count = tasks.size();
    }
}
