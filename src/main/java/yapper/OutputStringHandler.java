package yapper;

import yapper.tasks.Task;

// Output Text Formatter for Yapper
public class OutputStringHandler {
    // For Instruction: List
    public static void printTasks(Task[] tasks, int taskCount) {
        if (taskCount == 0) System.out.println("nothing in list");

        System.out.println(StringStorage.LINE_DIVIDER);
        System.out.println(StringStorage.LIST_ALL_TASKS_STRING);
        for (int i = 0; i < taskCount; i++) {
            System.out.print( (i + 1) + "." ); // task list is displayed 1-indexed
            System.out.println( tasks[i].taskToString() );
        }
        System.out.println(StringStorage.LINE_DIVIDER);
    }
    // For Instruction: Mark, Unmark
    public static void printTaskStatus(Task task, boolean isDone) {
        String status = (isDone ? "done" : "undone");
        System.out.println(StringStorage.LINE_DIVIDER);
        System.out.println("This task is now " + status + ": \n" + "  " + task);
        System.out.println(StringStorage.LINE_DIVIDER);
    }
}
