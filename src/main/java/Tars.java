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

    // Process user input and return true if "bye" is entered to exit
    public static boolean processUserInput(String input, List<Task> taskList, UserInterface ui) {
        try {
            // Handle "bye" command
            if (input.equals("bye"))
            {
                ui.showGoodbyeMessage();
                return true;  // Exit loop
            }
            // Handle "list" command
            else if (input.equals("list"))
            {
                ui.showTasks(taskList);
            }
            // Handle "todo" command
            else if (input.startsWith("todo"))
            {
                // Check if task description is provided after "todo"
                if (input.length() <= 5)
                {
                    // Throw humorous exception for missing description
                    throw new TarsException("Whoa there! Your todo needs something to do. Can't save air.");
                }
                String taskDescription = input.substring(5).trim();
                if (taskDescription.isEmpty())
                {
                    // Throw humorous exception for empty task description
                    throw new TarsException("Oops! Looks like you're trying to add a todo with no todo in it. I need more details.");
                }
                addTask(taskList, "todo", taskDescription);
            }
            // Handle "deadline" command
            else if (input.startsWith("deadline"))
            {
                if (!input.contains("/by"))
                {
                    // Check if the deadline format is correct
                    throw new TarsException("Oops! A deadline needs a due date. Format it like this: 'deadline <task> /by <due date>'.");
                }
                String deadlineDescription = input.substring(9).trim();
                if (deadlineDescription.isEmpty())
                {
                    // Throw exception if no description provided
                    throw new TarsException("Uh-oh! The deadline task seems to be missing its description. Try again with more details.");
                }
                addTask(taskList, "deadline", deadlineDescription);
            }
            // Handle "event" command
            else if (input.startsWith("event"))
            {
                if (!input.contains("/from") || !input.contains("/to"))
                {
                    // Check if the event format is correct
                    throw new TarsException("Hold up! An event needs both start and end times. Format it like this: 'event <task> /from <start> /to <end>'.");
                }
                String eventDescription = input.substring(6).trim();
                if (eventDescription.isEmpty())
                {
                    // Throw exception if no description provided
                    throw new TarsException("Uh-oh! The event description seems to be missing. Give me something to work with!");
                }
                addTask(taskList, "event", eventDescription);
            }
            // Handle "mark" and "unmark" commands
            else if (input.startsWith("mark"))
            {
                markTask(input, taskList, ui);
            } else if (input.startsWith("unmark"))
            {
                unmarkTask(input, taskList, ui);
            }
            // Handle unrecognized commands
            else
            {
                // Throw humorous exception for unknown command
                throw new TarsException("Hmm, that doesn't sound like a command I recognize. Maybe try again?");
            }
        }
        catch (TarsException e)
        {
            // Catch and display exception messages
            ui.printSeparator();
            System.out.println(e.getMessage());
            ui.printSeparator();
        }
        return false;  // Continue processing user input
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
    public static void markTask(String input, List<Task> taskList, UserInterface ui) throws TarsException {
        try {
            // Check if input contains a space between 'mark' and the task number
            if (!input.contains(" ")) {
                throw new TarsException("Oops! The correct format is 'mark <task number>'. For example: 'mark 1'.");
            }

            // Extract task number from user input. User input starts at 1, but array index starts at 0, so subtract 1.
            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;

            // Check if task number is within valid range
            if (taskNumber < 0 || taskNumber >= taskList.size()) {
                throw new TarsException("Oops! That task number is out of range. Try picking a number between 1 and " + taskList.size() + ". I believe in you!");
            }

            // Mark the task as done
            taskList.get(taskNumber).markAsDone();
            ui.printSeparator();
            System.out.println("    Great! Task marked as complete: ");
            System.out.println("    " + taskList.get(taskNumber));  // Display the marked task
            ui.printSeparator();
        }
        catch (NumberFormatException e)
        {
            // If user input is not a valid number, provide a clear correction method
            throw new TarsException("Uh-oh! That doesn't look like a number. Make sure to enter 'mark' followed by the task number. For example: 'mark 1'.");
        }
    }

    public static void unmarkTask(String input, List<Task> taskList, UserInterface ui) throws TarsException {
        try {
            // Check if input contains a space between 'unmark' and the task number
            if (!input.contains(" "))
            {
                throw new TarsException("Oops! The correct format is 'unmark <task number>'. For example: 'unmark 1'.");
            }

            // Extract task number from user input. User input starts at 1, but array index starts at 0, so subtract 1.
            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;

            // Check if task number is within valid range
            if (taskNumber < 0 || taskNumber >= taskList.size())
            {
                throw new TarsException("Oops! That task number is out of range. Try picking a number between 1 and " + taskList.size() + ". Don't worry, you'll get it!");
            }

            // Unmark the task
            taskList.get(taskNumber).markAsNotDone();
            ui.printSeparator();
            System.out.println("    Task has been unmarked. Let's get back to work: ");
            System.out.println("    " + taskList.get(taskNumber));  // Display the unmarked task
            ui.printSeparator();
        }
        catch (NumberFormatException e)
        {
            // If user input is not a valid number, provide a clear correction method
            throw new TarsException("Hmm, that doesn't look like a number. Remember, you need to enter 'unmark' followed by the task number. For example: 'unmark 1'.");
        }
    }

}