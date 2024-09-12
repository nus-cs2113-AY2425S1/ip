package bento;

import exception.*;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public static final String SAVE_TASK_LIST_SUCCESS_MESSAGE = "\tBanzai! I've saved all our tasks for you to work on them next time!";
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
    public static final String TASK_STATUS_DELIMITER = " | ";
    public static final String TASK_STATUS_DELIMITER_REGEX = " \\| ";

    // Deadline
    public static final int DEADLINE_NAME_INDEX = 0;
    public static final int DEADLINE_BY_INDEX = 1;
    public static final String BY_PREFIX = "/by";
    public static final String BY_REGEX = " " + BY_PREFIX + " ";

    // Event
    public static final String FROM_PREFIX = "/from";
    public static final String TO_PREFIX = "/to";

    // Saving and Loading
    public static final String TASK_DONE_INDICATOR = "1";
    public static final String TASK_UNDONE_INDICATOR = "0";
    public static final int TASK_INDEX = 0;
    public static final int TASK_STATUS_INDEX = 1;


    // Data
    public static final String FILE_PATH = "./data/save.txt";
    public static final Path DATA_DIRECTORY = Paths.get("./data");
    private final Scanner IN = new Scanner(System.in);
    private final ArrayList<Task> TASKS = new ArrayList<>();
    private boolean isExit = false;

    // Print Functions
    public void printSaveTaskListSuccessMessage() {
        printLine();
        System.out.println(SAVE_TASK_LIST_SUCCESS_MESSAGE);
        printLine();
    }

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
    public void addToDo(String input, boolean fromUserInput) throws InvalidToDoException {
        input = getTodo(input);
        if (input.isEmpty()) {
            throw new InvalidToDoException();
        }

        ToDo toAdd = new ToDo(input);
        TASKS.add(toAdd);

        if (fromUserInput) {
            printAddTaskSuccessMessage(toAdd.toString());
        }
    }

    public String getTodo(String input) {
        return input.replace(TODO_COMMAND, "").trim();
    }

    // tasks.Deadline Functions
    public void addDeadline(String input, boolean fromUserInput) throws InvalidDeadlineException {
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

        if (fromUserInput) {
            printAddTaskSuccessMessage(toAdd.toString());
        }
    }

    public String extractDeadlineBy(String input) {
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
    public void addEvent(String input, boolean fromUserInput) throws InvalidEventException {
        input = removeEventPrefix(input);
        int indexOfFrom = input.indexOf(FROM_PREFIX);
        int indexOfTo = input.indexOf(TO_PREFIX);

        if (indexOfFrom == -1 || indexOfTo == -1) {
            throw new InvalidEventException();
        }

        String eventName = extractEventName(input, indexOfFrom);
        String fromString = extractFromString(input, indexOfFrom, indexOfTo);
        String toString = extractToString(input, indexOfTo);

        if (eventName.isEmpty() || fromString.isEmpty() || toString.isEmpty()) {
            throw new InvalidEventException();
        }

        Event toAdd = new Event(eventName, fromString, toString);
        TASKS.add(toAdd);

        if (fromUserInput) {
            printAddTaskSuccessMessage(toAdd.toString());
        }
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

    // Overload
    public void markTaskAsDone(boolean isDone, int index) {
        updateTask(isDone, index);
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

    public void handleUserInput(String input, boolean fromUserInput) {
        // Remove excess whitespace
        input = input.trim();
        String[] inputList = getInputList(input);
        try {
            switch (inputList[COMMAND_INDEX]) {
            case BYE_COMMAND:
                saveTaskList();
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
                addToDo(input, fromUserInput);
                break;
            case DEADLINE_COMMAND:
                addDeadline(input, fromUserInput);
                break;
            case EVENT_COMMAND:
                addEvent(input, fromUserInput);
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

    public void loadTaskList() throws LoadFileErrorException {
        try {
            File saveFile = new File(FILE_PATH);
            Scanner fileScanner = new Scanner(saveFile);
            int currentTask = 0;
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                boolean isTaskDone = getTaskDone(line);
                String userCommand = getCommand(line);
                // Suppress success messages
                handleUserInput(userCommand, false);
                // Overloaded markTaskAsDone
                markTaskAsDone(isTaskDone, currentTask);
                currentTask++;
            }
            listTasks();
        } catch (Exception e) {
            // Exceptions encountered when loading the save file can be classified as LoadFileErrorExceptions
            throw new LoadFileErrorException();
        }
    }

    public boolean getTaskDone(String line) {
        return line.split(TASK_STATUS_DELIMITER_REGEX)[TASK_STATUS_INDEX].equals(TASK_DONE_INDICATOR);
    }

    public String getCommand(String line) {
        return line.split(TASK_STATUS_DELIMITER_REGEX)[TASK_INDEX];
    }

    public void saveTaskList() throws SaveFileErrorException {
        try {
            // Create data directory if it does not exist
            Files.createDirectories(DATA_DIRECTORY);
            FileWriter saveWriter = new FileWriter(FILE_PATH);
            for (Task task : TASKS) {
                saveWriter.write(task.getTaskAsCommand());
                saveWriter.write(TASK_STATUS_DELIMITER);
                if (task.isDone()) {
                    saveWriter.write(TASK_DONE_INDICATOR);
                } else {
                    saveWriter.write(TASK_UNDONE_INDICATOR);
                }
                saveWriter.write(System.lineSeparator());
            }
            saveWriter.close();
            printSaveTaskListSuccessMessage();
        } catch (IOException e) {
            throw new SaveFileErrorException();
        }
    }

    public void run() {
        sayKonichiwa();
        try {
            loadTaskList();
        } catch (BentoException e) {
            System.out.print(e.getMessage());
        }
        while (!isExit) {
            String input = getUserInput();
            // Outputs success messages
            handleUserInput(input, true);
        }
    }
}