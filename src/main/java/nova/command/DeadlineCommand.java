package nova.command;

import nova.TaskList;
import nova.Ui;
import nova.exception.InsufficientSpaceException;
import nova.exception.InvalidInputException;
import nova.task.Deadline;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to create a Deadline task.
 * This command allows the user to specify a task description and a due date.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private static final String DEADLINE_USAGE = "Usage: deadline <task description> /by <due date>.";

    private static String description;
    private static LocalDate by;

    /**
     * Executes the Deadline command by validating input, checking space,
     * and adding a new Deadline task to the task manager.
     *
     * @param inputs The input arguments provided by the user.
     * @param taskManager The TaskList instance managing tasks.
     */
    public static void execute(String[] inputs, TaskList taskManager) {
        try {
            validateDeadlineInput(inputs);
            taskManager.checkSpace();
            taskManager.addTask(new Deadline(description, by));
        } catch (InvalidInputException e) {
            Ui.displayInvalidInputMessage(e.getMessage(), DEADLINE_USAGE);
        } catch (InsufficientSpaceException e) {
            Ui.displayInvalidInputMessage(e.getMessage());
        }
    }

    /**
     * Validates the input for creating a Deadline task.
     * Ensures that the input has the correct format and contains necessary components.
     *
     * @param inputs The input arguments provided by the user.
     * @throws InvalidInputException If the input format is invalid.
     */
    private static void validateDeadlineInput(String[] inputs) throws InvalidInputException {
        if (inputs.length != 2) {
            throw new InvalidInputException("No description entered.");
        }
        if (!inputs[1].contains("/by")) {
            throw new InvalidInputException("/by not entered.");
        }
        String[] splitInput = inputs[1].split(" /by ");
        if (splitInput.length != 2) {
            throw new InvalidInputException("Description/deadline not entered.");
        }
        description = splitInput[0];
        try {
            by = LocalDate.parse(splitInput[1]);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Invalid date. Use YYYY-MM-DD format.");
        }
    }
}
