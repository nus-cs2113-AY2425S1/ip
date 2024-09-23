package Glendon;

import Glendon.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    public static final String lineDivider = "--------------------------------------------------------------------------";
    public Scanner scanner;
    public Parser parser;

    UI() {
        scanner = new Scanner(System.in);
    }

    public void fileCreationError(Exception e) {
        System.out.println("    Issue with creating file :" + e.getMessage());
    }

    public void fileNotFoundError(String filePath) {
        System.out.println("File not found" + filePath);
    }

    public void fileAccessError(String filePath) {
        System.out.println("    Error: Access denied while saving to " + filePath);
    }

    public void fileSavingError(Exception e) {
        System.out.println("    Unexpected error saving file: " + e.getMessage());
    }

    public String readCommand() {
        String command = scanner.nextLine();
        return command;
    }

    public void showMarkError() {
        System.out.println("    Task has already been marked");
    }

    public void showUnmarkError() {
        System.out.println("    Task has already been unmarked");
    }

    public void showMissingCommandError() {
        System.out.println("    Ayo no such commands.");
    }

    public void showLoadingError() {
        System.out.println("    Missing File\n  Attempting to create a new file");
    }

    public void showTaskNumberError() {
        System.out.println("    My guy you dont have that task number");
    }

    public void showTaskInfoError() {
        System.out.println("    Ayo you forgot to tell me what is the task");
    }

    public void showDateError() {
        System.out.println("    You have forgotten to tell me the date");
    }

    public void showTaskNameError() {
        System.out.println("    Bruh tell me a number, not the task.");
    }

    public void markedTaskMessage(TaskList tasks, int taskIndex) {
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("        " + tasks.taskList.get(taskIndex));
    }

    public void unmarkedTaskMessage(TaskList tasks, int taskIndex) {
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("        " + tasks.taskList.get(taskIndex));
    }

    public void showDeletedMessage(Task removedTask, TaskList tasks) {
        System.out.println(lineDivider);
        System.out.println("    Noted. I've removed this task:");
        System.out.println("        " + removedTask);
        System.out.println("    Now you have " + tasks.taskList.size() + " tasks in the list");
    }

    public void showAddedTask(TaskList tasks) {
        int taskCounter = tasks.taskList.size() - 1;
        System.out.println(lineDivider);
        System.out.println("    Got it. I've added this task:");
        System.out.println("        " + tasks.taskList.get(taskCounter));
        System.out.println("    Now you have " + (taskCounter+1) + " tasks in the list.");
    }

    public void showTaskList(TaskList tasks) {
        System.out.println(lineDivider);
        ArrayList<Task> currentTaskList = tasks.taskList;
        if (currentTaskList.isEmpty()) {
            System.out.println("    You are free from task");
            return;
        }
        int taskNumber = 1;
        Task currentTask;
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < currentTaskList.size(); i++) {
            currentTask = currentTaskList.get(i);
            if (currentTask != null) {
                System.out.println("        " + taskNumber + ". " + currentTask.toString());
                taskNumber++;
            }
        }
    }

    public void showBye() {
        System.out.println(lineDivider);
        System.out.println("    Bye. Hope to see you again soon!");
    }

    public void showLine() {
        System.out.println(lineDivider);
    }

    public void showWelcome() {
        String logo = " _______   _                     _\n"
                + "| ______| | |                   | |\n"
                + "| |  ___  | |  ___   _____   ___| |  _____   _____\n"
                + "| | |__ | | | / _ \\ |  _  | |  _  | |  _  | |  _  |\n"
                + "| |___| | | | | __/ | | | | | |_| | | |_| | | | | |\n"
                + "|_______| |_| \\___| |_| |_| |_____| |_____| |_| |_|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Glendon.\n" + "What can I do for you?\n");
    }
}
