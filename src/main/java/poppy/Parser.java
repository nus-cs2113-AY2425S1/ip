package poppy;

import tasks.Task;
import tasks.ToDo;
import tasks.Events;
import tasks.Deadline;
import exceptions.CustomExceptions;
import java.util.ArrayList;

import static commands.Commands.*;

/**
 * The {@code Parser} class is responsible for interpreting user input and
 * processing commands related to task management.
 */
public class Parser {

    /**
     * Processes input from a file and converts it into tasks to be added to the task list.
     *
     * @param input The input string containing the task information from the file.
     * @param taskList The list of tasks where the processed task will be added.
     */
    public static void processFile(String input, ArrayList<Task> taskList) {
        String[] commandArgs = input.split(" ", 3);
        if (commandArgs.length < 3) return; // Ensure there are enough arguments

        String command = commandArgs[0];
        String status = commandArgs[1];
        String description = commandArgs[2];

        switch (command) {
            case "T":
                ToDo task = new ToDo(description);
                taskList.add(task);
                break;
            case "D":
                String[] deadlineParts = description.split("\\|", 2);
                Deadline deadline = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
                taskList.add(deadline);
                break;
            case "E":
                String[] eventParts = description.split("\\| ", 2);
                Events event = new Events(eventParts[0].trim(), eventParts[1].trim());
                taskList.add(event);
                break;
            default:
                return;
        }

        if (status.equals("1")) {
            taskList.get(taskList.size() - 1).markAsDone();
        } else if (status.equals("0")) {
            taskList.get(taskList.size() - 1).markAsNotDone();
        }
    }

    /**
     * Processes a user command and performs the corresponding task management action.
     *
     * @param input The input string containing the command.
     * @param taskList The {@code TaskList} object that holds the current tasks.
     */
    public static void processCommand(String input, TaskList taskList) {
        String[] commandArgs = input.split(" ", 2);
        String command = commandArgs[0];
        try {
        switch (command) {
        case "delete":
            taskList.deleteTask(Integer.parseInt(commandArgs[1]) - 1);  // Adjust for 0-based index
            System.out.println("Task deleted.");
            break;
        case "mark":
            markAsDone(taskList.getTasks(), commandArgs);
            break;
        case "unmark":
            markAsNotDone(taskList.getTasks(), commandArgs);
            break;
        case "List":
            taskList.showTasks();
            break;
        case "todo":
            if (commandArgs.length < 2) {
                throw new CustomExceptions.MissingArgsException("Description of ToDo cannot be empty");
            }
            ToDo todo = new ToDo(commandArgs[1]);
            taskList.addTask(todo);
            System.out.println(todo.toString());
            System.out.println("You now have " + taskList.getSize() + " tasks");
            break;
        case "deadline":
            if (commandArgs.length < 2) {
                throw new CustomExceptions.MissingArgsException("Description of Deadline cannot be empty");
            }
            String[] deadlinestring = commandArgs[1].split("/by", 2);
            Deadline deadline = new Deadline(deadlinestring[0].trim(), deadlinestring[1].trim());
            taskList.addTask(deadline);
            System.out.println(deadline.toString());
            System.out.println("You now have " + taskList.getSize() + " tasks");
            break;
        case "event":
            if (commandArgs.length < 2) {
                throw new CustomExceptions.MissingArgsException("Description of Event cannot be empty");
            }
            String[] eventstring = commandArgs[1].split("/from", 2);
            Events event = new Events(eventstring[0].trim(), eventstring[1].trim());
            taskList.addTask(event);
            System.out.println(event.toString());
            System.out.println("You now have " + taskList.getSize() + " tasks");
            break;
        case "Find":
            System.out.println("Here are the matching results below:");
            findTask(taskList.getTasks(), commandArgs[1]);
        case "Bye":
            break;
        default:
            throw new IllegalArgumentException("Invalid command");
        }
        } catch (IllegalArgumentException e) {
            System.out.println("Wait, I don't understand what you are saying??");
        } catch (CustomExceptions.MissingArgsException | CustomExceptions.InvalidTaskIndexException e) {
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You are missing a keyword... read manual please!");
        }
    }

    /**
     * Generates a string representation of a task for file storage.
     *
     * @param task The task to be converted into a string.
     * @return A string representing the task in a format that can be written to a file.
     */
    public static String getString(Task task) {
        int statusNumber = (task.getStatusIcon().equals(" ")) ? 0 : 1;
        String type = "";
        if (task instanceof Deadline) {
            type = "D";
        } else if (task instanceof Events) {
            type = "E";
        } else if (task instanceof ToDo) {
            type = "T";
        }
        return type + " " + statusNumber + " " + task.description + " ";
    }
}

