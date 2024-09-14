package nova;

import nova.exception.InvalidInputException;
import nova.task.Task;

public class InputValidator {

    protected int validateIndex(String[] inputs) throws InvalidInputException {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(inputs[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidInputException("Invalid index. Please provide a valid task number after the command.");
        }

        if (taskIndex <= 0 || taskIndex > Task.getNumberOfTasks()) {
            throw new InvalidInputException("Invalid task index: " + taskIndex + ". " +
                    "Please provide a number between 1 and " + Task.getNumberOfTasks() + ".");
        }

        return taskIndex;
    }

    protected void validateTodoInput(String[] inputs) throws InvalidInputException {
        if (inputs.length != 2) {
            throw new InvalidInputException("No description entered.");
        }
    }

    protected String[] validateDeadlineInput(String[] inputs) throws InvalidInputException {
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

    protected String[] validateEventInput(String[] inputs) {
        if (inputs.length != 2) {
            throw new InvalidInputException("No description entered.");
        }
        if (!inputs[1].contains("/from")) {
            throw new InvalidInputException("/from not entered.");
        }
        if (!inputs[1].contains("/to")) {
            throw new InvalidInputException("/to not entered.");
        }
        String[] splitInput = inputs[1].split(" /from ");
        if (splitInput.length != 2) {
            throw new InvalidInputException("description/start/end not entered.");
        }
        String[] eventDetails = splitInput[1].split(" /to ");
        if (eventDetails.length != 2) {
            throw new InvalidInputException("description/start/end not entered.");
        }
        return new String[]{splitInput[0], eventDetails[0], eventDetails[1]};
    }
}
