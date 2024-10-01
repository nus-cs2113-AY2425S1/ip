package melchizedek;

import melchizedek.task.TaskList;

import java.util.ArrayList;

public class Ui {
    public static final String SEPARATOR = "\t____________________________________________________________";

    /**
     * Method to print a separator line in the terminal.
     */
    public static void printSeparator() {
        System.out.println(SEPARATOR);
    }

    /**
     * Method to print out greetings to the user.
     */
    public static void sayHelloToUser() {
        printSeparator();
        System.out.println("\tHello! I'm Melchizedek.");
        System.out.println("\tHow can I be a blessing to you?");
        printSeparator();
    }

    /**
     * Method to print out parting greetings to the user.
     */
    public static void sayByeToUser() {
        System.out.println("\tGoodbye. Hope to see you again soon! May peace be upon you.");
        printSeparator();
    }

    /**
     * Method to print out a warning that Melchizedek is unable to understand the command.
     */
    public static void printInvalidCommand() {
        System.out.println("\tSorry but I don't understand what you mean :(");
        System.out.println("\t\"help\" to get a list of commands.");
    }

    /**
     * Method to print out the whole list of commands available.
     */
    public static void listCommands() {
        System.out.println("\tHere is the list of commands:");
        System.out.println("\t  to add a todo: todo DESCRIPTION");
        System.out.println("\t  to add a deadline: deadline DESCRIPTION /by BY");
        System.out.println("\t  to add an event: event DESCRIPTION /from FROM /to TO");
        System.out.println("\t  to delete a task: delete TASK_NUMBER");
        System.out.println("\t  to mark a task as done: mark TASK_NUMBER");
        System.out.println("\t  to unmark a task as done: unmark TASK_NUMBER");
        System.out.println("\t  to display all tasks on the list: list");
        System.out.println("\t  to find tasks using keywords: find KEYWORD");
        System.out.println("\t  to exit: bye");
    }

    /**
     * Method to push an error when there is no directory available.
     */
    public static void printDirError() {
        System.err.println("Error. No directory could be created.");
        printSeparator();
    }

    /**
     * Method to push an error when there is no file available.
     */
    public static void printFileError() {
        System.err.println("Error. No file could be created or found.");
        printSeparator();
    }

    /**
     * Method to push an error when the task cannot be loaded properly from the save file.
     */
    public static void printTaskError() {
        System.err.println("Error. Unable to process task.");
        printSeparator();
    }

    /**
     * Method to push an error when the software fails to write into the save file.
     */
    public static void printWriteError() {
        System.err.println("Error. Cannot write to file.");
        printSeparator();
    }

    /**
     * Method to print the full task list.
     *
     * @param taskList The task list
     */
    public static void printTaskList(TaskList taskList) {
        int taskCount = taskList.getTaskCount();
        if (taskCount == 0) {
            System.out.println("\tNo tasks added!");
            return;
        }

        System.out.println("\tSure! Here are the tasks on your list:");

        for (int i = 0; i < taskCount; i++) {
            System.out.print("\t" + (i + 1) + ".");
            System.out.println(taskList.getTaskToString(i));
        }
    }

    /**
     * Method to print a single task.
     *
     * @param taskString The task in printable string format.
     */
    public static void printTask(String taskString) {
        System.out.println("\t  " + taskString);
    }

    /**
     * Method to print a filtered task list, after being filtered by a keyword.
     *
     * @param filteredTaskList The filtered task list
     */
    public static void printFilteredTaskList(ArrayList<String> filteredTaskList) {
        int taskCount = filteredTaskList.size();
        if (taskCount == 0) {
            System.out.println("\tNo matching tasks!");
            return;
        }

        System.out.println("\tHere are the matching tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.print("\t" + (i + 1) + ".");
            System.out.println(filteredTaskList.get(i));
        }
    }

    /**
     * Method to print the number of tasks in the task list.
     *
     * @param taskCount Number of tasks
     */
    public static void printNumberOfTasks(int taskCount) {
        if (taskCount != 1) {
            System.out.println("\tNow you have " + taskCount + " tasks in the list.");
        } else {
            System.out.println("\tNow you have " + taskCount + " task in the list.");
        }
    }

    /**
     * Method to print a task after it has just been added to the task list.
     *
     * @param taskString Task in printable string format
     * @param taskCount Number of tasks in the task list
     */
    public static void printAddedTask(String taskString, int taskCount) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + taskString);
        printNumberOfTasks(taskCount);
    }

    /**
     * Method to print a task after it has just been added to the task list.
     *
     * @param taskString Task in printable string format
     */
    public static void printDeletedTask(String taskString) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + taskString);
    }

    /**
     * Method to print out an example for the "mark" command.
     */
    public static void printMarkExample() {
        System.out.println("\tExample: mark 3");
    }

    /**
     * Method to print out an example for the "unmark" command.
     */
    public static void printUnmarkExample() {
        System.out.println("\tExample: unmark 2" );
    }

    /**
     * Method to print out an example for the "todo" command.
     */
    public static void printTodoExample() {
        System.out.println("\tExample: todo read lecture notes");
    }

    /**
     * Method to print out an example for the "deadline" command.
     */
    public static void printDeadlineExample() {
        System.out.println("\tExample: deadline coding assignment /by 12pm");
    }

    /**
     * Method to print out an example for the "event" command.
     */
    public static void printEventExample() {
        System.out.println("\tExample: event coding lecture /from 2pm /to 4pm" );
    }

    /**
     * Method to print out an example for the "delete" command.
     */
    public static void printDeleteExample() {
        System.out.println("\tExample: delete 1");
    }

    /**
     * Method to print out an example for the "find" command.
     */
    public static void printFindExample() {
        System.out.println("\tExample: find notes");
    }

    /**
     * Method to print out a confirmation of a task that has already been marked previously,
     * but user marks it again.
     */
    public static void printTaskHasAlreadyBeenMarked() {
        System.out.println("\tSure! But it was already marked as done?");
    }

    /**
     * Method to print out confirmation of a task being marked by the user.
     */
    public static void printTaskMarkedAsDone() {
        System.out.println("\tGreat! I've marked this task as done:");
    }

    /**
     * Method to print out a confirmation of a task that has already been unmarked previously,
     * but user unmarks it again.
     */
    public static void printTaskHasAlreadyBeenUnmarked() {
        System.out.println("\tHmmm... it was not done in the first place.");
    }

    /**
     * Method to print out confirmation of a task being unmarked bt the user.
     */
    public static void printTaskUnmarkedAsDone() {
        System.out.println("\tOK, I've marked this task as undone:");
    }

    /**
     * Method to print out a warning that specific keywords are missing,
     * and the command cannot be processed as a result.
     *
     * @param key The missing keyword
     */
    public static void printUnableToProcessWithoutKey(String key) {
        System.out.println("\tUh oh! I cannot process this task without the key " + key + "!");
    }

    /**
     * Method to print out a warning that a task number not within the range of the number of
     * tasks in the task list has been inputted.
     */
    public static void printInvalidTaskNumberException() {
        System.out.println("\tUh oh! Please input a valid task number!");
    }

    /**
     * Method to print out a warning that a task number has not been inputted by the user.
     * @param command The specific command that the user inputted (mark, unmark, delete)
     */
    public static void printUnspecifiedTaskNumber(String command) {
        System.out.println("\tOh no! Please specify which task number to " + command + ".");
    }

    /**
     * Method to print out a warning that the description for a task is missing.
     *
     * @param taskType The specific task type (todo, deadline or event)
     */
    public static void printUnableToProcessWithoutDescription(String taskType) {
        System.out.println("\tUh oh! I cannot create a " + taskType + " with no description!");
    }

    /**
     * Method to print out a warning that no keyword was inputted for the find function.
     */
    public static void printUnableToFindWithoutKeyword() {
        System.out.println("\tOh no! I cannot search without a keyword!");
    }
}
