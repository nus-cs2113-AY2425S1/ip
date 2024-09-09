package esme;

import esme.exceptions.EsmeException;
import esme.task.TaskList;

public class Ui {
    private TaskList taskList;
    private static final int SEPARATOR_LENGTH = 120;
    private static final String SEPARATOR = "-".repeat(SEPARATOR_LENGTH);

    public Ui() {
        taskList = new TaskList();
    }

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
        System.out.println("\tTime to work! You got " + taskList.getNumberOfTasks() +
                " tasks waiting for you!");
        displayLine(true);
    }

    /**
     * Checks if the given task index is valid in terms of the task list.
     * 
     * @param index The index to check.
     * @return True if the index is valid, false otherwise.
     */
    public boolean isIndexValid(int index) {
        return (index <= taskList.getNumberOfTasks() && index >= 0);
    }

    public boolean isTaskCompleted(int index) {
        return taskList.getTask(index - 1).hasCompleted();
    }

    /**
     * Marks the task at the given index as done. The index is 1-indexed since
     * arrays are 0-indexed.
     * 
     * @param taskIndex The index of the task to be marked as done.
     */
    public void markTaskInList(int taskIndex) {
        taskList.markTask(taskIndex - 1);
        displayLine(true);
        System.out.println("\tOutstanding! This task is marked as done, and your destiny shines brighter.");
        System.out.println("\t  [X] " + taskList.getTask(taskIndex - 1).getDescription());
        displayLine(true);
    }

    /**
     * Unmarks the task at the given index as done. The index is 1-indexed since
     * arrays are 0-indexed.
     * 
     * @param taskIndex The index of the task to be marked as undone.
     */
    public void unmarkTaskInList(int taskIndex) {
        taskList.unmarkTask(taskIndex - 1);
        displayLine(true);
        System.out.println("\tFear not, for this task remains unfinished. We shall conquer it in due time!");
        System.out.println("\t  [] " + taskList.getTask(taskIndex - 1).getDescription());
        displayLine(true);
    }

    /**
     * Handles the task status command given in the words array.
     * If the command is "mark", it marks the task at the given index.
     * If the command is "unmark", it unmarks the task at the given index.
     * The index should be a valid index in the list, and the command should
     * be one of the above two.
     * If the index is out of range or the command is invalid, it prints an
     * appropriate error message.
     * 
     * @param words The words array containing the command and the index.
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
            int index = Integer.parseInt(words[1]);
            if (!isTaskCompleted(index) && command.equals("unmark")) {
                throw new EsmeException("The stars have revealed that this task is yet to be completed. " +
                        "Finish it before the cosmos frowns upon you.");
            }
            if (isTaskCompleted(index) && command.equals("mark")) {
                throw new EsmeException("This task has already been blessed by the cosmos. " +
                        "Continue your journey with the next task.");
            }
            if (!isIndexValid(index)) {
                throw new EsmeException("Oh dear, it seems the index has wandered beyond the " +
                        "boundaries of our list!");
            }
            toggleTaskStatus(index,command);
        } catch (EsmeException e) {
            displayLine(true);
            System.out.println("\t" + e.getMessage());
            displayLine(true);
        }
    }

    public void toggleTaskStatus(int taskIndex, String command) {
        if (command.equals("mark")) {
            markTaskInList(taskIndex);
        } else {
            unmarkTaskInList(taskIndex);
        }
    }

    public void printTaskList() {
        taskList.printTaskList();
        displayLine(true);
    }

    private static final String esmeLogo = " _____                    \n" +
            "| ____|___ _ __ ___   ___ \n" +
            "|  _| / __| '_ ` _ \\ / _ \\ \n" +
            "| |___\\__ \\ | | | | |  __/ \n" +
            "|_____|___/_| |_| |_|\\___| ";

    /**
     * Prints a line to the console to separate different sections of the UI.
     * If hasIndent is true, the line is indented with 4 spaces.
     * 
     * @param hasIndent Whether the line should be indented or not.
     */
    public void displayLine(boolean hasIndent) {
        if (hasIndent) {
            System.out.println("\t" + SEPARATOR);
        } else {
            System.out.println(SEPARATOR);
        }
    }

    public void greet() {
        displayLine(false);
        System.out.println("Hello! I'm");
        System.out.print(esmeLogo);
        System.out.println(", the Astrologer. The cosmos whispers its secrets to me.");
        displayLine(false);
        System.out.println("How may I assist you today? The stars and I are at your service.");
        displayLine(false);
    }

    public void farewell() {
        displayLine(true);
        System.out.println("\tAu revoir, mon ami! May the cosmos continue to weave a tapestry of fortune" +
                " in your favor!");
        displayLine(true);
    }

    public void promptEmptyInput() {
        displayLine(true);
        System.out.println("\tThe stars are silent... Please share your thoughts so I can guide you " +
                "on your path.");
        displayLine(true);
    }

    public void handleUnknownCommand() {
        displayLine(true);
        System.out.println("\tThe stars are unclear on this command. Could you please try again?");
        displayLine(true);
    }
}
