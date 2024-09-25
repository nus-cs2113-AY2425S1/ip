package tasklist;

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
    
        } catch (IllegalArgumentException e) {
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
            if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty()) {
                throw new IllegalArgumentException(
                    "Oops! The deadline format is incorrect or missing :(\n" +
                    "Use: deadline <description> /by <date>");
            }
            String deadlineDescription = deadlineParts[0].trim();
            String by = deadlineParts[1].trim();
            return new Deadline(deadlineDescription, by);
        } else {
            throw new IllegalArgumentException(
                "Oops! Missing description for 'deadline' :( ");
        }
    }
    
    /**
     * Creates a new Event task.
     *
     * @param words The input arguments for the task.
     * @return A new Event task.
     */
    private static Task createEventTask(String[] words) {
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
                throw new IllegalArgumentException(
                    "Oops! The event format is incorrect or missing :( \n" + 
                    "Use: event <description> /from <start> /to <end>");
            }
    
            return new Event(eventDescription, from, to);
        } else {
            throw new IllegalArgumentException(
                "Oops! Missing description for 'event' :( ");
        }
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
}
