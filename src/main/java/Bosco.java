import java.util.Scanner;

public class Bosco {
    private static final String DIVIDER = "\t____________________________________________________________";
    private static final String INDENT_LARGE = "\t   ";
    private static final String MESSAGE_MARK_DONE = "\t Nice! I've marked this task as done:";
    private static final String MESSAGE_MARK_UNDONE = "\t OK, I've marked this task as not done yet:";
    private static final String MESSAGE_ADDED_TASK = "\t Got it. I've added this task:";

    private static final int MAX_TASKS = 100;
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

    private static void printWelcomeMessage() {
        printMessages("\t Hello! I'm Bosco APD.", "\t What can I do for you?");
    }

    private static void printExitMessage() {
        printMessages("\t Bye! Hope to see you again soon!");
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

    private static void printMessages(String... messages) {
        System.out.println(DIVIDER);
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println(DIVIDER);
    }

    private static String getTaskCountMessage() {
        return String.format("\t Now you have %1$d tasks in the list.", taskCount);
    }

    public static void main(String[] args) {
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
                printMessages(MESSAGE_MARK_DONE, INDENT_LARGE + selectedTask);
                break;
            case "unmark":
                selectedTask = tasks[Integer.parseInt(commandArgs) - 1];
                selectedTask.markAsNotDone();
                printMessages(MESSAGE_MARK_UNDONE, INDENT_LARGE + selectedTask);
                break;
            case "todo":
                Todo newTodo = new Todo(commandArgs);
                tasks[taskCount] = newTodo;
                taskCount++;
                printMessages(MESSAGE_ADDED_TASK, INDENT_LARGE + newTodo, getTaskCountMessage());
                break;
            case "deadline":
                int indexOfByPrefix = commandArgs.indexOf("/by");
                description = commandArgs.substring(0, indexOfByPrefix).strip();
                String by = commandArgs.substring(indexOfByPrefix + 3).strip();
                Deadline newDeadline = new Deadline(description, by);
                tasks[taskCount] = newDeadline;
                taskCount++;
                printMessages(MESSAGE_ADDED_TASK, INDENT_LARGE + newDeadline, getTaskCountMessage());
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
                printMessages(MESSAGE_ADDED_TASK, INDENT_LARGE + newEvent, getTaskCountMessage());
                break;
            case "bye":
                printExitMessage();
                System.exit(0);
            default:
                tasks[taskCount] = new Task(userInputString);
                taskCount++;
                printMessages("\t added: " + userInputString);
                break;
            }
        }
    }
}
