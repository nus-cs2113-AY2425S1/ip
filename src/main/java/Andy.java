import java.util.Scanner;

public class Andy {

    private TaskManager taskManager;  // TaskManager handles the task list and storage

    public Andy() {
        taskManager = new TaskManager();  // Initialize TaskManager (loads tasks from storage)
    }

    public void run() {
        greetUser(); // Greet the user
        Scanner scanner = new Scanner(System.in);
        String input = "";

        // Main loop for handling user input
        while (!input.equals("bye")) {
            try {
                input = scanner.nextLine();
                String command = input.split(" ")[0]; // Get the first word as the command

                switch (command) {
                case "list":
                    handleListInput(input);
                    break;

                case "mark":
                    markTaskAsDone(input);
                    break;

                case "unmark":
                    markTaskAsNotDone(input);
                    break;

                case "todo":
                    if (input.substring(4).trim().isEmpty()) {
                        throw new AndyException("Task description cannot be empty!");
                    }
                    taskManager.addTask(new TodoTask(input.substring(5).trim())); // Add task to TaskManager
                    break;

                case "deadline":
                    String[] parts = input.substring(9).split(" /by ");
                    if (parts.length != 2) {
                        throw new AndyException("Invalid deadline format. Use: deadline <task> /by <time>");
                    }
                    taskManager.addTask(new DeadlineTask(parts[0].trim(), parts[1].trim())); // Add DeadlineTask
                    break;

                case "event":
                    parts = input.substring(6).split(" /from ");
                    if (parts.length != 2) {
                        throw new AndyException("Invalid event format. Use: event <task> /from <start time> /to <end time>");
                    }
                    String[] timeParts = parts[1].split(" /to ");
                    if (timeParts.length != 2) {
                        throw new AndyException("Invalid event format. Use: event <task> /from <start time> /to <end time>");
                    }
                    taskManager.addTask(new EventTask(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim())); // Add EventTask
                    break;

                default:
                    throw new AndyException("I'm sorry, I don't understand that command.");
                }

            } catch (AndyException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }

        exit(); // Display the exit message
        scanner.close(); // Close the scanner to avoid resource leaks
    }

    // Method to greet the user
    private static void greetUser() {
        System.out.println("_______________________________________");
        System.out.println("Hello! I'm ANDY");
        System.out.println("What can I do for you?");
        System.out.println("_______________________________________");
    }

    // Method to handle list input
    private void handleListInput(String input) {
        String[] parts = input.split(" ", 2); // Split the input into command and item

        if (parts.length > 1) {
            String command = parts[1];

            if (command.equals("show")) {
                taskManager.showTasks(); // Show tasks in TaskManager
            } else {
                taskManager.addTask(new TodoTask(command)); // Add the item to TaskManager as a TodoTask
            }
        } else {
            System.out.println("Please provide a valid list command or item.");
        }
    }

    // Method to mark a task as done
    private void markTaskAsDone(String input) {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
            taskManager.markTaskAsDone(taskNumber);  // Delegate to TaskManager
        } catch (Exception e) {
            System.out.println("Invalid task number. Please try again.");
        }
    }

    // Method to mark a task as not done
    private void markTaskAsNotDone(String input) {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
            taskManager.markTaskAsNotDone(taskNumber);  // Delegate to TaskManager
        } catch (Exception e) {
            System.out.println("Invalid task number. Please try again.");
        }
    }

    // Method to exit the program
    private static void exit() {
        System.out.println("_______________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("_______________________________________");
    }

    public static void main(String[] args) {
        Andy andy = new Andy(); // Initialize Andy
        andy.run();  // Start the main loop
    }
}