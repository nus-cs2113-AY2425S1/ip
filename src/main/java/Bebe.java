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
     * Prints the available commands and their descriptions.
     */
    private static void showHelp() {
        System.out.println("Here are the commands you can use:");
        System.out.println("  todo <task description>          - Adds a ToDo task.");
        System.out.println("  deadline <task description> /by <date/time>  - Adds a Deadline task.");
        System.out.println("  event <task description> /from <start time> /to <end time>  - Adds an Event task.");
        System.out.println("  list                            - Lists all tasks.");
        System.out.println("  mark <task number>              - Marks a task as done.");
        System.out.println("  unmark <task number>            - Marks a task as not done.");
        System.out.println("  help                            - Shows this help message.");
        System.out.println("  bye                             - Exits the chatbot.\n");
        System.out.println("So what can I do for you?");
    }

    /**
     * Runs the main loop of the chatbot to handle user input.
     */
    private static void runChatbot() {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine().trim();
            String[] words = userInput.split(" ", 2);

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
                        markTaskAsDone(Integer.parseInt(words[1]));
                    }
                    break;
                case "unmark":
                    if (words.length == 2) {
                        int taskNumber = Integer.parseInt(words[1]) - 1;
                        markTaskAsNotDone(Integer.parseInt(words[1]));
                    }
                    break;
                case "todo":
                    addTask(new Todo(words[1]));
                    break;
                case "deadline":
                    String[] deadlineParts = words[1].split(" /by ");
                    addTask(new Deadline(deadlineParts[0], deadlineParts[1]));
                    break;
                case "event":
                    String[] eventParts = words[1].split(" /from | /to ");
                    addTask(new Event(eventParts[0], eventParts[1], eventParts[2]));
                    break;
                case "help":
                    showHelp();
                    break;
                default:
                    System.out.println("I'm sorry, I don't understand that command.");
                    break;
            }
        }
    }


    /**
     * Adds a new task to the list.
     *
     * @param task The task to be added
     */
    private static void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
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
