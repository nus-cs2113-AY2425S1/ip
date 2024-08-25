public class TaskList {
    private static String[] taskList = new String[100];
    private static int taskCount = 0;

    public static void addTask(String task) {
        taskList[taskCount++] = task;
    }

    public static void printList() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i+1) + ". " + taskList[i]);
        }
    }
}
