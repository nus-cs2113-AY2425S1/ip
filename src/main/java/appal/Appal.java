package appal;

import appal.exception.*;
import appal.task.Deadline;
import appal.task.Event;
import appal.task.Task;
import appal.task.ToDo;

import java.util.Scanner;
import java.util.ArrayList;

public class Appal {
    // Constants for commands
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_DELETE = "delete";
    public static final int COMMAND_INDEX = 0;

    // Integer constants for specific type of tasks
    public static final int ZERO_INDEX_OFFSET = 1;
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
    public static final String DELETE_TASK_MESSAGE = "The task below has been removed, it's always okay to change your mind!";
    public static final String BYE_MESSAGE = "See ya! An Appal a day, keeps the boredom away!";

    // Attributes
    private boolean isExited = false;
    //private Task[] taskList = new Task[MAX_TASKS];
    private ArrayList<Task> taskList = new ArrayList<>();
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
        printOneTask(taskList.get(latestTaskIndex));
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
            System.out.print((i + ZERO_INDEX_OFFSET) + ".");
            printOneTask(taskList.get(i));
        }
        printSeparator();
    }

    public void markTask(String[] inputDetails, boolean isMark) throws AppalException {
        try {
            int taskId = Integer.parseInt(inputDetails[TASK_INDEX]);
            int listIndex = taskId - 1;
            Task taskToMark = taskList.get(listIndex);
            taskToMark.setDone(isMark);
            printSeparator();
            if (isMark) {
                System.out.println(TASK_DONE_MESSAGE);
            } else {
                System.out.println(UNMARK_TASK_MESSAGE);
            }
            printOneTask(taskToMark);
            printSeparator();
        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        }
    }

    public void checkForTask(String[] inputDetails) throws EmptyTaskException {
        if (inputDetails[TASK_INDEX] == null) {
            throw new EmptyTaskException();
        }
    }

    public void addToDo(String[] inputDetails) throws AppalException {
        checkForTask(inputDetails);
        ToDo newToDo = new ToDo(inputDetails[TASK_INDEX]);
        taskList.add(newToDo);
    }

    public void addDeadline(String[] inputDetails) throws AppalException {
        checkForTask(inputDetails);
        if (inputDetails[BY_INDEX] == null) {
            throw new UnspecifiedDeadlineException();
        }
        Deadline newDeadline = new Deadline(inputDetails[TASK_INDEX], inputDetails[BY_INDEX]);
        taskList.add(newDeadline);
    }

    public void addEvent(String[] inputDetails) throws AppalException{
        checkForTask(inputDetails);
        if (inputDetails[FROM_INDEX] == null || inputDetails[TO_INDEX] == null) {
            throw new UnspecifiedEventDurationException();
        }
        Event newEvent = new
                Event(inputDetails[TASK_INDEX], inputDetails[FROM_INDEX], inputDetails[TO_INDEX]);
        taskList.add(newEvent);
    }

    public void deleteTask(String[] inputDetails) throws AppalException {
        try {
            int taskId = Integer.parseInt(inputDetails[TASK_INDEX]);
            int listIndex = taskId - 1;
            Task taskToDelete = taskList.get(listIndex);
            taskList.remove(listIndex);
            Task.setTotalTasks(Task.getTotalTasks() - 1);
            printSeparator();
            System.out.println(DELETE_TASK_MESSAGE);
            printOneTask(taskToDelete);
            printSeparator();
        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        }
    }

    public void exitAppal() {
        isExited = true;
        printMessage(BYE_MESSAGE);
    }

    public void handleInput() {
        String line = in.nextLine();
        String[] inputDetails = Parser.extractInputDetails(line);
        String command = inputDetails[COMMAND_INDEX];

        try {
            switch (command) {
            case COMMAND_BYE:
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
                markTask(inputDetails, true);
                break;
            case COMMAND_UNMARK:
                markTask(inputDetails, false);
                break;
            case COMMAND_DELETE:
                deleteTask(inputDetails);
                break;
            default:
                throw new AppalException();
            }
        } catch (AppalException e) {
            printMessage(e.getMessage());
        }
    }

    public void runAppal() {
        welcomeUser();
        while (!isExited) {
            handleInput();
        }
    }
}
