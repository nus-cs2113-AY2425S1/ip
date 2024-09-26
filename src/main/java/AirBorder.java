import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AirBorder {
    private static final String FILE_PATH = "C:\\Users\\anony\\OneDrive\\Documents\\GitHub\\ip\\src\\main\\java\\airborder.txt";
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        loadTasks();  // Load tasks from file at startup
        Scanner inputScanner = new Scanner(System.in);
        printWelcomeMessage();

        while (true) {
            String userCommand = inputScanner.nextLine().trim();

            if (userCommand.equalsIgnoreCase("bye")) {
                printExitMessage();
                saveTasks();  // Save tasks before exiting
                break;
            }

            try {
                switch (getCommandType(userCommand)) {
                    case "list":
                        printTaskList();
                        break;
                    case "todo":
                        addToDoTask(userCommand.substring(5).trim());
                        break;
                    case "deadline":
                        addDeadlineTask(userCommand);
                        break;
                    case "event":
                        addEventTask(userCommand);
                        break;
                    case "delete":
                        deleteTask(userCommand);
                        break;
                    case "mark":
                        markTaskAsDone(userCommand);
                        break;
                    case "unmark":
                        unmarkTask(userCommand);
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

    private static String getCommandType(String userCommand) {
        if (userCommand.startsWith("todo ")) {
            return "todo";
        } else if (userCommand.startsWith("deadline ")) {
            return "deadline";
        } else if (userCommand.startsWith("event ")) {
            return "event";
        } else if (userCommand.startsWith("delete ")) {
            return "delete";
        } else if (userCommand.equalsIgnoreCase("list")) {
            return "list";
        } else if (userCommand.startsWith("mark ")) {
            return "mark";
        } else if (userCommand.startsWith("unmark ")) {
            return "unmark";
        } else {
            return "unknown";
        }
    }

    // Methods for adding, deleting, and managing tasks

    private static void addToDoTask(String description) throws AirBorderException {
        if (description.isEmpty()) {
            throw new AirBorderException("Task description cannot be empty.");
        }
        Task newTask = new ToDo(description);
        taskList.add(newTask);
        printTaskAddedMessage(newTask);
    }

    private static void addDeadlineTask(String userCommand) throws AirBorderException {
        String[] taskDetails = userCommand.substring(9).split(" /by ");
        if (taskDetails.length < 2 || taskDetails[0].trim().isEmpty() || taskDetails[1].trim().isEmpty()) {
            throw new AirBorderException("Invalid deadline format. Use: deadline <description> /by <time>");
        }
        Task newTask = new Deadline(taskDetails[0].trim(), taskDetails[1].trim());
        taskList.add(newTask);
        printTaskAddedMessage(newTask);
    }

    private static void addEventTask(String userCommand) throws AirBorderException {
        String[] taskDetails = userCommand.substring(6).split(" /from ");
        if (taskDetails.length < 2 || taskDetails[0].trim().isEmpty()) {
            throw new AirBorderException("Invalid event format. Use: event <description> /from <start> /to <end>");
        }
        String[] timeDetails = taskDetails[1].split(" /to ");
        if (timeDetails.length < 2 || timeDetails[0].trim().isEmpty() || timeDetails[1].trim().isEmpty()) {
            throw new AirBorderException("Invalid event time format. Use: event <description> /from <start> /to <end>");
        }
        Task newTask = new Event(taskDetails[0].trim(), timeDetails[0].trim(), timeDetails[1].trim());
        taskList.add(newTask);
        printTaskAddedMessage(newTask);
    }

    private static void deleteTask(String userCommand) throws AirBorderException {
        try {
            int taskIndex = Integer.parseInt(userCommand.split(" ")[1]) - 1;
            if (taskIndex < 0 || taskIndex >= taskList.size()) {
                throw new AirBorderException("Invalid task number.");
            }
            Task removedTask = taskList.remove(taskIndex);
            printTaskDeletedMessage(removedTask);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new AirBorderException("Please provide a valid task number.");
        }
    }

    private static void markTaskAsDone(String userCommand) throws AirBorderException {
        try {
            int taskIndex = Integer.parseInt(userCommand.split(" ")[1]) - 1;
            if (taskIndex < 0 || taskIndex >= taskList.size()) {
                throw new AirBorderException("Invalid task number.");
            }
            taskList.get(taskIndex).markAsDone();
            printTaskDoneMessage(taskList.get(taskIndex));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new AirBorderException("Please provide a valid task number.");
        }
    }

    private static void unmarkTask(String userCommand) throws AirBorderException {
        try {
            int taskIndex = Integer.parseInt(userCommand.split(" ")[1]) - 1;
            if (taskIndex < 0 || taskIndex >= taskList.size()) {
                throw new AirBorderException("Invalid task number.");
            }
            taskList.get(taskIndex).markAsNotDone();
            printTaskUndoneMessage(taskList.get(taskIndex));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new AirBorderException("Please provide a valid task number.");
        }
    }

    // Save tasks to the hardcoded file path
    private static void saveTasks() {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs(); // Ensure the directory exists
            FileWriter writer = new FileWriter(file);
            for (Task task : taskList) {
                writer.write(formatTaskForSave(task) + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    // Load tasks from the hardcoded file path
    private static void loadTasks() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return;  // File does not exist, no tasks to load
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTaskFromFile(line);
                if (task != null) {
                    taskList.add(task);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    // Helper method to format tasks for saving to file
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

    // Helper method to parse tasks from file
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

    // Printing methods

    private static void printWelcomeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(" Welcome aboard AirBorder.");
        System.out.println(" Ready to assist you with your tasks!");
        System.out.println("____________________________________________________________");
    }

    private static void printExitMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(" Thank you for flying with AirBorder!");
        System.out.println("____________________________________________________________");
    }

    private static void printTaskList() {
        System.out.println("____________________________________________________________");
        if (taskList.isEmpty()) {
            System.out.println(" No tasks in your list.");
        } else {
            System.out.println(" Here are your tasks:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + taskList.get(i));
            }
        }
        System.out.println("____________________________________________________________");
    }

    private static void printErrorMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(" ERROR: " + message);
        System.out.println("____________________________________________________________");
    }

    private static void printTaskAddedMessage(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println(" Task added: " + task);
        System.out.println(" Now you have " + taskList.size() + " tasks.");
        System.out.println("____________________________________________________________");
    }

    private static void printTaskDeletedMessage(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println(" Task deleted: " + task);
        System.out.println(" Now you have " + taskList.size() + " tasks.");
        System.out.println("____________________________________________________________");
    }

    private static void printTaskDoneMessage(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println(" Task completed: " + task);
        System.out.println("____________________________________________________________");
    }

    private static void printTaskUndoneMessage(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println(" Task marked as incomplete: " + task);
        System.out.println("____________________________________________________________");
    }
}
