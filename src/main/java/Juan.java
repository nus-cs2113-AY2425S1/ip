import CustomExceptions.*; // Import custom exception classes
import TaskChildren.*; // Import task-related classes like ToDo, Deadline, and Event

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Juan {


    private final static String dataFilePath = "data.text";
    private UI ui;

    // Constructor
    public void Juan() {

        ui = new UI();
    }

    // Main running function
    public void run() {

        // Display initial line and welcome message
        ui.helloMessage();

        // Add function to Read Data
        readData();
        ui.lineMessage();

        // Continue chatting as long as user doesn't exit
        boolean continueChatting = true;
        while (continueChatting) {
            // Chat feature to handle user input
            continueChatting = chatFeature(ui.readUserInput());
        }

        // Add Function to Write Data
        writeDate();
        ui.lineMessage();
        // Display goodbye message when the chat ends
        ui.byeMessage();
    }

    public void readData() {

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

    public void writeDate() {
        try {
            FileWriter writer = new FileWriter(dataFilePath);
            for (int i = 0; i < Task.size(); i++) {
                writer.write(Task.dataFileEntry(i));
            }
            writer.close();
            System.out.println("Data File Written");
        } catch (IOException e) {
            ui.porFavorMessage(e.getMessage());
        }
    }

    // Handles user input and executes corresponding actions
    public boolean chatFeature(String line) {

        ui.lineMessage(); // Print separator line

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
                ui.porFavorMessage("DELETE EXCEPTION: INVALID TASK INDEX");
            } catch (NullPointerException e) {
                // Handle null task case
                ui.porFavorMessage("DELETE EXCEPTION: NULL TASK INDEX");
            }
        }
        // Handle "mark" command to mark a task as completed
        else if (line.startsWith("mark ")) {
            try {
                int taskIndex = Integer.parseInt(line.replace("mark ", "")) - 1; // Parse the task index
                Task.mark(taskIndex); // Mark the task as done
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                // Handle invalid index or format exceptions
                ui.porFavorMessage("MARK EXCEPTION: INVALID TASK INDEX");
            } catch (NullPointerException e) {
                // Handle null task case
                ui.porFavorMessage("MARK EXCEPTION: NULL TASK INDEX");
            }
        }
        // Handle "unmark" command to unmark a task as completed
        else if (line.startsWith("unmark ")) {
            try {
                int taskIndex = Integer.parseInt(line.replace("unmark ", "")) - 1; // Parse the task index
                Task.unmark(taskIndex); // Unmark the task
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                // Handle invalid index or format exceptions
                ui.porFavorMessage("UNMARK EXCEPTION: INVALID TASK INDEX");
            } catch (NullPointerException e) {
                // Handle null task case
                ui.porFavorMessage("UNMARK EXCEPTION: NULL TASK INDEX");
            }

        }
        // Handle "todo" command to create a new ToDo task
        else if (line.startsWith("todo ")) {
            try {
                new ToDo(line, true); // Create a new ToDo object
            } catch (ToDoConstructorException e) {
                // Handle custom ToDo exception
                ui.porFavorMessage(e.getMessage());
            }
        }
        // Handle "deadline" command to create a new Deadline task
        else if (line.startsWith("deadline ")) {
            try {
                new Deadline(line, true); // Create a new Deadline object
            } catch (DeadlineConstructorException e) {
                // Handle custom Deadline exception
                ui.porFavorMessage(e.getMessage());
            }
        }
        // Handle "event" command to create a new Event task
        else if (line.startsWith("event ")) {
            try {
                new Event(line, true); // Create a new Event object
            } catch (EventConstructorException e) {
                // Handle custom Event exception
                ui.porFavorMessage(e.getMessage());
            }
        }
        // Default case for unrecognized commands
        else {
            ui.porFavorMessage("UNRECOGNIZED REQUEST"); // Inform user about an unrecognized command
        }

        ui.lineMessage(); // Print separator line
        return true; // Continue conversation
    }

    public static void main(String[] args) {
        new Juan().run();
    }

}
