package esme.ui;

import esme.exceptions.EsmeException;
import esme.task.Task;
import esme.task.TaskList;

import java.util.ArrayList;

/**
 * This class represents the user interface of the application.
 * It contains methods for handling user input, storing and retrieving tasks, and printing messages to the user.
 * It also contains methods for validating user input and handling exceptions.
 */
public class Ui {
    private static final int SEPARATOR_LENGTH = 120;
    private static final String SEPARATOR = "-".repeat(SEPARATOR_LENGTH);
    private static final String HELP_MESSAGE = """
            Available commands:
            \t- bye: Exit the application.
            \t- todo [description]: Add a new todo task.
            \t- deadline [description] /by [YYYY-MM-DD]: Add a new task with a deadline.
            \t- event [description] /from [YYYY-MM-DD] /to [YYYY-MM-DD]: Add a new event.
            \t- mark [task number]: Mark a task as completed.
            \t- unmark [task number]: Unmark a completed task.
            \t- delete [task number]: Delete a task.
            \t- list: List all tasks.
            \t- task in [YYYY-MM-DD]: List all task in the same year and month.
            \t- find [keyword]: Find a task by keyword.
            \t- help: Show this help message.""";
    private static final String esmeLogo = """
             _____                   \s
            | ____|___ _ __ ___   ___\s
            |  _| / __| '_ ` _ \\ / _ \\\s
            | |___\\__ \\ | | | | |  __/\s
            |_____|___/_| |_| |_|\\___|\s""";

    private TaskList taskList;
    public Ui() {
        taskList = new TaskList();
    }

    public void callToWork() {
        System.out.println("\tTime to work! You got " + taskList.getNumberOfTasks() +
                " tasks waiting for you!");
        displayLine(true);
    }

    /**
     * Converts a given string to an integer, or throws an EsmeException if the conversion fails.
     * This method is used to validate user input when asking for a task index.
     *
     * @param str The string to be converted.
     * @return The integer value of the string.
     * @throws EsmeException If the string cannot be converted to an integer.
     */
    public int generateIndex(String str) throws EsmeException {
        int index;
        try {
            index = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new EsmeException(str + " is not a number!");
        }
        return index;
    }

    /**
     * Handles the delete task command given in the words array.
     * If the command is "delete", it deletes the task at the given index.
     * If the command is not valid, it throws an EsmeException.
     * If the index is not valid, it throws an EsmeException.
     * If the task is successfully deleted, it prints a message to the user
     * and calls callToWork() to print the number of tasks left.
     * If an exception is thrown, it prints an error message to the user and returns without calling callToWork().
     *
     * @param words The words array containing the command and index.
     */
    public void deleteTaskFromList(String[] words) {
        String command = words[0];
        String description;
        try {
            if (words.length != 2) {
                throw new EsmeException("Error: Wrong format! Please use the format: command index (e.g., '" +
                        command + " 1')");
            }
            int index = generateIndex(words[1]);
            if (!isIndexValid(index)) {
                throw new EsmeException("Oh dear, it seems the index has wandered beyond the " +
                        "boundaries of our list! Type 'list' for the index.");
            }
            description = taskList.deleteTask(index);
        } catch (EsmeException e) {
            displayLine(true);
            System.out.println("\t" + e.getMessage());
            displayLine(true);
            return;
        }
        displayLine(true);
        System.out.println("\t" + description + " has been removed from your destiny!");
        displayLine(true);
        callToWork();
    }

    /**
     * Returns an ArrayList containing all the tasks.
     * This method retrieves the tasks from the task list, formatted to indicate their completion status.
     *
     * @return An ArrayList containing the formatted tasks.
     */
    public ArrayList<String> getFormattedTasks() {
        return taskList.getFormattedTasks();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getNumberOfTasks() {
        return taskList.getNumberOfTasks();
    }

    /**
     * Adds a new task to the list based on the given command and input.
     * The command can be one of "todo", "deadline", or "event", and the
     * input should be in the format required for the corresponding task type.
     * If the command or input is invalid, an EsmeException is thrown.
     *
     * @param command The command to add the correct type of task.
     * @param input The input the user has provided.
     */
    public void addTaskToList(String command, String input) {
        String description;
        try {
            switch (command) {
            case "todo":
                description = taskList.addTodoTask(input);
                break;
            case "deadline":
                description = taskList.addDeadlineTask(input);
                break;
            case "event":
                description = taskList.addEventTask(input);
                break;
            default:
                throw new EsmeException("Invalid command");
            }
        } catch (EsmeException e) {
            displayLine(true);
            System.out.println("\t" + e.getMessage());
            displayLine(true);
            return;
        }
        displayLine(true);
        System.out.println("\tThe stars have aligned and " + description + " is now part of your destiny!");
        displayLine(true);
        callToWork();
    }

    /**
     * Returns true if the given index is valid, meaning it is within the range of the list size and greater than zero.
     * 
     * @param index The index to be validated.
     * @return True if the index is valid, false otherwise.
     */
    public boolean isIndexValid(int index) {
        return (index <= taskList.getNumberOfTasks() && index > 0);
    }


    /**
     * Returns true if the task at the given index is completed, false otherwise.
     * The index on the Ui is 1-based, meaning the first task is at index 1.
     *
     * @param index The index of the task.
     * @return True if the task is completed, false otherwise.
     */
    public boolean isTaskCompleted(int index) {
        return taskList.getTask(index - 1).hasCompleted();
    }

    /**
     * Marks the task at the given index as completed.
     * The index on the Ui is 1-based, meaning the first task is at index 1.
     * 
     * @param taskIndex The index of the task to be marked.
     */
    public void markTaskInList(int taskIndex) {
        taskList.markTask(taskIndex - 1);
        printTaskStatusMessage(taskIndex,true);
    }

    /**
     * Prints out Ui message on task status when marking or unmarking tasks.
     *
     * @param taskIndex The index of the task.
     * @param isMark True if function is called when marking a task, else false
     */
    public void printTaskStatusMessage(int taskIndex, boolean isMark) {
        displayLine(true);
        if (isMark) {
            System.out.println("\tOutstanding! This task is marked as done, and your destiny shines brighter.");
            System.out.println("\t  [X] " + taskList.getTask(taskIndex - 1).getDescription());
        } else {
            System.out.println("\tFear not, for this task remains unfinished. We shall conquer it in due time!");
            System.out.println("\t  [] " + taskList.getTask(taskIndex - 1).getDescription());
        }
        displayLine(true);
    }

    /**
     * Unmarks the task at the given index as uncompleted.
     * The index on the Ui is 1-based, meaning the first task is at index 1.
     * 
     * @param taskIndex The index of the task to be unmarked.
     */
    public void unmarkTaskInList(int taskIndex) {
        taskList.unmarkTask(taskIndex - 1);
        printTaskStatusMessage(taskIndex,false);
    }


    /**
     * Handles the task status command given in the words array.
     * If the command is "mark" or "unmark", it marks or unmarks the task at the given
     * index.
     * If the command or index is invalid, an EsmeException is thrown.
     * If the task is successfully marked or unmarked, a success message is printed to the user.
     * If an exception is thrown, an error message is printed to the user.
     * 
     * @param words The words array containing the command and index.
     */
    public void handleTaskStatus(String[] words) {
        String command = words[0];
        try {
            if (taskList.getNumberOfTasks() <= 0) {
                throw new EsmeException("Add some tasks! Currently, there are no tasks to be " +
                        command + "ed.");
            }
            if (words.length != 2) {
                throw new EsmeException("Error: Wrong format! Please use the format: command index (e.g., '" +
                        command + " 1')");
            }
            int index = generateIndex(words[1]);
            if (!isIndexValid(index)) {
                throw new EsmeException("Oh dear, it seems the index has wandered beyond the " +
                        "boundaries of our list! Type 'list' for the index.");
            }
            if (!isTaskCompleted(index) && command.equals("unmark")) {
                throw new EsmeException("The stars have revealed that this task is yet to be completed. " +
                        "Finish it before the cosmos frowns upon you.");
            }
            if (isTaskCompleted(index) && command.equals("mark")) {
                throw new EsmeException("This task has already been blessed by the cosmos. " +
                        "Continue your journey with the next task.");
            }
            toggleTaskStatus(index,command);
        } catch (EsmeException e) {
            displayLine(true);
            System.out.println("\t" + e.getMessage());
            displayLine(true);
        }
    }

    /**
     * Toggles the task status based on the given command and index.
     * If the command is "mark", it marks the task at the given index as completed.
     * If the command is "unmark", it unmarks the task at the given index as uncompleted.
     *
     * @param taskIndex The index of the task to be toggled.
     * @param command The command to toggle the task status.
     */
    public void toggleTaskStatus(int taskIndex, String command) {
        if (command.equals("mark")) {
            markTaskInList(taskIndex);
        } else {
            unmarkTaskInList(taskIndex);
        }
    }

    /**
     * Prints out the task list.
     * This method displays the tasks in the task list, indicating their completion status.
     */
    public void printTaskList() {
        taskList.printTaskList();
        displayLine(true);
    }

    /**
     * Prints a line separator with or without indentation.
     * The line separator is a sequence of 40 '-' characters.
     * If hasIndent is true, the line separator is indented with a tab character.
     * If hasIndent is false, the line separator is not indented.
     * 
     * @param hasIndent True if the line separator should be indented, false otherwise.
     */
    public void displayLine(boolean hasIndent) {
        if (hasIndent) {
            System.out.println("\t" + SEPARATOR);
        } else {
            System.out.println(SEPARATOR);
        }
    }

    /**
     * Prints out the tasks that are similar to the given line of input.
     * This method displays the tasks that contain the given keyword, indicating their completion status.
     * If no similar tasks are found, a message is printed to the user.
     * If an exception is thrown, an error message is printed to the user.
     * 
     * @param line The line of input containing the keyword to search for.
     */
    public void printTaskFound(String line) {
        try {
            ArrayList<Task> list = taskList.findTask(line);
            displayLine(true);
            if (list.isEmpty()) {
                System.out.println("\tNo similar tasks found!");
            }
            for (Task task : list) {
                System.out.println("\t" + task);
            }
            System.out.println("\tType 'list' to see the index to delete, mark, unmark task!");
            displayLine(true);
        } catch (EsmeException e) {
            displayLine(true);
            System.out.println("\t" + e.getMessage());
            displayLine(true);
        }
    }

    /**
     * Prints out the tasks that occur within the given month and year.
     * The tasks are printed in the format specified in the Task class.
     * If there are no tasks in the given month and year, a message is printed
     * to the user.
     * If the task format is incorrect, an EsmeException is thrown.
     * If the task format is correct, the tasks are printed and the user is prompted
     * to type 'list' to see the index of the tasks to delete, mark, unmark.
     * If an exception is thrown, an error message is printed to the user.
     * 
     * @param words The words array containing the task format
     */
    public void printTasksIn(String[] words) {
        try {
            ArrayList<Task> list = taskList.getTasksIn(words);
            displayLine(true);
            if (list.isEmpty()) {
                System.out.println("\tNo task in this month and year!");
            }
            for (Task task : list) {
                System.out.println("\t" + task);
            }
            System.out.println("\tType 'list' to see the index to delete, mark, unmark task!");
            displayLine(true);
        } catch (EsmeException e) {
            displayLine(true);
            System.out.println("\t" + e.getMessage());
            displayLine(true);
        }
    }

    /**
     * Prints a greeting message to the user.
     * The message is an ASCII art of the Esme logo, followed by a message
     * asking the user how they can be assisted.
     */
    public void greet() {
        displayLine(false);
        System.out.println("Hello! I'm");
        System.out.print(esmeLogo);
        System.out.println(", the Astrologer. The cosmos whispers its secrets to me.");
        displayLine(false);
        System.out.println("How may I assist you today? The stars and I are at your service.");
        displayLine(false);
    }

    /**
     * Prints a farewell message to the user, bidding them adieu and wishing
     * them good fortune in their endeavors.
     */
    public void farewell() {
        displayLine(true);
        System.out.println("\tAu revoir, mon ami! May the cosmos continue to weave a tapestry of fortune" +
                " in your favor!");
        displayLine(true);
    }

    /**
     * Prints a message to the user asking them to share their thoughts when they
     * enter an empty command.
     */
    public void promptEmptyInput() {
        displayLine(true);
        System.out.println("\tThe stars are silent... Please share your thoughts so I can guide you " +
                "on your path.");
        displayLine(true);
    }

    /**
     * Prints a message to the user asking them to re-enter their command when
     * they enter an unknown command.
     */
    public void handleUnknownCommand() {
        displayLine(true);
        System.out.println("\tThe stars are unclear on this command. Could you please try again? " +
                "Type \"help\" for more information.");
        displayLine(true);
    }

    /**
     * Prints an error message when the tasks cannot be saved to the external file.
     * The message is an error message that is printed to the user when the tasks
     * cannot be saved to the external file. The message explains the options the
     * user has when the error occurs.
     */
    public void printSaveErrorMessage() {
        displayLine(true);
        System.out.println("Error saving tasks to external drive! You can choose to CTRL + C to end the program " +
                "or redo your tasks management.");
        displayLine(true);
    }

    /**
     * Prints an error message to the user when the program cannot detect the "tasklist.txt" file in the root directory
     * or create the file.
     * The message explains the option the user has when the error occurs, which is to ensure the file "tasklist.txt"
     * is in the root directory.
     */
    public void printCreateFileError() {
        displayLine(false);
        System.out.println("My sincere apologies. I can't seem to detect the file or create the file for you." +
                " Please ensure the file \"tasklist.txt\" is in the root directory.");
        displayLine(false);
    }

    /**
     * Prints the help message to the user.
     * The message is a formatted string that displays all the available commands
     * and their descriptions.
     */
    public void printHelpMessage() {
        displayLine(true);

        System.out.println("\t" + HELP_MESSAGE);
        displayLine(true);
    }
}
