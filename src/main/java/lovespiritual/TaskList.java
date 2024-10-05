package lovespiritual;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lovespiritual.exception.lovespiritualException;
import lovespiritual.task.Deadline;
import lovespiritual.task.Event;
import lovespiritual.task.Task;
import lovespiritual.task.Todo;
import java.util.ArrayList;

/**
 * Handles the list of tasks and provides methods for modifying and retrieving tasks.
 */
public class TaskList {
    public static final String SEPARATOR = "_".repeat(30);
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Deletes a task from the list based on the task number provided in the input.
     *
     * @param input User input of the task number to delete.
     * @param tasks List of tasks.
     * @throws lovespiritualException If the task number is invalid or out of range.
     */
    public static void deleteTask(String input, ArrayList<Task> tasks) throws lovespiritualException {
        String taskNumber = input.substring("delete".length()).trim();
        if (taskNumber.isEmpty()) {
            throw new lovespiritualException("Hmm... ¯\\_(ツ)_/¯ A valid number, please?");
        }
        int indexNumber;
        try {
            indexNumber = Integer.parseInt(taskNumber) - 1;
        } catch (NumberFormatException e) {
            throw new lovespiritualException("Whoa there! (0.0) That’s not a number! Can you double-check?");
        }
        Task removedTask = tasks.get(indexNumber);
        if (indexNumber >= 0 && indexNumber < tasks.size()) {
            tasks.remove(indexNumber);
            System.out.println(SEPARATOR);
            System.out.println("Got it! :D This task is removed!");
            System.out.println(removedTask);
            System.out.println(SEPARATOR);
        } else {
            throw new lovespiritualException("Hmm... ¯\\_(ツ)_/¯ That number seems a bit off. Try again?");
        }
    }

    /**
     * Find tasks from the list based on the word provided in the input.
     * All tasks that has the word provided in it would be printed out.
     *
     * @param input User input of word to find.
     * @throws lovespiritualException If the input is missing the word to find.
     */
    public static void find(String input, ArrayList<Task> tasks) throws lovespiritualException {
        String findTask = input.substring("find".length()).trim();
        if (findTask.isEmpty()) {
            throw new lovespiritualException("Oops! (0.0) What should I find? Please give me a keyword.");
        }
        System.out.println(SEPARATOR);
        int matchCount = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.toString().toLowerCase().contains(findTask.toLowerCase())) {
                if (matchCount == 0) {
                    System.out.println("Here are the matching tasks in your list:");
                }
                matchCount++;
                System.out.println((i + 1) + "." + task);
            }
        }
        if (matchCount == 0) {
            System.out.println("No tasks found with the keyword: " + findTask);
        }
        System.out.println(SEPARATOR);
    }

    /**
     * Extracts a task from the file based on the line format.
     *
     * @param line String of data of the task.
     * @return Object corresponding to the data.
     */
    public static Task extractTasks(String line) {
        try {
            String[] parts = line.split(" \\| ");

            if (parts.length < 3) {
                throw new lovespiritualException("Error reading task from file: Incomplete task data.");
            }

            String type = parts[0];
            boolean isMarked = parts[1].equals("1");
            String description = parts[2];
            Task task;

            switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                if (parts.length < 4) {
                    throw new lovespiritualException("Error reading Deadline from file: Missing 'by' date.");
                }
                LocalDateTime by = LocalDateTime.parse(parts[3]);
                task = new Deadline(description, by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
                break;
            case "E":
                if (parts.length < 5) {
                    throw new lovespiritualException("Error reading Event from file: Missing 'from' or 'to' time.");
                }
                LocalDateTime from = LocalDateTime.parse(parts[3]);
                LocalDateTime to = LocalDateTime.parse(parts[4]);
                task = new Event(description, from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")), to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
                break;
            default:
                throw new lovespiritualException("Error reading task from file: Unknown task type.");
            }

            if (isMarked) {
                task.mark();
            }

            return task;
        } catch (lovespiritualException e) {
            System.out.println(SEPARATOR);
            System.out.println(e.getMessage());
            System.out.println(SEPARATOR);
            return null;
        } catch (Exception e) {
            System.out.println(SEPARATOR);
            System.out.println("Error parsing task from file: " + e.getMessage());
            System.out.println(SEPARATOR);
            return null;
        }
    }

    /**
     * Returns a formatted string suitable for saving a task to a file.
     *
     * @param task Task to be formatted.
     * @return Formatted string of the task.
     */
    public static String savedFormat(Task task) {
        String taskType = "";
        String formattedTask = "";

        if (task instanceof Todo) {
            taskType = "T";
            formattedTask = taskType + " | " + (task.isMarked ? "1" : "0") + " | " + task.description;
        } else if (task instanceof Deadline) {
            taskType = "D";
            Deadline deadline = (Deadline) task;
            formattedTask = taskType + " | " + (task.isMarked ? "1" : "0") + " | " + task.description + " | " + deadline.by;
        } else if (task instanceof Event) {
            taskType = "E";
            Event event = (Event) task;
            formattedTask = taskType + " | " + (task.isMarked ? "1" : "0") + " | " + task.description + " | " + event.from + " | " + event.to;
        }
        return formattedTask;
    }
}
