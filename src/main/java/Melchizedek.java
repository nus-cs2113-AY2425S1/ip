import java.util.Scanner;

public class Melchizedek {

    public static boolean isDone = false;
    public static String[] userList = new String[100];
    public static int listCount = 0;

    public static void printHorizontalLine() {
        System.out.println("\t____________________________________________________________");
    }

    public static void greet() {
        printHorizontalLine();
        System.out.println("\tHello! I'm Melchizedek.");
        System.out.println("\tHow can I assist you?");
        printHorizontalLine();
    }

    public static void exit() {
        System.out.println("\tGoodbye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public static void echo(String input) {
        System.out.println("\t" + input);
        printHorizontalLine();
    }

    public static void main(String[] args) {
        greet();
        TaskList taskList = new TaskList();
        Scanner in = new Scanner(System.in);
        while (!isDone) {
            String input = in.nextLine();
            printHorizontalLine();
            if (input.equalsIgnoreCase("bye")) {
                isDone = true;
            } else if (input.equalsIgnoreCase("list")) {
                taskList.printTaskList();
            } else {
                taskList.addToTaskList(input);
            }
        }
        exit();
    }
}
