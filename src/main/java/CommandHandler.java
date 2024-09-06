public class CommandHandler {

    private TaskManager taskManager;
    private InputValidator inputValidator;

    public CommandHandler(TaskManager taskManager, InputValidator inputValidator) {
        this.taskManager = taskManager;
        this.inputValidator = inputValidator;
    }

    public boolean handleInput(String[] inputs) {
        switch (inputs[0].toLowerCase()) {
        case "bye":
            return handleByeCase();

        case "list":
            taskManager.listTasks();
            break;

        case "mark":
        case "unmark":
            handleMarkAndUnmarkCase(inputs);
            break;

        case "todo":
            handleTodoCase(inputs);
            break;

        case "deadline":
            handleDeadlineCase(inputs);
            break;

        case "event":
            handleEventCase(inputs);
            break;

        default:
            MessageDisplay.displayInvalidInputMessage();
            break;
        }
        return false;
    }

    private boolean handleByeCase() {
        MessageDisplay.displayByeMessage();
        return true;
    }

    private void handleMarkAndUnmarkCase(String[] inputs) {
        int taskIndex = inputValidator.validateIndex(inputs);
        if (taskIndex == -1) {
            return;
        }

        if (inputs[0].equals("mark")) {
            taskManager.markTask(taskIndex - 1);
            MessageDisplay.displayMarkMessage(taskManager.getTask(taskIndex - 1));
        } else {
            taskManager.unmarkTask(taskIndex - 1);
            MessageDisplay.displayUnmarkMessage(taskManager.getTask(taskIndex - 1));
        }
    }

    private void handleTodoCase(String[] inputs) {
        String[] validatedInput = inputValidator.validateTodoInput(inputs);
        if (validatedInput == null) {
            return;
        }
        taskManager.addTask(new Todo(inputs[1]));
    }
    
    private void handleDeadlineCase(String[] inputs) {
        String[] validatedInput = inputValidator.validateDeadlineInput(inputs);
        if (validatedInput == null) {
            return;
        }
        taskManager.addTask(new Deadline(validatedInput[0], validatedInput[1]));
    }

    private void handleEventCase(String[] inputs) {
        String[] validatedInput = inputValidator.validateEventInput(inputs);
        if (validatedInput == null) {
            return;
        }
        taskManager.addTask(new Event(validatedInput[0], validatedInput[1], validatedInput[2]));
    }
}
