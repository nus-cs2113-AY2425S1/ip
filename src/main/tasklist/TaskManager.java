package tasklist;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;

import storage.Storage;
import tasklist.types.Deadline;
import tasklist.types.Event;
import tasklist.types.Task;
import tasklist.types.ToDo;
import ui.UI;

public class TaskManager {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static final String TASK_TYPE_TODO = "T";
    public static final String TASK_TYPE_DEADLINE = "D";
    public static final String TASK_TYPE_EVENT = "E";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = 
        DateTimeFormatter.ofPattern("dd/MM/yyyy' 'HHmm");
    public static final DateTimeFormatter DATE_FORMATTER = 
        DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter TIME_FORMATTER = 
        DateTimeFormatter.ofPattern("HHmm");

    /**
     * Marks a task as completed based on the provided task number.
     *
     * @param taskNumber The number of the task to be marked as completed.
     */
    public static void markTask(String taskNumber) {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).setAsDone();
                Storage.updateTaskFile();
                UI.printResponse("Marked task as done: " + tasks.get(index));
            } else {
                UI.printResponse("Task number out of range :(\n" + 
                                 "Current number of tasks: " + tasks.size());
            }
            
        } catch (NumberFormatException e) {
            UI.printResponse("Invalid task number :(");
        }
    }

    /**
     * Unmarks a task as incomplete based on the provided task number.
     *
     * @param taskNumber The number of the task to be unmarked.
     */
    public static void unmarkTask(String taskNumber) {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).setAsUndone();
                Storage.updateTaskFile();
                UI.printResponse("Unmarked task: " + tasks.get(index));
            } else {
                UI.printResponse("Task number is out of range :(");
            }
        } catch (NumberFormatException e) {
            UI.printResponse("Invalid task number :(");
        }
    }

    /**
     * Adds a new task based on the command and argument provided.
     *
     * @param command The type of task to add (e.g., "todo", "deadline", "event").
     * @param words The details of the task.
     */
    public static void addTask(String command, String[] words) {
        Task task = null;
    
        try {
            switch (command.toLowerCase()) {
                case "todo":
                    task = createToDoTask(words);
                    break;
                case "deadline":
                    task = createDeadlineTask(words);
                    break;
                case "event":
                    task = createEventTask(words);
                    break;
                default:
                    throw new IllegalArgumentException(
                        "Oh no! Unknown command: " + command + 
                        " :(\nTry commands todo, deadline, or event to create tasks,\n" + 
                        "or mark, unmark, delete and find to manage your list :>");
            }
    
            if (task != null) {
                tasks.add(task);
                Storage.appendTaskToFile(task); // Append the task to the file
                UI.printResponse("Added: " + task + "\n" +
                                 "You now have " + tasks.size() + 
                                 (tasks.size() == 1 ? " task!" : " tasks!"));
            }
    
        } catch (IllegalArgumentException | IllegalStateException e) {
            UI.printResponse("Error: " + e.getMessage());
        }
    }
    
    /**
     * Creates a new ToDo task.
     *
     * @param words The input arguments for the task.
     * @return A new ToDo task.
     */
    private static Task createToDoTask(String[] words) {
        if (words.length > 1 && !words[1].trim().isEmpty()) {
            return new ToDo(words[1]);
        } else {
            throw new IllegalArgumentException(
                "Oh no! The description for 'todo' is missing. :(");
        }
    }
    
    /**
     * Creates a new Deadline task.
     *
     * @param words The input arguments for the task.
     * @return A new Deadline task.
     */
    private static Task createDeadlineTask(String[] words) {
        if (words.length > 1) {
            String[] deadlineParts = words[1].split("/by", 2);
            String deadlineDescription = deadlineParts[0].trim();
            String byPart = deadlineParts.length > 1 ? deadlineParts[1].trim() : null;

            if (byPart != null && !byPart.isEmpty()) {
                Temporal deadlineDate = tryParseDateTime(byPart);

                if (deadlineDate != null) {
                    return new Deadline(deadlineDescription, deadlineDate);
                } else {
                    return new Deadline(deadlineDescription, byPart); // Store as plain string if not a date
                }
            } else {
                throw new IllegalArgumentException(
                    "Oops! The deadline format is incorrect or missing :( \n" + 
                    "Use: deadline <description> /by <date>");
            }
        } else {
            throw new IllegalArgumentException("Oops! Missing description for 'deadline' :( ");
        }
    }
    
    /**
     * Creates a new Event task based on the provided input.
     *
     * @param words The details of the task, expected to include a description, /from, and /to parts.
     * @return A new Event task if the input is valid, otherwise throws an exception.
     */
    private static Task createEventTask(String[] words) {
        if (words.length < 2) {
            throw new IllegalArgumentException("Oops! Missing description for 'event' :( ");
        }

        // Split the input to find the /from and /to parts
        String[] eventParts = words[1].split("/from", 2);
        if (eventParts.length < 2) {
            throw new IllegalArgumentException("Oops! 'event' must include '/from' time :(");
        }

        String eventDescription = eventParts[0].trim();
        String from = eventParts[1].trim();

        // Split to find the /to part
        String to = ""; 
        String[] timeParts = from.split("/to", 2);
        from = timeParts[0].trim();
        if (timeParts.length > 1) {
            to = timeParts[1].trim();
        }

        // Attempt to parse dates from 'from' and 'to' strings, if provided
        Temporal fromDateTime = null;
        Temporal toDateTime = null;

        if (!from.isEmpty()) {
            try {
                fromDateTime = tryParseDateTime(from); 
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException(
                    "Invalid date format for '/from'.\n" + 
                    "Please use " + DATE_TIME_FORMATTER.toString());
            }
        }

        if (!to.isEmpty()) {
            try {
                toDateTime = tryParseDateTime(to);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException(
                    "Invalid date format for '/to' :(\n" + 
                    "Please use " + DATE_TIME_FORMATTER.toString());
            }
        }

        // Create and return the Event task
        return new Event(eventDescription, fromDateTime, toDateTime);
    }

    /**
     * Generic method to parse a date or date-time string.
     * If the input contains only a date, it returns LocalDate.
     * If the input contains date and time, it returns LocalDateTime.
     * If the input contains only time, it return LocalTime.
     * If the input is invalid or empty, it returns null.
     *
     * @param input The input string to be parsed.
     * @return Either a LocalDate, LocalDateTime, LocalTime, or null if input is invalid.
     */
    public static Temporal tryParseDateTime(String input) {
        try {
            return LocalDateTime.parse(input, DATE_TIME_FORMATTER);  // Try parsing as LocalDateTime
        } catch (DateTimeParseException e) {
            // Ignore exception and continue to next check
        }

        try {
            return LocalDate.parse(input, DATE_FORMATTER);  // Try parsing as LocalDate
        } catch (DateTimeParseException e) {
            // Ignore exception and continue to next check
        }

        try {
            return LocalTime.parse(input, TIME_FORMATTER);  // Try parsing as LocalTime
        } catch (DateTimeParseException e) {
            // If none succeed, return null
        }

        return null;
    }
  
    /**
     * Delete the task at specified index
     * @param index The index of task to delete.
     */
    public static void deleteTask(String index) {
        try {
            int taskIndex = Integer.parseInt(index) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new IndexOutOfBoundsException();
            }
            
            StringBuilder result = new StringBuilder();
            result.append("Removed: " + tasks.get(taskIndex).getDescription());
            tasks.remove(taskIndex);
            Storage.updateTaskFile(); // Rewrite the file after deletion
            result.append("\nOnly " + tasks.size() + " ")
                  .append(tasks.size() == 1 ? "thing" : "things")
                  .append(" left to do!");
            UI.printResponse(result.toString());
    
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            UI.printResponse("Error: Invalid task number! :(" +
                (tasks.size() == 1 ? "There is only 1 task on the list!" :
                    "There are only " + tasks.size() + "tasks on the list!"));
        }
    }

    /**
     * Prints the list of tasks to the console.
     * Displays each task with its corresponding number.
     */
    public static void printTaskList() {
        StringBuilder result = new StringBuilder();
        if (tasks.isEmpty()) {
            result.append("Your task list is empty, maybe add a task?");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                result.append((i + 1))
                      .append(". ")
                      .append(TaskManager.tasks.get(i));
                if (i < tasks.size() - 1) {
                    result.append("\n");
                }
            }
        }
        UI.printResponse(result.toString());;
    }

    /**
     * Searches for tasks that match the given search term and prints the results.
     *
     * @param arguments The search term used to find matching tasks.
     */
    public static void findTask(String arguments) {
        if (arguments == null || arguments.trim().isEmpty()) {
            UI.printResponse("Please provide a search term.");
            return;
        }

        StringBuilder result = new StringBuilder("Found these tasks! :>\n");
        String[] words = arguments.split(" ");
        boolean found = false;
        
        for (int i = 0; i < tasks.size(); i++) {
            for (String word : words) {
                word = word.trim();
                if (tasks.get(i).toString().toLowerCase().contains(word.toLowerCase())) {
                    result.append(found ? "\n" : "").append((i + 1) + ". ")
                            .append(tasks.get(i));  // Add matching task to the result
                    found = true;
                    break; // Found a match, exit loop to look for other matches
                }
            }
            
        }

        if (!found) {
            UI.printResponse("No tasks found matching: " + 
                String.join(" ", words).trim() + " :<");
            return;
        }
        
    
        UI.printResponse(result.toString());
    }
}
