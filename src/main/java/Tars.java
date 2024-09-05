import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tars {
    public static void main(String[] args) {
        String logo = " _____                        \n"
                + "|_   _|__  _ __ __ _ _ __ ___  \n"
                + "  | |/ _ \\| '__/ _` | '_ ` _ \\ \n"
                + "  | | (_) | | | (_| | | | | | |\n"
                + "  |_|\\___/|_|  \\__,_|_| |_| |_|\n";

        UserInterface ui = new UserInterface();

        // Opening speech.
        ui.printSeparator();
        System.out.println("    Hello! I'm Tars.");
        System.out.println("    Ready to conquer the world? Or maybe just help with something smaller? What can I do for you?");
        ui.printSeparator();

        // Use ArrayList instead of a fixed-size array
        List<Task> taskList = new ArrayList<>();

        // Waiting for user input.
        Scanner scanner = new Scanner(System.in);
        String input;

        // Main loop to process user input
        while (true)
        {
            // Read user input.
            input = scanner.nextLine();
            // Process the input and handle "bye" within the processUserInput method
            if (processUserInput(input, taskList, ui))
            {
                break;  // Exit loop if "bye" is entered
            }
        }
    }

    // Process user input, return true if "bye" is entered to exit
    public static boolean processUserInput(String input, List<Task> taskList, UserInterface ui) {
        if (input.equals("bye"))
        {
            ui.showGoodbyeMessage();  // Show goodbye message
            return true;  // Return true to indicate that we want to exit the loop
        }
        else if (input.equals("list"))
        {
            ui.showTasks(taskList);
            printTaskCount(taskList.size());  // Print list count
        }
        else if (input.startsWith("todo"))
        {
            String taskDescription = input.substring(5);
            addTask(taskList, "todo", taskDescription);
        }
        else if (input.startsWith("deadline"))
        {
            String deadlineDescription = input.substring(9);
            addTask(taskList, "deadline", deadlineDescription);
        }
        else if (input.startsWith("event"))
        {
            String eventDescription = input.substring(6);
            addTask(taskList, "event", eventDescription);
        }
        else if (input.startsWith("mark"))
        {
            markTask(input, taskList, ui);
        }
        else if (input.startsWith("unmark"))
        {
            unmarkTask(input, taskList, ui);
        }
        // Handle unrecognized input and add as a new task
        else
        {
            taskList.add(new Task(input));
            ui.printSeparator();
            System.out.println("    added: " + input);
            ui.printSeparator();
        }
        return false;  // Return false to continue processing other inputs
    }

    // Print the total number of tasks in the list
    public static void printTaskCount(int taskCount) {
        System.out.println("    Now you have " + taskCount + " tasks in the list.");
    }

    // Add a task to the list based on the task type
    public static void addTask(List<Task> tasks, String taskType, String input) {

        UserInterface ui = new UserInterface();

        // Switch between task types: todo, deadline, event
        switch (taskType)
        {
            case "todo":
                tasks.add(new Todo(input));
                break;
            case "deadline":
                String[] deadlineParts = input.split("/by");
                tasks.add(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
                break;
            case "event":
                String[] eventParts = input.split("/from|/to");
                tasks.add(new Event(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim()));
                break;
            default:
                break;
        }

        ui.printSeparator();
        System.out.println("    Got it. I've added this task: ");
        System.out.println("    " + tasks.get(tasks.size() - 1));
        printTaskCount(tasks.size());
        ui.printSeparator();
    }

    // Mark a task as done based on task number
    public static void markTask(String input, List<Task> taskList, UserInterface ui) {
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;  // Extract task number from input
        taskList.get(taskNumber).markAsDone();  // Mark the task as done
        ui.printSeparator();
        System.out.println("    Nice! I've marked this task as done: ");
        System.out.println("    " + taskList.get(taskNumber));  // Print marked task
        ui.printSeparator();
    }

    // Unmark a task as not done based on task number
    public static void unmarkTask(String input, List<Task> taskList, UserInterface ui) {
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
        taskList.get(taskNumber).markAsNotDone();
        ui.printSeparator();
        System.out.println("    OK, I've marked this task as not done yet: ");
        System.out.println("    " + taskList.get(taskNumber));
        ui.printSeparator();
    }

}