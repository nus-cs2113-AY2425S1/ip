package Glendon;

import Glendon.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    public static final String lineDivider = "--------------------------------------------------------------------------";
    public Scanner scanner;
    public Parser parser;

    /**
     * Construct the user interface of interaction with user
     */
    UI() {
        scanner = new Scanner(System.in);
    }
    
  
    /**
     * Prints the message that there is no such task
     */
    public void noSuchTask() {
        System.out.println("    No such task found");
    }
    
    /**
     * Prints the list of task found with the specific keyword used
     * @param tasks the list of tasks 
     */
    public void tasksFound(ArrayList<Task> tasks) {
        int taskNumber = 1;
        Task currentTask;
        System.out.println("    Here are the tasks with the specific task name:");
        for (int i = 0; i < tasks.size(); i++) {
            currentTask = tasks.get(i);
            if (currentTask != null) {
                System.out.println("        " + taskNumber + ". " + currentTask.toString());
                taskNumber++;
            }
        }
    }

    /**
     * Prints the message for user when there is an issue creating the file and the description
     * of the issue
     */
    public void fileCreationError(Exception e) {
        System.out.println("    Issue with creating file :" + e.getMessage());
    }

    /**
     * Prints the message for user when there is an issue locating the file and the description
     * of the issue
     */
    public void fileNotFoundError(String filePath) {
        System.out.println("File not found" + filePath);
    }

    /**
     * Prints the message for user when there is an issue accessing the file and the description
     * of the issue
     */
    public void fileAccessError(String filePath) {
        System.out.println("    Error: Access denied while saving to " + filePath);
    }

    /**
     * Prints the message for user when there is an issue saving the file and the description
     * of the issue
     */
    public void fileSavingError(Exception e) {
        System.out.println("    Unexpected error saving file: " + e.getMessage());
    }

    /**
     * Reads the input from the user
     */
    public String readCommand() {
        String command = scanner.nextLine();
        return command;
    }

    /**
     * Prints the message for user when the chosen task has already been marked
     */
    public void showMarkError() {
        System.out.println("    Task has already been marked");
    }

    /**
     * Prints the message for user when the chosen task has already been unmarked
     */
    public void showUnmarkError() {
        System.out.println("    Task has already been unmarked");
    }

    /**
     * Prints the message for user when there is no such commands
     */
    public void showMissingCommandError() {
        System.out.println("    Ayo no such commands.");
    }

    /**
     * Prints the message for user when the file is missing and it will be created now
     */
    public void showLoadingError() {
        System.out.println("    Missing File\n  Attempting to create a new file");
    }

    /**
     * Prints the message for user when there is no input of the task number
     */
    public void showTaskNumberError() {
        System.out.println("    My guy you dont have that task number");
    }

    /**
     * Prints the message for user when there is no input of the task name
     */
    public void showTaskInfoError() {
        System.out.println("    Ayo you forgot to tell me what is the task");
    }

    /**
     * Prints the message for user when there is no input of the date
     */
    public void showDateError() {
        System.out.println("    You have forgotten to tell me the date");
    }

    /**
     * Prints the message for user when there is a task number is given instead of task name
     */
    public void showTaskNameError() {
        System.out.println("    Bruh tell me a number, not the task.");
    }

    /**
     * Prints the task has been marked as requested
     *
     * @param tasks the list of task
     * @param taskIndex the index of the task to be marked
     */
    public void markedTaskMessage(TaskList tasks, int taskIndex) {
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("        " + tasks.taskList.get(taskIndex));
    }

    /**
     * Prints the task has been unmarked as requested
     *
     * @param tasks the list of task
     * @param taskIndex the index of the task to be unmarked
     */
    public void unmarkedTaskMessage(TaskList tasks, int taskIndex) {
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("        " + tasks.taskList.get(taskIndex));
    }

    /**
     * Prints the information of the deleted task and inform the user that the task has been
     * deleted from the list of task
     *
     * @param removedTask the deleted task
     * @param tasks the updated list of task
     */
    public void showDeletedMessage(Task removedTask, TaskList tasks) {
        System.out.println(lineDivider);
        System.out.println("    Noted. I've removed this task:");
        System.out.println("        " + removedTask);
        System.out.println("    Now you have " + tasks.taskList.size() + " tasks in the list");
    }

    /**
     * Prints task has been added successfully
     * @param tasks the list of task to add the task onto
     */
    public void showAddedTask(TaskList tasks) {
        int taskCounter = tasks.taskList.size() - 1;
        System.out.println(lineDivider);
        System.out.println("    Got it. I've added this task:");
        System.out.println("        " + tasks.taskList.get(taskCounter));
        System.out.println("    Now you have " + (taskCounter+1) + " tasks in the list.");
    }

    /**
     * Prints the task list when prompted by user
     * @param tasks the list of task in the chatbot
     */
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

    /**
     * Prints the GoodBye message for user
     */
    public void showBye() {
        System.out.println(lineDivider);
        System.out.println("    Bye. Hope to see you again soon!");
    }

    /**
     * Prints divider for each command and output
     */
    public void showLine() {
        System.out.println(lineDivider);
    }

    /**
     * Prints the welcome message for the user
     */
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
