package Yukee;

import Yukee.storage.Storage;
import Yukee.task.Event;
import Yukee.task.Todo;
import Yukee.task.Task;
import Yukee.task.Deadline;
import Yukee.task.TaskList;
import Yukee.parser.Parser;
import Yukee.exception.YukeeException;

public class Yukee {
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    public Yukee(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        TaskList loadedTasks;

        try {
            loadedTasks = new TaskList(storage.load());
        } catch (YukeeException e) {
            ui.showLoadingError();
            loadedTasks = new TaskList();
        }

        taskList = loadedTasks;
    }

    public void run() {
        ui.showWelcome();
        ui.showLine();
        boolean isExit = false;

        while (!isExit) {
            String input = ui.readCommand();
            try {
                String[] parsedCommand = Parser.parse(input);
                String command = parsedCommand[0].toLowerCase();

                switch (command) {
                    case "bye":
                        ui.showGoodbye();
                        isExit = true;
                        break;

                    case "list":
                        taskList.printTasks();
                        break;

                    case "todo":
                        if (parsedCommand.length < 2) {
                            throw new YukeeException("The description of a todo cannot be empty.");
                        }
                        taskList.addTask(new Todo(parsedCommand[1]));  // 修改这里，直接使用 Todo 类
                        ui.showAddTask(taskList.getLastTask(), taskList.size());
                        storage.save(taskList.getTasks());
                        break;

                    case "deadline":
                        if (parsedCommand.length < 2 || !parsedCommand[1].contains("/by")) {
                            throw new YukeeException("The description of a deadline must contain '/by'.");
                        }
                        String[] deadlineParts = parsedCommand[1].split(" /by ");
                        taskList.addTask(new Deadline(deadlineParts[0], deadlineParts[1]));
                        ui.showAddTask(taskList.getLastTask(), taskList.size());
                        storage.save(taskList.getTasks());
                        break;

                    case "event":
                        if (parsedCommand.length < 2 || !parsedCommand[1].contains("/from") || !parsedCommand[1].contains("/to")) {
                            throw new YukeeException("The description of an event must contain '/from' and '/to'.");
                        }
                        String[] eventParts = parsedCommand[1].split(" /from | /to ");
                        taskList.addTask(new Event(eventParts[0], eventParts[1], eventParts[2])); // 修改这里，直接使用 Event 类
                        ui.showAddTask(taskList.getLastTask(), taskList.size());
                        storage.save(taskList.getTasks());
                        break;

                    case "mark":
                        if (parsedCommand.length < 2) {
                            throw new YukeeException("Please provide the task number to mark as done.");
                        }
                        int markIndex = Integer.parseInt(parsedCommand[1]) - 1;
                        taskList.markTaskAsDone(markIndex);
                        ui.showMarkTask(taskList.getTask(markIndex));
                        storage.save(taskList.getTasks());
                        break;

                    case "unmark":
                        if (parsedCommand.length < 2) {
                            throw new YukeeException("Please provide the task number to unmark.");
                        }
                        int unmarkIndex = Integer.parseInt(parsedCommand[1]) - 1;
                        taskList.markTaskAsNotDone(unmarkIndex);
                        ui.showUnmarkTask(taskList.getTask(unmarkIndex));
                        storage.save(taskList.getTasks());
                        break;

                    case "delete":
                        if (parsedCommand.length < 2) {
                            throw new YukeeException("Please provide the task number to delete.");
                        }
                        int deleteIndex = Integer.parseInt(parsedCommand[1]) - 1;
                        Task deletedTask = taskList.deleteTask(deleteIndex);
                        ui.showDeleteTask(deletedTask, taskList.size());
                        storage.save(taskList.getTasks());
                        break;

                    case "help":
                        ui.showHelp();
                        break;

                    default:
                        ui.showError("Unknown command: " + command);
                }
            } catch (YukeeException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("An error occurred: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Yukee("./src/main/java/yukee.txt").run();
    }
}
