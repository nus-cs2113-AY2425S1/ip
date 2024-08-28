public class TaskManager {
    private static Task[] tasks;
    private static int taskCount;

    public TaskManager() {
        tasks = new Task[100];
        taskCount = 0;
    }

    public static void addTask(String description) {
        if (taskCount < tasks.length) {
            tasks[taskCount] = new Task(description);
            taskCount ++;
            System.out.println("____________________________________________________________\n"
                    + "added: " + description + "\n"
                    + "____________________________________________________________\n");
        } else {
            System.out.println("____________________________________________________________\n"
                    + "You're too busy, go get some rest...ZZZ\n"
                    + "____________________________________________________________\n");
        }
    }

    public static void listTasks() {
        System.out.println("____________________________________________________________\n"
                + "Stop bothering me, u have unfinished tasks...:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
        System.out.println("____________________________________________________________\n");
    }

    public void markTask(int taskNumber) {
        if (taskNumber <= taskCount && taskNumber > 0) {
            tasks[taskNumber - 1].markAsDone();
            System.out.println("____________________________________________________________\n"
                    + "Yay...good for you:\n"
                    + " " + tasks[taskNumber - 1].toString() + "\n"
                    + "____________________________________________________________\n");
        }
    }

    public void unmarkTask(int taskNumber) {
        if (taskNumber <= taskCount && taskNumber > 0) {
            tasks[taskNumber - 1].markAsNotDone();
            System.out.println("____________________________________________________________\n"
                    + "Could you stop making me do extra work:\n"
                    + " " + tasks[taskNumber - 1].toString() + "\n"
                    + "____________________________________________________________\n");
        }
    }
}
