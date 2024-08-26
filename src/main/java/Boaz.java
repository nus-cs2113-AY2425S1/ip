import java.util.Scanner;

public class Boaz {

    public static boolean isDone = false;

    public static void printHorizontalLine() {
        System.out.println("\t____________________________________________________________");
    }

    public static void greet() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Boaz.");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
    }

    public static void exit() {
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }

    public static void main(String[] args) {
        greet();
        Scanner in = new Scanner(System.in);
        while (!isDone) {
            String command = in.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                isDone = true;
            } else {
                System.out.println("\t"+command);
                System.out.println("\t____________________________________________________________");
            }
        }
        exit();
    }
}
