import task.Task;
import task.Event;
import task.ToDo;
import task.Deadline;
import exception.LiaException;
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
            try {
                input = scanner.nextLine().trim();
                String[] inputArr = input.split(" ", 2);  // Splitting command and details

                // If the user types "bye", end the loop with a warm farewell
                if (inputArr[0].equalsIgnoreCase("bye")) {
                    printLine();
                    System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
                    printLine();
                    break;
                }

                // Handle commands
                handleCommand(inputArr, tasks, taskCount);
                taskCount++;

            } catch (LiaException e) {
                // Handle any Lia-specific exceptions
                printLine();
                System.out.println(INDENTATION + e.getMessage());
                printLine();
            }
        }

        scanner.close();
    }

    /**
     * Handles the user input command and performs the corresponding action.
     *
     * @param inputArr The split user input containing the command and arguments.
     * @param tasks The array of tasks to manage.
     * @param taskCount The current number of tasks.
     * @throws LiaException if the input is invalid or unrecognized.
     */
    private static void handleCommand(String[] inputArr, Task[] tasks, int taskCount) throws LiaException {
        String command = inputArr[0];

        switch (command.toLowerCase()) {
        case "list":
            printTasks(tasks, taskCount);
            break;
        case "todo":
            if (inputArr.length < 2 || inputArr[1].isBlank()) {
                throw new LiaException("Oops! The description of a todo cannot be empty.");
            }
            tasks[taskCount] = new ToDo(inputArr[1]);
            addTaskAndPrint(tasks[taskCount], taskCount + 1);
            break;
        case "deadline":
            if (inputArr.length < 2 || !inputArr[1].contains("/by")) {
                throw new LiaException("Oops! The deadline command requires a description and '/by' followed by a date.");
            }
            String[] deadlineDetails = inputArr[1].split(" /by ", 2);
            tasks[taskCount] = new Deadline(deadlineDetails[0], deadlineDetails[1]);
            addTaskAndPrint(tasks[taskCount], taskCount + 1);
            break;
        case "event":
            if (inputArr.length < 2 || !inputArr[1].contains("/from") || !inputArr[1].contains("/to")) {
                throw new LiaException("Oops! The event command requires a description, '/from', and '/to' followed by times.");
            }
            String[] eventDetails = inputArr[1].split(" /from ", 2);
            String[] times = eventDetails[1].split(" /to ", 2);
            tasks[taskCount] = new Event(eventDetails[0], times[0], times[1]);
            addTaskAndPrint(tasks[taskCount], taskCount + 1);
            break;
        default:
            throw new LiaException("Oops! I don't recognize that command.");
        }
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasks The array of tasks.
     * @param taskCount The number of tasks in the list.
     */
    private static void printTasks(Task[] tasks, int taskCount) {
        printLine();
        if (taskCount == 0) {
            System.out.println(INDENTATION + "No tasks found.");
        } else {
            System.out.println(INDENTATION + "Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println(INDENTATION + (i + 1) + "." + tasks[i].toString());
            }
        }
        printLine();
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
