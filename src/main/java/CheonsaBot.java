import java.util.ArrayList;
import java.util.Scanner;

public class CheonsaBot {
    public static final int LINE_LENGTH = 60;
    public static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * The entry point of the application. Starts the bot and listens for user input.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        printGreeting();
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                String userInput = scanner.nextLine();
                running = parseInput(userInput);
            }
        }
    }

    /**
     * Prints a greeting message to the console.
     * Displays the bot's logo and a welcome message.
     */
    private static void printGreeting() {
        String logo = """
                       (\\ -=- /)
                       ( \\( )/ )
                       (       )
                        `>   <'
                        /     \\
                        `-._.-'
                       """;
        System.out.println(getHorizontalLine());
        System.out.println("Hello, I'm 천사봇! (AngelBot)");
        System.out.print(logo);
        System.out.println("How may I assist you today?");
        System.out.println(getHorizontalLine());
    }

    /**
     * Prints a farewell message and exits the program.
     */
    private static void sayBye() {
        System.out.println(getHorizontalLine());
        System.out.println("Bye, see you again soon!");
        System.out.println(getHorizontalLine());
    }

    /**
     * Returns a horizontal line of dashes used for formatting console output.
     *
     * @return A string containing the horizontal line.
     */
    private static String getHorizontalLine() {
        return "-".repeat(LINE_LENGTH);
    }

    /**
     * Parses user input and executes the corresponding command.
     *
     * @param userInput The input provided by the user.
     * @return True if the bot should continue running, false if it should exit.
     */
    private static boolean parseInput(String userInput) {
        String[] words = userInput.split(" ", 2);
        String command = words[0].replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String argument = words.length > 1 ? words[1] : "";

        switch (command) {
            case "mark":
                markTask(argument);
                break;
            case "unmark":
                unmarkTask(argument);
                break;
            case "bye":
                sayBye();
                return false;
            case "list":
                printTaskList();
                break;
            default:
                addTask(command, words);
                break;
        }
        return true;
    }

    /**
     * Marks a task as completed based on the provided task number.
     *
     * @param taskNumber The number of the task to be marked as completed.
     */
    private static void markTask(String taskNumber) {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).setAsDone();
                System.out.println(getHorizontalLine());
                System.out.println("Marked task as done: " + tasks.get(index));
                System.out.println(getHorizontalLine());
            } else {
                System.out.println(getHorizontalLine());
                System.out.println("Task number out of range. Try again...");
                System.out.println(getHorizontalLine());
            }
        } catch (NumberFormatException e) {
            System.out.println(getHorizontalLine());
            System.out.println("Invalid task number...");
            System.out.println(getHorizontalLine());
        }
    }

    /**
     * Unmarks a task as incomplete based on the provided task number.
     *
     * @param taskNumber The number of the task to be unmarked.
     */
    private static void unmarkTask(String taskNumber) {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).setAsUndone();
                System.out.println(getHorizontalLine());
                System.out.println("Unmarked task: " + tasks.get(index));
                System.out.println(getHorizontalLine());
            } else {
                System.out.println(getHorizontalLine());
                System.out.println("Task number out of range.");
                System.out.println(getHorizontalLine());
            }
        } catch (NumberFormatException e) {
            System.out.println(getHorizontalLine());
            System.out.println("Invalid task number.");
            System.out.println(getHorizontalLine());
        }
    }

    /**
     * Adds a new task based on the command and argument provided.
     *
     * @param command The type of task to add (e.g., "todo", "deadline", "event").
     * @param words The details of the task.
     */
    private static void addTask(String command, String[] words) {
        Task task;
        switch (command.toLowerCase()) {
            case "todo":
            task = new ToDo(words[1]);
            break;

            case "deadline":
                String[] deadlineParts = words[1].split("/by", 2);
                String deadlineDescription = deadlineParts[0].trim();
                String by = deadlineParts.length > 1 ? deadlineParts[1].trim() : "";
                task = new Deadline(deadlineDescription, by);
                break;

            case "event":
                String[] eventParts = words[1].split("/from", 2);
                String eventDescription = eventParts[0].trim();
                String[] timeParts;
                if (eventParts.length > 1) {
                    timeParts = eventParts[1].split("/to", 2);
                } else {
                    timeParts = new String[]{"", ""};
                }
                String from = timeParts[0].trim();
                String to = timeParts.length > 1 ? timeParts[1].trim() : "";
                task = new Event(eventDescription, from, to);
                break;

            default:
                System.out.println(getHorizontalLine());
                System.out.println("Couldn't recognise: " + command);
                System.out.println("Try commands todo, deadline or event!");
                System.out.println(getHorizontalLine());
                return;
        }
        tasks.add(task);

        // Print to screen
        System.out.println(getHorizontalLine());
        System.out.println("Added: " + task);
        System.out.println(getHorizontalLine());
    }

     /**
     * Prints the list of tasks to the console.
     * Displays each task with its corresponding number.
     */
    private static void printTaskList() {
        System.out.println(getHorizontalLine());
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty, maybe add a task?");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println(getHorizontalLine());
    }
}
