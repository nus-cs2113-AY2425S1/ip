package parser;

import tasklist.TaskManager;
import tasklist.types.Deadline;
import tasklist.types.Event;
import tasklist.types.Task;
import tasklist.types.ToDo;
import ui.UI;

public class Parser {
    /**
     * Parses user input and executes the corresponding command.
     *
     * @param userInput The input provided by the user.
     * @return True if the bot should continue running, false if it should exit.
     */
    public static boolean parseInput(String userInput) {
        String[] words = userInput.split(" ", 2);
        String command = words[0].replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String argument = words.length > 1 ? words[1] : "";

        switch (command) {
            case "bye":
            UI.sayBye();
            return false;
        case "delete":
            TaskManager.deleteTask(argument);
            break;
        case "find":
            TaskManager.findTask(argument);
            break;
        case "list":
            TaskManager.printTaskList();
            break;
        case "mark":
            TaskManager.markTask(argument);
            break;
        case "unmark":
            TaskManager.unmarkTask(argument);
            break;
        default:
            TaskManager.addTask(command, words);
            break;
        }
        return true;
    }

    /**
     * Parses a line from the file and creates the appropriate Task object.
     * Throws FormatException if the line is not in the correct format.
     * @param line The line to read from the file on disk.
     * @return A Task instance if succesfully parsed.
     */
    public static Task parseTaskFromLine(String line) throws FormatException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new FormatException("Invalid format");
        }

        String taskType = parts[0];
        boolean isDone = Boolean.parseBoolean(parts[1]);
        String description = parts[2];

        Task task;
        switch (taskType) {
        case TaskManager.TASK_TYPE_TODO:
            task = new ToDo(description);
            break;
        case TaskManager.TASK_TYPE_DEADLINE:
            if (parts.length < 4) {
                throw new FormatException("Deadline task missing date");
            }
            task = new Deadline(description, parts[3]);
            break;
        case TaskManager.TASK_TYPE_EVENT:
            if (parts.length < 5) {
                throw new FormatException("Event task missing time");
            }
            task = new Event(description, parts[3], parts[4]);
            break;
        default:
            throw new FormatException("Unknown task type");
        }

        if (isDone) {
            task.setAsDone();
        }
        return task;
    }
}
