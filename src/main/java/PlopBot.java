import java.util.Scanner;
import java.util.ArrayList;

public class PlopBot {

    // Static variables
    private static final String name = "plopBot";
    private static final String HORIZONTAL_LINE = "//" + "\u2500".repeat(50);
    private static final String ECHO_LINE = "    " + "\u2500".repeat(48);
    private static Scanner scanner = new Scanner(System.in);

    private static ArrayList<String> storedInputs;

    public static void main(String[] args) {
        /**
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */
        welcomeMessage();
        storedInputs = new ArrayList<>();
        echo();
    }

    /**
     * Welcome message when the user runs the program.
     */
    static void welcomeMessage() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm " + name + ".");
        System.out.println("What can I do for you today?");
        System.out.println("");
    }

    static void farewellMessage() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Thank you for choosing " + name + ". Have a great day!");
    }

    /**
     * Echoes the user's inputs.
     */
    static void echo() {
        String userInput;

        while (true) {
            userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("bye") || userInput.equalsIgnoreCase("exit")) {
                farewellMessage();
                break;
            }
            if (userInput.equalsIgnoreCase("list")) {
                list();
            }
            else {
                storedInputs.add(userInput);
                System.out.println(ECHO_LINE);
                System.out.println("    Added: " + userInput);
                System.out.println(ECHO_LINE);
            }

        }
    }

    /**
     * Lists the user's inputs.
     */
    static void list() {
        System.out.println(ECHO_LINE);
        System.out.println("    Stored Inputs:\n");
        for (int i = 0; i < storedInputs.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + storedInputs.get(i));
        }
    }
}
