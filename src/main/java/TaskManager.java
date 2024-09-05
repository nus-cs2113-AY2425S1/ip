public class TaskManager {
    public static final int MAX_TASK = 100;
    private static Task[] tasks;
    private static int taskCount;
    private static final String LINE_SEPARATOR = "____________________________________________________________\n";

    public TaskManager() {
        tasks = new Task[MAX_TASK];
        taskCount = 0;
    }

    public void addTask(String input) throws SleepyException{
        int taskLength = tasks.length;
        if (taskCount >= taskLength) {
            throw new SleepyException("You're too busy, go get some rest...ZZZ\n");
        }

        if (input.startsWith("event")) {
            String[] parts = input.substring(5).split(" /from ");
            if (parts.length != 2) {
                throw new SleepyException("Invalid event format. Please use: event description /from time /to time\n");
            }

            String description = parts[0].trim();
            String[] toParts = parts[1].split(" /to ");
            if (toParts.length != 2 || toParts[0].trim().isEmpty() || toParts[1].trim().isEmpty()) {
                throw new SleepyException("Invalid event format. Please use: event description /from time /to time\n");
            }

            if (description.isEmpty()) {
                throw new SleepyException("Invalid event format. The event description cannot be empty\n");
            }

            String from = toParts[0].trim();
            String to = toParts[1].trim();
            tasks[taskCount] = new Event(description, from, to);
            taskCount++;
            System.out.println(LINE_SEPARATOR
                    + "Got it. I've added this task:\n"
                    + " " + tasks[taskCount - 1].toString() + "\n"
                    + "Now you have " + taskCount + " tasks in the list.\n"
                    + LINE_SEPARATOR);
            return;
        }

        if (input.startsWith("deadline")) {
            String[] parts = input.substring(8).split(" /by ");
            if (parts.length != 2) {
                throw new SleepyException("Invalid deadline format. Please use: deadline description /by date\n");
            }

            String description = parts[0].trim();
            if (description.isEmpty()) {
                throw new SleepyException("Invalid deadline format. The deadline description cannot be empty. Please use: deadline description /by date\n");

            }

            String by = parts[1].trim();
            tasks[taskCount] = new Deadline(description, by);
            taskCount++;
            System.out.println(LINE_SEPARATOR
                    + "Got it. I've added this task:\n"
                    + " " + tasks[taskCount - 1].toString() + "\n"
                    + "Now you have " + taskCount + " tasks in the list.\n"
                    + LINE_SEPARATOR);
            return;
        }

        if (input.startsWith("todo")) {
            String description = input.substring(4).trim();
            if (description.isEmpty()) {
                throw new SleepyException("Invalid todo format. Todo description cannot be empty\n");
            }
            tasks[taskCount] = new Todo(description);
            taskCount++;
            System.out.println(LINE_SEPARATOR
                    + "Got it. I've added this task:\n"
                    + " " + tasks[taskCount - 1].toString() + "\n"
                    + "Now you have " + taskCount + " tasks in the list.\n"
                    + LINE_SEPARATOR);
            return;
        }
        throw new SleepyException("Unknown command. Please start with 'event', 'deadline', or 'todo'.\n");
    }

    public void listTasks() {
        System.out.println(LINE_SEPARATOR
                + "Stop bothering me, u have unfinished tasks...:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
        System.out.println(LINE_SEPARATOR);
    }

    public void markTask(int taskNumber) throws SleepyException {
        if (taskNumber <= taskCount && taskNumber > 0) {
            tasks[taskNumber - 1].markAsDone();
            System.out.println(LINE_SEPARATOR
                    + "Yay...good for you:\n"
                    + " " + tasks[taskNumber - 1].toString() + "\n"
                    + LINE_SEPARATOR);
        } else {
            throw new SleepyException("you haven't even finished your current tasks but you're do tasks that don't even exist...\n");

        }
    }

    public void unmarkTask(int taskNumber) throws SleepyException {
        if (taskNumber <= taskCount && taskNumber > 0) {
            tasks[taskNumber - 1].markAsNotDone();
            System.out.println(LINE_SEPARATOR
                    + "Could you stop making me do extra work:\n"
                    + " " + tasks[taskNumber - 1].toString() + "\n"
                    + LINE_SEPARATOR);
        } else {
            throw new SleepyException("ummm...the task doesn't even exist...maybe you should get some sleep\n");
        }
    }
}
