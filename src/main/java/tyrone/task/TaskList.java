package tyrone.task;

public class TaskList {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void addTask(Task task) {
        tasks[taskCount++] = task;
    }

    public static boolean isValidTaskId(int taskId) {
        return taskId >= 0 && taskId < taskCount;
    }

    public static void markTaskAsDone (int taskId) {
        tasks[taskId].markAsDone();
    }

    public static void markTaskAsUndone (int taskId) {
        tasks[taskId].markAsUndone();
    }

    public static String getSingleTaskDetails (int taskId) {
        return tasks[taskId].getNameWithStatus();
    }

    public static void printList() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i+1) + ". " + tasks[i].getNameWithStatus());
        }
    }
}
