import java.util.Scanner;

public class Nell {
    public static void main(String[] args) {
        // Initialises scanner to take in user input
        Scanner input = new Scanner(System.in);

        // Greets user
        System.out.println("Hello! I'm Nell!");
        System.out.println("What can I do for you?");

        boolean isGettingCommands = true;

        while (isGettingCommands) {
            // Get user command and respond accordingly
            String command = input.nextLine();
            switch (command) {
            case "bye":
                // Exits
                System.out.println("-> Bye. Hope to see you again soon!" + System.lineSeparator());
                isGettingCommands = false;
                break;

            default:
                // Echoes command otherwise
                System.out.println("-> " + command);
                break;
            }
        }
    }
}
