package taskmanager;

import tasks.Task;
import tasks.Event;
import tasks.Deadline;
import tasks.ToDo;

import exceptions.TerriException;

import java.util.Arrays;
import java.util.ArrayList;


/**
 * The {@code TaskList} class is responsible for managing a collection of tasks, including ToDos,
 * Events, and Deadlines. It provides methods for adding, deleting, listing, and updating tasks,
 * as well as validating user inputs. The class uses an internal task counter to track the number
 * of tasks and saves the list of tasks to external storage after each modification.
 */

public class TaskList {

    private static ArrayList<Task> tasksStorage = new ArrayList<>(); // Array to store tasks

    /**
     * Static block to initialize tasks from the external storage when the {@code TaskList} class
     * is loaded. This ensures that the task list persists across application sessions.
     */
    static {
        tasksStorage = Storage.loadTasks(); // Load tasks from the file when TaskList is created
    }

    /**
     * Counter that tracks the current number of tasks stored in the list.
     */
    public static int taskCounter = 0;  // Counter to track current number of tasks

    /**
     * Displays the current list of tasks with their status and additional task-type-specific details
     * (e.g., deadlines, event times). The tasks are printed in a numbered list, starting from 1.
     */
    public static void listTasks() {
        System.out.println("Here's what you've got in your list:");
        for (int i = 1; i <= taskCounter; i++) {

            // Print generic task description
            System.out.print(i+". " + tasksStorage.get(i - 1).getTypeIcon()
                    + tasksStorage.get(i - 1).getStatusIcon() + tasksStorage.get(i - 1).getTaskName());

            // Print specific task-type information
            switch (tasksStorage.get(i - 1).getTypeIcon()) {
                case "[D]":
                    System.out.println(" (By: " + tasksStorage.get(i - 1).getBy() + ")");
                    continue;

                case "[T]":
                    System.out.println();
                    continue;

                case "[E]":
                    System.out.println(" (From: " + tasksStorage.get(i - 1).getEventStart()
                            + " To: " + tasksStorage.get(i - 1).getEventEnd() + ")");
                    continue;

                default:
                    System.out.println(" (ERROR: OTHER TYPE)");
            }
        }
    }

    /**
     * Prints the total number of tasks currently logged in the list, providing user feedback after
     * task-related operations such as addition or deletion.
     */
    private static void printNumberOfTasks() {
        if (taskCounter == 1) {
            System.out.println("There is now (1) logged task/event.");
        } else {
            System.out.println("There are now (" + tasksStorage.size() + ") logged tasks/events.");
        }
    }

    /**
     * Validates the user-input string representing a task index and returns the corresponding
     * zero-based index in the task storage list. If the index is invalid or non-numeric, it throws
     * a {@code TerriException}.
     *
     * @param userString the user input representing the task index.
     * @return the zero-based index of the task.
     * @throws TerriException if the input is not a valid task index or is out of bounds.
     */
    public static int handleTaskIndex (String userString) throws TerriException {

        int taskIndex;

        // Throw exception if user has input a non-numeric index string
        try {
            // Reduce user input integer by 1 to correspond to actual array index
            taskIndex = Integer.parseInt(userString) - 1;
        } catch (NumberFormatException e) {
            throw new TerriException("That's... not a numeral, ya know.");
        }

        // Throw exception if index referred to does not exist
        if (taskIndex > tasksStorage.size() || taskIndex < 0) {
            throw new TerriException("That index is out of bounds! " +
                    "Call 'list' to see how many tasks are in your list!");
        }

        return taskIndex;
    }

    /**
     * Adds a new ToDo task to the task list. The task description is extracted from the user input.
     * The task is then saved to storage.
     *
     * @param keyWord the array containing the keyword and description provided by the user.
     * @throws TerriException if the input is too short to form a valid ToDo task.
     */
    public static void addToDo(String[] keyWord) throws TerriException {

        // Throw exception if input length is not appropriate
        if (keyWord.length < 2) {
            throw new TerriException("Invalid input length. You gotta have a description!");
        }

        // Exclude keyword from task description
        String newToDo = extractSubArray(keyWord,1, keyWord.length);

        tasksStorage.add(taskCounter++, new ToDo(newToDo));
        System.out.println("Just added: " + newToDo + " to your list as a ToDo!");
        printNumberOfTasks();

        Storage.saveTasks(tasksStorage); // Save tasks after modification
    }


    /**
     * Handles user input to create a new Deadline task. The method extracts both the task description
     * and the due date from the user input and saves the task to storage.
     *
     * @param keyWord the array containing the keyword, description, and due date.
     * @throws TerriException if the input lacks a description or a due date.
     */
    public static void handleDeadline(String[] keyWord) throws TerriException {

        // Throw exception if input length is not appropriate
        if (keyWord.length < 2) {
            throw new TerriException("Invalid input length. You gotta have a description!");
        }

        // Throw exception if input length is not appropriate
        if (keyWord.length < 3) {
            throw new TerriException("Invalid input length. You gotta have a due date!");
        }

        String newBy = null;
        String newDeadline = null;
        StringBuilder tempDeadlineInfo = new StringBuilder();

        boolean dueDateFound = false;

        // Iterate through user input to concatenate
        // deadline description/date information
        for (int i = 1; i < keyWord.length; i++) {
            if (keyWord[i].equals("/by")) {
                newBy = extractSubArray(keyWord, i+1, keyWord.length);
                newDeadline = tempDeadlineInfo.toString().trim();
                dueDateFound = true;
                break;
            }
            tempDeadlineInfo.append(keyWord[i]).append(" ");
        }

        // Throw exception if no /by date provided
        if (!dueDateFound) {
            throw new TerriException("You haven't provided a due date!");
        }

        TaskList.addDeadline(newDeadline, newBy);
    }

    /**
     * Adds a new Deadline task to the task list with the specified description and due date.
     * The task is then saved to storage.
     *
     * @param newDeadline the description of the Deadline task.
     * @param newBy the due date of the Deadline task.
     * @throws TerriException if there is an issue adding the task.
     */
    public static void addDeadline(String newDeadline, String newBy) throws TerriException {

        tasksStorage.add(taskCounter++, new Deadline(newDeadline, newBy));
        System.out.println("Just added: '" + newDeadline + "' to your list as a Deadline!");

        printNumberOfTasks();
        Storage.saveTasks(tasksStorage); // Save tasks after modification
    }


    /**
     * Handles user input to create a new Event task. It extracts the event description, start time,
     * and end time from the user input, and saves the task to storage.
     *
     * @param keyWord the array containing the keyword, description, start time, and end time.
     * @throws TerriException if the input lacks a description, start time, or end time.
     */
    public static void handleEvent(String[] keyWord) throws TerriException {


        // Throw exception if input length is not appropriate
        if (keyWord.length < 2) {
            throw new TerriException("Invalid input length. You gotta have a description!");
        }

        int startIdx = 0;
        int endIdx = 0;

        // Locate time information in user-input
        for (int i = 0; i < keyWord.length; i++) {
            if (keyWord[i].equals("/from")) startIdx = i;
            if (keyWord[i].equals("/to")) {
                endIdx = i;
                break;
            }
        }

        // Throw exception if event timing info is missing
        if (startIdx == 0 || endIdx == 0) {
            throw new TerriException("You haven't provided a start/end time!");
        }

        String newDescription = extractSubArray(keyWord, 1, startIdx);
        String newStart = extractSubArray(keyWord, startIdx + 1, endIdx);
        String newEnd = extractSubArray(keyWord, endIdx + 1, keyWord.length);

        TaskList.addEvent(newDescription, newStart, newEnd);
    }

    /**
     * Adds a new Event task to the task list with the specified description, start time, and end time.
     * The task is then saved to storage.
     *
     * @param newEvent the description of the Event task.
     * @param From the start time of the Event.
     * @param To the end time of the Event.
     * @throws TerriException if there is an issue adding the task.
     */
    public static void addEvent(String newEvent, String From, String To) throws TerriException {

        tasksStorage.add(taskCounter++, new Event(newEvent, From, To));
        System.out.println("Just added: '" + newEvent + "' to your list as an Event!");

        printNumberOfTasks();
        Storage.saveTasks(tasksStorage); // Save tasks after modification
    }


    /**
     * Marks or unmarks a task as completed based on the user's input. The method modifies the task's
     * completion status and provides feedback to the user. It supports both marking as "done" and
     * unmarking (not done).
     *
     * @param keyWord the array containing the keyword and task index.
     * @param desiredState {@code true} if marking as done, {@code false} if marking as not done.
     * @throws TerriException if the input is invalid or if the task index is out of bounds.
     */
    public static void handleSetDone(String[] keyWord, boolean desiredState) throws TerriException {

        // Throw exception if input length and type is not appropriate
        if (keyWord.length < 2) {
            throw new TerriException("Invalid input length. " +
                    "You gotta specify the index of the task you want to (un)mark!");
        }

        /* Throws exception if user-input index is inappropriate,
            else assigns it for use
         */
        int taskIndex = handleTaskIndex(keyWord[1]);

        // (un)Mark task as indicated by user
        if (desiredState) {
            tasksStorage.get(taskIndex).setDone(true);
            System.out.println("Just marked that task completed!");
        } else {
            tasksStorage.get(taskIndex).setDone(false);
            System.out.println("Just marked that task as not completed!");
        }

        // Print summary of new state for user assurance
        System.out.println((taskIndex + 1) + ". "
                + tasksStorage.get(taskIndex).getTypeIcon()
                + tasksStorage.get(taskIndex).getStatusIcon()
                + tasksStorage.get(taskIndex).getTaskName());
    }

    /**
     * Deletes a task from the list based on the user-input task index. The method validates the index,
     * removes the task, and saves the updated list to storage.
     *
     * @param userString the array containing the keyword and task index.
     * @throws TerriException if the input is invalid or the task index is out of bounds.
     */
    public static void deleteTask(String[] userString) throws TerriException {

        // Throw exception if input length is not appropriate
        if (userString.length < 2) {
            throw new TerriException("Invalid input length. " +
                    "You gotta specify the index of the task you want to delete!");
        }

        // Verify the user-input string is a valid task index
        int taskIndex = handleTaskIndex(userString[1]);

        // Output the task being deleted
        System.out.println("Sure thing! I've removed this task: ");
        System.out.println(tasksStorage.get(taskIndex).getTypeIcon()
                + tasksStorage.get(taskIndex).getStatusIcon()
                + tasksStorage.get(taskIndex).getTaskName());

        // Remove the task using ArrayList's remove method
        tasksStorage.remove(taskIndex);
        taskCounter--;

        printNumberOfTasks();
        Storage.saveTasks(tasksStorage); // Save tasks after modification
    }


    /**
     * Extracts a subarray from a given array and returns it as a concatenated string.
     *
     * @param array the array from which to extract the subarray.
     * @param start the start index (inclusive) of the subarray.
     * @param end the end index (exclusive) of the subarray.
     * @return a concatenated string of the subarray's contents.
     */
    public static String extractSubArray(String[] array, int start, int end) {
        return String.join(" ", Arrays.copyOfRange(array, start, end)).trim();
    }

}
