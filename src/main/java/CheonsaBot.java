import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;
import tasks.FormatException;

public class CheonsaBot {
    public static final int LINE_LENGTH = 60;
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static final String FILE_PATH = getJarDirectoryPath().resolve("tasklist.txt").toString(); 

    /**
     * The entry point of the application. Starts bot and listens for user input.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        createFileIfNotExists(); 
        loadTasksFromFile();    
        getGreeting();
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                String userInput = scanner.nextLine();
                running = parseInput(userInput);
            }
        }
    }

    /**
     * Prints a greeting message to the console.
     * Displays the bot's logo and a welcome message.
     */
    private static void getGreeting() {
        String logo = """
                       (\\ -=- /)
                       ( \\( )/ )
                       (       )
                        `>   <'
                        /     \\
                        `-._.-'
                       """;
        System.out.println(getHorizontalLine());
        System.out.println("Hello, I'm 천사봇! (AngelBot)");
        System.out.print(logo);
        System.out.println("How may I assist you today?");
        System.out.println(getHorizontalLine());
    }

    /**
     * Prints a farewell message and exits the program.
     */
    private static void getBye() {
        System.out.println(getHorizontalLine());
        System.out.println("Bye, see you again soon!");
        System.out.println(getHorizontalLine());
    }

    /**
     * Returns a horizontal line of dashes used for formatting console output.
     *
     * @return A string containing the horizontal line.
     */
    private static String getHorizontalLine() {
        return "-".repeat(LINE_LENGTH);
    }

    /**
     * Parses user input and executes the corresponding command.
     *
     * @param userInput The input provided by the user.
     * @return True if the bot should continue running, false if it should exit.
     */
    private static boolean parseInput(String userInput) {
        String[] words = userInput.split(" ", 2);
        String command = words[0].replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String argument = words.length > 1 ? words[1] : "";

        switch (command) {
            case "mark":
                markTask(argument);
                break;
            case "unmark":
                unmarkTask(argument);
                break;
            case "bye":
                getBye();
                return false;
            case "list":
                printTaskList();
                break;
            case "delete":
                deleteTask(argument);
                break;
            default:
                addTask(command, words);
                break;
        }
        return true;
    }

    /**
     * Marks a task as completed based on the provided task number.
     *
     * @param taskNumber The number of the task to be marked as completed.
     */
    private static void markTask(String taskNumber) {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).setAsDone();
                updateTaskFile();
                System.out.println(getHorizontalLine());
                System.out.println("Marked task as done: " + tasks.get(index));
                System.out.println(getHorizontalLine());
            } else {
                System.out.println(getHorizontalLine());
                System.out.println("Task number out of range :(");
                System.out.println("Current number of tasks: " + tasks.size());
                System.out.println(getHorizontalLine());
            }
            
        } catch (NumberFormatException e) {
            System.out.println(getHorizontalLine());
            System.out.println("Invalid task number :(");
            System.out.println(getHorizontalLine());
        }
    }

    /**
     * Unmarks a task as incomplete based on the provided task number.
     *
     * @param taskNumber The number of the task to be unmarked.
     */
    private static void unmarkTask(String taskNumber) {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).setAsUndone();
                updateTaskFile();
                System.out.println(getHorizontalLine());
                System.out.println("Unmarked task: " + tasks.get(index));
                System.out.println(getHorizontalLine());
            } else {
                System.out.println(getHorizontalLine());
                System.out.println("Task number is out of range :(");
                System.out.println(getHorizontalLine());
            }
        } catch (NumberFormatException e) {
            System.out.println(getHorizontalLine());
            System.out.println("Invalid task number :(");
            System.out.println(getHorizontalLine());
        }
    }

    /**
     * Adds a new task based on the command and argument provided.
     *
     * @param command The type of task to add (e.g., "todo", "deadline", "event").
     * @param words The details of the task.
     */
    private static void addTask(String command, String[] words) {
        Task task = null;
        
        try {
            switch (command.toLowerCase()) {
                case "todo":
                    if (words.length > 1 && !words[1].trim().isEmpty()) {
                        task = new ToDo(words[1]);
                    } else {
                        throw new IllegalArgumentException("Oh no! The description for 'todo' is missing. :( ");
                    }
                    break;

                case "deadline":
                    if (words.length > 1) {
                        String[] deadlineParts = words[1].split("/by", 2);
                        if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty()) {
                            throw new IllegalArgumentException("Oops! The deadline format is incorrect or missing :( \nUse: deadline <description> /by <date>");
                        }
                        String deadlineDescription = deadlineParts[0].trim();
                        String by = deadlineParts[1].trim();
                        task = new Deadline(deadlineDescription, by);
                    } else {
                        throw new IllegalArgumentException("Oops! Missing description for 'deadline' :( ");
                    }
                    break;

                case "event":
                    if (words.length > 1) {
                        String[] eventParts = words[1].split("/from", 2);
                        String eventDescription = eventParts[0].trim();
                        String from = "";
                        String to = "";
                        if (eventParts.length > 1) {
                            String[] timeParts = eventParts[1].split("/to", 2);
                            from = timeParts[0].trim();
                            if (timeParts.length > 1) {
                                to = timeParts[1].trim();
                            }
                        }
                        if (eventDescription.isEmpty() || from.isEmpty() || (to.isEmpty() && eventParts.length == 1)) {
                            throw new IllegalArgumentException("Oops! The event format is incorrect or missing :( \nUse: event <description> /from <start> /to <end>");
                        }
                        task = new Event(eventDescription, from, to);
                    } else {
                        throw new IllegalArgumentException("Oops! Missing description for 'event' :( ");
                    }
                    break;

                default:
                    throw new IllegalArgumentException("Oh no! Unknown task type: " + command + " :(\nTry commands todo, deadline, or event with the correct format!");
            }

            if (task != null) {
                tasks.add(task);
                appendTaskToFile(task); // Append the task to the file
                System.out.println(getHorizontalLine());
                System.out.println("Added: " + task);
                System.out.println(getHorizontalLine());
            }

        } catch (IllegalArgumentException e) {
            System.out.println(getHorizontalLine());
            System.out.println("Error: " + e.getMessage());
            System.out.println(getHorizontalLine());
        }
    }
  
    /*
     * Delete the task at specified index
     * @param index The index of task to delete.
     */
    private static void deleteTask(String index) {
        try {
            int taskIndex = Integer.parseInt(index) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new IndexOutOfBoundsException();
            }
    
            System.out.println(getHorizontalLine());
            System.out.println("Removed: " + tasks.get(taskIndex).getDescription());
            tasks.remove(taskIndex);
            updateTaskFile(); // Rewrite the file after deletion
            System.out.println("Only " + tasks.size() + " thing(s) left to do!");
            System.out.println(getHorizontalLine());
    
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println(getHorizontalLine());
            System.out.println("Error: Invalid task number! :(");
            System.out.println(getHorizontalLine());
        }
    }

    /**
     * Appends a task to the tasklist.txt file.
     * @param task The task to write to the file on disk.
     */
    private static void appendTaskToFile(Task task) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(formatTaskForFile(task));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Oh no, error appending task to file: " + e.getMessage() + " :(");
        }
    }

    /**
     * Formats a task as a string for saving to the file.
     * @param task The task to write to the file on disk.
     * @return A line to be saved to file on disk.
     */
    private static String formatTaskForFile(Task task) {
        String taskType = "";
        if (task instanceof ToDo) {
            taskType = "T";
        } else if (task instanceof Deadline) {
            taskType = "D";
        } else if (task instanceof Event) {
            taskType = "E";
        }

        String isDone = String.valueOf(task.getIsDone());
        String description = task.getDescription();

        // Handle task-specific properties (like deadlines and event times)
        String taskDetails = "";
        if (task instanceof Deadline) {
            taskDetails = ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            taskDetails = ((Event) task).getFrom() + " | " + ((Event) task).getTo();
        }

        return taskType + " | " + isDone + " | " + description + (taskDetails.isEmpty() ? "" : " | " + taskDetails);
    }

     /**
     * Prints the list of tasks to the console.
     * Displays each task with its corresponding number.
     */
    private static void printTaskList() {
        System.out.println(getHorizontalLine());
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty, maybe add a task?");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println(getHorizontalLine());
    }

    /**
     * Get the directory where the JAR file is located.
     *
     * @return The Path to the directory containing the JAR file.
     */
    private static Path getJarDirectoryPath() {
        try {
            // Get the location of the JAR file or class folder during development
            return Paths.get(CheonsaBot.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Can't find JAR file path :(", e);
        }
    }

    /**
     * Creates the tasklist.txt file if it doesn't exist.
     */
    private static void createFileIfNotExists() {
        Path filePath = Paths.get(FILE_PATH);
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                System.out.println("Error creating task list file: " + e.getMessage());
            }
        }
    }

    /**
     * Loads tasks from the tasklist.txt file using Scanner.
     * Skips lines that are incorrectly formatted.
     */
    private static void loadTasksFromFile() {
        try (Scanner scanner = new Scanner(new File(FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    Task task = parseTaskFromLine(line);
                    tasks.add(task);
                } catch (FormatException e) {
                    System.out.println("Skipping malformed line: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: tasklist.txt not found");
        }
    }

    /**
     * Parses a line from the file and creates the appropriate Task object.
     * Throws FormatException if the line is not in the correct format.
     * @param line The line to read from the file on disk.
     * @return A Task instance if succesfully parsed.
     */
    private static Task parseTaskFromLine(String line) throws FormatException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) throw new FormatException("Invalid format");

        String taskType = parts[0];
        boolean isDone = Boolean.parseBoolean(parts[1]);
        String description = parts[2];

        Task task;
        switch (taskType) {
            case "T":
                task = new ToDo(description);
                break;
            case "D":
                if (parts.length < 4) throw new FormatException("Deadline task missing date");
                task = new Deadline(description, parts[3]);
                break;
            case "E":
                if (parts.length < 5) throw new FormatException("Event task missing time");
                task = new Event(description, parts[3], parts[4]);
                break;
            default:
                throw new FormatException("Unknown task type");
        }

        if (isDone) task.setAsDone();
        return task;
    }

    /**
     * Updates the tasklist.txt file after deleting a task by rewriting the entire file.
     */
    private static void updateTaskFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(formatTaskForFile(task));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error updating task file: " + e.getMessage());
        }
    }
}
