public class TaskManager {
    private static String[] tasks;
    private static int taskCount;

    public TaskManager() {
        tasks = new String[100];
        taskCount = 0;
    }

    public static void addTask(String task) {
        if (taskCount < tasks.length) {
            tasks[taskCount] = task;
            taskCount ++;
            System.out.println("____________________________________________________________\n"
                    + "added: " + task + "\n"
                    + "____________________________________________________________\n");
        } else {
            System.out.println("____________________________________________________________\n"
                    + "You're too busy, go get some rest...ZZZ\n"
                    + "____________________________________________________________\n");
        }
    }

    public static void listTasks() {
        System.out.println("____________________________________________________________\n");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println("____________________________________________________________\n");
    }
}
