import java.io.IOException;

/**
 * The Ui class handles all user interface operations. It provides methods to display
 * messages to the user and confirm various operations, such as adding, marking, unmarking,
 * or deleting tasks. It also handles error messages for invalid commands or inputs.
 */
public class Ui {

    /**
     * Prints the entry message when the application starts.
     * It welcomes the user and provides a brief instruction on how to get help.
     */
    public static void printEntry() {
        printSeparator();
        System.out.println("Hello!");
        System.out.println("I am ListBot, how can I help?");
        System.out.println("For list of all valid instructions: help");
        printSeparator();
    }

    /**
     * Prints a line separator to enhance the readability of the console output.
     */
    public static void printSeparator() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the exit message when the user decides to quit the application.
     * It confirms that the list has been saved and says goodbye to the user.
     */
    public static void printExit() {
        printSeparator();
        System.out.println("Your list has been saved successfully.");
        printSeparator();
        System.out.println("Let me know if I can help again!");
        System.out.println("Bye!");
        printSeparator();
    }

    /**
     * Prints a confirmation message after successfully adding a task to the list.
     * It also clears the storage and saves the updated task list.
     *
     * @param task The task that has been added.
     * @throws IOException if an I/O error occurs during saving.
     */
    public static void printAddConfirm(TaskList.Task task) throws IOException {
        printSeparator();
        System.out.println("Added: " + task.toString());
        printSeparator();
        Storage.clear();
        Storage.save();
    }

    /**
     * Prints a confirmation message after successfully marking a task as completed.
     * It also clears the storage and saves the updated task list.
     *
     * @param task The task that has been marked.
     * @throws IOException if an I/O error occurs during saving.
     */
    public static void printMarkConfirm(TaskList.Task task) throws IOException {
        printSeparator();
        System.out.println("Marked the following task: ");
        System.out.println(task.toString());
        printSeparator();
        Storage.clear();
        Storage.save();
    }

    /**
     * Prints a confirmation message after successfully unmarking a task as incomplete.
     * It also clears the storage and saves the updated task list.
     *
     * @param task The task that has been unmarked.
     * @throws IOException if an I/O error occurs during saving.
     */
    public static void printUnmarkConfirm(TaskList.Task task) throws IOException {
        printSeparator();
        System.out.println("Unmarked the following task:");
        System.out.println(task.toString());
        printSeparator();
        Storage.clear();
        Storage.save();
    }

    /**
     * Prints a confirmation message after successfully deleting a task from the list.
     * It also clears the storage and saves the updated task list.
     *
     * @param task The task that has been deleted.
     * @throws IOException if an I/O error occurs during saving.
     */
    public static void printDeleteConfirm(TaskList.Task task) throws IOException {
        printSeparator();
        System.out.println("Deleted the following task:");
        System.out.println(task.toString());
        printSeparator();
        Storage.clear();
        Storage.save();
    }

    /**
     * Prints a message indicating that the user has entered an invalid command.
     * It prompts the user to refer to the help instructions.
     */
    public static void printInvalidCommand() {
        printSeparator();
        System.out.println("I don't understand.");
        System.out.println("For list of all valid instructions: help");
        printSeparator();
    }

    /**
     * Prints an error message when the user provides an invalid deadline format.
     * It also shows the correct syntax for creating a deadline.
     */
    public static void printInvalidDeadline() {
        printSeparator();
        System.out.println("This is an invalid deadline.");
        System.out.println("Here's the deadline creation syntax: ");
        System.out.println("deadline *task* /by *by*");
        System.out.println("For list of all valid instructions: help");
        printSeparator();
    }

    /**
     * Prints an error message when the user provides an invalid todo format.
     * It also shows the correct syntax for creating a todo task.
     */
    public static void printInvalidTodo() {
        printSeparator();
        System.out.println("This is an invalid todo.");
        System.out.println("Here's the todo creation syntax: ");
        System.out.println("todo *task*");
        System.out.println("For list of all valid instructions: help");
        printSeparator();
    }

    /**
     * Prints an error message when the user provides an invalid event format.
     * It also shows the correct syntax for creating an event.
     */
    public static void printInvalidEvent() {
        printSeparator();
        System.out.println("This is an invalid event.");
        System.out.println("Here's the event creation syntax: ");
        System.out.println("event *task* /from *from* /to *to*");
        System.out.println("For list of all valid instructions: help");
        printSeparator();
    }

    /**
     * Prints an error message when the user provides an invalid mark command.
     * It also shows the correct syntax for marking a task as completed.
     */
    public static void printInvalidMark() {
        printSeparator();
        System.out.println("This is an invalid mark statement.");
        System.out.println("Here's the mark syntax: ");
        System.out.println("mark *task_number*");
        System.out.println("For list of all valid instructions: help");
        printSeparator();
    }

    /**
     * Prints an error message when the user provides an invalid unmark command.
     * It also shows the correct syntax for unmarking a task as incomplete.
     */
    public static void printInvalidUnmark() {
        printSeparator();
        System.out.println("This is an invalid unmark statement.");
        System.out.println("Here's the unmark syntax: ");
        System.out.println("unmark *task_number*");
        System.out.println("For list of all valid instructions: help");
        printSeparator();
    }

    /**
     * Prints an error message when the user provides an invalid delete command.
     * It also shows the correct syntax for deleting a task.
     */
    public static void printInvalidDelete() {
        printSeparator();
        System.out.println("This is an invalid delete statement.");
        System.out.println("Here's the delete syntax: ");
        System.out.println("delete *task_number*");
        System.out.println("For list of all valid instructions: help");
        printSeparator();
    }

    /**
     * Prints a message indicating that the task the user is trying to mark, unmark, or delete
     * does not exist in the task list.
     */
    public static void printNonExistentTask() {
        printSeparator();
        System.out.println("This task doesn't exist!");
        printSeparator();
    }

    /**
     * Prints a message indicating that the task the user is trying to mark is already marked.
     */
    public static void printAlreadyMarked() {
        printSeparator();
        System.out.println("This task is already marked!");
        printSeparator();
    }

    /**
     * Prints a message indicating that the task the user is trying to unmark is already unmarked.
     */
    public static void printAlreadyUnmarked() {
        printSeparator();
        System.out.println("This task is already unmarked!");
        printSeparator();
    }

    /**
     * Prints a message indicating that the task list is empty.
     */
    public static void printEmptyList() {
        printSeparator();
        System.out.println("The list is empty!");
        printSeparator();
    }

    /**
     * Prints a message confirming the successful creation of the save file.
     */
    public static void printSaveFileCreated() {
        printSeparator();
        System.out.println("Save file created successfully.");
        printSeparator();
    }

    /**
     * Prints the task list that has been successfully loaded from the save file.
     */
    public static void printLoadConfirm() {
        System.out.println("I've loaded the below save file:");
        for (int i = 0; i < TaskList.listCount; i += 1) {
            System.out.println(TaskList.tasks[i].toString());
        }
        printSeparator();
    }

    /**
     * Prints a list of all valid commands and their corresponding syntax.
     * This serves as a help guide for the user.
     */
    public static void printHelp() {
        printSeparator();
        System.out.println("All actions:");
        System.out.println("To add a deadline: deadline *task* /by *by*");
        System.out.println("To add a todo: todo *task*");
        System.out.println("To add an event: event *task* /from *from* /to *to*");
        System.out.println("To mark a task: mark *task_number*");
        System.out.println("To unmark a task: unmark *task_number*");
        System.out.println("To delete a task: delete *task_number*");
        System.out.println("To list all tasks: list");
        System.out.println("To find a task: find *keyword*");
        System.out.println("To exit: bye");
        printSeparator();
    }

    /**
     * Prints a message indicating that no task in the list contains the specified keyword.
     */
    public static void printNoTaskFound() {
        printSeparator();
        System.out.println("I found no matching tasks in the list!");
        printSeparator();
    }

    /**
     * Prints an error message when the user provides an invalid find format.
     * It also shows the correct syntax for searching for a task.
     */
    public static void printInvalidFind() {
        printSeparator();
        System.out.println("This is an invalid find statement.");
        System.out.println("Here's the find syntax: ");
        System.out.println("find *keyword*");
        System.out.println("For list of all valid instructions: help");
        printSeparator();
    }

}
