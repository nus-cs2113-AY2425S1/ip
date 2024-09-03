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
    public static final int COMMAND_INDEX = 0;

    // Integer constants for specific type of tasks
    public static final int TASK_INDEX = 1;
    public static final int BY_INDEX = 2;
    public static final int FROM_INDEX = 2;
    public static final int TO_INDEX = 3;

    // String constants for conversation
    public static final String SEPARATOR = "\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++";
    public static final String NEW_TASK_NOTICE = "I've added the below to your to-do list, you can do it!";
    public static final String BYE_MESSAGE = "See ya! An Appal a day, keeps the boredom away!";
    public static final String UNKNOWN_INPUT_NOTICE = "Oops! I don't recognise this command :(";
    public static final String TASK_DONE_MESSAGE = "Task done! One more step towards success :)";
    public static final String UNMARK_TASK_MESSAGE = "What's next on the agenda? :D";

    // Attributes
    private boolean isExited = false;
    private Task[] taskList = new Task[100];

    public void welcomeUser() {
        String chatbot = "Appal";
        printSeparator();
        System.out.println("Heyo! I'm your pal, " + chatbot + "!");
        System.out.println("Let's get things rolling, what would you like to do today?");
        printSeparator();
    }

    public void printSeparator() {
        System.out.println(SEPARATOR);
    }

    public void printReply() {
        printSeparator();
        System.out.println(NEW_TASK_NOTICE);
        int latestTaskIndex = Task.getTotalTasks() - 1;
        printOneTask(taskList[latestTaskIndex]);
        printSeparator();
    }

    public void exitAppal() {
        isExited = true;
        printSeparator();
        System.out.println(BYE_MESSAGE);
        printSeparator();
    }

    public void handleUnknownInput() {
        printSeparator();
        System.out.println(UNKNOWN_INPUT_NOTICE);
        printSeparator();
    }

    public void printOneTask(Task task) {
        System.out.println(task);
    }

    public void markTask(String[] commandDetails, boolean isMark) {
        int taskId = Integer.parseInt(commandDetails[TASK_INDEX]);
        int listIndex = taskId - 1;
        Task taskToMark = taskList[listIndex];
        taskToMark.setDone(isMark);
        printSeparator();
        if (isMark) {
            System.out.println(TASK_DONE_MESSAGE);
        } else {
            System.out.println(UNMARK_TASK_MESSAGE);
        }
        printOneTask(taskToMark);
        printSeparator();
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

    public void addToDo(String[] commandDetails) {
        int totalToDos = Task.getTotalTasks();
        taskList[totalToDos] = new ToDo(commandDetails[TASK_INDEX]);
    }

    public void addDeadline(String[] commandDetails) {
        int totalToDos = Task.getTotalTasks();
        taskList[totalToDos] = new Deadline(commandDetails[TASK_INDEX], commandDetails[BY_INDEX]);
    }

    public void addEvent(String[] commandDetails) {
        int totalToDos = Task.getTotalTasks();
        taskList[totalToDos] = new
                Event(commandDetails[TASK_INDEX], commandDetails[FROM_INDEX], commandDetails[TO_INDEX]);
    }

    public void handleInput() {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String[] commandDetails = Parser.extractCommandDetails(line);
        String command = commandDetails[COMMAND_INDEX];
        switch (command) {
        case COMMAND_BYE:
            exitAppal();
            break;
        case COMMAND_LIST:
            printTaskList();
            break;
        case COMMAND_TODO:
            addToDo(commandDetails);
            printReply();
            break;
        case COMMAND_DEADLINE:
            addDeadline(commandDetails);
            printReply();
            break;
        case COMMAND_EVENT:
            addEvent(commandDetails);
            printReply();
            break;
        case COMMAND_MARK:
            markTask(commandDetails, true);
            break;
        case COMMAND_UNMARK:
            markTask(commandDetails, false);
            break;
        default:
            handleUnknownInput();
            break;
        }
    }

    public void runAppal() {
        welcomeUser();
        while (!isExited) {
            handleInput();
        }
    }
}
