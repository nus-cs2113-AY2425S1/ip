public class TaskManager {
    public static final int MAX_TASK = 100;
    private static Task[] tasks;
    private static int taskCount;

    public TaskManager() {
        tasks = new Task[MAX_TASK];
        taskCount = 0;
    }

    public void addTask(String input) {
        int taskLength = tasks.length;

        if (taskCount >= taskLength) {
            System.out.println("""
                ____________________________________________________________
                You're too busy, go get some rest...ZZZ
                ____________________________________________________________
                """);
            return;
        }

        if (input.startsWith("event ")) {
            String[] parts = input.substring(6).split(" /from ");
            if (parts.length != 2) {
                System.out.println("""
                    ____________________________________________________________
                    Invalid event format. Please use: event description /from time /to time
                    ____________________________________________________________
                    """);
                return;
            }

            String description = parts[0].trim();
            String[] toParts = parts[1].split(" /to ");
            if (toParts.length != 2) {
                System.out.println("""
                    ____________________________________________________________
                    Invalid event format. Please use: event description /from time /to time
                    ____________________________________________________________
                    """);
                return;
            }

            String from = toParts[0].trim();
            String to = toParts[1].trim();
            tasks[taskCount] = new Event(description, from, to);
            taskCount++;
            System.out.println("____________________________________________________________\n"
                    + "Got it. I've added this task:\n"
                    + " " + tasks[taskCount - 1].toString() + "\n"
                    + "Now you have " + taskCount + " tasks in the list.\n"
                    + "____________________________________________________________\n");
            return;
        }

        if (input.startsWith("deadline ")) {
            String[] parts = input.substring(9).split(" /by ");
            if (parts.length != 2) {
                System.out.println("""
                    ____________________________________________________________
                    Invalid deadline format. Please use: deadline description /by date
                    ____________________________________________________________
                    """);
                return;
            }

            String description = parts[0].trim();
            String by = parts[1].trim();
            tasks[taskCount] = new Deadline(description, by);
            taskCount++;
            System.out.println("____________________________________________________________\n"
                    + "Got it. I've added this task:\n"
                    + " " + tasks[taskCount - 1].toString() + "\n"
                    + "Now you have " + taskCount + " tasks in the list.\n"
                    + "____________________________________________________________\n");
            return;
        }

        if (input.startsWith("todo ")) {
            String description = input.substring(5).trim();
            tasks[taskCount] = new Todo(description);
            taskCount++;
            System.out.println("____________________________________________________________\n"
                    + "Got it. I've added this task:\n"
                    + " " + tasks[taskCount - 1].toString() + "\n"
                    + "Now you have " + taskCount + " tasks in the list.\n"
                    + "____________________________________________________________\n");
            return;
        }

        System.out.println("""
            ____________________________________________________________
            Unknown command. Please start with 'event', 'deadline', or 'todo'.
            ____________________________________________________________
            """);
    }


    public void listTasks() {
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
