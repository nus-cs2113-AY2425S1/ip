public class InputValidator {

    private TaskManager taskManager;

    public InputValidator(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    protected int validateIndex(String[] inputs) {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(inputs[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            MessageDisplay.displayInvalidIndexMessage();
            return -1;
        }

        if (taskIndex <= 0 || taskIndex > taskManager.getNumberOfTasks()) {
            MessageDisplay.displayOutOfBoundsIndexMessage(taskManager.getNumberOfTasks(), taskIndex);
            return -1;
        }

        return taskIndex;
    }

    protected String[] validateTodoInput(String[] inputs) {
        if (inputs.length != 2) {
            MessageDisplay.displayInvalidTodoInputMessage();
            return null;
        }
        return inputs;
    }

    protected String[] validateDeadlineInput(String[] inputs) {
        if (inputs.length != 2) {
            MessageDisplay.displayInvalidDeadlineInputMessage();
            return null;
        }
        String[] splitInput = inputs[1].split(" /by ");
        if (splitInput.length != 2) {
            MessageDisplay.displayInvalidDeadlineInputMessage();
            return null;
        }
        return splitInput;
    }

    protected String[] validateEventInput(String[] inputs) {
        if (inputs.length != 2) {
            MessageDisplay.displayInvalidEventInputMessage();
            return null;
        }
        String[] splitInput = inputs[1].split(" /from ");
        if (splitInput.length != 2) {
            MessageDisplay.displayInvalidEventInputMessage();
            return null;
        }
        String[] eventDetails = splitInput[1].split(" /to ");
        if (eventDetails.length != 2) {
            MessageDisplay.displayInvalidEventInputMessage();
            return null;
        }
        return new String[]{splitInput[0], eventDetails[0], eventDetails[1]};
    }
}
