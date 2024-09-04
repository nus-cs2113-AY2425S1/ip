import java.util.Scanner;

public class Bron {

    public static final String LOGO = """
              ____    ____      ____    _     _
             |  _ \\  |   _\\   /  __  \\ | \\   | |
             | |_) | | |_) |  | |  | | |  \\  | |\s
             |  _ <  | ___/   | |  | | | |\\\\ | |
             | |_) | | | \\ \\  | |__| | | | \\\\| |
             |____/  |_|  \\_\\  \\____/  |_|  \\__|
            """;

    public static void main(String[] args) {
        displayIntro();

        Task[] tasks = initializeTasks();
        Scanner input = initializeScanner();
        int taskCount = 0;
        boolean isReadingInput = true;

        mainLoop(input, tasks, taskCount, isReadingInput);
    }

    private static Scanner initializeScanner() {
        return new Scanner(System.in);
    }

    private static Task[] initializeTasks() {
        return new Task[100];
    }

    private static void mainLoop(Scanner input, Task[] tasks, int taskCount, boolean isReadingInput) {
        while (isReadingInput) {
            String line = input.nextLine().trim();
            String[] parts = line.split(" ");
            String command = parts[0].toLowerCase();

            switch (command) {
            case "bye":
                printByeMessage();
                isReadingInput = false;
                break;
            case "list":
                System.out.println("You got " + taskCount + " task(s)");
                int listCount = 0;
                while (tasks[listCount] != null) {
                    System.out.println(listCount + 1 + ". " + tasks[listCount++]);
                }
                break;
            case "mark":
                if (parts.length < 2) {
                    System.out.println("Please specify the task number to mark.");
                    break;
                }
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks[taskIndex].markAsDone();
                    System.out.println("Good shit kid! I've marked this task as done:");
                    System.out.println("  " + tasks[taskIndex]);
                } else {
                    System.out.println("Task not found.");
                }
                break;
            case "unmark":
                if (parts.length < 2) {
                    System.out.println("Please specify the task number to unmark.");
                    break;
                }
                taskIndex = Integer.parseInt(parts[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks[taskIndex].markAsNotDone();
                    System.out.println("Get yo shit together son, this task aint done yet:");
                    System.out.println("  " + tasks[taskIndex]);
                } else {
                    System.out.println("Task not found.");
                }
                break;
            case "todo":
                String taskDescription = line.substring(line.indexOf(" ") + 1);
                ToDo todo = new ToDo(taskDescription);
                tasks[taskCount++] = todo;
                System.out.println("Got it. I've added this task:");
                System.out.println(todo);
                System.out.println("You got " + taskCount + " task(s)");
                break;
            case "deadline":
                if (!line.contains("/by")) {
                    System.out.println("Please specify the deadline using '/by'.");
                    break;
                }

                String[] deadlineParts = line.split("/by", 2);
                if (deadlineParts.length < 2) {
                    System.out.println("Please provide a valid deadline.");
                    break;
                }

                taskDescription = deadlineParts[0].trim().substring(9);
                String byWhen = deadlineParts[1].trim();

                Deadline deadline = new Deadline(taskDescription, byWhen);
                tasks[taskCount++] = deadline;

                System.out.println("Got it. I've added this task:");
                System.out.println("  " + deadline);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                break;
            case "event":
                if (!line.contains("/from") || !line.contains("/to")) {
                    System.out.println("Please specify the event time using '/from' and '/to'.");
                    break;
                }

                String[] eventParts = line.split("/from", 2);
                if (eventParts.length < 2) {
                    System.out.println("Please provide a valid event time.");
                    break;
                }

                String descriptionAndTo = eventParts[0].trim().substring(6); // Remove the "event " prefix
                String[] timeParts = eventParts[1].split("/to", 2);
                if (timeParts.length < 2) {
                    System.out.println("Please provide a valid event end time.");
                    break;
                }

                String fromTime = timeParts[0].trim();
                String toTime = timeParts[1].trim();

                Event event = new Event(descriptionAndTo, fromTime, toTime);
                tasks[taskCount++] = event;

                System.out.println("Got it. I've added this task:");
                System.out.println("  " + event);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                break;
            default:
                System.out.println("Unknown command.");
                break;
            }

        }
    }

    private static void printByeMessage() {
        System.out.println("Catch you on the flip cuh");
    }

    private static void displayIntro() {
        System.out.println(LOGO + "Hello! I'm Bron\n" + "What can I do for you?\n");
    }
}
