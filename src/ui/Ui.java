package ui;

import exception.IncompleteCommandException;
import tasklist.TaskList;

import java.util.Arrays;
import java.util.Scanner;
import static main.Sirius.*;

/**
 * The Ui class handles all interactions with the user. It includes methods for displaying messages,
 * reading input, and handling user errors and successes during the execution of commands.
 */
public class Ui {
    private final Scanner scanner;
    /**
     * Constructor for Ui. Initializes the Scanner to take input from the console.
     */
    public Ui() {
        scanner = new Scanner(System.in); // stores the input of the user
    }


    // ========== Greeting messages ==========

    /**
     * Prints the welcome message to the user.
     */
    public void sayHello(){
        System.out.println(SEPARATOR);
        System.out.println(WELCOME_MESSAGE);
        System.out.println(SEPARATOR);
    }

    /**
     * Prints the welcome message to the user.
     */
    public void sayGoodbye(){
        System.out.println(SEPARATOR);
        System.out.println(GOODBYE_MESSAGE);
        System.out.println(SEPARATOR);
    }

    // ========== Success messages ==========

    /**
     * Displays an error message when the task list file cannot be loaded.
     */
    public void showLoadingError(){
        System.out.println(SEPARATOR);
        System.out.println(LOADING_ERROR_MESSAGE);
        System.out.println(SEPARATOR);
    }

    /**
     * Displays an error message when the task list file cannot be loaded.
     */
    public void showSavingError(){
        System.out.println(SEPARATOR);
        System.out.println(SAVING_ERROR_MESSAGE);
        System.out.println(SEPARATOR);
    }

    /**
     * Displays an error message when the user inputs an invalid task index.
     */
    public void showIndexOutOfBoundError(){
        System.out.println(SEPARATOR);
        System.out.println(INDEX_BOUND_ERROR_MESSAGE);
        System.out.println(SEPARATOR);
    }

    /**
     * Displays an error message when the user inputs an invalid number format.
     */
    public void showNumberFormatError(){
        System.out.println(SEPARATOR);
        System.out.println(INDEX_FORMAT_ERROR_MESSAGE);
        System.out.println(SEPARATOR);
    }

    /**
     * Displays an error message when the user provides an incomplete command missing the task name.
     *
     * @param e The exception that contains the error message.
     */
    public void showIncompleteCommandError(IncompleteCommandException e){
        System.out.println(SEPARATOR);
        System.out.println(e.getMessage());
        System.out.println(SEPARATOR);
    }

    // ========== Success messages ==========

    /**
     * Displays a message when a task has been successfully added.
     *
     * @param tasks The Object that contains the list of tasks.
     * @param size The size of the task list after adding the new task.
     */
    public void showTaskAdded(TaskList tasks, int size){
        System.out.println(SEPARATOR);
        System.out.println(ADD_TASK_MESSAGE);
        System.out.println(tasks.getList().get(size-1).toString());
    }

    /**
     * Displays a message when a task has been successfully deleted.
     *
     * @param tasks The Object that contains the list of tasks.
     * @param taskNumber The task number that was deleted.
     */
    public void showTaskDeleted(TaskList tasks, int taskNumber){
        System.out.println(SEPARATOR);
        System.out.println(DELETE_TASK_MESSAGE);
        System.out.println(tasks.getList().get(taskNumber - 1).toString());
    }

    /**
     * Displays the current number of tasks in the list.
     *
     * @param tasks The Object that contains the list of tasks.
     */
    public void showCurrentSizeOfList(TaskList tasks){
        System.out.println("Now you have " + tasks.getListSize() + " tasks in the list.");
        System.out.println(SEPARATOR);
    }

    /**
     * Displays a message when a task is successfully marked as done.
     *
     * @param tasks The Object that contains the list of tasks.
     * @param taskNumber The task number that was marked.
     */
    public void showTaskMarked(TaskList tasks, int taskNumber){
        System.out.println(SEPARATOR);
        System.out.println(MARK_TASK_MESSAGE);
        System.out.println(tasks.getList().get(taskNumber - 1).toString());
        System.out.println(SEPARATOR);
    }

    /**
     * Displays a message when a task is successfully unmarked.
     *
     * @param tasks The Object that contains the list of tasks.
     * @param taskNumber The task number that was unmarked.
     */
    public void showTaskUnmarked(TaskList tasks, int taskNumber){
        System.out.println(SEPARATOR);
        System.out.println(UNMARK_TASK_MESSAGE);
        System.out.println(tasks.getList().get(taskNumber - 1).toString());
        System.out.println(SEPARATOR);
    }

    /**
     * Displays all tasks in the task list.
     *
     * @param tasks The Object that contains the list of tasks.
     */
    public void showTaskListed(TaskList tasks){
        System.out.println(SEPARATOR);
        System.out.println(LIST_TASK_MESSAGE);
        for (int i = 0; i < tasks.getList().size(); i++) {
            System.out.println(i + 1 + ". " + tasks.getList().get(i).toString());
        }
        System.out.println(SEPARATOR);
    }

    /**
     * Searches for tasks in the task list that contain the specified keyword.
     *
     * @param tasks The Object that contains the list of tasks.
     * @param keyWord The keyword to search for.
     */
    public void showTaskFound(TaskList tasks, String keyWord) {
        boolean found = false;
        System.out.println(SEPARATOR);
        for (int i = 0; i < tasks.getList().size(); i++) {
            String taskName = tasks.getList().get(i).getTaskName();
            String[] components = taskName.split(SPACE);

            if (Arrays.asList(components).contains(keyWord)) {
                if (!found) {
                    System.out.println(FIND_TASK_MESSAGE);  // Only the 1st find will print message
                    found = true;
                }
                System.out.println(i + 1 + ". " + tasks.getList().get(i).toString());
            }
        }
        if (!found) {
            System.out.println(NOT_FIND_TASK_MESSAGE + keyWord);  // 如果没有找到匹配项，可以打印一个提示消息
        }
        System.out.println(SEPARATOR);
    }


    /**
     * Reads the next command input by the user.
     *
     * @return The command entered by the user.
     */
    public String readCommand(){
        return scanner.nextLine();
    }

    /**
     * Prints a horizontal line separator to the console.
     */
    public void showLine(){
        System.out.println(SEPARATOR);
    }

    /**
     * Prints the specified message to the console.
     *
     * @param message The message to be printed.
     */
    public void print(String message){
        System.out.println(message);
    }

    /**
     * Prints an empty line to the console.
     */
    public void printEmptyLine(){
        System.out.println();
    }
}
