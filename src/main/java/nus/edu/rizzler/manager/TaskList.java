package nus.edu.rizzler.manager;

import nus.edu.rizzler.exception.RizzlerException;
import nus.edu.rizzler.task.Deadline;
import nus.edu.rizzler.task.Event;
import nus.edu.rizzler.task.Task;
import nus.edu.rizzler.task.Todo;
import nus.edu.rizzler.ui.Face;

import java.util.ArrayList;

/**
 * Manages a list of tasks, allowing addition, deletion, status updates, and searching.
 */
public class TaskList {
    private Face face = new Face();
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} with tasks loaded from a CSV string.
     *
     * @param csvString The CSV string containing task data.
     */
    public TaskList(String csvString) {
        tasks = new ArrayList<>();
        loadTasks(csvString);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns a string representation of all tasks in the list.
     *
     * @return The list of tasks as a formatted string.
     */
    public String toString() {
        int taskCount = getSize();
        if (taskCount == 0) {
            return "Nothing in the pipeline yet! Let's get to work! "
                    + face.getPartyFace();
        }

        StringBuilder tasksString = new StringBuilder();
        for (int taskIndex = 0; taskIndex < taskCount; taskIndex++) {
            Task task = tasks.get(taskIndex);

            if (taskIndex > 0){
                tasksString.append("\n");
            }
            tasksString.append(String.format("%d. %s", taskIndex + 1, task));
        }
        return tasksString.toString();
    }

    /**
     * Adds a new todo task to the list.
     *
     * @param taskName The name of the todo task.
     * @param isDone Whether the task is marked as done.
     * @return The string representation of the added todo task.
     */
    public String addTodo(String taskName, Boolean isDone) {
        Todo todo = new Todo(taskName, isDone);
        tasks.add(todo);
        return todo.toString();
    }

    /**
     * Adds a new deadline task to the list.
     *
     * @param taskName The name of the deadline task.
     * @param isDone Whether the task is marked as done.
     * @param by The deadline for the task.
     * @return The string representation of the added deadline task.
     */
    public String addDeadline(String taskName, Boolean isDone, String by) {
        Deadline deadline = new Deadline(taskName, isDone, by);
        tasks.add(deadline);
        return deadline.toString();
    }

    /**
     * Adds a new event task to the list.
     *
     * @param taskName The name of the event task.
     * @param isDone Whether the task is marked as done.
     * @param from The start time of the event.
     * @param to The end time of the event.
     * @return The string representation of the added event task.
     */
    public String addEvent(String taskName, Boolean isDone, String from, String to) {
        Event event = new Event(taskName, isDone, from, to);
        tasks.add(event);
        return event.toString();
    }

    /**
     * Updates the completion status of a task.
     *
     * @param taskIndex The index of the task to update.
     * @param isDone The new completion status.
     * @return The string representation of the updated task.
     * @throws RizzlerException If the task index is out of range.
     */
    public String updateTaskStatus(int taskIndex, boolean isDone) {
        if (taskIndex >= this.getSize()) {
            throw new RizzlerException("Update task status failed. Task index is out of range.");
        }

        Task task = tasks.get(taskIndex);
        task.setIsDone(isDone);
        return task.toString();
    }

    /**
     * Deletes a task from the list by index.
     *
     * @param taskIndex The index of the task to delete.
     * @return The string representation of the deleted task.
     * @throws RizzlerException If the task index is out of range.
     */
    public String deleteTask(int taskIndex) {
        if (taskIndex >= this.getSize()) {
            throw new RizzlerException("Delete Task failed. Task index is out of range.");
        }

        Task task = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        return task.toString();
    }

    /**
     * Finds and returns tasks containing the specified keyword.
     *
     * @param keyword The keyword to search for in tasks.
     * @return A formatted string of tasks containing the keyword.
     */
    public String findKeyword(String keyword) {
        int itemCount = 0;
        StringBuilder matchString = new StringBuilder();

        for (Task task : tasks) {
            String taskString = task.toString().toUpperCase();
            String keywordString = keyword.toUpperCase();
            if (taskString.contains(keywordString)) {
                if (itemCount > 0){
                    matchString.append("\n");
                }
                matchString.append(String.format("%d. %s", ++itemCount, task));
            }
        }
        if (itemCount == 0) {
            return "NO TASKS FOUND";
        }
        return matchString.toString();
    }

    /**
     * Converts the list of tasks to a CSV string format.
     *
     * @return The CSV string representation of the task list.
     */
    public String toCSVString() {
        StringBuilder csvString = new StringBuilder();

        for (int taskIndex = 0; taskIndex < getSize(); taskIndex++) {
            Task task = tasks.get(taskIndex);
            if (taskIndex != 0){
                csvString.append(System.lineSeparator());
            }
            csvString.append(task.toCSV());
        }
        return csvString.toString();
    }

    /**
     * Loads tasks from a CSV string into the task list.
     *
     * @param csvString The CSV string containing task data.
     * @throws RizzlerException If task data is corrupted or missing fields.
     */
    public void loadTasks(String csvString) throws RizzlerException {
        try{
            String[] taskStrings = csvString.split("\n");
            for (String taskString : taskStrings) {
                String[] taskArguments = taskString.split(", ");
                String taskType = taskArguments[0];
                Boolean isDone = Boolean.parseBoolean(taskArguments[1]);

                switch (taskType) {
                case "T": addTodo(taskArguments[2], isDone);
                    break;
                case "D": addDeadline(taskArguments[2], isDone, taskArguments[3]);
                    break;
                case "E": addEvent(taskArguments[2], isDone, taskArguments[3], taskArguments[4]);
                    break;
                default: throw new RizzlerException("Rizzler.java is corrupted. Could not" +
                        " recover previously saved tasks");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RizzlerException("Rizzler.java is corrupted. Saved tasks have missing fields.");
        } catch (Exception e) {
            throw new RizzlerException("Rizzler.java is corrupted. Could not " +
                    "recover previously saved tasks" + e.getMessage());
        }
    }
}
