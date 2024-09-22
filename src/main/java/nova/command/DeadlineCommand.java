package nova.command;

import nova.TaskList;
import nova.Ui;
import nova.exception.InsufficientSpaceException;
import nova.exception.InvalidInputException;
import nova.task.Deadline;

public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    private static final String DEADLINE_USAGE = "Usage: deadline <task description> /by <due date>.";

    public static void execute(String[] inputs, TaskList taskManager) {
        String[] validatedInput;
        try {
            validatedInput = validateDeadlineInput(inputs);
            taskManager.checkSpace();
            taskManager.addTask(new Deadline(validatedInput[0], validatedInput[1]));
        } catch (InvalidInputException e) {
            Ui.displayInvalidInputMessage(e.getMessage(), DEADLINE_USAGE);
        } catch (InsufficientSpaceException e) {
            Ui.displayInvalidInputMessage(e.getMessage());
        }
    }

    protected static String[] validateDeadlineInput(String[] inputs) throws InvalidInputException {
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
        return splitInput;
    }
}
