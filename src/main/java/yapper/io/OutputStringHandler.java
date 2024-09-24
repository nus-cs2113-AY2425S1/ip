package yapper.io;

import java.util.List;

import yapper.tasks.Task;
import yapper.tasks.TaskHandler;

// Output Text Formatter for Yapper
public class OutputStringHandler {

    // Used in Add (Todo, Deadline, Event), Delete, Mark, Unmark
    private static String displayTaskWithoutOrdinal(Task task) {
        return "  " + task.taskToDisplay(); // spacing is to be consistent with ordinal
    }
    // Used in Find and List
    private static String displayTaskWithOrdinal(Task task, int taskOrdinal) {
        return (taskOrdinal + StringStorage.INDEX_OFFSET) + "." + task.taskToDisplay();
    }

    // For Instruction: Find
    public static void printTasksMatchQuery(TaskHandler taskHandler, String query) {
        System.out.println(StringStorage.LIST_RELEVANT_TASKS_STRING);

        List<Task> tasks = taskHandler.getAllTasks();
        int totalMatching = 0;
        for (Task task : tasks) {
            if ( task.getDesc().contains(query) ) {
                int ordinal = taskHandler.getOrdinalOf(task);
                System.out.println( displayTaskWithOrdinal(task, ordinal);
                totalMatching++;
            }
        }

        if (totalMatching > 0) {
            System.out.println(
                    totalMatching + StringStorage.RELEVANT_TASKS_FOUND_STRING);
        } else {
            System.out.println(StringStorage.RELEVANT_TASKS_NOT_FOUND_STRING);
        }
    }
    // For Instruction: List
    public static void printAllTasks(TaskHandler taskHandler) {
        System.out.println(StringStorage.LIST_BEFORE_STRING);

        int taskTotal = taskHandler.getCurrTaskTotal();
        for (int ordinal = 0; ordinal < taskTotal; ordinal++) {
            Task task = taskHandler.getTaskAtOrdinal(ordinal);
            System.out.println( displayTaskWithOrdinal(task, ordinal);
        }

        System.out.println(StringStorage.LIST_AFTER_STRING);
    }

    // For Instruction: Todo Deadline Event
    public static void printAddedTask(Task task, int taskTotal) {
        System.out.println(StringStorage.ADD_BEFORE_STRING);

        System.out.println( displayTaskWithoutOrdinal(task) );
        System.out.println(StringStorage.LIST_SIZE_STRING + taskTotal);

        System.out.println(StringStorage.ADD_AFTER_STRING);
    }
    // For Instruction: Delete
    public static void printDeletedTask(Task task, int taskTotal) {
        System.out.println(StringStorage.DELETE_BEFORE_STRING);

        System.out.println( displayTaskWithoutOrdinal(task) );
        System.out.println(StringStorage.LIST_SIZE_STRING + taskTotal);

        System.out.println(StringStorage.DELETE_AFTER_STRING);
    }
    // For Instruction: Mark, Unmark
    public static void printTaskStatus(Task task, boolean isDone) {
        System.out.println(StringStorage.TASK_COMPLETION_STATUS_CHANGED_STRING +
                (isDone ? "done" : "not done") );
        System.out.println( displayTaskWithoutOrdinal(task) );
        System.out.println( isDone ?
                StringStorage.TASK_IS_DONE_STRING : StringStorage.TASK_IS_NOT_DONE_STRING);
    }
}
