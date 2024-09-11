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
        
        printGreeting();
        
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
                markTask(input, tasks, taskCount);
            } else if (input.startsWith(COMMAND_UNMARK)) {
                unmarkTask(input, tasks, taskCount);
            } else if (input.equals(COMMAND_TODO)) {
                printEmptyDescription("todo");
            } else if (input.startsWith(COMMAND_TODO + " ")) {
                String description = input.substring(COMMAND_TODO.length()).trim();
                if (description.isEmpty()) {
                    printEmptyDescription("todo");
                } else {
                    tasks[taskCount] = new ToDo(description);
                    taskCount++;
                    printAddedTask(tasks, taskCount);
                }
            } else if (input.equals(COMMAND_DEADLINE)) {
                printEmptyDescription("deadline");
            } else if (input.startsWith(COMMAND_DEADLINE + " ")) {
                String[] components = input.split(DEADLINE_BY);
                String description = components[0].substring(COMMAND_DEADLINE.length()).trim();
                if (description.isEmpty()) {
                    printEmptyDescription("deadline");
                } else {
                    try {
                        String by = components[1];
                        tasks[taskCount] = new Deadline(description, by);
                        taskCount++;
                        printAddedTask(tasks, taskCount);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        printSeparator();
                        System.out.println("Sorry! Please provide a valid deadline with '/by <date/time>'.");
                        printSeparator();
                    }
                }
            } else if (input.equals(COMMAND_EVENT)) {
                printEmptyDescription("event");
            } else if (input.startsWith(COMMAND_EVENT + " ")) {
                int fromIndex = input.indexOf(EVENT_FROM);
                int toIndex = input.indexOf(EVENT_TO);
                String userTypedDescription = input.substring(COMMAND_EVENT.length()).trim();
                if (userTypedDescription.isEmpty()) {
                    printEmptyDescription("event");
                } else {
                    try {
                        String description = input.substring(COMMAND_EVENT.length() +1, fromIndex);
                        String from = input.substring(fromIndex + EVENT_FROM.length(), toIndex);
                        String to = input.substring(toIndex + EVENT_TO.length());
                        tasks[taskCount] = new Event(description, from, to);
                        taskCount++;
                        printAddedTask(tasks, taskCount);
                    } catch (StringIndexOutOfBoundsException e) {
                        printSeparator();
                        System.out.println("Sorry! Please provide a valid event with '/from <start date/time> /to <end date/time>'.");
                        printSeparator();
                    }
                }
            } else {
                printInvalidInput();
            }
        }
        scanner.close();
    }

    public static void printGreeting() {
        String logo = "  ____        _\n"
                + " | |_) \\ ___ | |___\n"
                + " |  _ //  _  \\   _ \\\n"
                + " | |_)\\\\ (_) /  |_) |\n"
                + " |____/ \\___/|_|___/\n";

        System.out.println(logo);
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
    }
    
    public static void exit() {
        printSeparator();
        System.out.println("Bye. Hope to see you again soon!");
        printSeparator();
    }
    
    public static void printSeparator() {
        System.out.println(SEPARATOR);
    }

    public static void printList(Task[] tasks, int taskCount) {
        printSeparator();
        if (taskCount == 0) {
            System.out.println("Sorry! Your list is empty.");
            printSeparator();
            return;
        }
        System.out.println("Here " + (taskCount == 1 ? "is" : "are") + " the " + (taskCount == 1 ? "task" : "tasks") + " in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
        printSeparator();
    }

    private static void markTask(String input, Task[] tasks, int taskCount) {
        printSeparator();
        if (taskCount == 0) {
            System.out.println("Sorry! Your list is empty, please add a task before marking as done.");
            printSeparator();
            return;
        }
        try {
            String[] inputsInString = input.split(" ");
            int taskIndex = Integer.parseInt(inputsInString[1]) - 1;
            if (tasks[taskIndex].getStatusIcon().equals("X")) {
                System.out.println("Sorry! This task is already marked as done.");
            } else {
                tasks[taskIndex].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks[taskIndex]);
            }
            printSeparator();
        } catch (NullPointerException e) {
            String[] inputsInString = input.split(" ");
            int inputTaskNumber = Integer.parseInt(inputsInString[1]);
            System.out.println("Sorry! Task " + inputTaskNumber + " is not found in the list.");
            System.out.println("Please input a valid task number from 1 to " + taskCount);
            printSeparator();
        } catch (NumberFormatException e) {
            System.out.println("Sorry! Please input a valid task number from 1 to " + taskCount + " after inputting the word 'mark'.");
            printSeparator();
        }
    }

    private static void unmarkTask(String input, Task[] tasks, int taskCount) {
        printSeparator();
        if (taskCount == 0) {
            System.out.println("Sorry! Your list is empty, please add a task before unmarking.");
            printSeparator();
            return;
        }
        try {
            String[] inputsInString = input.split(" ");
            int taskIndex = Integer.parseInt(inputsInString[1]) - 1;
            if (tasks[taskIndex].getStatusIcon().equals(" ")) {
                System.out.println("Sorry! This task is already unmarked.");
            } else {
                tasks[taskIndex].unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks[taskIndex]);
            }
            printSeparator();
        } catch (NullPointerException e) {
            String[] inputsInString = input.split(" ");
            int inputTaskNumber = Integer.parseInt(inputsInString[1]);
            System.out.println("Sorry! Task " + inputTaskNumber + " is not found in the list.");
            System.out.println("Please input a valid task number from 1 to " + taskCount + ".");
            printSeparator();
        } catch (NumberFormatException e) {
            System.out.println("Sorry! Please input a valid task number from 1 to " + taskCount + " after inputting the word 'unmark'.");
            printSeparator();
        }
    }

    public static void printEmptyDescription(String taskType) {
        printSeparator();
        if (taskType.equals("event")) {
            System.out.println("Sorry! The description of an " + taskType + " cannot be empty.");
        } else {
            System.out.println("Sorry! The description of a " + taskType + " cannot be empty.");
        }
        printSeparator();
    }

    public static void printAddedTask (Task[] tasks, int taskCount) {
        printSeparator();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks[taskCount - 1]);
        System.out.println("Now you have " + taskCount + " " + (taskCount == 1 ? "task" : "tasks") + " in the list.");
        printSeparator();
    }

    public static void printInvalidInput() {
        printSeparator();
        System.out.println("Sorry! I don't understand what you mean.");
        printSeparator();
    }
}