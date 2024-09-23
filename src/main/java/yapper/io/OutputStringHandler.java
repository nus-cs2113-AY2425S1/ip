package yapper.io;

import java.util.List;

import yapper.tasks.Task;

// Output Text Formatter for Yapper
public class OutputStringHandler {
    // For Instruction: List
    public static void printTasks(List<Task> tasks, int taskCount) {
        System.out.println(StringStorage.LINE_DIVIDER_OUTPUT);
        System.out.println(StringStorage.LIST_ALL_TASKS_STRING);
        for (int i = 0; i < taskCount; i++) {
            System.out.print( (i + 1) + "." ); // task list is displayed 1-indexed
            System.out.println( tasks.get(i).taskToDisplay() );
        }
        System.out.println(StringStorage.LINE_DIVIDER_INPUT);
    }
    // For Instruction: Todo Deadline Event
    public static void printAddedTask(Task task, int taskCount) {
        StringStorage.printWithDividers(
                StringStorage.TASK_ADDED_STRING + "\n"
                + "  " + task.taskToDisplay() + "\n" // spacing is to be consistent with ordinal
                + StringStorage.LIST_SIZE_STRING + taskCount);
    }
    // For Instruction: Delete
    public static void printDeletedTask(Task task, int taskCount) {
        StringStorage.printWithDividers(
                StringStorage.TASK_DELETED_STRING + "\n"
                + "  " + task.taskToDisplay() + "\n"
                + StringStorage.LIST_SIZE_STRING + taskCount);
    }
    // For Instruction: Mark, Unmark
    public static void printTaskStatus(Task task, boolean isDone) {
        String status = (isDone ? "done" : "undone");
        System.out.println(
                "This task is now " + status + ": \n" + "  " + task.taskToDisplay());
    }
}
