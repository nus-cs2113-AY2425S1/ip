import java.util.Scanner;

class ArchibaldException extends Exception {
    public ArchibaldException(String message) {
        super(message);
    }
}

public class Archibald {

    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void printArchibaldResponse(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    public static void addTask(String input) {
        try {
            if (taskCount >= tasks.length) {
                throw new ArchibaldException("Error: Task list is full!");
            }
    
            String[] parts = input.split(" ", 2);
            String type = parts[0].toLowerCase();
    
            if (type.equals("todo") || type.equals("deadline") || type.equals("event")) {
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new ArchibaldException("Error: By royal decree, the description of a " + type + " cannot be empty.");
                }
            }
    
            switch (type) {
                case "todo":
                    tasks[taskCount++] = new Todo(parts[1]);
                    break;
                case "deadline":
                    String[] deadlineParts = parts[1].split(" /by ", 2);
                    if (deadlineParts.length < 2) {
                        throw new ArchibaldException("Error: Deadline must include '/by' followed by a date.");
                    }
                    tasks[taskCount++] = new Deadline(deadlineParts[0], deadlineParts[1]);
                    break;
                case "event":
                    String[] eventParts = parts[1].split(" /from | /to ");
                    if (eventParts.length < 3) {
                        throw new ArchibaldException("Error: Event must include '/from' and '/to' followed by dates.");
                    }
                    tasks[taskCount++] = new Event(eventParts[0], eventParts[1], eventParts[2]);
                    break;
                default:
                    throw new ArchibaldException("Error: Unfortunately, I don't know what that means >:(");
            }
    
            printArchibaldResponse("Got it. I've added this task:\n  " + tasks[taskCount - 1] +
                                   "\nNow you have " + taskCount + " tasks in the list.");
        } catch (ArchibaldException e) {
            printArchibaldResponse(e.getMessage());
        }
    }
    


    public static void printList() {
        if (taskCount == 0) {
            printArchibaldResponse("The task list is currently empty.");
            return;
        }
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
                throw new ArchibaldException("Error: Invalid task number.");
            }
        } catch (Exception e) {
            printArchibaldResponse("Error: Invalid command format or task number.");
        }
    }

    public static void unmarkTask(String input) {
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            if (taskIndex >= 0 && taskIndex < taskCount) {
                tasks[taskIndex].markAsNotDone();
                printArchibaldResponse("Big yikers! The task hath been marked undone:\n  " + tasks[taskIndex]);
            } else {
                throw new ArchibaldException("Error: Invalid task number.");
            }
        } catch (Exception e) {
            printArchibaldResponse("Error: Invalid command format or task number.");
        }
    }
}
