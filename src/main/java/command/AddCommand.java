package command;

import model.Deadline;
import model.Event;
import model.Task;
import model.Todo;
import exception.MondayException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private String input;

    /**
     * Constructs an AddCommand with the specified input.
     *
     * @param input the input string containing the task details
     */
    public AddCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command to add a task to the task list.
     *
     * @param tasks   the task list to which the task will be added
     * @param ui     the UI component for user interaction
     * @param storage the storage component for saving tasks
     * @throws MondayException if the input format is invalid or if required fields are empty
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MondayException {
        Task task = null;

        if (input.startsWith("todo ")) {
            String description = input.substring(5).trim();
            if (description.isEmpty()) {
                throw new MondayException("    The description of a todo cannot be empty.");
            }
            task = new Todo(description, false);

        } else if (input.startsWith("deadline ")) {
            String[] parts = input.split(" /by ");
            if (parts.length != 2) {
                throw new MondayException("    Invalid deadline format. Please use: deadline <description> /by <time>");
            }
            String description = parts[0].substring(9).trim();
            String by = parts[1].trim();
            if (description.isEmpty() || by.isEmpty()) {
                throw new MondayException("    The description or deadline time cannot be empty.");
            }

            // Parse the date-time format dd/MM/yyyy HHmm
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            try {
                LocalDateTime deadlineDate = LocalDateTime.parse(by, formatter);
                task = new Deadline(description, false, deadlineDate);
            } catch (DateTimeParseException e) {
                throw new MondayException("    Invalid date format. Please use: dd/MM/yyyy HHmm (e.g., 2/12/2019 1800)");
            }

        } else if (input.startsWith("event ")) {
            if (input.contains(" /from ") && input.contains(" /to ")) {
                String[] parts = input.substring(6).split(" /from | /to ");
                String description = parts[0].trim();
                String from = parts[1].trim();
                String to = parts[2].trim();
                if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                    throw new MondayException("    The description or event time cannot be empty.");
                }
                task = new Event(description, false, from, to);
            } else {
                throw new MondayException("    Invalid event format. Please use: event <description> /from <start time> /to <end time>");
            }
        } else {
            throw new MondayException("    Sorry, I don't understand the command. "
                    + "Please use keywords like: 'todo', 'deadline', or 'event'. "
                    + "For example, 'todo <description>' or 'deadline <description> /by <time>'.");
        }

        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks.getTasks());
    }
}
