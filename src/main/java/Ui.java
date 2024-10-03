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

    public static void printAddedTaskMessage() {
        System.out.println("Got it! I have added this task:");
        TaskList.taskCount++;
        System.out.println(TaskList.tasks.get(TaskList.taskCount - 1).toString());
        System.out.println("Now you have " + TaskList.taskCount + " tasks in the list.");
        printHorizontalLine();
    }

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

    public static void printDeletedMessage(Task t) {
        System.out.println("Noted. I have deleted the task below: \n" + t);
        System.out.println("Now you have " + TaskList.taskCount + " tasks in the list.");
        printHorizontalLine();
    }

    public static void printFoundMessageHeader() {
        System.out.println("Here are the matching tasks in your list:");
    }

    public static void printMissingMessage() {
        System.out.println("Sorry, there are no task with that word.");
        System.out.println("Can you key in a valid task?");
    }

    public static String readInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
