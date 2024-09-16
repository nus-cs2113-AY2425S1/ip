package quinn;

import quinn.exception.QuinnException;
import quinn.task.Deadline;
import quinn.task.Event;
import quinn.task.Task;
import quinn.task.ToDo;
import quinn.ui.Ui;
import quinn.storage.Storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Quinn {
    private final Ui ui;
    private Storage storage;
    private List<Task> tasks;

    public Quinn(String folderName, String fileName) {
        ui = new Ui();

        try {
            storage = new Storage(folderName, fileName);
            tasks = storage.loadTasksFromFile();
        } catch (QuinnException | IOException e) {
            ui.displayError(e.getMessage());
            tasks = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        new Quinn("data", "tasks.txt").run();
    }

    public void run() {
        ui.displayWelcome();

        String commandLine = "";

        while (!commandLine.equals("bye")) {
            commandLine = ui.readCommand();

            try {
                processCommand(commandLine);
            } catch (QuinnException | IOException e) {
                ui.displayError(e.getMessage());
            } finally {
                ui.displayLine();
            }
        }
    }

    public void processCommand(String commandLine) throws QuinnException, IOException {
        String[] commandLineParts = commandLine.split(" ", 2);

        String commandType;
        String commandInfo;

        if (commandLineParts.length == 2) {
            commandType = commandLineParts[0].trim();
            commandInfo = commandLineParts[1].trim();

            if ((commandType.equals("bye") || commandType.equals("list")) && !commandInfo.isEmpty()) {
                throw new QuinnException("INVALID COMMAND. Please try again!");
            }
        } else { // for "bye" and "list" commands which does not have any input behind
            commandType = commandLineParts[0];
            commandInfo = "";
        }

        executeCommand(commandType, commandInfo);
    }

    public void executeCommand(String commandType, String commandInfo) throws QuinnException, IOException {
        int taskNum;
        Task task;
        String taskDescription;
        String taskInfo;

        switch (commandType.toLowerCase()) {
            case "bye":
                ui.displayExit();
                break;
            case "list":
                displayTasks();
                break;
            case "mark":
                taskNum = getTaskNumFromMarkCommand(commandInfo);
                markTask(taskNum);
                break;
            case "unmark":
                taskNum = getTaskNumFromUnmarkCommand(commandInfo);
                unmarkTask(taskNum);
                break;
            case "delete":
                taskNum = getTaskNumFromDeleteCommand(commandInfo);
                deleteTask(taskNum);
                break;
            case "todo":
                taskDescription = getTaskDescriptionFromToDoCommand(commandInfo);
                task = new ToDo(taskDescription);
                addTask(task);
                break;
            case "deadline":
                taskInfo = processTaskInfoFromDeadlineCommand(commandInfo);
                String[] deadlineTaskDetails = taskInfo.split("/by", 2);
                task = new Deadline(deadlineTaskDetails[0].trim(), deadlineTaskDetails[1].trim());
                addTask(task);
                break;
            case "event":
                taskInfo = processTaskInfoFromEventCommand(commandInfo);
                String[] eventTaskDetails = taskInfo.split("/from|/to", 3);
                task = new Event(eventTaskDetails[0].trim(), eventTaskDetails[1].trim(), eventTaskDetails[2].trim());
                addTask(task);
                break;
            default:
                throw new QuinnException("INVALID COMMAND. Please try again!");
        }
    }

    private boolean isCommandInfoPresent(String commandInfo) {
        return !commandInfo.trim().isEmpty();
    }

    private int getTaskNumFromMarkCommand(String commandInfo) throws QuinnException {
        if (isCommandInfoPresent(commandInfo)) {
            try {
                return Integer.parseInt(commandInfo);
            } catch (NumberFormatException e) {
                throw new QuinnException("Please enter a valid task number to be marked as done!");
            }
        } else {
            throw new QuinnException("Please enter a task number to be marked as done!");
        }
    }

    private int getTaskNumFromUnmarkCommand(String commandInfo) throws QuinnException {
        if (isCommandInfoPresent(commandInfo)) {
            try {
                return Integer.parseInt(commandInfo);
            } catch (NumberFormatException e) {
                throw new QuinnException("Please enter a valid task number to be marked as not done yet!");
            }
        } else {
            throw new QuinnException("Please enter a task number to be marked as not done yet!");
        }
    }

    private int getTaskNumFromDeleteCommand(String commandInfo) throws QuinnException {
        if (isCommandInfoPresent(commandInfo)) {
            try {
                return Integer.parseInt(commandInfo);
            } catch (NumberFormatException e) {
                throw new QuinnException("Please enter a valid task number to be deleted!");
            }
        } else {
            throw new QuinnException("Please enter a task number to be deleted!");
        }
    }

    private String getTaskDescriptionFromToDoCommand(String commandInfo) throws QuinnException {
        if (isCommandInfoPresent(commandInfo)) {
            return commandInfo;
        } else {
            throw new QuinnException("The description of a todo cannot be empty!");
        }
    }

    private String processTaskInfoFromDeadlineCommand(String commandInfo) throws QuinnException {
        if (!isCommandInfoPresent(commandInfo)) {
            throw new QuinnException("INCOMPLETE COMMAND"
                    + System.lineSeparator() + "\t"
                    + "The description and date/time of a deadline cannot be empty!"
                    + System.lineSeparator() + "\t"
                    + "[Note: Enter /by before specifying the date/time]");
        } else {
            String[] deadlineInfoParts = commandInfo.split("/by", 2);

            if (deadlineInfoParts.length != 2) {
                throw new QuinnException("INVALID COMMAND"
                        + System.lineSeparator() + "\t"
                        + "Please check that the description and date/time of a deadline is present!"
                        + System.lineSeparator() + "\t"
                        + "[Note: Enter /by before specifying the date/time]");
            }

            String deadlineDescription = deadlineInfoParts[0].trim();
            String deadlineByDateTime = deadlineInfoParts[1].trim();

            if (deadlineDescription.isEmpty()) {
                throw new QuinnException("INCOMPLETE COMMAND"
                        + System.lineSeparator() + "\t"
                        + "The description of a deadline cannot be empty!");
            }

            if (deadlineByDateTime.isEmpty()) {
                throw new QuinnException("INCOMPLETE COMMAND"
                        + System.lineSeparator() + "\t"
                        + "The date/time of a deadline cannot be empty!"
                        + System.lineSeparator() + "\t"
                        + "[Note: Enter /by before specifying the date/time]");
            }

            return commandInfo;
        }
    }

    private String processTaskInfoFromEventCommand(String commandInfo) throws QuinnException {
        if (!isCommandInfoPresent(commandInfo)) {
            throw new QuinnException("INCOMPLETE COMMAND"
                    + System.lineSeparator() + "\t"
                    + "The description and date/time of an event cannot be empty!"
                    + System.lineSeparator() + "\t"
                    + "[Note: Specify the date/time with '/from /to']");
        } else {
            String[] eventInfoParts = commandInfo.split("/from|/to", 3);

            if (eventInfoParts.length != 3) {
                throw new QuinnException("INVALID COMMAND"
                        + System.lineSeparator() + "\t"
                        + "Please check that the description and date/time of an event is present!"
                        + System.lineSeparator() + "\t"
                        + "[Note: Specify the date/time with '/from /to']");
            }

            String eventDescription = eventInfoParts[0].trim();
            String eventFromDateTime = eventInfoParts[1].trim();
            String eventToDateTime = eventInfoParts[2].trim();

            if (eventDescription.isEmpty()) {
                throw new QuinnException("INCOMPLETE COMMAND"
                        + System.lineSeparator() + "\t"
                        + "The description of an event cannot be empty!");
            }

            if (eventFromDateTime.isEmpty() || eventToDateTime.isEmpty()) {
                throw new QuinnException("INCOMPLETE COMMAND"
                        + System.lineSeparator() + "\t"
                        + "The date/time of an event cannot be empty!"
                        + System.lineSeparator() + "\t"
                        + "[Note: Specify the date/time with '/from /to']");
            }

            return commandInfo;
        }
    }

    public void addTask(Task task) throws IOException {
        tasks.add(task);

        String response = ui.taskAddedMessage(task)
                + System.lineSeparator()
                + ui.numOfTasksInListMessage(tasks);
        ui.displayResponse(response);

        storage.saveTasksToFile(tasks);
    }

    public void displayTasks() throws QuinnException {
        if (!tasks.isEmpty()) {
            String response = ui.tasksInListMessage(tasks);
            ui.displayResponse(response);
        } else {
            throw new QuinnException("There are no tasks in your list!");
        }
    }

    public void markTask(int taskNum) throws QuinnException, IOException {
        if (taskNum > 0 && taskNum <= tasks.size()) {
            Task task = tasks.get(taskNum - 1);
            task.setDone();

            String message = ui.taskDoneMessage(task);
            ui.displayResponse(message);

            storage.saveTasksToFile(tasks);
        } else {
            throw new QuinnException("Task not found. Please try again!");
        }
    }

    public void unmarkTask(int taskNum) throws QuinnException, IOException {
        if (taskNum > 0 && taskNum <= tasks.size()) {
            Task task = tasks.get(taskNum - 1);
            task.setNotDone();

            String message = ui.taskNotDoneMessage(task);
            ui.displayResponse(message);

            storage.saveTasksToFile(tasks);
        } else {
            throw new QuinnException("Task not found. Please try again!");
        }
    }

    public void deleteTask(int taskNum) throws QuinnException, IOException {
        if (taskNum > 0 && taskNum <= tasks.size()) {
            Task task = tasks.get(taskNum - 1);
            tasks.remove(task);

            String message = ui.taskDeletedMessage(task)
                    + System.lineSeparator()
                    + ui.numOfTasksInListMessage(tasks);
            ui.displayResponse(message);

            storage.saveTasksToFile(tasks);
        } else {
            throw new QuinnException("Task not found. Please try again!");
        }
    }
}
