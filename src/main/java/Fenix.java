import java.util.List;

/**
 * Fenix is the main controller class of the application.
 * It manages task handling, user commands, storage, and interaction with the user.
 * Fenix processes user inputs and delegates tasks to the appropriate handlers.
 */
public class Fenix implements SampleStrings {
    private TaskHandler taskHandler;
    private UserInterface ui;
    private Parser parser;
    private Storage storage;
    private boolean isExit;
    
    /**
     * Constructs a new instance of Fenix, initializing the necessary components for task management.
     * This includes setting up storage, user interface, parser, and task handler objects.
     */
    public Fenix() {
        storage = new Storage(this);
        ui = new UserInterface(this);
        parser = new Parser(this);
        taskHandler = new TaskHandler(this);
    }

    /**
     * Returns the total number of tasks currently in the list.
     *
     * @return the number of tasks in the task list.
     */
    public int getSize() {
        return taskHandler.getTaskArrayList().size();
    }

    /**
     * Retrieves the index of the specified task in the task list.
     *
     * @param task The task whose index is to be found.
     * @return the index of the task, or -1 if the task is not found.
     */
    public int indexOfTask(Task task) {
        return taskHandler.getTaskArrayList().indexOf(task);
    }

    /**
     * Processes the user input to determine and return the task index.
     * If the input is invalid or blank, appropriate messages are shown.
     *
     * @param taskNumber the user input for the task number.
     * @return the index of the task, or -1 if the input is invalid.
     */
    public int getTaskIndexFromInput(String taskNumber) {
        if (taskNumber.isBlank()) {
            ui.requestForTask();
            return -1;
        }
        if (!parser.isValidTaskNumber(taskNumber)) {
            ui.requestForValidTask();
            return -1;
        }
        return parser.parseInteger(taskNumber) - 1;
    }

    /**
     * Returns an unmodifiable view of the task list.
     *
     * @return a list of tasks that cannot be modified.
     */
    public List<Task> getTaskArrayList() {
        return taskHandler.getTaskArrayList();
    }

    /**
     * Handles the 'bye' command, which ends the user session.
     * It displays a farewell message and sets the exit flag to true.
     */
    public void handleBye() {
        ui.bidFarewell();
        isExit = true;
    }

    /**
     * Handles the 'list' command by displaying all tasks in the task list.
     */
    public void handleList() {
        ui.showAllTasks(false);
    }

    /**
     * Handles the 'mark' command, marking the specified task as done.
     *
     * @param commandInfo the task number provided by the user to mark as done.
     */
    public void handleMark(String commandInfo) {
        Task task = taskHandler.markAsDone(commandInfo);
        ui.showFenixModification(MARK, task);
    }

    /**
     * Handles the 'unmark' command, marking the specified task as not done.
     *
     * @param commandInfo the task number provided by the user to unmark as not done.
     */
    public void handleUnmark(String commandInfo) {
        Task task = taskHandler.unmarkAsDone(commandInfo);
        ui.showFenixModification(UNMARK, task);
    }

    /**
     * Handles the addition of tasks such as to-dos, deadlines, or events based on user input.
     * If the command type or task details are invalid, prompts are shown to request valid input.
     *
     * @param commandType the type of task (todo, deadline, event).
     * @param commandInfo the details of the task to be added.
     */
    public void handleAddTask(String commandType, String commandInfo) {
        if (commandType == null || commandType.isBlank()) {
            ui.requestForCommand();
            return;
        } else if (commandInfo == null || commandInfo.isBlank()) {
            ui.requestForTask();
            return;
        }
        Task task = parser.returnTaskObject(commandType, commandInfo);
        if (task == null) {
            return;
        }
        taskHandler.storeTask(task);
        ui.showFenixModification(ADD, task);
    }

    /**
     * Handles the 'delete' command, removing the specified task from the task list.
     *
     * @param commandInfo the task number provided by the user to be deleted.
     */
    public void handleDelete(String commandInfo) {
        Task task = taskHandler.deleteTask(commandInfo);
        ui.showFenixModification(DELETE, task);
    }

    /**
     * Handles invalid commands by prompting the user to enter a valid command.
     */
    public void handleInvalidCommand() {
        ui.requestForValidCommand();
    }

    /**
     * Adds tasks from storage during the loading phase. The task's type, status,
     * and details are used to recreate tasks from stored data.
     *
     * @param taskType the type of task (todo, deadline, event).
     * @param taskStatus the completion status of the task.
     * @param taskInfo the details of the task.
     */
    public void addTaskFromStorage(String taskType, String taskStatus, String taskInfo) {
        taskHandler.storeTask(parser.returnTaskObject(taskType, taskStatus, taskInfo));
    }

    /**
     * Runs the Fenix application by loading tasks from storage, greeting the user,
     * and continuously processing user input until the exit command is issued.
     * On exit, all tasks are saved back to storage.
     */
    public void run() {
        storage.loadAllInfo();
        ui.greet();
        while (!isExit) {
            String userInput = ui.getUserInput();
            parser.processUserInput(userInput);
        }
        storage.writeAllTasks();
    }
}
