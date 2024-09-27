package tyrone.task;

import java.util.ArrayList;

/**
 * An abstract class representing a list of tasks.
 * This class provides static methods to manage a collection of Task objects.
 */
public abstract class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a new task to the list.
     *
     * @param task The Task object to be added.
     */
    public static void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Checks if the given task ID is valid.
     * Task ID is invalid if it is not a positive number or exceeds current number of tasks.
     *
     * @param taskId The given task ID to check.
     * @return true if the taskId is valid, false otherwise.
     */
    public static boolean isValidTaskId(int taskId) {
        return taskId >= 0 && taskId < tasks.size();
    }

    /**
     * Marks a task as done.
     *
     * @param taskId The ID of the task to mark as done.
     */
    public static void markTaskAsDone (int taskId) {
        tasks.get(taskId).markAsDone();
    }

    /**
     * Marks a task as undone.
     *
     * @param taskId The ID of the task to mark as undone.
     */
    public static void markTaskAsUndone (int taskId) {
        tasks.get(taskId).markAsUndone();
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskId The ID of the task to delete.
     */
    public static void deleteTask (int taskId) {
        tasks.remove(taskId);
    }

    /**
     * Gets the type, description and status (Done or not done) of a single task.
     *
     * @param taskId The ID of the task to get details for.
     * @return A string containing the task's type, description and status (Done or not done).
     */
    public static String getSingleTaskDetails (int taskId) {
        return tasks.get(taskId).getNameWithStatus();
    }

    /**
     * Gets the save record of a single task to write to the chatbot save file.
     *
     * @param taskId The ID of the task to get the save record for.
     * @return A string containing the task's save record.
     */
    public static String getSingleTaskSaveRecord (int taskId) {
        return tasks.get(taskId).getSaveRecord();
    }

    /**
     * Gets the save records of all tasks to write to the chatbot save file.
     *
     * @return A string containing the save records of all tasks, separated by line breaks.
     */
    public static String getAllTaskSaveRecords() {
        String details = "";
        for (int i = 0; i < tasks.size(); i++) {
            details += getSingleTaskSaveRecord(i);
            details += System.lineSeparator();
        }
        return details;
    }

    /**
     * Gets the type, description and status (Done or not done) of all tasks.
     *
     * @return A string containing a numbered list of the details of all tasks, separated by line breaks.
     */
    public static String getAllTaskDetails() {
        String details = "";
        for (int i = 0; i < tasks.size(); i++) {
            details += getSingleTaskDetails(i);
            details += System.lineSeparator();
        }
        return details;
    }
}
