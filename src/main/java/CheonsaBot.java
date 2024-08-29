import java.util.ArrayList;
import java.util.Scanner;

public class CheonsaBot {
    public static String horizontalLine = "____________________________________________________________";

    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String logo = """
                       (\\ -=- /)
                       ( \\( )/ )
                       (       )
                        `>   <'
                        /     \\
                        `-._.-'
                       """;
        System.out.println(horizontalLine);
        System.out.println("Hello, I'm 천사봇! (AngelBot)");
        System.out.print(logo);
        System.out.println("How may I assist you today?");
        System.out.println(horizontalLine);
        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;
        while (isRunning) {
            String userInput = scanner.nextLine();  // Read user input
            isRunning = parseInput(userInput);
        }

        sayBye();
        scanner.close();
    }

    public static boolean parseInput(String userInput) {
        String[] words = userInput.split(" ", 2);
        String command = words[0];
        String argument = words.length > 1 ? words[1] : "";
        // Normalize the command by removing trailing punctuation
        command = command.replaceAll("[^a-zA-Z0-9]", "");

        switch (command.toLowerCase()) {
            case "mark":
                markTask(argument);
                break;
            case "unmark":
                unmarkTask(argument);
                break;
            case "bye":
                return false;
            default:
                addTask(userInput);
                break;
        }

        return true;
    }

    public static void markTask(String taskNumber) {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).markAsDone();
                System.out.println(horizontalLine);
                System.out.println("Marked task as done: " + tasks.get(index));
                System.out.println(horizontalLine);
            } else {
                System.out.println(horizontalLine);
                System.out.println("Task number out of range.");
                System.out.println(horizontalLine);
            }
        } catch (NumberFormatException e) {
            System.out.println(horizontalLine);
            System.out.println("Invalid task number.");
            System.out.println(horizontalLine);
        }
    }

    public static void unmarkTask(String taskNumber) {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).markAsUndone();
                System.out.println(horizontalLine);
                System.out.println("Unmarked task: " + tasks.get(index));
                System.out.println(horizontalLine);
            } else {
                System.out.println(horizontalLine);
                System.out.println("Task number out of range.");
                System.out.println(horizontalLine);
            }
        } catch (NumberFormatException e) {
            System.out.println(horizontalLine);
            System.out.println("Invalid task number.");
            System.out.println(horizontalLine);
        }
    }


    public static void sayBye() {
        System.out.println(horizontalLine);
        System.out.println("Bye, see you again soon!");
        System.out.println(horizontalLine);
    }

    public static void addTask(String description) {
        Task task = new Task(description);
        tasks.add(task);
        System.out.println(horizontalLine);
        System.out.println("Added: " + task);
        System.out.println(horizontalLine);
    }

    public static void printTaskList() {
        System.out.println(horizontalLine);
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty!");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println(horizontalLine);
    }
}
