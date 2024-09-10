public class CommandHandler {

    private static final String TODO_USAGE = "Usage: todo <task description>.";
    private static final String DEADLINE_USAGE = "Usage: deadline <task description> /by <due date>.";
    private static final String EVENT_USAGE = "Usage: event <task description> /from <start time> /to <end time>.";

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
        int taskIndex;
        try {
            taskIndex = inputValidator.validateIndex(inputs);
        } catch (InvalidInputException e) {
            MessageDisplay.displayInvalidInputMessage(e.getMessage());
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
        try {
            inputValidator.validateTodoInput(inputs);
            taskManager.checkSpace();
            taskManager.addTask(new Todo(inputs[1]));
        } catch (InvalidInputException e) {
            MessageDisplay.displayInvalidInputMessage(e.getMessage(), TODO_USAGE);
        } catch (InsufficientSpaceException e) {
            MessageDisplay.displayInvalidInputMessage(e.getMessage());
        }
    }

    private void handleDeadlineCase(String[] inputs) {
        String[] validatedInput;
        try {
            validatedInput = inputValidator.validateDeadlineInput(inputs);
            taskManager.checkSpace();
            taskManager.addTask(new Deadline(validatedInput[0], validatedInput[1]));
        } catch (InvalidInputException e) {
            MessageDisplay.displayInvalidInputMessage(e.getMessage(), DEADLINE_USAGE);
        } catch (InsufficientSpaceException e) {
            MessageDisplay.displayInvalidInputMessage(e.getMessage());
        }
    }

    private void handleEventCase(String[] inputs) {
        String[] validatedInput;
        try {
            validatedInput = inputValidator.validateEventInput(inputs);
            taskManager.checkSpace();
            taskManager.addTask(new Event(validatedInput[0], validatedInput[1], validatedInput[2]));
        } catch (InvalidInputException e) {
            MessageDisplay.displayInvalidInputMessage(e.getMessage(), EVENT_USAGE);
        } catch (InsufficientSpaceException e) {
            MessageDisplay.displayInvalidInputMessage(e.getMessage());
        }
    }
}
