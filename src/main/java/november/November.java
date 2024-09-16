package november;

import november.exceptions.NovemberExceptions;
import november.tasks.Task;
import november.tasks.Todo;
import november.tasks.Deadline;
import november.tasks.Event;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class November {

    private static final String SAVE_FILE = "data/saveFile.txt"; // Path to the save file
    private static final File saveFile = new File(SAVE_FILE); // File object for the save file

    private static final String SEPARATOR = "____________________________________________________________"; // Separator for output
    private static final String EXIT_COMMAND = "bye"; // Command to exit the chatbot
    private static final String MARK_COMMAND = "mark"; // Command to mark a task as done
    private static final String UNMARK_COMMAND = "unmark"; // Command to unmark a task
    private static final String LIST_COMMAND = "list"; // Command to list all tasks
    private static final String TODO_COMMAND = "todo"; // Command to add a TODO task
    private static final String DEADLINE_COMMAND = "deadline"; // Command to add a DEADLINE task
    private static final String EVENT_COMMAND = "event"; // Command to add an EVENT task
    private static final String DELETE_COMMAND = "delete"; // Command to delete a task

    private static final String INIT_SENTENCE = "Hello! I'm November." + System.lineSeparator() + "What can I do for you?"; // Initial greeting
    private static final String EXIT_MESSAGE = "Bye! Hope to see you again soon!"; // Exit message
    private static final String LIST_MESSAGE = "Here are the tasks in your list:"; // Message when listing tasks
    private static final String LIST_EMPTY_MESSAGE = "Your list is currently empty."; // Message when no tasks are in the list
    private static final String MARK_TASK_MESSAGE = "Nice! I've marked this task as done:" + System.lineSeparator() + "  "; // Message when a task is marked as done
    private static final String UNMARK_TASK_MESSAGE = "Ok, I've marked this task as not done yet:" + System.lineSeparator() + "  "; // Message when a task is unmarked
    private static final String ADD_TASK_MESSAGE = "Got it. I've added this task:" + System.lineSeparator() + "  "; // Message when a new task is added
    private static final String DELETE_TASK_MESSAGE = "Got it. I've removed this task:" + System.lineSeparator() + "  "; // Message when a task is deleted
    private static final String NONNUMERICAL_INDEX_MESSAGE = "Please provide a valid numerical index."; // Message for non-numeric index
    private static final String INDEX_OUT_OF_BOUNDS_MESSAGE = "Sorry, but that index is not within the list."; // Message for out-of-bounds index
    private static final String INVALID_INPUT_MESSAGE = "I'm sorry, I don't know what that means."; // Message for invalid input

    /**
     * Initializes the save file by creating the necessary directories and file if they do not exist.
     */
    private static void initialiseSaveFile() {
        Path dirPath = Paths.get("data"); // Path to the data directory
        Path filePath = dirPath.resolve("saveFile.txt"); // Path to the save file
        File file = new File(filePath.toString()); // File object for the save file

        // Create directories if they do not exist
        if (!file.exists()) {
            try {
                Files.createDirectories(dirPath);
            } catch (IOException e) {
                System.out.println("Directory could not be created");
                throw new RuntimeException(e);
            }
        }

        // Create a save file if it does not already exist
        try {
            if (file.createNewFile()) {
                System.out.println("No existing save file found. New save file created: " + file.getName());
            } else {
                System.out.println("Accessing existing save file...");
            }
        } catch (IOException e) {
            System.out.println("Save file could not be created.");
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads the tasks from the save file into the given task list.
     *
     * @param taskList The list to populate with tasks from the save file.
     * @throws FileNotFoundException If the save file is not found.
     */
    private static void loadSaveFile(List<Task> taskList) throws FileNotFoundException {
        Scanner s = new Scanner(saveFile); // Create a Scanner to read the save file
        while (s.hasNext()) {
            String line = s.nextLine(); // Read each line from the file
            String command = line.split(" ", 2)[0]; // Extract command from the line
            switch (command) {
                case TODO_COMMAND:
                    String[] todoData = line.split(" \\| ", 3); // Split the line for TODO task data
                    String todoDescription = todoData[2]; // Get the description of the TODO task
                    Todo todoTask = NovemberExceptions.validTodo(todoDescription); // Create a TODO task
                    taskList.add(todoTask); // Add the TODO task to the list
                    if (Boolean.parseBoolean(todoData[1])) {
                        markTask(String.valueOf(taskList.size()), taskList, false); // Mark task if needed
                    }
                    break;
                case DEADLINE_COMMAND:
                    String[] deadlineData = line.split(" \\| ", 4); // Split the line for DEADLINE task data
                    String deadlineDescription = deadlineData[2] + " /by " + deadlineData[3]; // Format the description
                    Deadline deadlineTask = NovemberExceptions.validDeadline(deadlineDescription); // Create a DEADLINE task
                    taskList.add(deadlineTask); // Add the DEADLINE task to the list
                    if (Boolean.parseBoolean(deadlineData[1])) {
                        markTask(String.valueOf(taskList.size()), taskList, false); // Mark task if needed
                    }
                    break;
                case EVENT_COMMAND:
                    String[] eventData = line.split(" \\| ", 5); // Split the line for EVENT task data
                    String eventDescription = eventData[2] + " /from " + eventData[3] + " /to " + eventData[4]; // Format the description
                    Event eventTask = NovemberExceptions.validEvent(eventDescription); // Create an EVENT task
                    taskList.add(eventTask); // Add the EVENT task to the list
                    if (Boolean.parseBoolean(eventData[1])) {
                        markTask(String.valueOf(taskList.size()), taskList, false); // Mark task if needed
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid entry in save file."); // Handle invalid entries
            }
        }
        System.out.println("Save file successfully loaded.");
    }

    /**
     * Updates the save file with the current list of tasks.
     *
     * @param taskList The list of tasks to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    private static void updateSaveFile(List<Task> taskList) throws IOException {
        try (FileWriter fw = new FileWriter(saveFile)) {
            for (Task task : taskList) {
                fw.write(task.toString()); // Write the task to the file
                fw.write(System.lineSeparator()); // Add a new line after each task
            }
        }
    }

    /**
     * Prints a line of underscores to mark the start of a print segment.
     */
    public static void beginSegment() {
        System.out.println(SEPARATOR);
    }

    /**
     * Prints a line of underscores followed by a newline to mark the end of a print segment.
     */
    public static void endSegment() {
        System.out.println(SEPARATOR + System.lineSeparator());
    }

    /**
     * Prints the list of tasks.
     *
     * @param taskList The list of tasks to print.
     */
    public static void printTaskList(List<Task> taskList) {
        beginSegment();
        if (taskList.isEmpty()) {
            System.out.println(LIST_EMPTY_MESSAGE);
            return;
        }
        int index = 0;
        System.out.println(LIST_MESSAGE);
        while (index < taskList.size()) {
            System.out.print(index + 1 + ". ");
            taskList.get(index).printTask(); // Print each task
            index++;
        }
        endSegment();
    }

    /**
     * Prints the current number of tasks in the list.
     *
     * @param taskList The list of tasks.
     */
    private static void printTaskCount(List<Task> taskList) {
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Adds a new TODO task to the list and updates the save file.
     *
     * @param description The description of the TODO task.
     * @param taskList    The list to add the task to.
     */
    private static void addNewTodo(String description, List<Task> taskList) {
        try {
            Todo todoTask = NovemberExceptions.validTodo(description); // Create a new TODO task
            taskList.add(todoTask); // Add the task to the list
            beginSegment();
            System.out.print(ADD_TASK_MESSAGE);
            todoTask.printTask(); // Print the added task
            printTaskCount(taskList); // Print the current task count
            endSegment();
            updateSaveFile(taskList); // Update the save file
        } catch (IllegalArgumentException e) {
            beginSegment();
            System.out.println(e.getMessage()); // Print error message if task is invalid
            endSegment();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a new DEADLINE task to the list and updates the save file.
     *
     * @param description The description of the DEADLINE task.
     * @param taskList    The list to add the task to.
     */
    private static void addNewDeadline(String description, List<Task> taskList) {
        try {
            Deadline deadlineTask = NovemberExceptions.validDeadline(description); // Create a new DEADLINE task
            taskList.add(deadlineTask); // Add the task to the list
            beginSegment();
            System.out.print(ADD_TASK_MESSAGE);
            deadlineTask.printTask(); // Print the added task
            printTaskCount(taskList); // Print the current task count
            endSegment();
            updateSaveFile(taskList); // Update the save file
        } catch (IllegalArgumentException e) {
            beginSegment();
            System.out.println(e.getMessage()); // Print error message if task is invalid
            endSegment();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a new EVENT task to the list and updates the save file.
     *
     * @param description The description of the EVENT task.
     * @param taskList    The list to add the task to.
     */
    private static void addNewEvent(String description, List<Task> taskList) {
        try {
            Event eventTask = NovemberExceptions.validEvent(description); // Create a new EVENT task
            taskList.add(eventTask); // Add the task to the list
            beginSegment();
            System.out.print(ADD_TASK_MESSAGE);
            eventTask.printTask(); // Print the added task
            printTaskCount(taskList); // Print the current task count
            endSegment();
            updateSaveFile(taskList); // Update the save file
        } catch (IllegalArgumentException e) {
            beginSegment();
            System.out.println(e.getMessage()); // Print error message if task is invalid
            endSegment();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Unmarks a task as incomplete.
     *
     * @param description The index of the task to unmark.
     * @param taskList    The list of tasks.
     */
    private static void unmarkTask(String description, List<Task> taskList) {
        try {
            int unmarkIndex = Integer.parseInt(description) - 1; // Parse the index and adjust for 0-based indexing
            taskList.get(unmarkIndex).setIncomplete(); // Set the task as incomplete
            beginSegment();
            System.out.print(UNMARK_TASK_MESSAGE);
            taskList.get(unmarkIndex).printTask(); // Print the unmarked task
            endSegment();
            updateSaveFile(taskList); // Update the save file
        } catch (NumberFormatException e) {
            beginSegment();
            System.out.println(NONNUMERICAL_INDEX_MESSAGE); // Print error message for invalid index
            endSegment();
        } catch (IndexOutOfBoundsException e) {
            beginSegment();
            System.out.println(INDEX_OUT_OF_BOUNDS_MESSAGE); // Print error message for out-of-bounds index
            endSegment();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Marks a task as complete.
     *
     * @param description The index of the task to mark.
     * @param taskList    The list of tasks.
     * @param printMessage Flag indicating whether to print a message.
     */
    private static void markTask(String description, List<Task> taskList, Boolean printMessage) {
        try {
            int markIndex = Integer.parseInt(description) - 1; // Parse the index and adjust for 0-based indexing
            taskList.get(markIndex).setComplete(); // Set the task as complete
            if (printMessage) {
                beginSegment();
                System.out.print(MARK_TASK_MESSAGE);
                taskList.get(markIndex).printTask(); // Print the marked task
                endSegment();
            }
        } catch (NumberFormatException e) {
            beginSegment();
            System.out.println(NONNUMERICAL_INDEX_MESSAGE); // Print error message for invalid index
            endSegment();
        } catch (IndexOutOfBoundsException e) {
            beginSegment();
            System.out.println("Sorry, but that mark index is not within the list."); // Print error message for out-of-bounds index
            endSegment();
        }
    }

    /**
     * Deletes a task from the list.
     *
     * @param sentence The input sentence containing the task index to delete.
     * @param taskList The list of tasks.
     */
    private static void deleteTask(String[] sentence, List<Task> taskList) {
        try {
            int deleteIndex = Integer.parseInt(sentence[1]) - 1; // Parse the index and adjust for 0-based indexing
            beginSegment();
            System.out.print(DELETE_TASK_MESSAGE);
            taskList.get(deleteIndex).printTask(); // Print the deleted task
            taskList.remove(deleteIndex); // Remove the task from the list
            printTaskCount(taskList); // Print the updated task count
            endSegment();
            updateSaveFile(taskList); // Update the save file
        } catch (NumberFormatException e) {
            beginSegment();
            System.out.println(NONNUMERICAL_INDEX_MESSAGE); // Print error message for non-numeric index
            endSegment();
        } catch (IndexOutOfBoundsException e) {
            beginSegment();
            System.out.println(INDEX_OUT_OF_BOUNDS_MESSAGE); // Print error message for out-of-bounds index
            endSegment();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Prints the exit message.
     */
    private static void printExitMessage() {
        beginSegment();
        System.out.println(EXIT_MESSAGE); // Print the exit message
        endSegment();
    }

    /**
     * Prints the message for unrecognized input.
     */
    private static void printUnrecognizedInputMessage() {
        beginSegment();
        System.out.println(INVALID_INPUT_MESSAGE); // Print the unrecognized input message
        endSegment();
    }

    /**
     * Main method to start the program and handle user input.
     *
     * @param args Command-line arguments (not used).
     * @throws FileNotFoundException If the save file is not found.
     */
    public static void main(String[] args) throws FileNotFoundException {
        List<Task> taskList = new ArrayList<>(); // Initialize the task list
        initialiseSaveFile(); // Initialize the save file
        loadSaveFile(taskList); // Load tasks from the save file

        beginSegment();
        System.out.println(INIT_SENTENCE); // Print the initial greeting
        endSegment();

        Scanner scan = new Scanner(System.in); // Create a Scanner for user input
        String input = scan.nextLine(); // Read the initial input

        // Continue reading user input until the exit command is entered
        while (!input.equals(EXIT_COMMAND)) {
            String[] sentence = {input, input};
            String command = input;
            String description = "";

            // Split the input into command and description if applicable
            if (input.contains(" ")) {
                sentence = input.split(" ", 2);
                command = sentence[0];
                description = sentence[1];
            }

            switch (command) {
                case MARK_COMMAND:
                    markTask(description, taskList, true); // Mark a task as complete
                    break;
                case UNMARK_COMMAND:
                    unmarkTask(description, taskList); // Unmark a task as incomplete
                    break;
                case LIST_COMMAND:
                    printTaskList(taskList); // Print the list of tasks
                    break;
                case TODO_COMMAND:
                    addNewTodo(description, taskList); // Add a new TODO task
                    break;
                case DEADLINE_COMMAND:
                    addNewDeadline(description, taskList); // Add a new DEADLINE task
                    break;
                case EVENT_COMMAND:
                    addNewEvent(description, taskList); // Add a new EVENT task
                    break;
                case DELETE_COMMAND:
                    deleteTask(sentence, taskList); // Delete a task
                    break;
                default:
                    printUnrecognizedInputMessage(); // Response to unrecognized inputs
                    break;
            }

            input = scan.nextLine(); // Read the next input
        }

        printExitMessage(); // Print the exit message
    }
}
