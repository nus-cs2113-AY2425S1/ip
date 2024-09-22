package dobby.tasklist;

import dobby.exceptions.MissingDescriptionException;
import dobby.exceptions.TaskAlreadyMarkedException;
import dobby.exceptions.TaskAlreadyUnmarkedException;
import dobby.tasks.Task;
import dobby.ui.Ui;
import dobby.storage.Storage;

import java.util.ArrayList;

/**
 * The TaskList class manages the collection of tasks and handles operations such as adding,
 * deleting, marking tasks as done/undone, and interacting with the UI and Storage.
 */
public class TaskList {

    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Creates a new TaskList with an empty list of tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Returns the list of tasks.
     *
     * @return The ArrayList of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the task list is empty; false otherwise.
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns the task at a specified index.
     *
     * @param index The index of the task.
     * @return The task at the given index.
     */

    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Adds a task to the list based on the command entered by the user.
     *
     * @param line The command containing task details.
     * @param ui The UI object to interact with the user.
     * @param storage The storage object to save the task list.
     * @throws MissingDescriptionException If the task description is missing.
     */
    public void addTaskFromCommand(String line, Ui ui, Storage storage) throws MissingDescriptionException {
        Task task = TaskCreator.createTask(line);
        if (task != null) {
            addTask(task);
            Ui.printTaskAddedMessage(task, size());
            storage.saveTasks(taskList);
        }
    }

    /**
     * Deletes a task from the list based on the user command.
     *
     * @param line The command specifying which task to delete.
     * @param ui The UI object to interact with the user.
     */
    public void deleteTask(String line, Ui ui) {
        int taskNumber = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1));
        if (isValidTaskNumber(taskNumber)) {
            Task t = taskList.get(taskNumber - 1);
            taskList.remove(t);
            ui.printSeparator();
            System.out.println("    Dobby is removing this task:");
            System.out.println("        " + t);
            System.out.println("    Dobby says master has " + size() + " remaining tasks!");
            ui.printSeparator();
        }
    }

    /**
     * Marks a task as done based on the user command.
     *
     * @param line The command specifying which task to delete.
     * @param ui The UI object to interact with the user.
     * @param storage The storage object to save the task list.
     * @throws TaskAlreadyMarkedException If the task is already marked as done.
     */
    public void markTaskAsDone(String line, Ui ui, Storage storage) throws TaskAlreadyMarkedException {
        int taskNumber = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1));
        if (isValidTaskNumber(taskNumber)){
            Task task = taskList.get(taskNumber - 1);
            if (task.isDone()){
                throw new TaskAlreadyMarkedException();
            }
            task.markAsDone();
            ui.printTaskStatus("done", task);
            storage.saveTasks(taskList);
        }
    }

    /**
     * Unmarks a task as done based on the user command.
     *
     * @param line The command specifying which task to delete.
     * @param ui The UI object to interact with the user.
     * @param storage The storage object to save the task list.
     * @throws TaskAlreadyUnmarkedException If the task is already marked as undone.
     */
    public void unmarkTaskAsDone(String line, Ui ui, Storage storage) throws TaskAlreadyUnmarkedException {
        int taskNumber = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1));
        if (isValidTaskNumber(taskNumber)) {
            Task task = taskList.get(taskNumber-1);
            if (!task.isDone()){
                throw new TaskAlreadyUnmarkedException();
            }
            task.unmarkAsDone();
            ui.printTaskStatus("incomplete", task);
            storage.saveTasks(taskList);
        }
    }

    /**
     * Validates whether the task number provided is within the bounds of the task list.
     *
     * @param taskNumber The task number to validate.
     * @return true if the task number is valid; false otherwise.
     */
    public boolean isValidTaskNumber(int taskNumber) {
        return taskNumber > 0 && taskNumber <= taskList.size();
    }

}
