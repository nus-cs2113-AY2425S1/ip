import java.util.Scanner;

public class Aly {

    private static void printLine() {
        System.out.println("-----------------------------------------------------------------------------");
    }

    private static void initialise() {
        System.out.println("I'm hungry, what do you want?");
        System.out.println("Enter 1 for echo function, 2 for list function, 0 to exit");
        printLine();
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        switch (input) {
        case "0":
            exit();
        case "1":
            echo();
        default:
            System.out.println("Invalid input");
            printLine();
            initialise();
        }
    }

    private static void exit() {
        System.out.println("Bye! I'm going to eat MacDonalds now!");
        printLine();
        System.exit(0);
    }

    public static void echo() {
        String line;
        Scanner in = new Scanner(System.in);
        System.out.println("Waiting for Input...");
        line = in.nextLine();
        printLine();
        System.out.println(line);
        printLine();
        initialise();
    }

    public static void main(String[] args) {
        printLine();
        String logo = "    _      _     _   _\n"
                + "   / \\    | |   \\ \\ / /\n"
                + "  / _ \\   | |    \\ V / \n"
                + " / ___ \\  | |__   | |  \n"
                + "/_/   \\_\\ |____|  |_|  \n";
        System.out.print("Hello! My name is \n" + logo);
        printLine();
        initialise();
    }
}