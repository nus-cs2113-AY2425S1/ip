package tyrone.task;

import java.util.ArrayList;

/**
 * An abstract class representing a list of tasks.
 * This class provides  methods to manage a collection of Task objects.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a new task to the list.
     *
     * @param task The Task object to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns current number of tasks in task list.
     *
     * @return Number of tasks in task list.
     */
    public int getNumOfTasks() {
        return tasks.size();
    }

    /**
     * Checks if the given task ID is valid.
     * Task ID is invalid if it is not a positive number or exceeds current number of tasks.
     *
     * @param taskId The given task ID to check.
     * @return true if the taskId is valid, false otherwise.
     */
    public boolean isValidTaskId(int taskId) {
        return taskId >= 0 && taskId < tasks.size();
    }

    /**
     * Marks a task as done.
     *
     * @param taskId The ID of the task to mark as done.
     */
    public void markTaskAsDone (int taskId) {
        tasks.get(taskId).markAsDone();
    }

    /**
     * Marks a task as undone.
     *
     * @param taskId The ID of the task to mark as undone.
     */
    public void markTaskAsUndone (int taskId) {
        tasks.get(taskId).markAsUndone();
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskId The ID of the task to delete.
     */
    public void deleteTask (int taskId) {
        tasks.remove(taskId);
    }

    /**
     * Gets the type, description and status (Done or not done) of a single task.
     *
     * @param taskId The ID of the task to get details for.
     * @return A string containing the task's type, description and status (Done or not done).
     */
    public String getSingleTaskDetails (int taskId) {
        return tasks.get(taskId).getNameWithStatus();
    }

    /**
     * Gets the save record of a single task to write to the chatbot save file.
     *
     * @param taskId The ID of the task to get the save record for.
     * @return A string containing the task's save record.
     */
    public String getSingleTaskSaveRecord (int taskId) {
        return tasks.get(taskId).getSaveRecord();
    }

    /**
     * Gets the save records of all tasks to write to the chatbot save file.
     *
     * @return A string containing the save records of all tasks, separated by line breaks.
     */
    public String getAllTaskSaveRecords() {
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
    public String getAllTaskDetails() {
        String details = "";
        for (int i = 0; i < tasks.size(); i++) {
            details += (i+1) + ". " + getSingleTaskDetails(i);
            details += System.lineSeparator();
        }
        return details;
    }

    public String listTasksWithKeyword (String keyword) {
        String tasksWithKeyword = "";
        int countTasksWithKeyword = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                tasksWithKeyword += (++countTasksWithKeyword) + ". " + getSingleTaskDetails(i);
                tasksWithKeyword += System.lineSeparator();
            }
        }
        return tasksWithKeyword;
    }
}
