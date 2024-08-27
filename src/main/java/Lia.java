import java.util.Scanner;

public class Lia {

    public static void main(String[] args) {
        // Customizing the chatbot with the name Lia
        String botName = "Lia";
        Scanner scanner = new Scanner(System.in);
        String input;
        String[] tasks = new String[100];  // Fixed-size array to store tasks
        int taskCount = 0;  // Counter to keep track of the number of tasks

        // Greet the user with enthusiasm
        printLine();
        System.out.println("    Hello! I'm " + botName);
        System.out.println("    aWhat can I do for you?");
        printLine();

        // Chatbot loop: keep asking for input until "bye" is entered
        while (true) {
            input = scanner.nextLine().trim();

            // If the user types "bye", end the loop with a warm farewell
            if (input.equalsIgnoreCase("bye")) {
                printLine();
                System.out.println("    Bye. Hope to see you again soon!");
                printLine();
                break;
            }

            // If the user types "list", display all tasks added
            if (input.equalsIgnoreCase("list")) {
                printLine();
                if (taskCount == 0) {
                    System.out.println(" No tasks found.");
                } else {
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println("    " + (i + 1) + ". " + tasks[i]);
                    }
                }
                printLine();
            } else {
                // Add the user's input as a new task
                tasks[taskCount] = input;
                taskCount++;
                printLine();
                System.out.println("    added: " + input);
                printLine();
            }
        }

        // Close the scanner to prevent resource leak
        scanner.close();
    }

    // Function to print the line
    public static void printLine() {
        System.out.println("    ___________________________________________________________");
    }
}
