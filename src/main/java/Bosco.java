import java.util.Scanner;

public class Bosco {
    private static final String DIVIDER = "\t____________________________________________________________";
    private static final int MAX_TASKS = 100;
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

    private static void printWelcomeMessage() {
        System.out.println(DIVIDER);
        System.out.println("\t Hello! I'm Bosco APD." + System.lineSeparator() + "\t What can I do for you?");
        System.out.println(DIVIDER);
    }

    private static void printExitMessage() {
        System.out.println(DIVIDER);
        System.out.println("\t Bye! Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    private static String getUserInput() {
        String userInputString = SCANNER.nextLine();
        // Ignore blank lines
        while (userInputString.trim().isEmpty()) {
            userInputString = SCANNER.nextLine();
        }
        return userInputString;
    }

    private static String[] splitCommandTypeAndArgs(String userInputString) {
        String[] stringParts = userInputString.split("\\s+", 2);
        return stringParts.length == 2 ? stringParts : new String[] {stringParts[0], ""};
    }

    public static void main(String[] args) {
        String[] words;
        String description;
        Task selectedTask;

        printWelcomeMessage();

        while (true) {
            String userInputString = getUserInput();
            String[] commandTypeAndArgs = splitCommandTypeAndArgs(userInputString);
            String commandType = commandTypeAndArgs[0];
            String commandArgs = commandTypeAndArgs[1];
            switch (commandType) {
            case "list":
                System.out.println(DIVIDER);
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("\t " + (i + 1) + "." + tasks[i]);
                }
                System.out.println(DIVIDER);
                break;
            case "mark":
                selectedTask = tasks[Integer.parseInt(commandArgs) - 1];
                selectedTask.markAsDone();
                System.out.println(DIVIDER);
                System.out.println("\t Nice! I've marked this task as done:");
                System.out.println("\t   " + selectedTask);
                System.out.println(DIVIDER);
                break;
            case "unmark":
                selectedTask = tasks[Integer.parseInt(commandArgs) - 1];
                selectedTask.markAsNotDone();
                System.out.println(DIVIDER);
                System.out.println("\t OK, I've marked this task as not done yet:");
                System.out.println("\t   " + selectedTask);
                System.out.println(DIVIDER);
                break;
            case "todo":
                Todo newTodo = new Todo(commandArgs);
                tasks[taskCount] = newTodo;
                taskCount++;
                System.out.println(DIVIDER);
                System.out.println("\t Got it. I've added this task:");
                System.out.println("\t   " + newTodo);
                System.out.println("\t Now you have " + taskCount + " tasks in the list.");
                System.out.println(DIVIDER);
                break;
            case "deadline":
                int indexOfByPrefix = commandArgs.indexOf("/by");
                description = commandArgs.substring(0, indexOfByPrefix).strip();
                String by = commandArgs.substring(indexOfByPrefix + 3).strip();
                Deadline newDeadline = new Deadline(description, by);
                tasks[taskCount] = newDeadline;
                taskCount++;
                System.out.println(DIVIDER);
                System.out.println("\t Got it. I've added this task:");
                System.out.println("\t   " + newDeadline);
                System.out.println("\t Now you have " + taskCount + " tasks in the list.");
                System.out.println(DIVIDER);
                break;
            case "event":
                int indexOfFromPrefix = commandArgs.indexOf("/from");
                int indexOfToPrefix = commandArgs.indexOf("/to");
                description = commandArgs.substring(0, indexOfFromPrefix).strip();
                String from = commandArgs.substring(indexOfFromPrefix+5, indexOfToPrefix).strip();
                String to = commandArgs.substring(indexOfToPrefix+3).strip();
                Event newEvent = new Event(description, from, to);
                tasks[taskCount] = newEvent;
                taskCount++;
                System.out.println(DIVIDER);
                System.out.println("\t Got it. I've added this task:");
                System.out.println("\t   " + newEvent);
                System.out.println("\t Now you have " + taskCount + " tasks in the list.");
                System.out.println(DIVIDER);
                break;
            case "bye":
                printExitMessage();
                System.exit(0);
            default:
                tasks[taskCount] = new Task(userInputString);
                taskCount++;
                System.out.println(DIVIDER);
                System.out.println("\t added: " + userInputString);
                System.out.println(DIVIDER);
                break;
            }
        }
    }
}
