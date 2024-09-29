package appal.ui;

import appal.task.Task;
import appal.task.TaskList;

import static appal.common.Messages.LOGO;
import static appal.common.Messages.SEPARATOR;
import static appal.common.Messages.WELCOME_MESSAGE;
import static appal.common.Messages.NEW_TASK_NOTICE;

import java.util.Scanner;

/**
 * Ui class handles the user interface of the chatbot,
 * and contains methods to take in user input, as well as
 * methods to display messages on the command line.
 */
public class Ui {
    /** Constant in order to show user a task list that starts from index 1 instead of 0 */
    public static final int ZERO_INDEX_OFFSET = 1;

    /** Scanner in to take in user input */
    private final Scanner in;

    /**
     * Class constructor.
     */
    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Returns a String of the user's input.
     *
     * @return String containing user's input.
     */
    public String getInput() {
        return in.nextLine();
    }

    /**
     * Prints out a line separator.
     */
    public void printSeparator() {
        System.out.println(SEPARATOR);
    }

    /**
     * Prints out a message wrapped in line separators.
     *
     * @param message Message to show user.
     */
    public void printMessage(String message) {
        printSeparator();
        System.out.println(message);
        printSeparator();
    }

    /**
     * Welcomes user by printing out the Appal logo, which is an apple,
     * as well as a welcome message.
     */
    public void welcomeUser() {
        printMessage(LOGO + WELCOME_MESSAGE);
    }

    /**
     * Prints out a specified Task, wrapped in line separators.
     *
     * @param task Specified task to be printed.
     */
    public void printOneTask(Task task) {
        System.out.println(task);
    }

    /**
     * Prints out a message along with a specified Task,
     * wrapped in line separators.
     *
     * @param taskList List of tasks that the user has inputted.
     * @param message Message to show user based on their input.
     * @param indexOfTaskToPrint Index of the specified task in the taskList.
     */
    public void printReply(TaskList taskList, String message, int indexOfTaskToPrint) {
        printSeparator();
        System.out.println(message);
        printOneTask(taskList.getTask(indexOfTaskToPrint));
        printSeparator();
    }

    /**
     * Prints out all tasks in the taskList, wrapped in line separators.
     *
     * @param taskList List of tasks that the user has inputted.
     */
    public void printTaskList(TaskList taskList) {
        printSeparator();
        int totalTasks = Task.getTotalTasks();
        System.out.println("You have " + totalTasks + " to-dos!");
        for (int i = 0; i < totalTasks; i += 1) {
            System.out.print((i + ZERO_INDEX_OFFSET) + ".");
            printOneTask(taskList.getTask(i));
        }
        printSeparator();
    }

    /**
     * Prints, whenever the user adds a new task, an update message along
     * with the recently added task, wrapped in line separators.
     * Does not print anything if task is added from pre-saved tasks list.
     *
     * @param taskList List of tasks that the user has inputted.
     * @param isFromUser Indicates whether instruction to add task is from the user or from pre-saved tasks.
     */
    public void updateUserOnAddedTask(TaskList taskList, boolean isFromUser) {
        int latestTaskIndex = Task.getTotalTasks() - 1;
        if (isFromUser) {
            printReply(taskList, NEW_TASK_NOTICE, latestTaskIndex);
        }
    }
}
