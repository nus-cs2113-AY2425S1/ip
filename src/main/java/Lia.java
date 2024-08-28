import java.util.Scanner;

public class Lia {

    public static void main(String[] args) {
        // Customizing the chatbot with the name Lia
        String botName = "Lia";
        Scanner scanner = new Scanner(System.in);
        String input;
        Task[] tasks = new Task[100];  // Fixed-size array to store tasks
        int taskCount = 0;  // Counter to keep track of the number of tasks

        // Greet the user with enthusiasm
        printLine();
        System.out.println("    Hello! I'm " + botName);
        System.out.println("    What can I do for you?");
        printLine();

        // Chatbot loop: keep asking for input until "bye" is entered
        while (true) {
            input = scanner.nextLine().trim();
            String[] inputArr = input.split(" ", 2);  // Splitting command and details

            // If the user types "bye", end the loop with a warm farewell
            if (inputArr[0].equalsIgnoreCase("bye")) {
                printLine();
                System.out.println("    Bye. Hope to see you again soon!");
                printLine();
                break;
            }

            // If the user types "list", display all tasks added
            if (inputArr[0].equalsIgnoreCase("list")) {
                printLine();
                if (taskCount == 0) {
                    System.out.println("    No tasks found.");
                } else {
                    System.out.println("    Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println("    " + (i + 1) + "." + tasks[i].toString());
                    }
                }
                printLine();
            } else if (inputArr[0].equalsIgnoreCase("mark")) {
                int taskIndex = Integer.parseInt(inputArr[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks[taskIndex].markAsDone();
                    printLine();
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("       " + tasks[taskIndex].toString());
                    printLine();
                }
            } else if (inputArr[0].equalsIgnoreCase("unmark")) {
                int taskIndex = Integer.parseInt(inputArr[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks[taskIndex].markAsNotDone();
                    printLine();
                    System.out.println("    OK, I've marked this task as not done yet:");
                    System.out.println("      " + tasks[taskIndex].toString());
                    printLine();
                }
            } else {
                // Add the user's input as a new task
                tasks[taskCount] = new Task(input);
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
