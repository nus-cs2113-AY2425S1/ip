import java.util.Scanner;
import java.util.ArrayList;


public class Bebe {

    private static ArrayList<Task> tasks = new ArrayList<>();

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
            String[] words = userInput.split(" ");

            switch (words[0].toLowerCase()) {
                case "bye":
                    scanner.close();
                    return; // Exits the method, thus ending the program
                case "list":
                    listTasks();
                    break;
                case "mark":
                    if (words.length == 2) {
                        int taskNumber = Integer.parseInt(words[1]) - 1;
                        markTaskAsDone(taskNumber);
                    }
                    break;
                case "unmark":
                    if (words.length == 2) {
                        int taskNumber = Integer.parseInt(words[1]) - 1;
                        markTaskAsNotDone(taskNumber);
                    }
                    break;
                default:
                    addTask(userInput);
                    break;
            }
        }
    }


    /**
     * Adds a new task to the list.
     *
     * @param description The description of the task to be added.
     */
    private static void addTask(String description) {
        Task newTask = new Task(description);
        tasks.add(newTask);
        System.out.println("added: " + description);
    }

    /**
     * Lists all the tasks added by the user.
     */
    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to mark as done.
     */
    private static void markTaskAsDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks.get(index).toString());
        } else {
            System.out.println("Invalid task number.");
        }
    }

    /**
     * Marks a task as not done.
     *
     * @param index The index of the task to mark as not done.
     */
    private static void markTaskAsNotDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks.get(index).toString());
        } else {
            System.out.println("Invalid task number.");
        }
    }

    /**
     * Prints an exit message when the chatbot ends.
     */
    private static void exitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

}
