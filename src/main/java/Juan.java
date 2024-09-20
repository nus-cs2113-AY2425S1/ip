import CustomExceptions.*; // Import custom exception classes
import TaskChildren.*; // Import task-related classes like ToDo, Deadline, and Event

import java.util.Scanner; // Import Scanner for user input

public class Juan {
    // Constant for common error message to improve code readability and reusability
    private final static String porFavor = "Por Favor?\n";

    // Main entry point for the application
    public static void main(String[] args) {
        // Display initial line and welcome message
        lineMessage();
        helloMessage();
        lineMessage();

        // Continue chatting as long as user doesn't exit
        boolean continueChatting = true;
        while (continueChatting) {
            // Chat feature to handle user input
            continueChatting = chatFeature();
        }

        // Display goodbye message when the chat ends
        byeMessage();
        lineMessage();
    }

    // Handles user input and executes corresponding actions
    public static boolean chatFeature() {

        Scanner scanner = new Scanner(System.in); // Initialize the scanner for reading user input
        String line = scanner.nextLine(); // Read user input
        lineMessage(); // Print separator line

        // Handle "bye" command to end chat
        if (line.equals("bye")) {
            return false; // End the conversation
        }
        // Handle "list" command to display the list of tasks
        else if (line.equals("list")) {
            Task.printTasksList(); // Print task list from Task class
        }
        // Handle "delete" command
        else if (line.startsWith("delete ")) {
            try {
                int taskIndex = Integer.parseInt(line.replace("delete ", "")) - 1; // Parse the task index
                Task.deleteTask(taskIndex); // Mark the task as done
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                // Handle invalid index or format exceptions
                System.out.println(porFavor + "DELETE EXCEPTION: INVALID TASK INDEX");
            } catch (NullPointerException e) {
                // Handle null task case
                System.out.println(porFavor + "DELETE EXCEPTION: NULL TASK INDEX");
            }
        }
        // Handle "mark" command to mark a task as completed
        else if (line.startsWith("mark ")) {
            try {
                int taskIndex = Integer.parseInt(line.replace("mark ", "")) - 1; // Parse the task index
                Task.mark(taskIndex); // Mark the task as done
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                // Handle invalid index or format exceptions
                System.out.println(porFavor + "MARK EXCEPTION: INVALID TASK INDEX");
            } catch (NullPointerException e) {
                // Handle null task case
                System.out.println(porFavor + "MARK EXCEPTION: NULL TASK INDEX");
            }
        }
        // Handle "unmark" command to unmark a task as completed
        else if (line.startsWith("unmark ")) {
            try {
                int taskIndex = Integer.parseInt(line.replace("unmark ", "")) - 1; // Parse the task index
                Task.unmark(taskIndex); // Unmark the task
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                // Handle invalid index or format exceptions
                System.out.println(porFavor + "UNMARK EXCEPTION: INVALID TASK INDEX");
            } catch (NullPointerException e) {
                // Handle null task case
                System.out.println(porFavor + "UNMARK EXCEPTION: NULL TASK INDEX");
            }

        }
        // Handle "todo" command to create a new ToDo task
        else if (line.startsWith("todo ")) {
            try {
                new ToDo(line); // Create a new ToDo object
            } catch (ToDoConstructorException e) {
                // Handle custom ToDo exception
                System.out.println(porFavor + e.getMessage());
            }
        }
        // Handle "deadline" command to create a new Deadline task
        else if (line.startsWith("deadline ")) {
            try {
                new Deadline(line); // Create a new Deadline object
            } catch (DeadlineConstructorException e) {
                // Handle custom Deadline exception
                System.out.println(porFavor + e.getMessage());
            }
        }
        // Handle "event" command to create a new Event task
        else if (line.startsWith("event ")) {
            try {
                new Event(line); // Create a new Event object
            } catch (EventConstructorException e) {
                // Handle custom Event exception
                System.out.println(porFavor + e.getMessage());
            }
        }
        // Default case for unrecognized commands
        else {
            System.out.println(porFavor + "UNRECOGNIZED REQUEST"); // Inform user about an unrecognized command
        }

        lineMessage(); // Print separator line
        return true; // Continue conversation
    }

    // Utility function to print a separator line for clean output formatting
    public static void lineMessage() {
        String line = "____________________________________________________________\n";
        System.out.print(line);
    }

    // Displays a welcome message when the program starts
    public static void helloMessage() {
        String greeting =
                "               ._-'-_ .\n" +
                        "          . '  /_-_-_\\   ` .\n" +
                        "       .'     |-_-_-_-|      `.\n" +
                        "      (       `.-_-_-.'        )\n" +
                        "      !`.                    .'!\n" +
                        "        ! ` .            . ' !\n" +
                        "          ! ! ! ! ! ! ! !  !\n" +
                        "            / /       \\ \\\n" +
                        "          _-| \\___ ___/ /-_\n" +
                        "         (_ )__\\_)\\(_/__( _)\n" +
                        "             ))))\\X\\ ((((\n" +
                        "               \\/ \\/ \n" +
                        "Hola Amigo, I am Juan Cervantes Salamanca from Michoacan \n" +
                        "Welcome to la familia \n" +
                        "How can we help you? \n";
        System.out.print(greeting); // Print the welcome message
    }

    // Displays a goodbye message when the program ends
    public static void byeMessage() {
        String bye = "Adios amigo, la familia will miss you\n";
        System.out.print(bye); // Print the goodbye message
    }
}
