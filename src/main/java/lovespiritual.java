import java.util.Scanner;

public class lovespiritual {
    public static final String SEPARATOR = "_".repeat(30);
    public static final int MAX_TASKS = 100;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASKS]; // array of tasks
        boolean[] isMarked = new boolean[MAX_TASKS]; // check if task is marked
        String[] taskTypes = new String[MAX_TASKS]; // task category
        int taskCount = 0; // count the number of tasks added in the array

        printWelcomeScreen();

        // loop that keeps recurring when the program is running
        while (true) {
            String input = in.nextLine().trim();

            try {
                if (input.equalsIgnoreCase("bye")) {
                    printExitScreen();
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    printList(taskCount, isMarked, taskTypes, tasks);
                } else if (input.startsWith("mark")) {
                    markTask(input, taskCount, isMarked, tasks);
                } else if (input.startsWith("unmark")) {
                    unmarkTask(input, taskCount, isMarked, tasks);
                } else if (input.startsWith("todo")) {
                    taskCount = todo(input, tasks, taskCount);
                } else if (input.startsWith("deadline")) {
                    taskCount = deadline(input, taskCount, tasks);
                } else if (input.startsWith("event")) {
                    taskCount = event(input, tasks, taskCount);
                } else {
                    throw new lovespiritualException("Start with a command");
                }
            } catch (lovespiritualException e) {
                System.out.println(SEPARATOR);
                System.out.println("OOPS!!! " + e.getMessage());
                System.out.println(SEPARATOR);
            } catch (Exception e) {
                System.out.println(SEPARATOR);
                System.out.println("OOPS!!! Something went wrong.");
                System.out.println(SEPARATOR);
            }
        }
    }

    private static int event(String input, Task[] tasks, int taskCount) throws lovespiritualException {
        String fullTaskDescription = input.substring("event".length()).trim();
        if (fullTaskDescription.isEmpty()) {
            throw new lovespiritualException("Event description is empty!");
        }
        if (!fullTaskDescription.contains("from")) {
            throw new lovespiritualException("Event description must contain 'from' keyword");
        }
        if (!fullTaskDescription.contains("to")) {
            throw new lovespiritualException("Event description must contain 'to' keyword");
        }
        String taskDescription;
        String from;
        String to;
        String[] taskDetails = fullTaskDescription.split("from ");
        if (taskDetails[0].trim().isEmpty()) {
            throw new lovespiritualException("Event description is empty");
        }
        taskDescription = taskDetails[0].trim();
        String[] time = taskDetails[1].split("to ");
        if (time.length < 2 || time[0].trim().isEmpty()) {
            throw new lovespiritualException("Start time is empty");
        }
        if (time[1].trim().isEmpty()) {
            throw new lovespiritualException("End time is empty");
        }
        from = time[0].trim();
        to = time[1].trim();
        tasks[taskCount] = new Event(taskDescription, from, to);
        taskCount++;
        System.out.println(SEPARATOR);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount - 1]);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(SEPARATOR);
        return taskCount;
    }

    private static int deadline(String input, int taskCount, Task[] tasks) throws lovespiritualException {
        String fullTaskDescription = input.substring("deadline".length()).trim();
        if (fullTaskDescription.isEmpty()) {
            throw new lovespiritualException("Deadline description is empty");
        }
        if (!fullTaskDescription.contains("by")) {
            throw new lovespiritualException("Deadline description must contain 'by' keyword");
        }
        String taskDescription;
        String by;
        String[] taskDetails = fullTaskDescription.split("by", 2);
        if (taskDetails.length < 2 || taskDetails[0].trim().isEmpty()) {
            throw new lovespiritualException("Deadline description is empty");
        }
        if (taskDetails[1].trim().isEmpty()) {
            throw new lovespiritualException("Due date/time is empty");
        }
        taskDescription = taskDetails[0].trim();
        by = taskDetails[1].trim();
        tasks[taskCount] = new Deadline(taskDescription, by);
        taskCount++;
        System.out.println(SEPARATOR);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount - 1]);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(SEPARATOR);
        return taskCount;
    }

    private static int todo(String input, Task[] tasks, int taskCount) throws lovespiritualException {
        String taskDescription = input.substring("todo".length()).trim();
        tasks[taskCount] = new Todo(taskDescription);
        taskCount++;
        System.out.println(SEPARATOR);
        System.out.println("Got it. I've added this task:");
        System.out.println(" [T][ ] " + taskDescription);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(SEPARATOR);
        return taskCount;
    }

    private static void unmarkTask(String input, int taskCount, boolean[] isMarked, Task[] tasks) throws lovespiritualException {
        String taskNumber = input.substring("unmark".length()).trim();
        int indexNumber = Integer.parseInt(taskNumber) - 1;
        if (indexNumber >= 0 && indexNumber < taskCount) {
            tasks[indexNumber].unmark();
            System.out.println(SEPARATOR);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks[indexNumber]);
            System.out.println(SEPARATOR);
        } else {
            System.out.println(SEPARATOR);
            System.out.println("Invalid number. Please enter a valid number.");
            System.out.println(SEPARATOR);
        }
    }

    private static void markTask(String input, int taskCount, boolean[] isMarked, Task[] tasks) throws lovespiritualException {
        String taskNumber = input.substring("mark".length()).trim();
        int indexNumber = Integer.parseInt(taskNumber) - 1;
        if (indexNumber >= 0 && indexNumber < taskCount) {
            tasks[indexNumber].mark();
            System.out.println(SEPARATOR);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks[indexNumber]);
            System.out.println(SEPARATOR);
        } else {
            System.out.println(SEPARATOR);
            System.out.println("Invalid number. Please enter a valid number.");
            System.out.println(SEPARATOR);
        }
    }

    private static int addTask(Task[] tasks, int taskCount, Task input, String[] taskTypes) {
        tasks[taskCount] = input;
        taskTypes[taskCount] = "[ ]";
        taskCount++;
        System.out.println(SEPARATOR);
        System.out.println("added: " + input);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(SEPARATOR);
        return taskCount;
    }

    private static void printList(int taskCount, boolean[] isMarked, String[] taskTypes, Task[] tasks) {
        System.out.println(SEPARATOR);
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println(SEPARATOR);
    }

    private static void printExitScreen() {
        System.out.println(SEPARATOR);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(SEPARATOR);
    }

    private static void printWelcomeScreen() {
        System.out.println(SEPARATOR);
        System.out.println("Hello! I'm lovespiritual");
        System.out.println("What can I do for you?");
        System.out.println(SEPARATOR);
    }
}
