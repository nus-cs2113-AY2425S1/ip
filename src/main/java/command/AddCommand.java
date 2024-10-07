package command;

import exception.InvalidCommandException;
import exception.UserInputException;
import tasktypes.Deadline;
import tasktypes.Event;
import tasktypes.Task;
import tasktypes.Todo;
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
    private static final int TODO_PREFIX_LENGTH = 5;
    private static final int DEADLINE_PREFIX_LENGTH = 9;
    private static final int EVENT_PREFIX_LENGTH = 6;
    private static final int DEADLINE_PARTS_LENGTH = 2;
    private static final int EVENT_PARTS_LENGTH = 3;

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
     * @param ui      the UI component for user interaction
     * @param storage the storage component for saving tasks
     * @throws UserInputException if the input format is invalid or if required fields are empty
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException, UserInputException {
        Task task = null;

        if (input.startsWith("todo ")) {
            String description = input.substring(TODO_PREFIX_LENGTH).trim();
            if (description.isEmpty()) {
                throw new UserInputException(" The description of a todo cannot be empty.");
            }
            task = new Todo(description, false);

        } else if (input.startsWith("deadline ")) {
            String[] parts = input.split(" /by ");
            if (parts.length != DEADLINE_PARTS_LENGTH) {
                throw new UserInputException(" Invalid deadline format. Please use: deadline <description> /by <time>");
            }
            String description = parts[0].substring(DEADLINE_PREFIX_LENGTH).trim();
            String by = parts[1].trim();
            if (description.isEmpty() || by.isEmpty()) {
                throw new UserInputException(" The description or deadline time cannot be empty.");
            }

            // Parse the date-time format dd/MM/yyyy HHmm
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            try {
                LocalDateTime deadlineDate = LocalDateTime.parse(by, formatter);
                task = new Deadline(description, false, deadlineDate);
            } catch (DateTimeParseException e) {
                throw new UserInputException(" Invalid date format. Please use: dd/MM/yyyy HHmm (e.g., 2/12/2019 1800)");
            }

        } else if (input.startsWith("event ")) {
            if (input.contains(" /from ") && input.contains(" /to ")) {
                String[] parts = input.substring(EVENT_PREFIX_LENGTH).split(" /from | /to ");
                if (parts.length != EVENT_PARTS_LENGTH) {
                    throw new UserInputException(" Invalid event format. Please use: event <description> /from <start time> /to <end time>");
                }
                String description = parts[0].trim();
                String from = parts[1].trim();
                String to = parts[2].trim();
                if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                    throw new UserInputException(" The description or event time cannot be empty.");
                }
                task = new Event(description, false, from, to);
            } else {
                throw new UserInputException(" Invalid event format. Please use: event <description> /from <start time> /to <end time>");
            }
        } else {
            throw new InvalidCommandException(" Sorry, I don't understand the command. "
                    + "Please use keywords like: 'todo', 'deadline', or 'event'. "
                    + "For example, 'todo <description>' or 'deadline <description> /by <time>'.");
        }

        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks.getTasks());
    }
}
