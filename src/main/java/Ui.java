import java.util.ArrayList;
import java.util.Scanner;

/**
 * Provides user interface functionalities
 */

public class Ui {
    static Scanner in = new Scanner(System.in);

    /**
     * Display welcome message
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Anke.");
        System.out.println("What can I do for you?\n");
    }

    /**
     * Display goodbye message
     */
    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    /**
     * Read and trim user command
     */
    public String readCommand() {
        String line;
        line = in.nextLine().trim();
        return line;
    }

    /**
     * Display no input file
     */
    public void showLoadingError() {
        System.out.println("No input file found.\n");
    }

    /**
     * Display default error message
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Display when task successfully added to TaskList
     */
    public static void showAddTask() {
        System.out.println("Got it. I've added this task:");
        System.out.println(TaskList.getTask(Storage.getCount()));
        Storage.incrementCount();
        System.out.println("Now you have " + Storage.getCount() + " tasks in the list.\n");
    }

    /**
     * Display TaskList
     */
    public static void printList() {
        for (int i = 0; i < Storage.getCount(); i++) {
            System.out.println((i + 1) + ". " + TaskList.getTask(i));
        }
        System.out.println("");
    }

    /**
     * Display functionalities of chatbot when input command is unknown
     */
    public static void handleWrongFormat() {
        System.out.println("Please follow the format (parameter inside {} must be non-empty!) : \n");
        System.out.println("list : visualizing tasks");
        System.out.println("mark {int n} : set task number {n} as done");
        System.out.println("unmark {int n} : set task number {n} as not done");
        System.out.println("todo {String s} : create todo with description {s}");
        System.out.println("deadline {String s1} /by {String s2} : create deadline with description {s1} and due date {s2}");
        System.out.println("event {String s1} /from {String s2} /to {String s3} : create event with description {s1} from {s2} to {s3}");
        System.out.println("delete {int n} : remove task number {n} from the list");
        System.out.println("find {String s} : find tasks with description containing s as substring");
        System.out.println("bye : quit the program\n");
    }

    /**
     * Display empty task description error
     */
    public void printEmptyTaskException() {
        System.out.println("The description of a task cannot be empty.\n");
    }

    /**
     * Display empty deadline time error
     */
    public void printEmptyByOrFromException(String command, String ByOrFrom) {
        System.out.println("Please enter the " + command + " after \"/" + ByOrFrom + "\". \n");
    }

    /**
     * Display empty event end time error
     */
    public void printEmptyToException() {
        System.out.println("Please enter the end time of event after \"/to\". \n");
    }

    /**
     * Display invalid number error
     *
     * @param command executing upon reaching this error
     */
    public void printNumberFormatException(String command) {
        System.out.println("Please enter a valid number after "+ command + "\n");
    }

    /**
     * Display index out of range error
     */
    public void printIndexOutOfRangeException() {
        System.out.println("Please enter a task index from 1 to " + Storage.getCount() + "\n");
    }

    /**
     * Display error when marking marked task or unmarking unmarked task
     *
     * @param MARK_FLAG indicates marking marked task or unmarking unmarked task situation
     */
    public void TaskSameStateException(String MARK_FLAG) {
        System.out.println("Task is already " + MARK_FLAG + "ed\n");
    }

    /**
     * Display message when unmark a task successfully
     *
     * @param tasks taskList containing all tasks
     * @param index of the task unmarked in taskList
     */
    public void printUnmarkTask(ArrayList<Task> tasks, int index) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index - 1).toString() + "\n");
    }

    /**
     * Display message when mark a task successfully
     *
     * @param tasks taskList containing all tasks
     * @param index of the task marked in taskList
     */
    public void printMarkTask(ArrayList<Task> tasks, int index) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index - 1).toString() + "\n");
    }

    /**
     * Display message when delete a task successfully
     *
     * @param tasks taskList containing all tasks
     * @param index of the task removed in taskList
     */
    public void printDeleteTask(ArrayList<Task> tasks, int index) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(tasks.get(index - 1));
        System.out.println("Now you have " + (Storage.getCount() - 1) + " tasks in the list.\n");
    }

    /**
     * Display error message when a case should not be reached by design is reached
     */
    public void printError() {
        System.out.println("Error! Something is wrong with the code\n");
    }

    /**
     * Display start finding task message
     */
    public void printFind() {
        System.out.println("Tasks found:\n");
    }

    /**
     * Display task that has been found
     *
     * @param index of the task found in taskList
     */
    public void printFindTask(int index) {
        System.out.println(TaskList.getTask(index) + "\n");
    }
}
