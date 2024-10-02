public class Parser implements SampleStrings{
    UserInterface ui;
    TaskHandler taskHandler;

    public Parser(UserInterface ui, TaskHandler taskHandler) {
        this.ui = ui;
        this.taskHandler = taskHandler;
    }

    public void processUserInput(String userInput) {
        Task task;
        String[] words = userInput.trim().split(" ", 2);
        String commandType = words[0];
        String commandInfo = ((words.length > 1) ? words[1] : "");
        switch (commandType) {
        case "bye":
            ui.bidFarewell();
            return;
        case "list":
            ui.showAllTasks(false);
            break;
        case "mark":
            task = taskHandler.markAsDone(commandInfo);
            ui.showFenixModification(MARK, task);
            break;
        case "unmark":
            task = taskHandler.unmarkAsDone(commandInfo);
            ui.showFenixModification(UNMARK, task);
            break;
        case "todo":
        case "deadline":
        case "event":
            task = processTasks(commandType, commandInfo);
            ui.showFenixModification(ADD, task);
            break;
        case "delete":
            task = taskHandler.deleteTask(commandInfo);
            ui.showFenixModification(DELETE, task);
            break;
        default:
            ui.requestForValidCommand();
        }
        ui.acceptUserInput();
    }

    public Task processTasks(String commandType, String commandInfo) {
        if (commandType == null || commandType.isBlank()) {
            ui.requestForCommand();
            return null;
        } else if (commandInfo == null || commandInfo.isBlank()) {
            ui.requestForTask();
            return null;
        }
        Task task = returnTaskObject(commandType, commandInfo);
        if (task == null) {
            return null;
        }
        taskHandler.storeTask(task);
        return task;
    }

    private Task returnTaskObject(String type, String information) {
        switch (type) {
        case "todo":
            return new Todo(information);
        case "deadline":
            try {
                return new Deadline(information);
            } catch (IllegalArgumentException | FenixException e) {
                System.out.println(e.getMessage());
                return null;
            }
        case "event":
            try {
                return new Event(information);
            } catch (IllegalArgumentException | FenixException e) {
                System.out.println(e.getMessage());
                return null;
            }
        default:
            return null;
        }
    }

    public Task returnTaskObject(String taskType, String taskStatus, String taskInfo) {
        boolean isDone = (taskStatus.equals("X"));
        return switch (taskType) {
            case "T" -> new Todo(isDone, taskInfo);
            case "D" -> new Deadline(isDone, taskInfo);
            case "E" -> new Event(isDone, taskInfo);
            default -> null;
        };
    }
}
