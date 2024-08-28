import java.util.Scanner;
import java.util.ArrayList;

public class Joe {
    private static String INTENDATION = "      ";
    private static String SEPARATOR = "_________________________________________________";
    private static String[] USER_COMMANDS = {"bye", "list"};
    private static ArrayList<String> userInputs = new ArrayList<>();


    public static void main(String[] args) {
        String logo = INTENDATION + "     _            \n"
            + INTENDATION + "    (_)           \n"
            + INTENDATION + "     _  ___   ___ \n"
            + INTENDATION + "    | |/ _ \\ / _ \\\n"
            + INTENDATION + "    | | (_) |  __/\n"
            + INTENDATION + "    | |\\___/ \\___|\n"
            + INTENDATION + "   _/ |           \n"
            + INTENDATION + "  |__/            \n";
        System.out.println(INTENDATION + "Hello from\n" + logo);
        printGreeting();
        chatWithJoe();
        printFarewell();
    }

    public static void printReply(String input, boolean isFromJoe, String actionPerformed) {
        if (isFromJoe) {
            System.out.println(INTENDATION + actionPerformed + input);
        } else {
            System.out.println(input);
        }
        System.out.println(INTENDATION + SEPARATOR);
    }

    public static void printGreeting() {
        System.out.println(INTENDATION + SEPARATOR);
        System.out.println(INTENDATION + "Hello I'm Joe.");
        System.out.println(INTENDATION + "How can I help you?");
        System.out.println(INTENDATION + SEPARATOR);
    }

    public static void printFarewell() {
        System.out.println(INTENDATION + "See you soon!");
        System.out.println(INTENDATION + SEPARATOR);
    }

    public static void addToList(String input) {
        userInputs.add(input);
    }

    public static void  printList() {
        int counter = 0;
        for (String input : userInputs) {
            counter +=1;
            System.out.println(INTENDATION + counter + ": " + input);
        }
    }


    public static void chatWithJoe() {
        //get and echo user input
        Scanner in = new Scanner(System.in);
        String input = "";
        while (!input.equals("bye")) {
            input = in.nextLine();
            switch (input) {
            case "bye":
                break;
            case "list":
                printList();
                break;
            default:
                addToList(input);
                printReply(input, true, "Added: ");
            }
        }
    }
}
