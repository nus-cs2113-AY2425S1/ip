import java.util.Scanner;

public class PlopBot {

    // Static variables
    private static final String name = "plopBot";
    private static final String HORIZONTAL_LINE = "//" + "\u2500".repeat(50);
    private static final String ECHO_LINE = "    " + "\u2500".repeat(50);
    private static Scanner scanner = new Scanner(System.in);

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
        System.out.println(HORIZONTAL_LINE);
        System.out.println("");
        System.out.println("Thank you for choosing " + name + ". Have a great day!");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Echoes the users inputs.
     */
    static void echo() {
        String userInput;

        while (true) {
            userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.println(ECHO_LINE);
            System.out.println("    " + userInput);
            System.out.println(ECHO_LINE);
        }
    }
}
