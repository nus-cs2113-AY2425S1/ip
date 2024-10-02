import java.util.List;

public class Fenix implements SampleStrings {
    private TaskHandler taskHandler;
    private UserInterface ui;
    private Parser parser;
    private Storage storage;
    private boolean isExit;

    // Constructor
    public Fenix() {
        storage = new Storage(this);
        ui = new UserInterface(this);
        parser = new Parser(this);
        taskHandler = new TaskHandler(this);
    }

    public int getSize() {
        return taskHandler.getTaskArrayList().size();
    }

    public int indexOfTask(Task task) {
        return taskHandler.getTaskArrayList().indexOf(task);
    }

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

    // Return an unmodifiable view of the taskArrayList
    public List<Task> getTaskArrayList() {
        return taskHandler.getTaskArrayList();
    }

    // Method to handle 'bye' command
    public void handleBye() {
        ui.bidFarewell();
        isExit = true;
    }

    // Method to handle 'list' command
    public void handleList() {
        ui.showAllTasks(false);
    }

    // Method to handle 'mark' command
    public void handleMark(String commandInfo) {
        Task task = taskHandler.markAsDone(commandInfo);
        ui.showFenixModification(MARK, task);
    }

    // Method to handle 'unmark' command
    public void handleUnmark(String commandInfo) {
        Task task = taskHandler.unmarkAsDone(commandInfo);
        ui.showFenixModification(UNMARK, task);
    }

    // Method to handle adding tasks (todo, deadline, event)
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

    // Method to handle 'delete' command
    public void handleDelete(String commandInfo) {
        Task task = taskHandler.deleteTask(commandInfo);
        ui.showFenixModification(DELETE, task);
    }

    // Fallback for invalid commands
    public void handleInvalidCommand() {
        ui.requestForValidCommand();
    }

    public void addTaskFromStorage(String taskType, String taskStatus, String taskInfo) {
        taskHandler.storeTask(parser.returnTaskObject(taskType, taskStatus, taskInfo));
    }

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