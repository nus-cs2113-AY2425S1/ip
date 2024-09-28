package medea.core;

import medea.exceptions.MedeaException;
import medea.task.*;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the task management system.
 * This class provides methods for managing tasks, including adding,
 * deleting, and updating their status.
 */
public class TaskList {

    /** The list of tasks stored in this TaskList. */
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Constructs a TaskList and loads tasks from a CSV string.
     *
     * @param csvString a string representation of tasks in CSV format
     */
    public TaskList(String csvString) {
        tasks = new ArrayList<Task>();
        loadTasks(csvString);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return the size of the task list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns a string representation of the task list.
     *
     * @return a formatted string of all tasks in the list
     */
    public String toString() {
        int listSize = getSize();

        if (listSize == 0) {
            return "No items currently in your task list.\n";
        }

        StringBuilder output = new StringBuilder();

        for (int index = 0; index < listSize; index++) {
            Task task = tasks.get(index);

            if (index != 0) output.append("\n");
            output.append(String.format("%d. %s", index + 1, task));
        }
        return output.toString();
    }

    /**
     * Returns a string representation of tasks that match the filter.
     *
     * @param filter the keyword or phrase to search for in the task list
     * @return a formatted string of matching tasks
     */
    public String toFilteredString(String filter) {
        int itemCount = 0;
        StringBuilder output = new StringBuilder();

        for (Task task : tasks) {
            String taskString = task.toString().toLowerCase();
            String filterString = filter.toLowerCase();
            boolean isMatch = taskString.contains(filterString);
            if (!isMatch) continue;

            if (itemCount != 0) output.append("\n");
            output.append(String.format("%d. %s", ++itemCount, task));
        }

        if (itemCount == 0) {
            return "No matching items currently in your task list.\n";
        }

        return output.toString();
    }

    /**
     * Returns a string representation of tasks in CSV format.
     *
     * @return a CSV string representation of all tasks
     */
    public String toCSVString() {
        StringBuilder output = new StringBuilder();
        for (int index = 0; index < getSize(); index++) {
            Task currentTask = tasks.get(index);
            if (index != 0) output.append(System.lineSeparator());
            output.append(currentTask.toCSV());
        }
        return output.toString();
    }

    /**
     * Loads tasks from a CSV string into the TaskList.
     *
     * @param csvString a string representation of tasks in CSV format
     * @throws MedeaException if the task data is corrupted
     */
    public void loadTasks(String csvString) {
        String[] taskStrings = csvString.split("\n");
        for (String taskString : taskStrings) {
            String[] taskArguments = taskString.split(",");
            String taskType = taskArguments[0];
            switch (taskType) {
                case "T":
                    addTodo(taskArguments[1]);
                    break;
                case "D":
                    addDeadline(taskArguments[1], taskArguments[2]);
                    break;
                case "E":
                    addEvent(taskArguments[1], taskArguments[2], taskArguments[3]);
                    break;
                default:
                    throw new MedeaException("Corrupted task data.");
            }
        }
    }

    /**
     * Adds a todo task to the TaskList.
     *
     * @param description the description of the todo task
     * @return a string representation of the newly added task
     */
    public String addTodo(String description) {
        Todo newTodo = new Todo(description);
        tasks.add(newTodo);
        return newTodo.toString();
    }

    /**
     * Adds a deadline task to the TaskList.
     *
     * @param description the description of the deadline task
     * @param deadlineDate the deadline date for the task
     * @return a string representation of the newly added task
     */
    public String addDeadline(String description, String deadlineDate) {
        Deadline newDeadline = new Deadline(description, deadlineDate);
        tasks.add(newDeadline);
        return newDeadline.toString();
    }

    /**
     * Adds an event task to the TaskList.
     *
     * @param description the description of the event task
     * @param eventStart the start time of the event
     * @param eventEnd the end time of the event
     * @return a string representation of the newly added task
     */
    public String addEvent(String description, String eventStart, String eventEnd) {
        Event newEvent = new Event(description, eventStart, eventEnd);
        tasks.add(newEvent);
        return newEvent.toString();
    }

    /**
     * Updates the done status of a task in the TaskList.
     *
     * @param index the index of the task to update
     * @param isDone the new done status for the task
     * @return a string representation of the updated task
     * @throws MedeaException if the index is out of range
     */
    public String updateTaskDoneStatus(int index, boolean isDone) {
        if (index < 0 || index >= this.getSize()) {
            throw new MedeaException(isDone ? "Mark" : "Unmark" + " Task failed. Index out of range.");
        }

        Task selectedTask = tasks.get(index);
        selectedTask.setIsDone(isDone);
        return selectedTask.toString();
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param index the index of the task to delete
     * @return a string representation of the deleted task
     * @throws MedeaException if the index is out of range
     */
    public String deleteTask(int index) {
        if (index < 0 || index >= this.getSize()) {
            throw new MedeaException("Delete Task failed. Index out of range.");
        }

        Task selectedTask = tasks.get(index);
        tasks.remove(index);
        return selectedTask.toString();
    }
}
