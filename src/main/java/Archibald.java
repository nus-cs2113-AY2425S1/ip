import java.util.Scanner;

public class Archibald {

    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void printArchibaldResponse(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    public static void addTask(String input) {
        if (taskCount >= tasks.length) {
            printArchibaldResponse("Error: Task list is full!");
            return;
        }

        String[] parts = input.split(" ", 2);
        String type = parts[0].toLowerCase();
        String details = parts.length > 1 ? parts[1] : "";

        switch (type) {
            case "todo":
                tasks[taskCount++] = new Todo(details);
                break;
            case "deadline":
                String[] deadlineParts = details.split(" /by ", 2);
                tasks[taskCount++] = new Deadline(deadlineParts[0], deadlineParts[1]);
                break;
            case "event":
                String[] eventParts = details.split(" /from | /to ");
                tasks[taskCount++] = new Event(eventParts[0], eventParts[1], eventParts[2]);
                break;
            default:
                printArchibaldResponse("Error: Unknown task type.");
                return;
        }

        printArchibaldResponse("Got it. I've added this task:\n  " + tasks[taskCount - 1] +
                               "\nNow you have " + taskCount + " tasks in the list.");
    }

    public static void printList() {
        printArchibaldResponse("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
    }

    public static void main(String[] args) {
        String name = "Archibald";
        printArchibaldResponse("Hello, I am known as " + name + ",\nhow may I be of assistance!");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            
            if (input.equals("bye")) {
                printArchibaldResponse("I bid thee farewell! May our paths cross again!");
                break;
            } else if (input.equals("list")) {
                printList();
            } else if (input.startsWith("mark")) {
                markTask(input);
            } else if (input.startsWith("unmark")) {
                unmarkTask(input);
            } else {
                addTask(input);
            }
        }

        scanner.close();
    }

    public static void markTask(String input) {
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            if (taskIndex >= 0 && taskIndex < taskCount) {
                tasks[taskIndex].markAsDone();
                printArchibaldResponse("Great Wizard of Skibidiness! Thou hath completed task:\n  " + tasks[taskIndex]);
            } else {
                printArchibaldResponse("Error: Invalid task number.");
            }
        } catch (Exception e) {
            printArchibaldResponse("Error: Invalid command format.");
        }
    }

    public static void unmarkTask(String input) {
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            if (taskIndex >= 0 && taskIndex < taskCount) {
                tasks[taskIndex].markAsNotDone();
                printArchibaldResponse("Big yikers! The task hath been marked undone:\n  " + tasks[taskIndex]);
            } else {
                printArchibaldResponse("Error: Invalid task number.");
            }
        } catch (Exception e) {
            printArchibaldResponse("Error: Invalid command format.");
        }
    }
}
