import java.util.Scanner;

public class lovespiritual {
    public static final String SEPARATOR = "_".repeat(30);
    public static final int MAX_TASKS = 100;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] tasks = new String[MAX_TASKS]; // array of tasks
        boolean[] isMarked = new boolean[MAX_TASKS]; // check if task is marked
        String[] taskTypes = new String[MAX_TASKS]; // task category
        int taskCount = 0; // count the number of tasks added in the array

        printWelcomeScreen();

        // loop that keeps recurring when the program is running
        while (true) {
            String input = in.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                printExitScreen();
                break;
            } else if (input.equalsIgnoreCase("list")) {
                printList(taskCount, isMarked, taskTypes, tasks);
            } else if (input.startsWith("mark ")) {
                markTask(input, taskCount, isMarked, tasks);
            } else if (input.startsWith("unmark ")) {
                unmarkTask(input, taskCount, isMarked, tasks);
            } else if (input.startsWith("todo")){
                taskCount = todo(input, tasks, taskCount, taskTypes);
            } else if (input.startsWith("deadline ")){
                taskCount = deadline(input, taskTypes, taskCount, tasks);
            } else if (input.startsWith("event ")) {
                taskCount = event(input, tasks, taskCount, taskTypes);
            } else {
                taskCount = addTask(tasks, taskCount, input, taskTypes);
            }
        }
    }

    private static int event(String input, String[] tasks, int taskCount, String[] taskTypes) {
        String fullTaskDescription = input.substring("event".length()).trim();
        String taskDescription;
        String from;
        String to;
        if (fullTaskDescription.contains("from")) {
            String[] taskDetails = fullTaskDescription.split("from ");
            taskDescription = taskDetails[0].trim();
            if (taskDetails[1].contains("to")) {
                String[] time = taskDetails[1].split("to ");
                from = time[0].trim();
                to = time[1].trim();
            } else {
                from = taskDetails[1].trim();
                to = "null";
            }
        } else {
            taskDescription = fullTaskDescription;
            from = "null";
            to = "null";
        }
        tasks[taskCount] = taskDescription + " (from: " + from + ", to: " + to + ")";
        taskCount++;
        taskTypes[taskCount] = "[E]";
        System.out.println(SEPARATOR);
        System.out.println("Got it. I've added this task:");
        System.out.println(" [E][ ] " + tasks[taskCount - 1]);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(SEPARATOR);
        return taskCount;
    }

    private static int deadline(String input, String[] taskTypes, int taskCount, String[] tasks) {
        String fullTaskDescription = input.substring("deadline".length()).trim();
        String taskDescription;
        String by;
        if (fullTaskDescription.contains("by")) {
            String[] taskDetails = fullTaskDescription.split("by");
            taskDescription = taskDetails[0].trim();
            by = taskDetails[1].trim();
        } else {
            taskDescription = fullTaskDescription;
            by = "null";
        }
        taskTypes[taskCount] = "[D]";
        tasks[taskCount] = taskDescription + " (by: " + by + ")";
        taskCount++;
        System.out.println(SEPARATOR);
        System.out.println("Got it. I've added this task:");
        System.out.println(" [D][ ] " + tasks[taskCount - 1]);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(SEPARATOR);
        return taskCount;
    }

    private static int todo(String input, String[] tasks, int taskCount, String[] taskTypes) {
        String taskDescription = input.substring("todo".length()).trim();
        tasks[taskCount] = taskDescription;
        taskTypes[taskCount] = "[T]";
        taskCount++;
        System.out.println(SEPARATOR);
        System.out.println("Got it. I've added this task:");
        System.out.println(" [T][ ] " + taskDescription);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(SEPARATOR);
        return taskCount;
    }


    private static void unmarkTask(String input, int taskCount, boolean[] isMarked, String[] tasks) {
        String taskNumber = input.substring("unmark".length()).trim();
        int indexNumber = Integer.parseInt(taskNumber) - 1;
        if (indexNumber >= 0 && indexNumber < taskCount) {
            isMarked[indexNumber] = false;
            System.out.println(SEPARATOR);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(" [ ] " + tasks[indexNumber]);
            System.out.println(SEPARATOR);
        } else {
            System.out.println(SEPARATOR);
            System.out.println("Invalid number. Please enter a valid number.");
            System.out.println(SEPARATOR);
        }
    }

    private static void markTask(String input, int taskCount, boolean[] isMarked, String[] tasks) {
        String taskNumber = input.substring("mark".length()).trim();
        int indexNumber = Integer.parseInt(taskNumber) - 1;
        if (indexNumber >= 0 && indexNumber < taskCount) {
            isMarked[indexNumber] = true;
            System.out.println(SEPARATOR);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(" [X] " + tasks[indexNumber]);
            System.out.println(SEPARATOR);
        } else {
            System.out.println(SEPARATOR);
            System.out.println("Invalid number. Please enter a valid number.");
            System.out.println(SEPARATOR);
        }
    }

    private static int addTask(String[] tasks, int taskCount, String input, String[] taskTypes) {
        tasks[taskCount] = input;
        taskTypes[taskCount] = "[ ]";
        taskCount++;
        System.out.println(SEPARATOR);
        System.out.println("added: " + input);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(SEPARATOR);
        return taskCount;
    }

    private static void printList(int taskCount, boolean[] isMarked, String[] taskTypes, String[] tasks) {
        System.out.println(SEPARATOR);
        for (int i = 0; i < taskCount; i++) {
            String checkbox = isMarked[i] ? "[X]" : "[ ]";
            System.out.println((i + 1) + ". " + taskTypes[i] + checkbox + " " + tasks[i]);
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
