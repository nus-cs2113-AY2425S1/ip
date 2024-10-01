package wildpeace.task;

import wildpeace.exceptions.EmptyCommandException;
import wildpeace.exceptions.InvalidInputException;
import wildpeace.parser.Parser;
import wildpeace.Storage.Storage;
import wildpeace.ui.Ui;

public class TaskManager {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    public TaskManager() {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList(storage.load());
        parser = new Parser();
    }

    public void run() {
        ui.showGuide();
        boolean isExit = false;

        while (!isExit) {
            String fullCommand = ui.readCommand();
            try {
                String[] parsedInput = parser.parse(fullCommand);
                String command = parser.getCommand(parsedInput);
                String arguments = parser.getArguments(parsedInput);
                isExit = handleCommand(command, arguments);
            } catch (EmptyCommandException e) {
                ui.showError("The description cannot be empty: " + e.getMessage());
            }
        }

        storage.save(taskList.getTasks());
        ui.showLine("Goodbye!");
        ui.close();
    }

    private boolean handleCommand(String command, String arguments) {
        try {
            switch (command) {
            case "list":
                taskList.listTasks();
                break;
            case "todo":
                if (arguments.isEmpty()) {
                    throw new EmptyCommandException("The description of a todo cannot be empty.");
                }
                taskList.addTask(new Task(arguments, Task.TaskType.TODO));
                storage.save(taskList.getTasks());
                break;
            case "deadline":
                // Assuming '/by' separates task and deadline
                if (!arguments.contains("/by")) {
                    throw new InvalidInputException("Deadline tasks must include '/by <deadline>'.");
                }
                String[] deadlineParts = arguments.split("/by", 2);
                taskList.addTask(new Task(deadlineParts[0].trim(), deadlineParts[1].trim(), Task.TaskType.DEADLINE));
                storage.save(taskList.getTasks());
                break;
            case "event":
                // Assuming '/at' separates task and event time
                if (!arguments.contains("/at")) {
                    throw new InvalidInputException("Event tasks must include '/at <event time>'.");
                }
                String[] eventParts = arguments.split("/at", 2);
                taskList.addTask(new Task(eventParts[0].trim(), eventParts[1].trim(), Task.TaskType.EVENT));
                storage.save(taskList.getTasks());
                break;
            case "mark":
                taskList.markTask(Integer.parseInt(arguments) - 1);
                storage.save(taskList.getTasks());
                break;
            case "unmark":
                taskList.unmarkTask(Integer.parseInt(arguments) - 1);
                storage.save(taskList.getTasks());
                break;
            case "delete":
                taskList.deleteTask(Integer.parseInt(arguments) - 1);
                storage.save(taskList.getTasks());
                break;
            case "bye":
                return true;
            case "q":
                ui.showGuide();
                break;
            default:
                ui.showError("Unknown command.");
            }
        } catch (InvalidInputException e) {
            ui.showError("Invalid input: " + e.getMessage());
        } catch (EmptyCommandException e) {
            ui.showError("Empty command: " + e.getMessage());
        } catch (NumberFormatException e) {
            ui.showError("Please enter a valid number for the task index.");
        }
        return false;
    }
}
