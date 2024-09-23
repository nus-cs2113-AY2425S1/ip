package yapper.io;

import java.util.List;

import yapper.tasks.Task;

/**
 * Output String Formatter for Yapper.
 *
 * This class handles the formatting and displaying of output messages
 * related to task management, including listing tasks, adding tasks,
 * deleting tasks, and updating task statuses.
 */
public class OutputStringHandler {

    /**
     * Prints a message listing all tasks in the list.
     *
     * @param tasks     the list of tasks to display
     * @param taskCount the number of tasks in the list
     */
    public static void printTasks(List<Task> tasks, int taskCount) {
        System.out.println(StringStorage.LIST_ALL_TASKS_STRING);
        for (int i = 0; i < taskCount; i++) {
            System.out.print((i + 1) + "."); // task list is displayed 1-indexed
            System.out.println(tasks.get(i).taskToDisplay());
        }
    }

    /**
     * Prints a message indicating a task has been added.
     *
     * @param task      the task that has been added
     * @param taskCount the current count of tasks in the list
     */
    public static void printAddedTask(Task task, int taskCount) {
        System.out.println(
                StringStorage.TASK_ADDED_STRING + "\n"
                + "  " + task.taskToDisplay() + "\n" // spacing is to be consistent with ordinal
                + StringStorage.LIST_SIZE_STRING + taskCount);
    }

    /**
     * Prints a message indicating a task has been deleted.
     *
     * @param task      the task that has been deleted
     * @param taskCount the current count of tasks in the list
     */
    public static void printDeletedTask(Task task, int taskCount) {
        System.out.println(
                StringStorage.TASK_DELETED_STRING + "\n"
                + "  " + task.taskToDisplay() + "\n"
                + StringStorage.LIST_SIZE_STRING + taskCount);
    }

    /**
     * Prints the status of a task after it has been marked or unmarked.
     *
     * @param task   the task whose status has changed
     * @param isDone true if the task is marked as done, false if undone
     */
    public static void printTaskStatus(Task task, boolean isDone) {
        String status = (isDone ? "done" : "undone");
        System.out.println(
                "This task is now " + status + ": \n" + "  " + task.taskToDisplay());
    }
}
