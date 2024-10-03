import java.util.Scanner;
import tasks.*;

public class Ui {
    private final Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
    }

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void sayHello() {
        printHorizontalLine();
        System.out.println("Hola! I'm Crystal.\n"
            + "What can I do for you today?");
        printHorizontalLine();
    }

    public static void sayBye() {
        System.out.println("Adios, hasta luego!");
        printHorizontalLine();
    }

    /**
     * Method will be called whenever any form of task is added.
     * It will print the task added in the correct format, as well as inform
     * users the number of tasks in the current list.
     */
    public static void printAddedTaskMessage() {
        System.out.println("Got it! I have added this task:");
        TaskList.taskCount++;
        System.out.println(TaskList.tasks.get(TaskList.taskCount - 1).toString());
        System.out.println("Now you have " + TaskList.taskCount + " tasks in the list.");
        printHorizontalLine();
    }

    /**
     * Method will be called whenever any exceptions are called.
     * It will print any existing exception message in the exception itself,
     * as well as print its own message to inform users to repeat.
     *
     * @param e the exception invoked
     */
    public static void printExceptionMessage(Exception e) {
        String message = e.getMessage();
        if (message != null && !message.isEmpty()) {
            System.out.print(message);
        }
        System.out.println(" Can you repeat it again?");
        printHorizontalLine();
    }

    public static void printMarkedMessage(Task t) {
        System.out.println("YAY!! This task is now marked done:\n" + t);
        printHorizontalLine();
    }

    public static void printUnmarkedMessage(Task t) {
        System.out.println("OK, I've marked this task as not done yet:\n" + t);
        printHorizontalLine();
    }

    /**
     * Method will be called whenever any form of task is deleted.
     * It will print the task deleted in the correct format, as well as inform
     * users the number of tasks left in the current list.
     *
     * @param t the task that is deleted
     */
    public static void printDeletedMessage(Task t) {
        System.out.println("Noted. I have deleted the task below: \n" + t);
        System.out.println("Now you have " + TaskList.taskCount + " tasks in the list.");
        printHorizontalLine();
    }

    /**
     * Method will be called when the keyword is "find".
     * It is a header and will only be shown if there is at least a task containing
     * the keyword.
     */
    public static void printFoundMessageHeader() {
        System.out.println("Here are the matching tasks in your list:");
    }

    /**
     * Method will be called when the keyword is "find".
     * It will inform the users the to search for a valid task that exists in the
     * current list.
     */
    public static void printMissingMessage() {
        System.out.println("Sorry, there are no task with that word.");
        System.out.println("Can you key in a valid task?");
    }

    public static String readInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
