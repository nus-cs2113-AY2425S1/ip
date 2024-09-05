import java.util.Scanner;

public class Bob {

    public static final String SEPARATOR = "____________________________________________________________";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String DEADLINE_BY = " /by ";
    private static final String EVENT_FROM = " /from ";
    private static final String EVENT_TO = " /to ";
    private static final int MAX_TASKS = 100;

    public static void main(String[] args) {

        String logo = "  ____        _\n"
                + " | |_) \\ ___ | |___\n"
                + " |  _ //  _  \\   _ \\\n"
                + " | |_)\\\\ (_) /  |_) |\n"
                + " |____/ \\___/|_|___/\n";

        System.out.println(logo);
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASKS];
        int taskCount = 0;

        while (true) {
            String input = scanner.nextLine();
            
            if (input.equals(COMMAND_BYE)) {
                exit();
                break;
            } else if (input.equals(COMMAND_LIST)) {
                printList(tasks, taskCount);
            } else if (input.startsWith(COMMAND_MARK)) {
                String[] inputsInString = input.split(" ");
                int taskIndex = Integer.parseInt(inputsInString[1]) - 1;
                tasks[taskIndex].markAsDone();
                System.out.println(SEPARATOR);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks[taskIndex]);
                System.out.println(SEPARATOR);
            } else if (input.startsWith(COMMAND_UNMARK)) {
                String[] inputsInString = input.split(" ");
                int taskIndex = Integer.parseInt(inputsInString[1]) - 1;
                tasks[taskIndex].unmark();
                System.out.println(SEPARATOR);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks[taskIndex]);
                System.out.println(SEPARATOR);
            } else if (input.startsWith(COMMAND_TODO + " ")) {
                String description = input.substring(COMMAND_TODO.length() + 1);
                tasks[taskCount] = new ToDo(description);
                taskCount++;
                printAddedTask(tasks, taskCount) ;
            } else if (input.startsWith(COMMAND_DEADLINE + " ")) {
                String[] components = input.split(DEADLINE_BY);
                String description = components[0].substring(COMMAND_DEADLINE.length() + 1);
                String by = components[1];
                tasks[taskCount] = new Deadline(description, by);
                taskCount++;
                printAddedTask(tasks, taskCount);
            } else if (input.startsWith(COMMAND_EVENT + " ")) {
                int fromIndex = input.indexOf(EVENT_FROM);
                int toIndex = input.indexOf(EVENT_TO);
                String description = input.substring(COMMAND_EVENT.length() + 1, fromIndex);
                String from = input.substring(fromIndex + EVENT_FROM.length(), toIndex);
                String to = input.substring(toIndex + EVENT_TO.length());
                tasks[taskCount] = new Event(description,from, to);
                taskCount++;
                printAddedTask(tasks, taskCount);
            } else {
                printWarning();
            }
        }
        scanner.close();
    }

    public static void exit() {
        System.out.println(SEPARATOR);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(SEPARATOR);
    }

    public static void printList(Task[] tasks, int taskCount) {
        System.out.println(SEPARATOR);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
        System.out.println(SEPARATOR);
    }

    public static void printAddedTask (Task[] tasks, int taskCount) {
        System.out.println(SEPARATOR);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks[taskCount - 1]);
        System.out.println("Now you have " + taskCount + " " + (taskCount == 1 ? "task" : "tasks") + " in the list.");
        System.out.println(SEPARATOR);
    }

    public static void printWarning() {
        System.out.println(SEPARATOR);
        System.out.println("Sorry, the input is invalid. I don't understand what you mean.");
        System.out.println(SEPARATOR);
    }
}