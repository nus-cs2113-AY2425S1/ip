// Output Text Formatter for Yapper
public class OutputStringHandler {
    // For Command: Add
    public static void printAddedTask(int taskCount, Task task) {
        System.out.println(CommonStrings.LINE_DIVIDER);
        System.out.println("Now, ya gotta do this too:");
        System.out.println( "  " + task.taskToString() );
        System.out.println("Your list is now THIS BIG: " + taskCount);
        System.out.println(CommonStrings.LINE_DIVIDER);
    }
    // For Command: Todo
    public static void printAddedTask(int taskCount, TaskTodo todo) {
        System.out.println(CommonStrings.LINE_DIVIDER);
        System.out.println("Now, ya gotta do this too:");
        System.out.println( todo.taskToString() );
        System.out.println("Your list is now THIS BIG: " + taskCount);
        System.out.println(CommonStrings.LINE_DIVIDER);
    }
    // For Command: Deadline
    public static void printAddedTask(int taskCount, TaskDeadline taskDeadline) {
        System.out.println(CommonStrings.LINE_DIVIDER);
        System.out.println("Now, ya gotta do this too:");
        System.out.println( taskDeadline.taskToString() );
        System.out.println("Your list is now THIS BIG: " + taskCount);
        System.out.println(CommonStrings.LINE_DIVIDER);
    }
    // For Command: Event
    public static void printAddedTask(int taskCount, TaskEvent taskEvent) {
        System.out.println(CommonStrings.LINE_DIVIDER);
        System.out.println("Now, ya gotta do this too:");
        System.out.println( taskEvent.taskToString() );
        System.out.println("Your list is now THIS BIG: " + taskCount);
        System.out.println(CommonStrings.LINE_DIVIDER);
    }
    // For Command: List
    public static void printTasks(Task[] tasks, int taskCount) {
        System.out.println(CommonStrings.LINE_DIVIDER);
        System.out.println("Da tasks in yer list are:");
        for (int i = 0; i < taskCount; i++) {
            System.out.print( (i + 1) + "." ); // task list is displayed 1-indexed
            System.out.println( tasks[i].taskToString() );
        }
        System.out.println(CommonStrings.LINE_DIVIDER);
    }
    // For Command: Mark, Unmark
    public static void printTaskStatus(Task task, boolean isDone) {
        String status = (isDone ? "done" : "undone");
        System.out.println(CommonStrings.LINE_DIVIDER);
        System.out.println("This task is now " + status + ":\n" + "  " + task);
        System.out.println(CommonStrings.LINE_DIVIDER);
    }
}
