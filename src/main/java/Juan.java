import CustomExceptions.*; // Import custom exception classes
import TaskChildren.*; // Import task-related classes like ToDo, Deadline, and Event
import java.io.File;
import java.io.FileWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner; // Import Scanner for user input

public class Juan {
    // Constant for common error message to improve code readability and reusability
    private final static String porFavor = "Por Favor?\n";

    private final static String dataFilePath = "data.text";

    // Main entry point for the application
    public static void main(String[] args) {
        // Display initial line and welcome message
        lineMessage();
        helloMessage();
        lineMessage();

        // Add function to Read Data
        readData();
        lineMessage();

        // Continue chatting as long as user doesn't exit
        boolean continueChatting = true;
        while (continueChatting) {
            // Chat feature to handle user input
            continueChatting = chatFeature();
        }

        // Add Function to Write Data
        writeDate();
        lineMessage();
        // Display goodbye message when the chat ends
        byeMessage();
        lineMessage();
    }

    public static void readData() {

        File dataFile = new File(dataFilePath);
        try {
            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNextLine()) {
                String inputLine = scanner.nextLine();
                String[] lineSegments = inputLine.split(" /isdone ");
                String line = lineSegments[0];
                boolean isDone = Boolean.parseBoolean(lineSegments[1]);
                if (line.startsWith("todo ")) {
                    try {
                        new ToDo(line, false); // Create a new ToDo object
                    } catch (ToDoConstructorException e) {
                        // Handle custom ToDo exception
                        System.out.println("CORRUPTED: " + line);
                        return;
                    }
                }
                // Handle "deadline" command to create a new Deadline task
                else if (line.startsWith("deadline ")) {
                    try {
                        new Deadline(line, false); // Create a new Deadline object
                    } catch (DeadlineConstructorException e) {
                        // Handle custom Deadline exception
                        System.out.println("CORRUPTED: " + line);
                        return;
                    }
                }
                // Handle "event" command to create a new Event task
                else if (line.startsWith("event ")) {
                    try {
                        new Event(line, false); // Create a new Event object
                    } catch (EventConstructorException e) {
                        // Handle custom Event exception
                        System.out.println("CORRUPTED: " + line);
                        return;
                    }
                }
                else {
                    System.out.println("CORRUPTED: " + line);
                    return;
                }

                if (isDone) {
                    Task.markLatestTask();
                }
            }
            System.out.println("Data File Read");
        } catch (FileNotFoundException e) {
            System.out.println("Data File does not exist");
        }

    }

    public static void writeDate() {
        try {
            FileWriter writer = new FileWriter(dataFilePath);
            for (int i = 0; i < Task.size(); i++) {
                writer.write(Task.dataFileEntry(i));
            }
            writer.close();
            System.out.println("Data File Written");
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
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
        // Handle "mark" command to mark a task as completed
        else if (line.startsWith("mark ")) {
            try {
                int taskIndex = Integer.parseInt(line.replace("mark ", "")) - 1; // Parse the task index
                Task.mark(taskIndex); // Mark the task as done
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
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
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
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
                new ToDo(line, true); // Create a new ToDo object
            } catch (ToDoConstructorException e) {
                // Handle custom ToDo exception
                System.out.println(porFavor + e.getMessage());
            }
        }
        // Handle "deadline" command to create a new Deadline task
        else if (line.startsWith("deadline ")) {
            try {
                new Deadline(line, true); // Create a new Deadline object
            } catch (DeadlineConstructorException e) {
                // Handle custom Deadline exception
                System.out.println(porFavor + e.getMessage());
            }
        }
        // Handle "event" command to create a new Event task
        else if (line.startsWith("event ")) {
            try {
                new Event(line, true); // Create a new Event object
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
