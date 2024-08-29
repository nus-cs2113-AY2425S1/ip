import java.util.ArrayList;
import java.util.Scanner;

public class CheonsaBot {
    public static final int LINE_LENGTH = 60; // Adjust the length as needed
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

    private static String getHorizontalLine() {
        return "-".repeat(LINE_LENGTH);
    }

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
                addTask(userInput);
                break;
        }
        return true;
    }

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
                System.out.println("Task number out of range.");
                System.out.println(getHorizontalLine());
            }
        } catch (NumberFormatException e) {
            System.out.println(getHorizontalLine());
            System.out.println("Invalid task number.");
            System.out.println(getHorizontalLine());
        }
    }

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

    private static void sayBye() {
        System.out.println(getHorizontalLine());
        System.out.println("Bye, see you again soon!");
        System.out.println(getHorizontalLine());
    }

    private static void addTask(String description) {
        Task task = new Task(description);
        tasks.add(task);
        System.out.println(getHorizontalLine());
        System.out.println("Added: " + task);
        System.out.println(getHorizontalLine());
    }

    private static void printTaskList() {
        System.out.println(getHorizontalLine());
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty!");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println(getHorizontalLine());
    }
}