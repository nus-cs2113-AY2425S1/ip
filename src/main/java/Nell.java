import java.util.Scanner;

public class Nell {
    private static String[] tasks = new String[100];
    private static int taskCount = 0;

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
                // Stores text in task list
                tasks[taskCount] = command;
                taskCount++;
                System.out.printf("-> added: %s%n", command);
                break;
            }
        }
    }
}
