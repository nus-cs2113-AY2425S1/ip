public class TaskList {
    private static Task[] taskList = new Task[100];
    private static int taskCount = 0;

    public static void addTask(Task task) {
        taskList[taskCount++] = task;
    }

    public static boolean checkValidTaskId(int taskId) {
        return taskId >= 0 && taskId < taskCount;
    }

    public static void markTaskAsDone (int taskId) {
        taskList[taskId].markAsDone();
    }

    public static void markTaskAsUndone (int taskId) {
        taskList[taskId].markAsUndone();
    }

    public static String singleTaskDetails (int taskId) {
        return taskList[taskId].taskNameWithStatus();
    }

    public static void printList() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i+1) + ". " + taskList[i].taskNameWithStatus());
        }
    }
}
