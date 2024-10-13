package taskmanager;

import tasks.Task;
import tasks.Event;
import tasks.Deadline;
import tasks.ToDo;

import exceptions.TerriException;

import java.util.Arrays;
import java.util.ArrayList;


/**
 * The Tasklist class manages task storage, and handles the validation
 * and addition of new tasks.
 */

public class TaskList {

    private static final ArrayList<Task> tasks = new ArrayList<>(); // Array to store tasks
    public static int taskCounter = 0;  // Counter to track current number of tasks

    public static void listTasks() {
        System.out.println("Here's what you've got in your list:");
        for (int i = 1; i <= taskCounter; i++) {

            // Print generic task description
            System.out.print(i+". " + tasks.get(i - 1).getTypeIcon()
                    + tasks.get(i - 1).getStatusIcon() + tasks.get(i - 1).getTaskName());

            // Print specific task-type information
            switch (tasks.get(i - 1).getTypeIcon()) {
                case "[D]":
                    System.out.println(" (By: " + tasks.get(i - 1).getBy() + ")");
                    continue;

                case "[T]":
                    System.out.println();
                    continue;

                case "[E]":
                    System.out.println(" (From: " + tasks.get(i - 1).getEventStart()
                            + " To: " + tasks.get(i - 1).getEventEnd() + ")");
                    continue;

                default:
                    System.out.println(" (ERROR: OTHER TYPE)");
            }
        }
    }

    private static void printNumberOfTasks() {
        if (taskCounter == 1) {
            System.out.println("There is now (1) logged task/event.");
        } else {
            System.out.println("There are now (" + taskCounter + ") logged tasks/events.");
        }
    }

    // Verifies a user-input string representing a task index
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
        if (taskIndex > TaskList.taskCounter || taskIndex < 0) {
            throw new TerriException("That index is out of bounds! " +
                    "Call 'list' to see how many tasks are in your list!");
        }

        return taskIndex;
    }


    public static void addToDo(String[] keyWord) throws TerriException {

        // Throw exception if input length is not appropriate
        if (keyWord.length < 2) {
            throw new TerriException("Invalid input length. You gotta have a description!");
        }

        // Exclude keyword from task description
        String newToDo = extractSubArray(keyWord,1, keyWord.length);

        tasks.add(taskCounter++, new ToDo(newToDo));
        System.out.println("Just added: " + newToDo + " to your list as a ToDo!");
        printNumberOfTasks();
    }


    // Parse deadline information from user input and log Deadline
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

    public static void addDeadline(String newDeadline, String newBy) throws TerriException {

        tasks.add(taskCounter++, new Deadline(newDeadline, newBy));
        System.out.println("Just added: '" + newDeadline + "' to your list as a Deadline!");
        printNumberOfTasks();
    }


    // Parse event information from user input and log event
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

    public static void addEvent(String newEvent, String From, String To) throws TerriException {

        tasks.add(taskCounter++, new Event(newEvent, From, To));
        System.out.println("Just added: '" + newEvent + "' to your list as an Event!");
        printNumberOfTasks();
    }


    // Updates task isDone field to be (not) completed as indicate by user
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
            tasks.get(taskIndex).setDone(true);
            System.out.println("Just marked that task completed!");
        } else {
            tasks.get(taskIndex).setDone(false);
            System.out.println("Just marked that task as not completed!");
        }

        // Print summary of new state for user assurance
        System.out.println((taskIndex + 1) + ". "
                + tasks.get(taskIndex).getTypeIcon()
                + tasks.get(taskIndex).getStatusIcon()
                + tasks.get(taskIndex).getTaskName());
    }

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
        System.out.println(tasks.get(taskIndex).getTypeIcon()
                + tasks.get(taskIndex).getStatusIcon()
                + tasks.get(taskIndex).getTaskName());

        // Shift tasks left to fill the gap
        for (int i = taskIndex; i < taskCounter - 1; i++) {
            tasks.set(i, tasks.get(i + 1));
        }

        printNumberOfTasks();
    }


    // Return the contents of a subarray as a concatenated string
    public static String extractSubArray(String[] array, int start, int end) {
        return String.join(" ", Arrays.copyOfRange(array, start, end)).trim();
    }

}
