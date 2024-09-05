import java.util.ArrayList;
import java.util.Scanner;

public class CheonsaBot {
    public static final int LINE_LENGTH = 60;
    public static ArrayList<Task> tasks = new ArrayList<>();

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

    // Print greeting to screen
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

    // Print farewell message to screen
    private static void sayBye() {
        System.out.println(getHorizontalLine());
        System.out.println("Bye, see you again soon!");
        System.out.println(getHorizontalLine());
    }

    // Draw LINE_LENGTH times dashes as horizontal line
    private static String getHorizontalLine() {
        return "-".repeat(LINE_LENGTH);
    }

    // Decode user input and respond accordingly
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

    // Mark task number as complete
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

    // Mark task nunmber as incomplete
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

    // Function to create new task
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

    // Print formatted task list to screen
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
