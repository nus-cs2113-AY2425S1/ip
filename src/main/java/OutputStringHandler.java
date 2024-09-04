// Output Text Formatter for Yapper
public class OutputStringHandler {
    // For Command: List
    public static void printTasks(Task[] tasks, int taskCount) {
        System.out.println(StringStorage.LINE_DIVIDER);
        System.out.println("You don't remember your tasks? Lemme refresh your memory:");
        for (int i = 0; i < taskCount; i++) {
            System.out.print( (i + 1) + "." ); // task list is displayed 1-indexed
            System.out.println( tasks[i].taskToString() );
        }
        System.out.println(StringStorage.LINE_DIVIDER);
    }
    // For Command: Mark, Unmark
    public static void printTaskStatus(Task task, boolean isDone) {
        String status = (isDone ? "done" : "undone");
        System.out.println(StringStorage.LINE_DIVIDER);
        System.out.println("This task is now " + status + ":\n" + "  " + task);
        System.out.println(StringStorage.LINE_DIVIDER);
    }
}
