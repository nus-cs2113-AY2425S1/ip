package nova.command;

import nova.TaskList;
import nova.Ui;
import nova.exception.InsufficientSpaceException;
import nova.exception.InvalidInputException;
import nova.task.Deadline;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    private static final String DEADLINE_USAGE = "Usage: deadline <task description> /by <due date>.";

    private static String description;
    private static LocalDate by;

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

    protected static void validateDeadlineInput(String[] inputs) throws InvalidInputException {
        if (inputs.length != 2) {
            throw new InvalidInputException("No description entered.");
        }
        if (!inputs[1].contains("/by")) {
            throw new InvalidInputException("/by not entered.");
        }
        String[] splitInput = inputs[1].split(" /by ");
        if (splitInput.length != 2) {
            throw new InvalidInputException("description/deadline not entered.");
        }
        description = splitInput[0];
        try {
            by = LocalDate.parse(splitInput[1]);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }
}
