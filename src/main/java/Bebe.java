import java.util.Scanner;

public class Bebe {

    private static String[] tasks = new String[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        welcomeMessage();
        runChatbot();
        exitMessage();
    }

    /**
     * Prints a welcome message when the chatbot starts.
     */
    private static void welcomeMessage() {
        System.out.println("Hello! I'm Bebe");
        System.out.println("What can I do for you?");
    }

    /**
     * Runs the main loop of the chatbot to handle user input.
     */
    private static void runChatbot() {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine().trim();

            // Use switch-case to handle different commands
            switch (userInput.toLowerCase()) {
                case "bye":
                    scanner.close();
                    return; // Exits the method, thus ending the program
                case "list":
                    listTasks();
                    break;
                default:
                    addTask(userInput);
                    break;
            }
        }
    }

    /**
     * Adds a new task to the array.
     *
     * @param description The description of the task to be added.
     */
    private static void addTask(String description) {
        if (taskCount < tasks.length) {
            tasks[taskCount] = description;
            taskCount++;
            System.out.println("added: " + description);
        } else {
            System.out.println("Task list is full! Cannot add more tasks.");
        }
    }

    /**
     * Lists all the tasks added by the user.
     */
    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
    }

    /**
     * Prints an exit message when the chatbot ends.
     */
    private static void exitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

}
