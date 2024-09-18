import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Bebe {

    private static final String FILE_PATH = "./data/bebe.txt";
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        loadTasksFromFile();
        welcomeMessage();
        runChatbot();
    }

    /**
     * Prints a welcome message when the chatbot starts.
     */
    private static void welcomeMessage() {
        System.out.println("Hello! I'm Bebe");
        System.out.println("What can I do for you?");
    }

    /**
     * Runs the main loop of the chatbot to handle user input.
     */
    private static void runChatbot() {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine().trim();
            String[] words = userInput.split(" ", 2);

            try {
                processUserInput(words);
            } catch (BebeException e) {
                System.out.println("OOPS!!! " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }

    /**
     * Processes the user's input and calls the appropriate methods.
     */
    private static void processUserInput(String[] words) throws BebeException {
        switch (words[0].toLowerCase()) {
            case "bye":
                exitChatbot();
                break;
            case "list":
                listTasks();
                break;
            case "mark":
                handleMark(words);
                break;
            case "unmark":
                handleUnmark(words);
                break;
            case "todo":
                handleTodo(words);
                break;
            case "deadline":
                handleDeadline(words);
                break;
            case "event":
                handleEvent(words);
                break;
            case "location":
                showFileLocation();
            case "help":
                showHelp();
                break;
            case "delete":
                handleDelete(words);
                break;
            default:
                throw new BebeException("I'm sorry, but I don't understand that command. Try using 'help' to guide you.");
        }
    }
    private static void handleMark(String[] words) throws BebeException {
        if (words.length < 2) {
            throw new BebeException("Task number to mark cannot be empty. Use format: mark <task number>");
        }
        markTaskAsDone(words[1]);
    }

    private static void handleUnmark(String[] words) throws BebeException {
        if (words.length < 2) {
            throw new BebeException("Task number to unmark cannot be empty. Use format: unmark <task number>");
        }
        markTaskAsNotDone(words[1]);
    }

    private static void handleTodo(String[] words) throws BebeException {
        if (words.length < 2) {
            throw new BebeException("The description of a todo cannot be empty. Use format: todo <task>");
        }
        addTask(new Todo(words[1]));
    }

    private static void handleDeadline(String[] words) throws BebeException {
        if (words.length < 2 || !words[1].contains("/by")) {
            throw new BebeException("Deadline description and due date cannot be empty. Use format: deadline <task> /by <date>");
        }
        String[] deadlineParts = words[1].split(" /by ");
        addTask(new Deadline(deadlineParts[0], deadlineParts[1]));
    }

    private static void handleEvent(String[] words) throws BebeException {
        if (words.length < 2 || !words[1].contains("/from") || !words[1].contains("/to")) {
            throw new BebeException("Event description, start, and end time cannot be empty. Use format: event <task> /from <start> /to <end>");
        }
        String[] eventParts = words[1].split(" /from | /to ");
        addTask(new Event(eventParts[0], eventParts[1], eventParts[2]));
    }

    private static void handleDelete(String[] words) throws BebeException{
        if (words.length < 2) {
            throw new BebeException("Task number to delete cannot be empty. Use format: delete <task number>");
        }
        deleteTask(words[1]);
    }

    private static void exitChatbot() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to be added.
     */
    private static void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        saveTasksToFile();
    }

    /**
     * Lists all the tasks added by the user.
     */
    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    /**
     * Marks a task as done.
     *
     * @param taskNumber The index of the task to mark as done.
     */
    private static void markTaskAsDone(String taskNumber) throws BebeException {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks.get(index).toString());
                saveTasksToFile();
            } else {
                throw new BebeException("Invalid task number.");
            }
        } catch (NumberFormatException e) {
            throw new BebeException("Please provide a valid task number.");
        }
    }

    /**
     * Marks a task as not done.
     *
     * @param taskNumber The index of the task to mark as not done.
     */
    private static void markTaskAsNotDone(String taskNumber) throws BebeException {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks.get(index).toString());
                saveTasksToFile();
            } else {
                throw new BebeException("Invalid task number.");
            }
        } catch (NumberFormatException e) {
            throw new BebeException("Please provide a valid task number.");
        }
    }

    /**
     * Saves the current list of tasks to a file.
     */
    private static void saveTasksToFile() {
        try {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdir(); // Create directory if it doesn't exist
            }

            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                writer.write(taskToFileFormat(task) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Converts a Task object into a string for saving in a file.
     */
    private static String taskToFileFormat(Task task) {
        String type = "";
        String status = task.isDone ? "1" : "0"; // 1 for done, 0 for not done

        if (task instanceof Todo) {
            type = "T";
            return type + " | " + status + " | " + task.description;
        } else if (task instanceof Deadline) {
            type = "D";
            Deadline deadline = (Deadline) task;
            return type + " | " + status + " | " + task.description + " | " + deadline.by;
        } else if (task instanceof Event) {
            type = "E";
            Event event = (Event) task;
            return type + " | " + status + " | " + task.description + " | " + event.from + " to " + event.to;
        }

        return "";
    }

    /**
     * Loads tasks from the file into the task list.
     */
    private static void loadTasksFromFile() {
        try {
            if (!Files.exists(Paths.get(FILE_PATH))) {
                return; // No file to load
            }

            Scanner scanner = new Scanner(new File(FILE_PATH));
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");

                switch (parts[0]) {
                    case "T":
                        Todo todo = new Todo(parts[2]);
                        if (parts[1].equals("1")) todo.markAsDone();
                        tasks.add(todo);
                        break;
                    case "D":
                        Deadline deadline = new Deadline(parts[2], parts[3]);
                        if (parts[1].equals("1")) deadline.markAsDone();
                        tasks.add(deadline);
                        break;
                    case "E":
                        String[] timeParts = parts[3].split(" to ");
                        Event event = new Event(parts[2], timeParts[0], timeParts[1]);
                        if (parts[1].equals("1")) event.markAsDone();
                        tasks.add(event);
                        break;
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    /**
     * Shows the file location where data is stored.
     */
    private static void showFileLocation() {
        System.out.println("The tasks are being saved at: " + new File(FILE_PATH).getAbsolutePath());
    }
    /**
     * Deletes a task from the list.
     *
     * @param taskNumber The index of the task to delete.
     */
    private static void deleteTask(String taskNumber) throws BebeException {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            if (index >= 0 && index < tasks.size()) {
                Task task = tasks.remove(index);
                System.out.println("Noted. I've removed this task:");
                System.out.println("  " + task.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                saveTasksToFile();
            } else {
                throw new BebeException("Invalid task number.");
            }
        } catch (NumberFormatException e) {
            throw new BebeException("Please provide a valid task number.");
        }
    }

    /**

     * Prints the available commands and their descriptions.
     */
    private static void showHelp() {
        System.out.println("Here are the commands you can use:");
        System.out.println("  todo <task description>          - Adds a ToDo task.");
        System.out.println("  deadline <task description> /by <date/time>  - Adds a Deadline task.");
        System.out.println("  event <task description> /from <start time> /to <end time>  - Adds an Event task.");
        System.out.println("  list                            - Lists all tasks.");
        System.out.println("  mark <task number>              - Marks a task as done.");
        System.out.println("  unmark <task number>            - Marks a task as not done.");
        System.out.println("  location                        - Shows the location where the tasks are saved.");
        System.out.println("  delete <task number>            - Deletes a task.");
        System.out.println("  help                            - Shows this help message.");
        System.out.println("  bye                             - Exits the chatbot.");
        System.out.println("So what can I do for you?");
    }

}
