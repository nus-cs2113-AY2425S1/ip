package ui;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;

import java.time.LocalDate;
import java.util.Scanner;

import static constants.Message.ADD_TASK_SUCCESS_MESSAGE;
import static constants.Message.DELETE_TASK_SUCCESS_MESSAGE;
import static constants.Message.EXISTING_TASKS_MESSAGE;
import static constants.Message.GREETING_MESSAGE;
import static constants.Message.LINE_MESSAGE;
import static constants.Message.LOGO;
import static constants.Message.MARKED_MESSAGE;
import static constants.Message.NO_PENDING_TASKS_MESSAGE;
import static constants.Message.NO_TASK_OF_INTEREST_MESSAGE;
import static constants.Message.SAVE_TASK_LIST_SUCCESS_MESSAGE;
import static constants.Message.SAYONARA_MESSAGE;
import static constants.Message.TASKS_OF_INTEREST_MESSAGE;
import static constants.Message.UNMARKED_MESSAGE;

/**
 * This class is responsible for handling the user interface of Bento.
 * It includes methods for displaying messages to the user and getting user input.
 */
public class Ui {

    /** Scanner for reading user input. */
    private Scanner inputScanner;

    /**
     * Constructor for Ui.
     * Initializes the input scanner to read user input.
     */
    public Ui() {
        inputScanner = new Scanner(System.in);
    }

    /**
     * Prints the Bento logo.
     */
    public void printLogo() {
        System.out.print(LOGO);
    }

    /**
     * Prints a line divider for visual clarity.
     */
    public void printLine() {
        System.out.println(LINE_MESSAGE);
    }

    /**
     * Displays the greeting message and Bento logo to the user.
     */
    public void sayKonichiwa() {
        printLine();
        printLogo();
        System.out.println(GREETING_MESSAGE);
        printLine();
    }

    /**
     * Displays the farewell message to the user.
     */
    public void saySayonara() {
        printLine();
        System.out.println(SAYONARA_MESSAGE);
        printLine();
    }

    /**
     * Retrieves input from the user.
     * @return The user's input as a string.
     */
    public String getUserInput() {
        return inputScanner.nextLine();
    }

    /**
     * Displays a message indicating a task has been added, along with the current task count.
     * @param task The task that was added.
     * @param tasks The list of tasks to display the current task count.
     */
    public void printAddTaskSuccessMessage(String task, TaskList tasks) {
        printLine();
        System.out.printf("%s\n\t\t%s\n%s", ADD_TASK_SUCCESS_MESSAGE, task, getTaskCountMessage(tasks));
        printLine();
    }

    /**
     * Displays a message indicating the task list was saved successfully.
     */
    public void printSaveTaskListSuccessMessage() {
        printLine();
        System.out.println(SAVE_TASK_LIST_SUCCESS_MESSAGE);
        printLine();
    }

    /**
     * Displays the list of tasks in the task list.
     * @param tasks The task list to display.
     */
    public void listTasks(TaskList tasks) {
        if (tasks.getTaskCount() == 0) {
            printAllDoneMessage();
            return;
        }

        printLine();
        System.out.println(EXISTING_TASKS_MESSAGE);
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            System.out.printf("\t%d. %s\n", i + 1, tasks.getTask(i));
        }
        printLine();
    }

    /**
     * Displays tasks of interest (Deadlines or Events) that occur on a specific date.
     * @param dateOfInterest The date to filter tasks by.
     * @param tasks The task list to search through.
     */
    public void listTasksOfInterest(LocalDate dateOfInterest, TaskList tasks) {
        printLine();
        int currentTask = 0;
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            Task task = tasks.getTask(i);
            if (isDeadlineOfInterest(dateOfInterest, task) || isEventOfInterest(dateOfInterest, task)) {
                if (currentTask == 0) {
                    System.out.println(TASKS_OF_INTEREST_MESSAGE);
                }
                System.out.printf("\t%d. %s\n", currentTask + 1, task);
                currentTask++;
            }
        }
        if (currentTask == 0) {
            System.out.println(NO_TASK_OF_INTEREST_MESSAGE);
        }
        printLine();
    }

    /**
     * Displays all tasks of interest in a given task list.
     * @param tasks The task list to display.
     */
    public void listTasksOfInterest(TaskList tasks) {
        printLine();
        System.out.println(TASKS_OF_INTEREST_MESSAGE);
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            System.out.printf("\t%d. %s\n", i + 1, tasks.getTask(i));
        }
        printLine();
    }

    /**
     * Checks if a task is an event that occurs on the specified date.
     * @param dateOfInterest The date to check.
     * @param task The task to check.
     * @return True if the task is an event on the specified date, false otherwise.
     */
    public boolean isEventOfInterest(LocalDate dateOfInterest, Task task) {
        return task instanceof Event && (((Event) task).getFrom().equals(dateOfInterest) ||
                ((Event) task).getTo().equals(dateOfInterest));
    }

    /**
     * Checks if a task is a deadline that is due on the specified date.
     * @param dateOfInterest The date to check.
     * @param task The task to check.
     * @return True if the task is a deadline on the specified date, false otherwise.
     */
    public boolean isDeadlineOfInterest(LocalDate dateOfInterest, Task task) {
        return task instanceof Deadline && ((Deadline) task).getBy().equals(dateOfInterest);
    }

    /**
     * Displays a message indicating a task has been unmarked as not done.
     * @param tasks The task list that contains the task.
     * @param index The index of the task to display.
     */
    public void printUnmarked(TaskList tasks, int index) {
        System.out.println(UNMARKED_MESSAGE);
        System.out.printf("\t\t%s\n", tasks.getTask(index));
    }

    /**
     * Displays a message indicating a task has been marked as done.
     * @param tasks The task list that contains the task.
     * @param index The index of the task to display.
     */
    public void printMarked(TaskList tasks, int index) {
        System.out.println(MARKED_MESSAGE);
        System.out.printf("\t\t%s\n", tasks.getTask(index));
    }

    /**
     * Displays an update message depending on whether a task is marked as done or unmarked as not done.
     * @param tasks The task list that contains the task.
     * @param isDone True if the task is done, false otherwise.
     * @param index The index of the task.
     */
    public void printMarkUpdate(TaskList tasks, boolean isDone, int index) {
        printLine();
        if (isDone) {
            printMarked(tasks, index);
        } else {
            printUnmarked(tasks, index);
        }
        printLine();
    }

    /**
     * Displays a message indicating a task has been deleted, along with the current task count.
     * @param task The task that was deleted.
     * @param tasks The task list to display the current task count.
     */
    public void printDeleteTaskSuccessMessage(Task task, TaskList tasks) {
        printLine();
        System.out.printf("%s\n\t\t%s\n%s", DELETE_TASK_SUCCESS_MESSAGE, task, getTaskCountMessage(tasks));
        printLine();
    }

    /**
     * Retrieves the number of tasks currently in the task list as a message.
     * @param tasks The task list to get the task count.
     * @return A string message showing the current task count.
     */
    public String getTaskCountMessage(TaskList tasks) {
        return String.format("\tYou currently have %d tasks! Way to go, you busy bee!\n", tasks.getTaskCount());
    }

    /**
     * Displays a given error message.
     * @param errorMessage The error message to display.
     */
    public void displayErrorMessage(String errorMessage) {
        System.out.print(errorMessage);
    }

    public void printAllDoneMessage() {
        printLine();
        System.out.println(NO_PENDING_TASKS_MESSAGE);
        printLine();
    }
}
