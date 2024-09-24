package yapper.io;

import java.util.List;

import yapper.tasks.Task;
import yapper.tasks.TaskHandler;

// Output Text Formatter for Yapper
public class OutputStringHandler {

    // Used in Add (Todo, Deadline, Event), Delete, Mark, Unmark
    private static String printTaskWithoutOrdinal(Task task) {
        return "  " + task.taskToDisplay(); // spacing is to be consistent with ordinal
    }
    // Used in Find and List
    private static String printTaskWithOrdinal(Task task, int taskOrdinal) {
        return (taskOrdinal + StringStorage.INDEX_OFFSET) + "." + task.taskToDisplay();
    }

    // For Instruction: Find
    public static void printTasksMatchQuery(TaskHandler taskHandler, String query) {
        System.out.println(StringStorage.LIST_RELEVANT_TASKS_STRING);

        List<Task> tasks = taskHandler.getAllTasks();
        int totalMatching = 0;
        for (Task task : tasks) {
            if ( task.getDesc().contains(query) ) {
                printTaskWithOrdinal( task, taskHandler.getTaskOrdinal(task) );
                totalMatching++;
            }
        }

        if (totalMatching > 0) {
            System.out.println(
                    "I can recall " + totalMatching + " tasks that may be relevant. " +
                    "Is any of them what you were looking for? ");
        } else {
            System.out.println(StringStorage.RELEVANT_TASKS_NOT_FOUND_STRING);
        }
    }
    // For Instruction: List
    public static void printAllTasks(TaskHandler taskHandler) {
        System.out.println(StringStorage.LIST_ALL_TASKS_STRING);

        List<Task> tasks = taskHandler.getAllTasks();
        for (Task task : tasks) {
            printTaskWithOrdinal( task, taskHandler.getTaskOrdinal(task) );
        }
    }

    // For Instruction: Todo Deadline Event
    public static void printAddedTask(Task task, int taskTotal) {
        System.out.println(
                StringStorage.TASK_ADDED_STRING + "\n"
                + printTaskWithoutOrdinal(task) + "\n"
                + StringStorage.LIST_SIZE_STRING + taskTotal);
    }
    // For Instruction: Delete
    public static void printDeletedTask(Task task, int taskTotal) {
        System.out.println(
                StringStorage.TASK_DELETED_STRING + "\n"
                + printTaskWithoutOrdinal(task) + "\n"
                + StringStorage.LIST_SIZE_STRING + taskTotal);
    }
    // For Instruction: Mark, Unmark
    public static void printTaskStatus(Task task, boolean isDone) {
        String status = (isDone ? "done" : "undone");
        System.out.println(
                "This task is now " + status + ": \n" + printTaskWithoutOrdinal(task) );
    }
}
