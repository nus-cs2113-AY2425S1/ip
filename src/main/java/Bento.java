import java.util.ArrayList;
import java.util.Scanner;

public class Bento {
    // Constants
    // Logo and Messages
    public static final String LOGO = "\t  ____             _        \n"
            + "\t | __ )  ___ _ __ | |_ ___  \n"
            + "\t |  _ \\ / _ \\ '_ \\| __/ _ \\ \n"
            + "\t | |_) |  __/ | | | || (_) |\n"
            + "\t |____/ \\___|_| |_|\\__\\___/ \n"
            + "\t                            \n";
    public static final String GREETING_MESSAGE = "\tKonichiwa! I am Bento, your personal assistant!\n\tHow can I help you with your tasks today?";
    public static final String LINE_MESSAGE = "\t____________________________________________________________";
    public static final String SAYONARA_MESSAGE = "\tThank you for working with me today! See you next time! Sayonara~";
    public static final String ADD_TASK_SUCCESS_MESSAGE = "\tRoger that! Successfully added task:";
    public static final String EXISTING_TASKS_MESSAGE = "\tHere is the list of your existing tasks!";
    public static final String UNMARKED_MESSAGE = "\tMaybe you're not quite ready for the task just yet. No worries, I'll be here to make sure you clear it.";
    public static final String MARKED_MESSAGE = "\tYou've crushed this task! I've gone ahead and marked it as done for you.";
    public static final String INVALID_COMMAND_MESSAGE = "\tHmm... Something seems wrong with your input. Give it a closer look and try again!";
    public static final String NO_TASK_FOUND_MESSAGE = "\tHmm... I don't think that task exists... Check again with list!";
    public static final String INVALID_INDEX_MESSAGE = "\tHey! The index provided was not a number!";

    // Commands
    public static final String BYE_COMMAND = "bye";
    public static final String LIST_COMMAND = "list";
    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";

    // Prefixes and Regexes
    // General
    public static final String SPACE_REGEX = " ";
    public static final String EMPTY_REGEX = "";

    // ToDo
    public static final String TODO_PREFIX = TODO_COMMAND + SPACE_REGEX;

    // Deadline
    public static final String DEADLINE_PREFIX = DEADLINE_COMMAND + SPACE_REGEX;
    public static final int DEADLINE_NAME_INDEX = 0;
    public static final int DEADLINE_BY_INDEX = 1;
    public static final String BY_PREFIX = "/by";
    public static final String BY_REGEX = " " + BY_PREFIX + " ";

    // Event
    public static final String EVENT_PREFIX = EVENT_COMMAND + SPACE_REGEX;
    public static final String FROM_PREFIX = "/from";
    public static final String TO_PREFIX = "/to";
    public static final String TO_REGEX = TO_PREFIX + SPACE_REGEX;
    public static final String FROM_REGEX = FROM_PREFIX + SPACE_REGEX;

    // Data
    private final Scanner in = new Scanner(System.in);
    private boolean isExit = false;
    private final ArrayList<Task> tasks = new ArrayList<>();
    private int taskCount = 0;

    // Print Functions
    public void printLogo() {
        System.out.print(LOGO);
    }

    public void sayKonichiwa() {
        printLine();
        printLogo();
        System.out.println(GREETING_MESSAGE);
        printLine();
    }

    public void printLine() {
        System.out.println(LINE_MESSAGE);
    }

    public void saySayonara() {
        isExit = true;
        printLine();
        System.out.println(SAYONARA_MESSAGE);
        printLine();
    }

    public void printAddTaskSuccessMessage(String task) {
        printLine();
        System.out.printf("%s\n\t\t%s\n%s", ADD_TASK_SUCCESS_MESSAGE, task, getTaskCountMessage());
        printLine();
    }

    public String getTaskCountMessage() {
        return String.format("\tYou currently have %d tasks! Way to go, you busy bee!\n", taskCount);
    }

    public String getUserInput() {
        return in.nextLine();
    }

    public void addTask(String input) {
        Task toAdd = new Task(input);
        tasks.add(toAdd);
        taskCount++;

        printAddTaskSuccessMessage(toAdd.toString());
    }

    // ToDo Functions
    public void addToDo(String input) {
        ToDo toAdd = new ToDo(input);
        tasks.add(toAdd);
        taskCount++;

        printAddTaskSuccessMessage(toAdd.toString());
    }

    private static String getTodo(String input) {
        return input.replace(TODO_PREFIX, "");
    }

    // Deadline Functions
    public void addDeadline(String input) {
        input = removeDeadlinePrefix(input);
        final int indexOfByPrefix = input.indexOf(BY_PREFIX);
        if (indexOfByPrefix == -1) {
            printInvalidCommandMessage();
            return;
        }

        Deadline toAdd = new Deadline(extractDeadlineName(input), extractDeadlineBy(input));
        tasks.add(toAdd);
        taskCount++;

        printAddTaskSuccessMessage(toAdd.toString());
    }

    public static String extractDeadlineBy(String input) {
        return input.split(BY_REGEX)[DEADLINE_BY_INDEX];
    }

    public static String extractDeadlineName(String input) {
        return input.split(BY_REGEX)[DEADLINE_NAME_INDEX];
    }

    public static String removeDeadlinePrefix(String input) {
        return input.replace(DEADLINE_PREFIX, "");
    }

    // Event Functions
    public void addEvent(String input) {
        input = removeEventPrefix(input);
        final int indexOfFrom = input.indexOf(FROM_PREFIX);
        final int indexOfTo = input.indexOf(TO_PREFIX);
        if (indexOfFrom == -1 || indexOfTo == -1) {
            printInvalidCommandMessage();
            return;
        }
        String eventName = extractEventName(input, indexOfFrom);
        String fromString = extractFromString(input, indexOfFrom, indexOfTo);
        String toString  = extractToString(input, indexOfTo);

        Event toAdd = new Event(eventName, fromString, toString);
        tasks.add(toAdd);
        taskCount++;

        printAddTaskSuccessMessage(toAdd.toString());
    }

    public String extractToString(String input, int indexOfTo) {
        return input.substring(indexOfTo).replace(TO_REGEX, EMPTY_REGEX);
    }

    public String extractFromString(String input, int indexOfFrom, int indexOfTo) {
        return input.substring(indexOfFrom, indexOfTo).replace(FROM_REGEX, EMPTY_REGEX).trim();
    }

    public String extractEventName(String input, int indexOfFrom) {
        return input.substring(0, indexOfFrom).replace(SPACE_REGEX, EMPTY_REGEX);
    }

    public String removeEventPrefix(String input) {
        return input.replace(EVENT_PREFIX, EMPTY_REGEX);
    }

    private void listTasks() {
        printLine();
        System.out.println(EXISTING_TASKS_MESSAGE);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("\t%d. %s\n", i + 1, tasks.get(i));
        }
        printLine();
    }

    // Marking Functions
    private void markTaskAsDone(boolean isDone, String taskIndex) {
        try {
            int index = Integer.parseInt(taskIndex) - 1;
            updateTask(isDone, index);
            printLine();
            if (isDone) {
                printMarked(index);
            } else {
                printUnmarked(index);
            }
            printLine();
        } catch (NumberFormatException e) {
            printInvalidIndexMessage();
        } catch (IndexOutOfBoundsException e) {
            printNoTaskFoundMessage();
        }
    }

    private void printUnmarked(int index) {
        System.out.println(UNMARKED_MESSAGE);
        System.out.printf("\t\t%s\n", tasks.get(index));
    }

    private void printMarked(int index) {
        System.out.println(MARKED_MESSAGE);
        System.out.printf("\t\t%s\n", tasks.get(index));
    }


    // User Error Messages
    public void printInvalidCommandMessage() {
        printLine();
        System.out.println(INVALID_COMMAND_MESSAGE);
        printLine();
    }

    public void printNoTaskFoundMessage() {
        printLine();
        System.out.println(NO_TASK_FOUND_MESSAGE);
        printLine();
    }

    public void printInvalidIndexMessage() {
        printLine();
        System.out.println(INVALID_INDEX_MESSAGE);
        printLine();
    }

    // Task Status Update
    private void updateTask(boolean isDone, int index) {
        tasks.get(index).setDone(isDone);
    }

    private static String[] getInputList(String input) {
        return input.split(SPACE_REGEX);
    }

    public void handleUserInput(String input) {
        String[] inputList = getInputList(input);
        switch (inputList[0]) {
        case BYE_COMMAND:
            saySayonara();
            break;
        case LIST_COMMAND:
            listTasks();
            break;
        case MARK_COMMAND:
            markTaskAsDone(true, inputList[1]);
            break;
        case UNMARK_COMMAND:
            markTaskAsDone(false, inputList[1]);
            break;
        case TODO_COMMAND:
            addToDo(getTodo(input));
            break;
        case DEADLINE_COMMAND:
            addDeadline(input);
            break;
        case EVENT_COMMAND:
            addEvent(input);
            break;
        default:
            addTask(input);
        }
    }

    public void run() {
        sayKonichiwa();
        while (!isExit) {
            String input = getUserInput();
            handleUserInput(input);
        }
    }
}