import java.util.Scanner;

public class Nell {
    /**
     * Prints a 40-character long horizontal line
     */
    public static void printLine() {
        for (int i = 0; i < 40; i++)
        {
            System.out.print('_');
        }

        System.out.println();
    }

    public static void main(String[] args) {
        // Initialises scanner to take in user input
        Scanner input = new Scanner(System.in);

        // Greets user
        printLine();
        System.out.println("Hello! I'm Nell!");
        System.out.println("What can I do for you?");
        printLine();

        boolean isGettingCommands = true;

        while (isGettingCommands) {
            // Get user command and respond accordingly
            String command = input.nextLine();
            switch (command) {
            case "bye":
                // Exits
                printLine();
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                isGettingCommands = false;
                break;

            default:
                // Echoes command otherwise
                printLine();
                System.out.println(command);
                printLine();
                break;
            }
        }
    }
}
