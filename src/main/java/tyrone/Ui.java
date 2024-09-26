package tyrone;

import java.util.Scanner;

public class Ui {

    private static final Scanner scanner = new Scanner(System.in);

    public static void println(String s) {
        System.out.println(s);
    }

    public static void printIntroMessage() {
        System.out.println("Hello! I'm Tyrone.\nWhat can I do for you?");
    }

    public static void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static String receiveInput() {
        return scanner.nextLine();
    }

}
