package bento;

import exception.*;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

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
    public static final String DELETE_TASK_SUCCESS_MESSAGE = "\tThe following task has been removed successfully:";
    public static final String EXISTING_TASKS_MESSAGE = "\tHere is the list of your existing tasks!";
    public static final String UNMARKED_MESSAGE = "\tMaybe you're not quite ready for the task just yet. No worries, I'll be here to make sure you clear it.";
    public static final String MARKED_MESSAGE = "\tYou've crushed this task! I've gone ahead and marked it as done for you.";

    // Commands
    public static final String BYE_COMMAND = "bye";
    public static final String LIST_COMMAND = "list";
    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";
    public static final String DELETE_COMMAND = "delete";
    public static final int COMMAND_INDEX = 0;

    // Prefixes and Regexes
    // General
    public static final String SPACE_REGEX = " ";
    public static final String EMPTY_REGEX = "";


    // tasks.Deadline
    public static final int DEADLINE_NAME_INDEX = 0;
    public static final int DEADLINE_BY_INDEX = 1;
    public static final String BY_PREFIX = "/by";
    public static final String BY_REGEX = " " + BY_PREFIX + " ";

    // tasks.Event
    public static final String FROM_PREFIX = "/from";
    public static final String TO_PREFIX = "/to";


    // Data
    private final Scanner IN = new Scanner(System.in);
    private boolean isExit = false;
    private final ArrayList<Task> TASKS = new ArrayList<>();

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
        return String.format("\tYou currently have %d tasks! Way to go, you busy bee!\n", TASKS.size());
    }

    public String getUserInput() {
        return IN.nextLine();
    }

    // tasks.ToDo Functions
    public void addToDo(String input) throws InvalidToDoException {
        if (input.isEmpty()) {
            throw new InvalidToDoException();
        }

        ToDo toAdd = new ToDo(input);
        TASKS.add(toAdd);

        printAddTaskSuccessMessage(toAdd.toString());
    }

    public String getTodo(String input) {
        return input.replace(TODO_COMMAND, "").trim();
    }

    // tasks.Deadline Functions
    public void addDeadline(String input) throws InvalidDeadlineException {
        input = removeDeadlinePrefix(input);
        final int indexOfByPrefix = input.indexOf(BY_PREFIX);

        // Throw exception if no "/by" found
        if (indexOfByPrefix == -1) {
            throw new InvalidDeadlineException();
        }

        String deadlineName = extractDeadlineName(input);
        String deadlineBy = extractDeadlineBy(input);

        if (deadlineName.isEmpty() || deadlineBy.isEmpty()) {
            throw new InvalidDeadlineException();
        }

        Deadline toAdd = new Deadline(deadlineName, deadlineBy);
        TASKS.add(toAdd);

        printAddTaskSuccessMessage(toAdd.toString());
    }

    public static String extractDeadlineBy(String input) {
        String[] inputList = input.split(BY_REGEX);
        if (inputList.length == 1) {
            return "";
        }
        return inputList[DEADLINE_BY_INDEX].trim();
    }

    public String extractDeadlineName(String input) {
        return input.split(BY_REGEX)[DEADLINE_NAME_INDEX].trim();
    }

    public String removeDeadlinePrefix(String input) {
        return input.replace(DEADLINE_COMMAND, EMPTY_REGEX);
    }

    // tasks.Event Functions
    public void addEvent(String input) throws InvalidEventException {
        input = removeEventPrefix(input);
        int indexOfFrom = input.indexOf(FROM_PREFIX);
        int indexOfTo = input.indexOf(TO_PREFIX);

        if (indexOfFrom == -1 || indexOfTo == -1) {
            throw new InvalidEventException();
        }

        String eventName = extractEventName(input, indexOfFrom);
        String fromString = extractFromString(input, indexOfFrom, indexOfTo);
        String toString  = extractToString(input, indexOfTo);

        if (eventName.isEmpty() || fromString.isEmpty() || toString.isEmpty()) {
            throw new InvalidEventException();
        }

        Event toAdd = new Event(eventName, fromString, toString);
        TASKS.add(toAdd);

        printAddTaskSuccessMessage(toAdd.toString());
    }

    public String extractToString(String input, int indexOfTo) {
        return input.substring(indexOfTo).replace(TO_PREFIX, EMPTY_REGEX).trim();
    }

    public String extractFromString(String input, int indexOfFrom, int indexOfTo) {
        return input.substring(indexOfFrom, indexOfTo).replace(FROM_PREFIX, EMPTY_REGEX).trim();
    }

    public String extractEventName(String input, int indexOfFrom) {
        return input.substring(0, indexOfFrom).trim();
    }

    public String removeEventPrefix(String input) {
        return input.replace(EVENT_COMMAND, EMPTY_REGEX);
    }

    public Task retrieveTask(int index) {
        return TASKS.get(index);
    }

    public void listTasks() {
        printLine();
        System.out.println(EXISTING_TASKS_MESSAGE);
        for (int i = 0; i < TASKS.size(); i++) {
            System.out.printf("\t%d. %s\n", i + 1, retrieveTask(i));
        }
        printLine();
    }

    // Marking Functions
    public void markTaskAsDone(boolean isDone, String input) throws InvalidIndexException, MissingTaskException {
        try {
            input = removeMarkPrefix(input);
            int index = Integer.parseInt(input) - 1;
            updateTask(isDone, index);
            printLine();
            if (isDone) {
                printMarked(index);
            } else {
                printUnmarked(index);
            }
            printLine();
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        } catch (IndexOutOfBoundsException e) {
            throw new MissingTaskException();
        }
    }

    public String removeMarkPrefix(String input) {
        return input.replace(UNMARK_COMMAND, EMPTY_REGEX).replace(MARK_COMMAND, EMPTY_REGEX).trim();
    }

    public void printUnmarked(int index) {
        System.out.println(UNMARKED_MESSAGE);
        System.out.printf("\t\t%s\n", retrieveTask(index));
    }

    public void printMarked(int index) {
        System.out.println(MARKED_MESSAGE);
        System.out.printf("\t\t%s\n", retrieveTask(index));
    }

    // tasks.Task Status Update
    public void updateTask(boolean isDone, int index) {
        retrieveTask(index).setDone(isDone);
    }

    // Delete Functions
    public void deleteTask(String input) throws InvalidIndexException, MissingTaskException {
        try {
            String parsed = removeDeletePrefix(input);
            int index = Integer.parseInt(parsed) - 1;
            Task task = TASKS.get(index);
            deleteTaskFromList(index);
            printDeleteTaskSuccessMessage(task);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        } catch (IndexOutOfBoundsException e) {
            throw new MissingTaskException();
        }
    }

    public String removeDeletePrefix(String input) {
        return input.replace(DELETE_COMMAND, EMPTY_REGEX).trim();
    }

    public void deleteTaskFromList(int index) {
        TASKS.remove(index);
    }

    public void printDeleteTaskSuccessMessage(Task task) {
        printLine();
        System.out.printf("%s\n\t\t%s\n%s", DELETE_TASK_SUCCESS_MESSAGE, task, getTaskCountMessage());
        printLine();
    }

    public static String[] getInputList(String input) {
        return input.split(SPACE_REGEX);
    }

    public void handleUserInput(String input) {
        // Remove excess whitespace
        input = input.trim();
        String[] inputList = getInputList(input);
        try {
            switch (inputList[COMMAND_INDEX]) {
            case BYE_COMMAND:
                saySayonara();
                break;
            case LIST_COMMAND:
                listTasks();
                break;
            case MARK_COMMAND:
                markTaskAsDone(true, input);
                break;
            case UNMARK_COMMAND:
                markTaskAsDone(false, input);
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
            case DELETE_COMMAND:
                deleteTask(input);
                break;
            default:
                throw new InvalidCommandException();
            }
        } catch (BentoException e) {
            System.out.print(e.getMessage());
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