import java.util.ArrayList;
import java.util.Scanner;

public class Aly {

    private static void printLine() {
        System.out.println("-----------------------------------------------------------------------------");
    }

    private static void initialise() {
        printLine();
        System.out.println("I'm hungry, what do you want?");
        System.out.println("Faster lah! Enter 1 for echo function, 2 for list function, 0 to exit");
        printLine();
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        switch (input) {
        case "0":
            exit();
        case "1":
            System.out.println("Enter 0 to return to main menu anytime!");
            printLine();
            echo();
            break;
        case "2":
            System.out.println("Enter 0 to return to main menu anytime!");
            printLine();
            list();
            break;
        default:
            System.out.println("Can read instructions anot? Try again!");
            initialise();
        }
    }

    private static void exit() {
        System.out.println("Bye! I'm going to eat MacDonalds now!");
        printLine();
        System.exit(0);
    }

    private static void echo() {
        boolean isExit = false;
        while (!isExit) {
            String line;
            Scanner in = new Scanner(System.in);
            System.out.println("Waiting for Input...");
            line = in.nextLine();
            if (line.equals("0")) {
                isExit = true;
                System.out.println("Returning to main menu!");
            } else {
                printLine();
                System.out.println(line);
                printLine();
            }
        }
        initialise();
    }

    private static void list() {
        ArrayList<String> listItems = new ArrayList<>();
        boolean isExit = false;
        while (!isExit) {
            System.out.println("Enter 'list' to see your list of tasks, 'add' to add another task");
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            switch (input) {
            case "list":
                printLine();
                int count = 1;
                for (String item : listItems) {
                    System.out.println(count + ". " + item);
                    count += 1;
                }
                printLine();
                break;
            case "add":
                printLine();
                System.out.println("Enter your task:");
                Scanner addItem = new Scanner(System.in);
                String task = addItem.nextLine();
                listItems.add(task);
                printLine();
                System.out.println("added task: " + task);
                printLine();
                break;
            case "0":
                isExit = true;
                System.out.println("Returning to main menu!");
            }
        }
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
        initialise();
    }
}