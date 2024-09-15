package appal;

import appal.exception.*;
import appal.task.Deadline;
import appal.task.Event;
import appal.task.Task;
import appal.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Appal {
    // Constants for commands
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final int COMMAND_INDEX = 0;

    // Integer constants for specific type of tasks
    public static final int MAX_TASKS = 100;
    public static final int TASK_INDEX = 1;
    public static final int BY_INDEX = 2;
    public static final int FROM_INDEX = 2;
    public static final int TO_INDEX = 3;

    // String constants for conversation
    public static final String LOGO =
            "        /)\n" +
            "   .-\"\".L,\"\"-.\n" +
            "  ;           :\n" +
            "  (    ^_^  :7)\n" +
            "   :         ;\n" +
            "    \"..-\"-..\"\n";
    public static final String SEPARATOR = "\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n";
    public static final String WELCOME_MESSAGE = "Heyo! I'm your pal, Appal!\nLet's get things rolling, what would you like to do today?";
    public static final String NEW_TASK_NOTICE = "I've added the below to your to-do list, you can do it!";
    public static final String TASK_DONE_MESSAGE = "Task done! One more step towards success :)";
    public static final String UNMARK_TASK_MESSAGE = "What's next on the agenda? :D";
    public static final String BYE_MESSAGE = "See ya! An Appal a day, keeps the boredom away!";

    // Constants for file reading
    public static final String FILE_PATH = "./data/saved_tasks.txt";
    public static final Path FILE_DIRECTORY = Paths.get("./data");

    // Attributes
    private boolean isExited = false;
    private Task[] taskList = new Task[MAX_TASKS];
    private final Scanner in = new Scanner(System.in);


    public void printSeparator() {
        System.out.println(SEPARATOR);
    }

    public void printMessage(String message) {
        printSeparator();
        System.out.println(message);
        printSeparator();
    }

    public void welcomeUser() {
        printMessage(LOGO + WELCOME_MESSAGE);
    }

    public void printReply() {
        printSeparator();
        System.out.println(NEW_TASK_NOTICE);
        int latestTaskIndex = Task.getTotalTasks() - 1;
        printOneTask(taskList[latestTaskIndex]);
        printSeparator();
    }

    public void printOneTask(Task task) {
        System.out.println(task);
    }

    public void printTaskList() {
        printSeparator();
        int totalTasks = Task.getTotalTasks();
        System.out.println("You have " + totalTasks + " to-dos!");
        for (int i = 0; i < totalTasks; i += 1) {
            System.out.print(taskList[i].getId() + ".");
            printOneTask(taskList[i]);
        }
        printSeparator();
    }

    public void handleMarkTask(String[] inputDetails, boolean isMark) throws AppalException {
        try {
            int taskId = Integer.parseInt(inputDetails[TASK_INDEX]);
            int listIndex = taskId - 1;
            Task taskToMark = markTask(listIndex, isMark);
            printSeparator();
            if (isMark) {
                System.out.println(TASK_DONE_MESSAGE);
            } else {
                System.out.println(UNMARK_TASK_MESSAGE);
            }
            printOneTask(taskToMark);
            printSeparator();
        } catch (NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        }
    }

    public Task markTask(int listIndex, boolean isMark) {
        Task taskToMark = taskList[listIndex];
        taskToMark.setDone(isMark);
        return taskToMark;
    }

    public void checkForTask(String[] inputDetails) throws EmptyTaskException {
        if (inputDetails[TASK_INDEX] == null) {
            throw new EmptyTaskException();
        }
    }

    public void addToDo(String[] inputDetails) throws AppalException {
        int totalToDos = Task.getTotalTasks();
        checkForTask(inputDetails);
        taskList[totalToDos] = new ToDo(inputDetails[TASK_INDEX]);
    }

    public void addDeadline(String[] inputDetails) throws AppalException {
        int totalToDos = Task.getTotalTasks();
        checkForTask(inputDetails);
        if (inputDetails[BY_INDEX] == null) {
            throw new UnspecifiedDeadlineException();
        }
        taskList[totalToDos] = new Deadline(inputDetails[TASK_INDEX], inputDetails[BY_INDEX]);
    }

    public void addEvent(String[] inputDetails) throws AppalException{
        int totalToDos = Task.getTotalTasks();
        checkForTask(inputDetails);
        if (inputDetails[FROM_INDEX] == null || inputDetails[TO_INDEX] == null) {
            throw new UnspecifiedEventDurationException();
        }
        taskList[totalToDos] = new
                Event(inputDetails[TASK_INDEX], inputDetails[FROM_INDEX], inputDetails[TO_INDEX]);
    }

    public void exitAppal() {
        isExited = true;
        printMessage(BYE_MESSAGE);
    }

    public void handleInput(String[] inputDetails) {
        String command = inputDetails[COMMAND_INDEX];

        try {
            switch (command) {
            case COMMAND_BYE:
                appendToFile();
                exitAppal();
                break;
            case COMMAND_LIST:
                printTaskList();
                break;
            case COMMAND_TODO:
                addToDo(inputDetails);
                printReply();
                break;
            case COMMAND_DEADLINE:
                addDeadline(inputDetails);
                printReply();
                break;
            case COMMAND_EVENT:
                addEvent(inputDetails);
                printReply();
                break;
            case COMMAND_MARK:
                handleMarkTask(inputDetails, true);
                break;
            case COMMAND_UNMARK:
                handleMarkTask(inputDetails, false);
                break;
            default:
                throw new AppalException();
            }
        } catch (AppalException e) {
            printMessage(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadFileContents() throws FileNotFoundException, AppalException {
        File savedTasks = new File(FILE_PATH); // create a File for the given file path
        Scanner fileReader = new Scanner(savedTasks); // create a Scanner using the File as the source
        int savedTasksCount = 0;
        while (fileReader.hasNext()) {
            String line = fileReader.nextLine();
            String[] words = line.split(", ");
            boolean isTaskMarked = words[0].equals("1");
            String[] taskDetails = Arrays.copyOfRange(words, 1, words.length);
            handleInput(taskDetails);
            markTask(savedTasksCount, isTaskMarked);
            savedTasksCount += 1;
        }
        Task.setTotalTasks(savedTasksCount);
    }

    public void loadExistingTasksData() {
        try {
            loadFileContents();
        } catch (FileNotFoundException | AppalException e) {
            System.out.println("File not found");
        }
    }

    public void appendToFile() throws IOException {
        try {
            Files.createDirectories(FILE_DIRECTORY);
            FileWriter fw = new FileWriter(FILE_PATH); // create a FileWriter in append mode
            for (int i = 0; i < Task.getTotalTasks(); i++) {
                fw.write(taskList[i].getStatusValue() + ", " + taskList[i].getTaskInfo());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("error occurred");;
        }
    }

    public void runAppal() {
        welcomeUser();
        loadExistingTasksData();
        while (!isExited) {
            String line = in.nextLine();
            String[] inputDetails = Parser.extractInputDetails(line);
            handleInput(inputDetails);
        }
    }
}
