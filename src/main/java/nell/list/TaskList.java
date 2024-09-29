package nell.list;

import nell.common.Messages;
import nell.tasks.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a list of Tasks
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty task list
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Returns the number of tasks in the task list
     *
     * @return The number of tasks in the task list
     */
    public int getTaskCount() {
        return this.tasks.size();
    }

    /**
     * Adds a task to the task list
     *
     * @param taskToAdd The task to be added to the task list
     */
    public void addTask(Task taskToAdd) {
        System.out.println(Messages.TASK_ADD_MESSAGE);
        tasks.add(taskToAdd);
        System.out.println("   " + taskToAdd);
        System.out.println(String.format(Messages.NEW_TASK_COUNT_MESSAGE, this.tasks.size()));
    }

    /**
     * Returns the task at a specified index in the task list
     *
     * @param index The index of the selected task in the list
     * @return The task at index
     * @throws IndexOutOfBoundsException if index is not within the task list
     */
    public Task getTaskAtIndex(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    /**
     * Returns the formatted string for the task at a specified index
     *
     * @param index The index of the task
     */
    public String getTaskStringAtIndex(int index) throws IndexOutOfBoundsException {
        return String.format("   %d. %s", (index + 1), tasks.get(index));
    }

    /**
     * Removes a task at the specified index in the task list
     *
     * @param index The index of the task to be removed in the list
     * @throws IndexOutOfBoundsException if the index is outside the task list
     */
    public void removeTask(int index) throws IndexOutOfBoundsException {
        Task taskToRemove = tasks.get(index);
        System.out.println(Messages.TASK_REMOVE_MESSAGE);
        tasks.remove(index);
        System.out.println("   " + taskToRemove);
        System.out.println(String.format(Messages.NEW_TASK_COUNT_MESSAGE, this.tasks.size()));
    }

    /**
     * Writes the tasks in the task list to a file at a specified file path
     *
     * @param filePath The file path of the file to be written to
     * @throws IOException if the specified file cannot be written to or cannot be created
     */
    public void writeListToFile(String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath, false);
        for (Task task : tasks) {
            String formatLine = task.getFileLine() + System.lineSeparator();
            writer.write(formatLine);
        }
        writer.close();
    }

    /**
     * Loads a specified task to the task list.
     * For the loading of a saved task list from a file at startup
     *
     * @param taskToAdd The task to be loaded
     */
    public void loadTask(Task taskToAdd) {
        tasks.add(taskToAdd);
    }

    /**
     * Returns a list of tasks that contain a specified keyword.
     * If no tasks are found, returns an empty string
     *
     * @param keyword The keyword
     * @return A list of matching tasks, in the form of a formatted string
     */
    public String getMatchingTaskList(String keyword) {
        String matchingTaskList = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).containsKeyword(keyword)) {
                matchingTaskList += getTaskStringAtIndex(i);
                matchingTaskList += System.lineSeparator();
            }
        }

        return matchingTaskList;
    }

    /**
     * Returns a list of tasks that occur on a specified date
     *
     * @param date The specified date
     * @return A list of tasks that occur on date
     */
    public String getTasksOnDate(LocalDate date) {
        String matchingTasksList = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.isOnDate(date)) {
                matchingTasksList += getTaskStringAtIndex(i);
                matchingTasksList += System.lineSeparator();
            }
        }

        return matchingTasksList;
    }
}
