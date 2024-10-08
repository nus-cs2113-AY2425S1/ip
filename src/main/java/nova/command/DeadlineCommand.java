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
 * This command allows the user to specify a task description and a due date for the deadline.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private static final String DEADLINE_USAGE = "Usage: deadline <task description> /by <due date>.";

    private static String description;
    private static LocalDate by;

    /**
     * Executes the Deadline command by validating the input and adding a new Deadline task to the task manager.
     *
     * @param inputs The input arguments provided by the user.
     * @param taskManager The {@code TaskList} instance managing the tasks.
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
     *
     * @param inputs The input arguments provided by the user.
     * @throws InvalidInputException If the input format is invalid.
     */
    private static void validateDeadlineInput(String[] inputs) throws InvalidInputException {
        checkForDescription(inputs);
        checkForBy(inputs);
        String[] splitInput = inputs[1].split(" /by ");
        checkForDeadline(splitInput);
        description = splitInput[0];
        checkForValidDate(splitInput);
    }

    /**
     * Validates the due date  is in the correct {@code YYYY-MM-DD} format.
     *
     * @param splitInput The input array split by "/by".
     * @throws InvalidInputException If the date format is invalid.
     */
    private static void checkForValidDate(String[] splitInput) {
        try {
            by = LocalDate.parse(splitInput[1]);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Invalid date. Use YYYY-MM-DD format.");
        }
    }

    /**
     * Checks if both description and due date are provided in the input.
     *
     * @param splitInput The input array split by "/by".
     * @throws InvalidInputException If either the description or due date is missing.
     */
    private static void checkForDeadline(String[] splitInput) {
        if (splitInput.length != 2) {
            throw new InvalidInputException("Description or deadline not entered.");
        }
    }

    /**
     * Checks if the "/by" keyword is present in the user input to indicate the due date.
     *
     * @param inputs The input arguments provided by the user.
     * @throws InvalidInputException If the "/by" keyword is missing.
     */
    private static void checkForBy(String[] inputs) {
        if (!inputs[1].contains("/by")) {
            throw new InvalidInputException("/by not entered.");
        }
    }

    /**
     * Checks if the task description is provided in the input.
     *
     * @param inputs The input arguments provided by the user.
     * @throws InvalidInputException If the description is missing.
     */
    private static void checkForDescription(String[] inputs) {
        if (inputs.length != 2) {
            throw new InvalidInputException("No description entered.");
        }
    }
}
