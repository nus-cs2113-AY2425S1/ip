import java.util.ArrayList;

public class PlopBot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public PlopBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (PlopBotException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        parser = new Parser();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            try {
                String[] commandParts = parser.parseCommand(fullCommand);
                switch (commandParts[0]) {
                    case "bye":
                    case "exit":
                    case "quit":
                        isExit = true;
                        break;
                    case "list":
                        ui.showTasks(tasks.getTasks());
                        break;
                    case "todo":
                        Task newToDo = parser.parseTask(commandParts);
                        tasks.addTask(newToDo);
                        storage.save(tasks.getTasks());
                        ui.showTaskAdded(newToDo, tasks.size());
                        break;
                    case "deadline":
                        try {
                            Task newDeadline = parser.parseTask(commandParts);
                            tasks.addTask(newDeadline);
                            storage.save(tasks.getTasks());
                            ui.showTaskAdded(newDeadline, tasks.size());
                        } catch (PlopBotException e) {
                            ui.showError(e.getMessage() + "\n    Usage: deadline description /by DATE" +
                                                          "\n    DATE can be 'Sunday', 'Mon', 'Tuesday', or 'YYYY-MM-DD'");
                        }
                        break;
                    case "event":
                        try {
                            Task newEvent = parser.parseTask(commandParts);
                            tasks.addTask(newEvent);
                            storage.save(tasks.getTasks());
                            ui.showTaskAdded(newEvent, tasks.size());
                        } catch (PlopBotException e) {
                            ui.showError(e.getMessage() + "\n    Usage: event description /from START_TIME /to END_TIME" +
                                                          "\n    TIME can be 'Mon 2pm', 'Tuesday 14:00', or 'YYYY-MM-DD HH:MM'");
                        }
                        break;
                    case "delete":
                        int index = Integer.parseInt(commandParts[1]) - 1;
                        Task removedTask = tasks.removeTask(index);
                        storage.save(tasks.getTasks());
                        ui.showTaskRemoved(removedTask, tasks.size());
                        break;
                    case "find":
                        String keyword = commandParts[1];
                        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
                        ui.showMatchingTasks(matchingTasks);
                        break;
                    case "mark":
                        handleMarkTask(commandParts);
                        break;
                    case "unmark":
                        handleUnmarkTask(commandParts);
                        break;
                    default:
                        throw new PlopBotException("Unknown command");
                }
            } catch (PlopBotException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showExit();
    }

    public static void main(String[] args) {
        new PlopBot("data/tasks.txt").run();
    }

    private void handleMarkTask(String[] commandParts) throws PlopBotException {
        if (commandParts.length != 2) {
            throw new PlopBotException("Invalid mark command. Usage: mark <task number>");
        }
        try {
            int taskIndex = Integer.parseInt(commandParts[1]) - 1;
            Task task = tasks.getTask(taskIndex);
            task.markAsDone();
            storage.save(tasks.getTasks());
            ui.showTaskMarked(task);
        } catch (NumberFormatException e) {
            throw new PlopBotException("Invalid task number. Please provide a number.");
        }
    }

    private void handleUnmarkTask(String[] commandParts) throws PlopBotException {
        if (commandParts.length != 2) {
            throw new PlopBotException("Invalid unmark command. Usage: unmark <task number>");
        }
        try {
            int taskIndex = Integer.parseInt(commandParts[1]) - 1;
            Task task = tasks.getTask(taskIndex);
            task.markAsUndone();
            storage.save(tasks.getTasks());
            ui.showTaskUnmarked(task);
        } catch (NumberFormatException e) {
            throw new PlopBotException("Invalid task number. Please provide a number.");
        }
    }
}