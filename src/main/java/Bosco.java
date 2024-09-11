import java.util.Scanner;

public class Bosco {
    private static final String DIVIDER = "\t____________________________________________________________";
    private static final String INDENT_LARGE = "\t   ";
    private static final String MESSAGE_MARK_DONE = "\t Nice! I've marked this task as done:";
    private static final String MESSAGE_MARK_UNDONE = "\t OK, I've marked this task as not done yet:";
    private static final String MESSAGE_ADDED_TASK = "\t Got it. I've added this task:";
    private static final String DEADLINE_PREFIX_BY = "/by";
    private static final String EVENT_PREFIX_FROM = "/from";
    private static final String EVENT_PREFIX_TO = "/to";

    private static final int MAX_TASKS = 100;
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final Task[] taskList = new Task[MAX_TASKS];
    private static int taskCount = 0;

    private static void printWelcomeMessage() {
        printMessages("\t Hello! I'm Bosco APD.", "\t What can I do for you?");
    }

    private static void printExitMessage() {
        printMessages("\t Bye! Hope to see you again soon!");
    }

    private static void printMessages(String... messages) {
        System.out.println(DIVIDER);
        for (String message : messages) {
            System.out.println(message);
        }
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

    private static void executeCommand(String userInputString) {
        String[] commandTypeAndArgs = splitCommandTypeAndArgs(userInputString);
        String commandType = commandTypeAndArgs[0];
        String commandArgs = commandTypeAndArgs[1];
        switch (commandType) {
        case "list":
            executeListTasks();
            break;
        case "mark":
            executeMarkTask(commandArgs);
            break;
        case "unmark":
            executeUnmarkTask(commandArgs);
            break;
        case "todo":
            executeAddTodo(commandArgs);
            break;
        case "deadline":
            executeAddDeadline(commandArgs);
            break;
        case "event":
            executeAddEvent(commandArgs);
            break;
        case "bye":
        case "exit":
            executeExitProgram();
        default:
            taskList[taskCount] = new Task(userInputString);
            taskCount++;
            printMessages("\t added: " + userInputString);
            break;
        }
    }

    private static String[] splitCommandTypeAndArgs(String userInputString) {
        String[] stringParts = userInputString.split("\\s+", 2);
        return stringParts.length == 2 ? stringParts : new String[] {stringParts[0], ""};
    }

    private static String getTaskCountMessage() {
        return String.format("\t Now you have %1$d tasks in the list.", taskCount);
    }

    private static void executeListTasks() {
        System.out.println(DIVIDER);
        for (int i = 0; i < taskCount; i++) {
            System.out.println("\t " + (i + 1) + "." + taskList[i]);
        }
        System.out.println(DIVIDER);
    }

    private static void executeMarkTask(String commandArgs) {
        Task selectedTask = taskList[Integer.parseInt(commandArgs) - 1];
        selectedTask.markAsDone();
        printMessages(MESSAGE_MARK_DONE, INDENT_LARGE + selectedTask);
    }

    private static void executeUnmarkTask(String commandArgs) {
        Task selectedTask = taskList[Integer.parseInt(commandArgs) - 1];
        selectedTask.markAsNotDone();
        printMessages(MESSAGE_MARK_UNDONE, INDENT_LARGE + selectedTask);
    }

    private static void executeAddTodo(String commandArgs) {
        addToTaskList(new Todo(commandArgs.strip()));
    }

    private static void executeAddDeadline(String commandArgs) {
        int indexOfByPrefix = commandArgs.indexOf(DEADLINE_PREFIX_BY);
        String description = commandArgs.substring(0, indexOfByPrefix).strip();
        String by = removePrefix(commandArgs.substring(indexOfByPrefix), DEADLINE_PREFIX_BY).strip();
        addToTaskList(new Deadline(description, by));
    }

    private static void executeAddEvent(String commandArgs) {
        int indexOfFromPrefix = commandArgs.indexOf(EVENT_PREFIX_FROM);
        int indexOfToPrefix = commandArgs.indexOf(EVENT_PREFIX_TO);
        String description = commandArgs.substring(0, indexOfFromPrefix).strip();
        String from = removePrefix(commandArgs.substring(indexOfFromPrefix, indexOfToPrefix),
                EVENT_PREFIX_FROM).strip();
        String to = removePrefix(commandArgs.substring(indexOfToPrefix), EVENT_PREFIX_TO).strip();
        addToTaskList(new Event(description, from, to));
    }

    private static String removePrefix(String inputStr, String prefix) {
        return inputStr.replace(prefix, "");
    }

    private static void addToTaskList(Task newTask) {
        taskList[taskCount] = newTask;
        taskCount++;
        printMessages(MESSAGE_ADDED_TASK, INDENT_LARGE + newTask, getTaskCountMessage());
    }

    private static void executeExitProgram() {
        printExitMessage();
        System.exit(0);
    }

    public static void main(String[] args) {
        printWelcomeMessage();
        while (true) {
            String userInputString = getUserInput();
            executeCommand(userInputString);
        }
    }
}
