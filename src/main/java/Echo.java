import java.util.Scanner;

public class Echo {
    private static final String SEPARATOR = "____________________________________________________________";

    public static void main(String[] args) {
        // Utilize Scanner object to allow user input
        Scanner scanner = new Scanner(System.in);

        TaskList taskList = new TaskList();

        // Define the greeting message and the chatbot name as variables
        String chatbotName = "Echo";
        String greetingMessage = "Hello! I'm " + chatbotName + "\nWhat can I do for you?\n";

        // Define the exit message as variable
        String exitMessage = "Bye. Hope to see you again soon!";

        // Print the greeting message from Echo
        printGreeting(greetingMessage);

        String userInput;
        do {
            // Read user input
            userInput = scanner.nextLine();

            // Handles different user inputs for list, bye, mark, and unmark
            if (userInput.equalsIgnoreCase("list")) {
                printTaskList(taskList);
            } else if (userInput.startsWith("mark ")) {
                handleMarking(userInput, taskList);
            } else if (userInput.startsWith("unmark ")) {
                handleUnmarking(userInput, taskList);
            } else if (!userInput.equalsIgnoreCase("bye")) {
                addTask(userInput, taskList);
            }
        } while (!userInput.equalsIgnoreCase("bye"));

        // Print the exit message from Echo
        printExitMessage(exitMessage);
    }

    private static void printGreeting(String message) {
        System.out.println(SEPARATOR);
        System.out.println(message);
        System.out.println(SEPARATOR);
    }

    private static void printTaskList(TaskList taskList) {
        System.out.println(SEPARATOR);
        System.out.println("Here are the tasks in your list:");
        System.out.println(taskList.displayTasks());
        System.out.println(SEPARATOR);
    }

    private static void handleMarking(String userInput, TaskList taskList) {
        int taskNumber = Integer.parseInt(userInput.substring(5).trim());
        System.out.println(SEPARATOR);
        System.out.println(taskList.markTaskAsDone(taskNumber));
        System.out.println(SEPARATOR);
    }

    private static void handleUnmarking(String userInput, TaskList taskList) {
        int taskNumber = Integer.parseInt(userInput.substring(7).trim());
        System.out.println(SEPARATOR);
        System.out.println(taskList.markTaskAsNotDone(taskNumber));
        System.out.println(SEPARATOR);
    }

    private static void addTask(String userInput, TaskList taskList) {
        userInput = userInput.trim();

        if (userInput.startsWith("todo ")) {
            addToDoTask(userInput, taskList);
        } else if (userInput.startsWith("deadline ")) {
            addDeadlineTask(userInput, taskList);
        } else if (userInput.startsWith("event ")) {
            addEventTask(userInput, taskList);
        }
    }

    private static void addToDoTask(String userInput, TaskList taskList) {
        String description = userInput.substring(5).trim();
        ToDo newTask = new ToDo(description);
        taskList.storeTask(newTask);
        printTaskAddedMessage(newTask, taskList.getTaskNumber());
    }

    private static void addDeadlineTask(String userInput, TaskList taskList) {
        int byIndex = userInput.indexOf(" /by ");
        String description = userInput.substring(9, byIndex).trim();
        String dueDate = userInput.substring(byIndex + 5).trim();
        Deadline newTask = new Deadline(description, dueDate);
        taskList.storeTask(newTask);
        printTaskAddedMessage(newTask, taskList.getTaskNumber());
    }

    private static void addEventTask(String userInput, TaskList taskList) {
        int fromIndex = userInput.indexOf(" /from ");
        int toIndex = userInput.indexOf(" /to ", fromIndex + 6);
        String description = userInput.substring(6, fromIndex).trim();
        String start = userInput.substring(fromIndex + 6, toIndex).trim();
        String end = userInput.substring(toIndex + 4).trim();
        Event newTask = new Event(description, start, end);
        taskList.storeTask(newTask);
        printTaskAddedMessage(newTask, taskList.getTaskNumber());
    }

    private static void printTaskAddedMessage(Task task, int totalTasks) {
        System.out.println(SEPARATOR);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        System.out.println(SEPARATOR);
    }

    private static void printExitMessage(String message) {
        System.out.println(SEPARATOR);
        System.out.println(message);
        System.out.println(SEPARATOR);
    }
}
