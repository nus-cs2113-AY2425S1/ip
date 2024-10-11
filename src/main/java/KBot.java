import java.util.Scanner;
import java.util.ArrayList;

public class KBot {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private Scanner scanner;

    public KBot() {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        KBot bot = new KBot();
        bot.run();
    }

    public void run() {
        ui.greetUser();

        boolean isRunning = true;
        while (isRunning) {
            try {
                String userInput = ui.getUserInput(scanner);
                isRunning = handleCommand(userInput);
            } catch (KBotException e) {
                ui.showError(e.getMessage());
            }
        }

        ui.exit();
    }

    private boolean handleCommand(String input) throws KBotException {
        String[] inputParts = parser.parseInput(input);
        String command = inputParts[0];
        String argument = (inputParts.length > 1) ? inputParts[1] : "";

        switch (command) {
            case "bye":
                return false;
            case "list":
                listTasks();
                break;
            case "mark":
                int markIndex = parser.parseTaskNumber(argument);
                markTaskAsDone(markIndex);
                break;
            case "unmark":
                int unmarkIndex = parser.parseTaskNumber(argument);
                markTaskAsNotDone(unmarkIndex);
                break;
            case "todo":
                if (argument.isEmpty()) {
                    throw new KBotException("The description of a todo cannot be empty.");
                } else {
                    addTodoTask(argument);
                }
                break;
            case "deadline":
                parser.validateDeadlineArgument(argument);
                addDeadlineTask(argument);
                break;
            case "event":
                parser.validateEventArgument(argument);
                addEventTask(argument);
                break;
            case "delete":
                int deleteIndex = parser.parseTaskNumber(argument);
                deleteTask(deleteIndex);
                break;
            case "find":
                handleFindCommand(input);
                break;
            default:
                throw KBotException.unknownCommand();
        }

        storage.saveTasksToFile(taskList.getTasks()); // Save the updated task list after each change
        return true;
    }

    private void handleFindCommand(String input) {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            System.out.println("Please provide a keyword to search for.");
            return;
        }

        String keyword = parts[1];
        ArrayList<Task> matchingTasks = taskList.findTasks(keyword);
        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found for keyword: " + keyword);
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + "." + matchingTasks.get(i).toString());
            }
        }
    }

    private void listTasks() {
        ui.printSeparator();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.getTask(i));
        }
        ui.printSeparator();
    }

    private void addTodoTask(String description) {
        Task task = new Todo(description);
        taskList.addTask(task);
        ui.printTaskAddedMessage(task, taskList.size());
    }

    private void addDeadlineTask(String input) throws KBotException {
        String[] parts = input.split(" /by ");
        if (parts.length < 2) {
            throw new KBotException("The deadline description or date is missing.");
        }
        String description = parts[0];
        String time = parts[1]; // This will be the deadline date
        Task task = new Deadline(description, time);
        taskList.addTask(task);
        ui.printTaskAddedMessage(task, taskList.size());
    }

    private void addEventTask(String input) throws KBotException {
        String[] parts = input.split(" /from | /to ");
        if (parts.length < 3) {
            throw new KBotException("The event description or timing is missing.");
        }
        String description = parts[0];
        String from = parts[1]; // Start time
        String to = parts[2]; // End time
        Task task = new Event(description, from, to);
        taskList.addTask(task);
        ui.printTaskAddedMessage(task, taskList.size());
    }


    private void markTaskAsDone(int index) throws KBotException {
        validateTaskIndex(index);
        taskList.getTask(index).markAsDone();
        ui.printMarkDoneMessage(taskList.getTask(index));
    }

    private void markTaskAsNotDone(int index) throws KBotException {
        validateTaskIndex(index);
        taskList.getTask(index).markAsNotDone();
        ui.printMarkNotDoneMessage(taskList.getTask(index));
    }

    private void validateTaskIndex(int index) throws KBotException {
        if (index >= taskList.size() || index < 0) {
            throw new KBotException("Invalid task number.");
        }
    }

    public void deleteTask(int taskIndex) throws KBotException {
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new KBotException("OOPS!!! Task does not exist.");
        }
        Task removedTask = taskList.getTask(taskIndex);
        taskList.deleteTask(taskIndex);
        ui.printDeleteTaskMessage(removedTask, taskList.size());
    }
}
