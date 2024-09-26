import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AirBorder {
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        String filePath = args.length > 0 ? args[0] : "./data/airborder.txt"; // Command-line argument for file path
        loadTasks(filePath);  // Load tasks from file at startup
        Scanner inputScanner = new Scanner(System.in);

        printWelcomeMessage();

        while (true) {
            String userCommand = inputScanner.nextLine().trim();

            if (userCommand.equalsIgnoreCase("bye")) {
                printExitMessage();
                saveTasks(filePath);  // Save tasks before exiting
                break;
            }

            try {
                switch (getCommandType(userCommand)) {
                    case "list":
                        printTaskList();
                        break;
                    case "todo":
                        addToDoTask(userCommand.substring(5).trim(), filePath);
                        break;
                    case "deadline":
                        addDeadlineTask(userCommand, filePath);
                        break;
                    case "event":
                        addEventTask(userCommand, filePath);
                        break;
                    case "delete":
                        deleteTask(userCommand, filePath);
                        break;
                    case "mark":
                        markTaskAsDone(userCommand, filePath);
                        break;
                    case "unmark":
                        unmarkTask(userCommand, filePath);
                        break;
                    default:
                        throw new InvalidCommandException();
                }
            } catch (AirBorderException e) {
                printErrorMessage(e.getMessage());
            }
        }

        inputScanner.close();
    }

    // Task management methods with added file path parameter
    private static void addToDoTask(String description, String filePath) throws AirBorderException {
        if (description.isEmpty()) {
            throw new AirBorderException("Insufficient Documentation: Task description cannot be empty. Please provide a valid task.");
        }
        Task newTask = new ToDo(description);
        taskList.add(newTask);
        printTaskAddedMessage(newTask);
        saveTasks(filePath);  // Save tasks after adding
    }

    private static void addDeadlineTask(String userCommand, String filePath) throws AirBorderException {
        String[] taskDetails = userCommand.substring(9).split(" /by ");
        if (taskDetails.length < 2 || taskDetails[0].trim().isEmpty()) {
            throw new AirBorderException("Insufficient Documentation: Invalid deadline format. Use: deadline <description> /by <date>");
        }
        Task newTask = new Deadline(taskDetails[0].trim(), taskDetails[1].trim());
        taskList.add(newTask);
        printTaskAddedMessage(newTask);
        saveTasks(filePath);  // Save tasks after adding
    }

    private static void addEventTask(String userCommand, String filePath) throws AirBorderException {
        String[] taskDetails = userCommand.substring(6).split(" /from | /to ");
        if (taskDetails.length < 3 || taskDetails[0].trim().isEmpty()) {
            throw new AirBorderException("Minimum Passport Validity Condition Not Met for Destination: Invalid event format. Use: event <description> /from <start> /to <end>");
        }
        Task newTask = new Event(taskDetails[0].trim(), taskDetails[1].trim(), taskDetails[2].trim());
        taskList.add(newTask);
        printTaskAddedMessage(newTask);
        saveTasks(filePath);  // Save tasks after adding
    }

    private static void deleteTask(String userCommand, String filePath) throws AirBorderException {
        int taskIndex = Integer.parseInt(userCommand.split(" ")[1]) - 1;
        if (isValidTaskIndex(taskIndex)) {
            Task removedTask = taskList.remove(taskIndex);
            printTaskDeletedMessage(removedTask);
            saveTasks(filePath);  // Save tasks after deleting
        } else {
            throw new AirBorderException("Check-In Refused: Invalid task number.");
        }
    }

    private static void markTaskAsDone(String userCommand, String filePath) throws AirBorderException {
        int taskIndex = Integer.parseInt(userCommand.split(" ")[1]) - 1;
        if (isValidTaskIndex(taskIndex)) {
            taskList.get(taskIndex).markAsDone();
            printTaskDoneMessage(taskList.get(taskIndex));
            saveTasks(filePath);  // Save tasks after marking
        } else {
            throw new AirBorderException("Check-In Refused: Invalid task number.");
        }
    }

    private static void unmarkTask(String userCommand, String filePath) throws AirBorderException {
        int taskIndex = Integer.parseInt(userCommand.split(" ")[1]) - 1;
        if (isValidTaskIndex(taskIndex)) {
            taskList.get(taskIndex).markAsNotDone();
            printTaskUndoneMessage(taskList.get(taskIndex));
            saveTasks(filePath);  // Save tasks after unmarking
        } else {
            throw new AirBorderException("Check-In Refused: Invalid task number.");
        }
    }

    // Check if the task index is valid
    private static boolean isValidTaskIndex(int taskIndex) {
        return taskIndex >= 0 && taskIndex < taskList.size();
    }

    // Save tasks to file
    private static void saveTasks(String filePath) {
        try {
            File file = new File(filePath);
            // Ensure the directory exists
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(file);
            for (Task task : taskList) {
                writer.write(formatTaskForSave(task) + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    // Load tasks from file
    private static void loadTasks(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return; // No file to load from
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTaskFromFile(line);
                taskList.add(task);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    // Helper to format tasks for saving
    private static String formatTaskForSave(Task task) {
        if (task instanceof ToDo) {
            return "T | " + (task.isDone ? "1" : "0") + " | " + task.description;
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D | " + (task.isDone ? "1" : "0") + " | " + task.description + " | " + deadline.by;
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + (task.isDone ? "1" : "0") + " | " + task.description + " | " + event.from + " | " + event.to;
        }
        return "";
    }

    // Helper to parse tasks from file
    private static Task parseTaskFromFile(String line) {
        String[] parts = line.split(" \\| ");
        switch (parts[0]) {
            case "T":
                ToDo todo = new ToDo(parts[2]);
                if (parts[1].equals("1")) {
                    todo.markAsDone();
                }
                return todo;
            case "D":
                Deadline deadline = new Deadline(parts[2], parts[3]);
                if (parts[1].equals("1")) {
                    deadline.markAsDone();
                }
                return deadline;
            case "E":
                Event event = new Event(parts[2], parts[3], parts[4]);
                if (parts[1].equals("1")) {
                    event.markAsDone();
                }
                return event;
            default:
                return null;
        }
    }

    // Other helper methods (printTaskAddedMessage, printTaskDeletedMessage, etc.) remain unchanged
}
