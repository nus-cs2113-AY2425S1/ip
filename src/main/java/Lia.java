import java.util.Scanner;

/**
 * Lia is a simple chatbot program that helps users manage tasks.
 */
public class Lia {
    private static final String INDENTATION = "    "; // Constant for Chatbot output indentation

    public static void main(String[] args) {
        // Customizing the chatbot with the name Lia
        String LOGO =
                """
                             ██▓     ██▓ ▄▄▄     \s
                            ▓██▒    ▓██▒▒████▄   \s
                            ▒██░    ▒██▒▒██  ▀█▄ \s
                            ▒██░    ░██░░██▄▄▄▄██\s
                            ░██████▒░██░ ▓█   ▓██▒
                            ░ ▒░▓  ░░▓   ▒▒   ▓▒█░
                            ░ ░ ▒  ░ ▒ ░  ▒   ▒▒ ░
                            ░ ░    ▒ ░  ░   ▒  \s
                                ░  ░ ░        ░  ░
                        """;

        Scanner scanner = new Scanner(System.in);
        String input;
        Task[] tasks = new Task[100];  // Fixed-size array to store tasks
        int taskCount = 0;  // Counter to keep track of the number of tasks

        // Greet the user with enthusiasm
        printLine();
        System.out.println(INDENTATION + "Hello! I'm \n" + LOGO);
        System.out.println(INDENTATION + "What can I do for you?");
        printLine();

        // Chatbot loop: keep asking for input until "bye" is entered
        while (true) {
            input = scanner.nextLine().trim();
            String[] inputArr = input.split(" ", 2);  // Splitting command and details

            // If the user types "bye", end the loop with a warm farewell
            if (inputArr[0].equalsIgnoreCase("bye")) {
                printLine();
                System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
                printLine();
                break;
            }

            // If the user types "list", display all tasks added
            if (inputArr[0].equalsIgnoreCase("list")) {
                printLine();
                if (taskCount == 0) {
                    System.out.println(INDENTATION + "No tasks found.");
                } else {
                    System.out.println(INDENTATION + "Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println("    " + (i + 1) + "." + tasks[i].toString());
                    }
                }
                printLine();
            } else if (inputArr[0].equalsIgnoreCase("mark")) {
                int taskIndex = Integer.parseInt(inputArr[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks[taskIndex].markAsDone();
                    printLine();
                    System.out.println(INDENTATION + "Nice! I've marked this task as done:");
                    System.out.println(INDENTATION + tasks[taskIndex].toString());
                    printLine();
                }
            } else if (inputArr[0].equalsIgnoreCase("unmark")) {
                int taskIndex = Integer.parseInt(inputArr[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks[taskIndex].markAsNotDone();
                    printLine();
                    System.out.println(INDENTATION + "OK, I've marked this task as not done yet:");
                    System.out.println(INDENTATION + tasks[taskIndex].toString());
                    printLine();
                }
            } else if (inputArr[0].equalsIgnoreCase("todo")) {
                tasks[taskCount] = new ToDo(inputArr[1]);
                taskCount++;
                addTaskAndPrint(tasks[taskCount - 1], taskCount);
            } else if (inputArr[0].equalsIgnoreCase("deadline")) {
                String[] details = inputArr[1].split(" /by ", 2);
                tasks[taskCount] = new Deadline(details[0], details[1]);
                taskCount++;
                addTaskAndPrint(tasks[taskCount - 1], taskCount);
            } else if (inputArr[0].equalsIgnoreCase("event")) {
                String[] details = inputArr[1].split(" /from ", 2);
                String[] times = details[1].split(" /to ", 2);
                tasks[taskCount] = new Event(details[0], times[0], times[1]);
                taskCount++;
                addTaskAndPrint(tasks[taskCount - 1], taskCount);
            } else {
                // Add the user's input as a new task
                tasks[taskCount] = new Task(input);
                taskCount++;
                printLine();
                System.out.println(INDENTATION + "added: " + input);
                printLine();
            }
        }

        // Close the scanner to prevent resource leak
        scanner.close();
    }

    /**
     * Adds a task and prints the confirmation message.
     *
     * @param task The task to be added.
     * @param taskCount The current number of tasks.
     */
    private static void addTaskAndPrint(Task task, int taskCount) {
        printLine();
        System.out.println(INDENTATION + "Got it. I've added this task:");
        System.out.println(INDENTATION + task.toString());
        System.out.println(INDENTATION + "Now you have " + taskCount + " tasks in the list.");
        printLine();
    }

    /**
     * Prints a line separator.
     */
    public static void printLine() {
        System.out.println(INDENTATION + "___________________________________________________________");
    }
}
