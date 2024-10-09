import customexceptions.*; // Import custom exception classes
import taskpackage.*; // Import task-related classes like ToDo, Deadline, and Event


public class Juan {


    private final static String dataFilePath = "data.text";
    private UI ui;
    private Storage storage;
    private TaskList tasks;

    // Constructor
    public Juan(String dataFilePath) {

        ui = new UI();
        storage = new Storage(dataFilePath, ui);
        tasks = storage.readData();
    }

    // Main running function
    public void run() {

        // Display initial line and welcome message
        ui.helloMessage();

        // Continue chatting as long as user doesn't exit
        boolean continueChatting = true;
        while (continueChatting) {
            // Chat feature to handle user input
            continueChatting = chatFeature(ui.readUserInput());
        }

        // Add Function to Write Data
        storage.writeDate(tasks);
        ui.lineMessage();
        // Display goodbye message when the chat ends
        ui.byeMessage();
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
            tasks.printTasksList(); // Print task list from Task class
        }
        // Handle "delete" command
        else if (line.startsWith("delete ")) {
            try {
                int taskIndex = Integer.parseInt(line.replace("delete ", "")) - 1; // Parse the task index
                tasks.deleteTask(taskIndex); // Mark the task as done
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
                tasks.mark(taskIndex); // Mark the task as done
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
                tasks.unmark(taskIndex); // Unmark the task
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
                new ToDo(line, tasks, true); // Create a new ToDo object
            } catch (ToDoConstructorException e) {
                // Handle custom ToDo exception
                ui.porFavorMessage(e.getMessage());
                tasks.deleteLatestTask();
            }
        }
        // Handle "deadline" command to create a new Deadline task
        else if (line.startsWith("deadline ")) {
            try {
                new Deadline(line, tasks, true); // Create a new Deadline object
            } catch (DeadlineConstructorException e) {
                // Handle custom Deadline exception
                ui.porFavorMessage(e.getMessage());
                tasks.deleteLatestTask();
            }
        }
        // Handle "event" command to create a new Event task
        else if (line.startsWith("event ")) {
            try {
                new Event(line, tasks, true); // Create a new Event object
            } catch (EventConstructorException e) {
                // Handle custom Event exception
                ui.porFavorMessage(e.getMessage());
                tasks.deleteLatestTask();
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
        new Juan(dataFilePath).run();
    }

}
