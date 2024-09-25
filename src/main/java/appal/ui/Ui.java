package appal.ui;

import appal.task.Task;

import static appal.common.Messages.LOGO;
import static appal.common.Messages.SEPARATOR;
import static appal.common.Messages.WELCOME_MESSAGE;
import static appal.common.Messages.NEW_TASK_NOTICE;
import static appal.common.Messages.TASK_DONE_MESSAGE;
import static appal.common.Messages.UNMARK_TASK_MESSAGE;
import static appal.common.Messages.DELETE_TASK_MESSAGE;
import static appal.common.Messages.BYE_MESSAGE;
import static appal.common.Messages.LOAD_SAVED_TASKS_MESSAGE;

import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    public static final int ZERO_INDEX_OFFSET = 1;

    private final Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    public String getInput() {
        return in.nextLine();
    }

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

    public void printReply(ArrayList<Task> taskList, String message, int indexOfTaskToPrint) {
        printSeparator();
        System.out.println(message);
        printOneTask(taskList.get(indexOfTaskToPrint));
        printSeparator();
    }

    public void printOneTask(Task task) {
        System.out.println(task);
    }

    public void printTaskList(ArrayList<Task> taskList) {
        printSeparator();
        int totalTasks = Task.getTotalTasks();
        System.out.println("You have " + totalTasks + " to-dos!");
        for (int i = 0; i < totalTasks; i += 1) {
            System.out.print((i + ZERO_INDEX_OFFSET) + ".");
            printOneTask(taskList.get(i));
        }
        printSeparator();
    }

    public void updateUserOnAddedTask(ArrayList<Task> taskList, boolean isFromUser) {
        int latestTaskIndex = Task.getTotalTasks() - 1;
        if (isFromUser) {
            printReply(taskList, NEW_TASK_NOTICE, latestTaskIndex);
        }
    }
}
