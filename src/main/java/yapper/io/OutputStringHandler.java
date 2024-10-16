package yapper.io;

import yapper.tasks.Task;
import yapper.tasks.TaskHandler;

/**
 * Output String Formatter for Yapper.
 *
 * <p>
 * This class handles the formatting and displaying of output messages
 * related to task management, including listing tasks, adding tasks,
 * deleting tasks, and updating task statuses.
 * </p>
 *
 */
public class OutputStringHandler {


    /**
     * Formats a task for display without an ordinal number.
     * Used for displaying a task when the task's ordinal position is not needed
     * like in Add (Todo, Deadline, Event), Delete, Mark, Unmark
     *
     * @param task the task to display
     * @return the formatted string representation of the task
     */
    private static String displayTaskWithoutOrdinal(Task task) {
        return "  " + task.taskToDisplay(); // spacing is to be consistent with ordinal
    }
    /**
     * Formats a task for display with an ordinal number.
     * Used for listing tasks with their ordinal positions, like in Find or List.
     *
     * @param task the task to display
     * @param taskOrdinal the ordinal number of the task
     * @return the formatted string representation of the task with its ordinal number
     */
    private static String displayTaskWithOrdinal(Task task, int taskOrdinal) {
        return (taskOrdinal + StringStorage.INDEX_OFFSET) + "." + task.taskToDisplay();
    }


    /**
     * Prints the list of tasks that match the query string.
     * Used in the "find" instruction to filter and display tasks by description.
     *
     * @param taskHandler the handler managing the task list
     * @param query the string to search for in task descriptions
     */
    public static void printSelectedTasks(TaskHandler taskHandler, String query) {
        System.out.println(StringStorage.LIST_RELEVANT_TASKS_STRING);

        int totalTasksThatContainsQuery = 0;
        for (Task task : taskHandler.getAllTasks()) {
            if (task.getDesc().contains(query)) {
                int ordinal = taskHandler.getOrdinalOf(task);
                System.out.println(displayTaskWithOrdinal(task, ordinal));
                totalTasksThatContainsQuery++;
            }
        }

        if (totalTasksThatContainsQuery > 0) {
            System.out.println(
                    totalTasksThatContainsQuery + StringStorage.RELEVANT_TASKS_FOUND_STRING);
        } else {
            System.out.println(StringStorage.RELEVANT_TASKS_NOT_FOUND_STRING);
        }
    }
    /**
     * Prints a message listing all tasks in the list.
     * Used to display the current list of tasks in order.
     *
     * @param taskHandler the handler managing the task list
     */
    public static void printAllTasks(TaskHandler taskHandler) {
        System.out.println(StringStorage.LIST_BEFORE_STRING);

        int taskTotal = taskHandler.getCurrTaskTotal();
        for (int ordinal = 0; ordinal < taskTotal; ordinal++) {
            Task task = taskHandler.getTaskAtOrdinal(ordinal);
            System.out.println(displayTaskWithOrdinal(task, ordinal));
        }

        System.out.println(StringStorage.LIST_AFTER_STRING);
    }

    /**
     * Prints a message indicating a task has been added.
     * Displays the task details and the updated total number of tasks.
     *
     * @param task      the task that has been added
     * @param taskTotal the current count of tasks in the list
     */
    public static void printAddedTask(Task task, int taskTotal) {
        System.out.println(StringStorage.ADD_BEFORE_STRING);

        System.out.println(displayTaskWithoutOrdinal(task));
        System.out.println(StringStorage.LIST_SIZE_STRING + taskTotal);

        System.out.println(StringStorage.ADD_AFTER_STRING);
    }

    /**
     * Prints a message indicating a task has been deleted.
     * Displays the task details and the updated total number of tasks.
     *
     * @param task      the task that has been deleted
     * @param taskTotal the current count of tasks in the list
     */
    public static void printDeletedTask(Task task, int taskTotal) {
        System.out.println(StringStorage.DELETE_BEFORE_STRING);

        System.out.println(displayTaskWithoutOrdinal(task));
        System.out.println(StringStorage.LIST_SIZE_STRING + taskTotal);

        System.out.println(StringStorage.DELETE_AFTER_STRING);
    }

    /**
     * Prints the status of a task after it has been marked or unmarked.
     * Displays whether the task is marked as "done" or "not done"
     *
     * @param task   the task whose status has changed
     * @param isDone true if the task is marked as done, false if undone
     */
    public static void printTaskStatus(Task task, boolean isDone) {
        System.out.println(StringStorage.TASK_COMPLETION_STATUS_CHANGED_STRING
                + (isDone ? "done" : "not done"));
        System.out.println(displayTaskWithoutOrdinal(task));
        System.out.println(isDone
                ? StringStorage.TASK_IS_DONE_STRING : StringStorage.TASK_IS_NOT_DONE_STRING);
    }
}
