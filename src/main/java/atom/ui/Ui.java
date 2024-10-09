package atom.ui;

import atom.task.Task;
import atom.tasklist.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all interactions with the user.
 */
public class Ui {

    /**
     * Prints a divider line for formatting purposes.
     */
    public void printDivider() {
        System.out.println("__________________________________________________");
    }

    /**
     * Displays the welcome/start-up screen to the user.
     */
    public void showWelcome() {
        printDivider();

        String logo = "    ____   __________  ________  __       __\n"
                + "   / __ \\ |___    ___||  ____  ||  \\     /  |\n"
                + "  / /__\\ \\    |  |    | |    | || |\\\\   //| |\n"
                + " / /    \\ \\   |  |    | |____| || | \\\\_// | |\n"
                + "/_/      \\_\\  |__|    |________||_|  \\_/  |_|\n";

        System.out.println(logo);

        printDivider();

        System.out.println("Hey there! I'm your friendly chatbot, ATOM!");
        System.out.println("How can i assist you today?");

        printDivider();
    }

    /**
     * Returns the full command entered by the user.
     *
     * @return User input.
     */
    public String readCommand() {
        String userInput;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter command: ");
        userInput = scanner.nextLine().trim();

        return userInput;
    }

    /**
     * Prints out all the tasks in the list.
     *
     * @param tasks Task list containing tasks.
     */
    public void printTasksInList(ArrayList<Task> tasks) {
        int index = 1;

        for (Task item : tasks) {

            System.out.print(index + "." + "[" + item.setTaskType() + "]" +
                    "[" + item.getStatus() + "] " + item.getItem());

            if (item.setTaskType().equals("D")) {
                System.out.print(" (by: " + item.getDueDate() + ")");
            } else if (item.setTaskType().equals("E")) {
                System.out.print(" (from: " + item.getStartDate() + " to: " + item.getEndDate() + ")");
            }

            System.out.println();
            index++;
        }
    }

    /**
     * Prints the task list.
     * <p>
     * If the task list is empty, prints an error message instead.
     *
     * @param tasks Task list.
     */
    public void printList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Oh oh! List is empty.");
            return;
        }

        System.out.println("Here is your list:\n");
        printTasksInList(tasks);
    }

    /**
     * Prints out task list containing matching tasks from the <code>find</code> command.
     *
     * @param tasks Task list containing tasks with matching keyword.
     */
    public void printMatchingTasksList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Your search resulted in 0 matches. :(");
            return;
        }

        System.out.println("Your search resulted in " + tasks.size() + " matches.\n");
        System.out.println("Here are the matching tasks that I found:");
        printTasksInList(tasks);
    }

    /**
     * Prints out task list containing tasks filtered by specified date
     * from the <code>filter</code> command.
     *
     * @param tasks Task list containing tasks with matching date.
     * @param date Date to filter tasks by.
     */
    public void printTasksFilteredByDateList(ArrayList<Task> tasks, String date) {
        if (tasks.isEmpty()) {
            System.out.println("Your search resulted in 0 matches. :(");
            return;
        }

        System.out.println("Your search resulted in " + tasks.size() + " matches.\n");
        System.out.println("Here are the tasks occurring on " + date + ":");
        printTasksInList(tasks);
    }

    /**
     * Prints an error message when there is an issue loading the tasks stored in the
     * txt file to the task list.
     */
    public void showLoadingError() {
        System.out.println("File contents corrupted. Error loading contents to list.. :(");
    }

    /**
     * Displays the error message to the user.
     *
     * @param errorMessage Error message.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints an error message when a <code>NumberFormatException</code> occurs.
     */
    public void showNumberFormatExceptionMessage() {
        System.out.println("Task id must be a number!");
        System.out.println("-> Pssst, just a reminder, I'm SPACE sensitive!!");
    }

    /**
     * Prints an error message when the <code>deadline</code> task name
     * is not specified.
     */
    public void showMissingDeadlineNameMessage() {
        System.out.println("Hey!! Your deadline task is missing!!");
    }

    /**
     * Prints an error message when the <code>event</code> task name
     * is not specified.
     */
    public void showMissingEventNameMessage() {
        System.out.println("Really?! An event without a name??");
    }

    /**
     * Prints an error message when the user command entered is
     * an invalid command not recognised by ATOM.
     */
    public void showInvalidCommandMessage() {
        System.out.println("Me no understand what you saying...");
    }

    /**
     * Prints a message showing user that task has successfully been marked as done
     * or undone.
     *
     * @param tasks Task list containing tasks.
     * @param taskId Task id of marked or unmarked task.
     * @param command <code>mark</code> or <code>unmark</code> command
     */
    public void showTaskStatusMessage(TaskList tasks, int taskId, String command) {
        ArrayList<Task> tasksList = tasks.getTasksList();
        Task currTask = tasksList.get(taskId);

        if (command.equals("mark")) {
            System.out.println("Wonderful! Task successfully marked as DONE!");
        } else {
            System.out.println("Got it. Task successfully marked as UNDONE!");
        }
        System.out.print("> [" + currTask.setTaskType() + "]["
                + currTask.getStatus() + "] " + currTask.getItem());

        if (currTask.setTaskType().equals("D")) {
            System.out.print(" (by: " + currTask.getDueDate() + ")");
        } else if (currTask.setTaskType().equals("E")) {
            System.out.print(" (from: " + currTask.getStartDate() + " to: " +
                    currTask.getEndDate() + ")");
        }

        System.out.println();
    }

    /**
     * Prints a message showing user that task was successfully deleted
     * from the task list.
     * <p>
     * The updated task count after deletion is also displayed to the user.
     *
     * @param tasks Task list containing tasks.
     * @param taskId Task id of to-be-deleted task
     */
    public void showDeleteTaskMessage(TaskList tasks, int taskId) {
        ArrayList<Task> tasksList = tasks.getTasksList();
        Task currTask = tasksList.get(taskId);

        System.out.println("Okie Dokie!! Removed task from list:");
        System.out.print("> [" + currTask.setTaskType() + "]" +
                "[" + currTask.getStatus() + "] " + currTask.getItem());

        if (currTask.setTaskType().equals("D")) {
            System.out.print(" (by: " + currTask.getDueDate() + ")");
        } else if (currTask.setTaskType().equals("E")) {
            System.out.print(" (from: " + currTask.getStartDate() + " to: " + currTask.getEndDate() + ")");
        }

        System.out.println();
        System.out.println("You now have " + (tasksList.size() - 1) + " tasks in your list.");
    }

    /**
     * Prints an error message if the day parameter of the date is invalid.
     */
    public void showInvalidDayMessage() {
        System.out.println("DAY entered is invalid!!" +
                "\nThere are only at most 31 days in a month!!");
    }

    /**
     * Prints an error message if the month parameter of the date is invalid.
     */
    public void showInvalidMonthMessage() {
        System.out.println("MONTH entered is invalid!!" +
                "\nThere are only 12 months in a year!!");
    }

    /**
     * Prints an error message if the year parameter of the date is invalid.
     */
    public void showInvalidYearMessage() {
        System.out.println("YEAR entered is invalid!! (YEAR must be >= 2024)" +
                "\nWhat's the point of tracking tasks in the past eh??\n" +
                "\nReminder to enter the full year too. (e.g 2024)");
    }

    /**
     * Prints an error message if the hour parameter of the time is invalid.
     */
    public void showInvalidHourMessage() {
        System.out.println("HOUR entered is invalid!!" +
                "\nHOUR should be between 0 and 24!!");
    }

    /**
     * Prints an error message if the minute parameter of the time is invalid.
     */
    public void showInvalidMinuteMessage() {
        System.out.println("MINUTE entered is invalid!!" +
                "\nThere are only 60 minutes in an hour you know..");
    }

    /**
     * Displays the valid date time format to the user.
     */
    public void showDateTimeFormat() {
        System.out.println("\nHere's the DATE TIME format:");
        System.out.println("-> DD/MM/YYYY HOUR:MIN");
    }

    /**
     * Displays the valid date format to the user.
     */
    public void showDateFormat() {
        System.out.println("\nHere's the DATE format:");
        System.out.println("-> DD/MM/YYYY");
    }

    /**
     * Prints an error message if the date time format is invalid.
     */
    public void showInvalidDateTimeFormatMessage() {
        System.out.println("Oops!! Wrong date time format");
        showDateTimeFormat();
    }

    /**
     * Prints an error message if the date and/or time parameters
     * in the date time format are invalid.
     */
    public void showInvalidDateTimeParamsMessage() {
        System.out.println("Oh man.. Cannot identify date or time.");
        System.out.println("DATE or TIME doesn't conform with the format.");
        showDateTimeFormat();
    }

    /**
     * Prints an error message if the date format is invalid.
     */
    public void showInvalidDateFormatMessage() {
        System.out.println("Oops!! Wrong date format");
        showDateFormat();
    }

    /**
     * Prints an error message if the date parameters in the date format are invalid.
     */
    public void showInvalidDateParamsMessage() {
        System.out.println("Sorry.. Cannot identify date.");
        System.out.println("DATE doesn't conform with the format.");
        showDateFormat();
    }

    /**
     * Prints an error message if the date time format entered by user
     * does not comply with the format specified by <code>DateTimeFormatter</code>.
     */
    public void showDateTimeParseErrorMessage() {
        System.out.println("Erm.. Error in parsing date or time..");
        showDateTimeFormat();
        System.out.println("\nRemember to add a \"0\" in front for values 0 to 9.");

    }

    /**
     * Prints an error message if the date format entered by user
     * does not comply with the format specified by <code>DateTimeFormatter</code>.
     */
    public void showDateParseErrorMessage() {
        System.out.println("Erm.. Error in parsing date..");
        showDateFormat();
        System.out.println("\nRemember to add a \"0\" in front for values 0 to 9.");

    }

    /**
     * Prints the exit message when user exits the program.
     */
    public void showExitMessage() {
        System.out.println("Bye Bye. See ya soon!");
    }
}
