package bron.command;

import bron.exception.*;
import bron.task.*;
import bron.storage.FileStorage;
import bron.ui.TextUI;

public class CommandHandler {
    private TaskList taskList;
    private FileStorage storage;

    public CommandHandler(TaskList taskList, FileStorage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    public void handleCommand(Command command, String line) {
        try {
            switch (command) {
            case BYE:
                printByeMessage();
                break;
            case LIST:
                handleList();
                break;
            case MARK:
                handleMark(parseIndex(line));
                storage.save(taskList);
                break;
            case DELETE:
                deleteTask(parseIndex(line));
                storage.save(taskList);
                break;
            case UNMARK:
                handleUnmark(parseIndex(line));
                storage.save(taskList);
                break;
            case TODO:
                handleTodo(line);
                storage.save(taskList);
                break;
            case DEADLINE:
                handleDeadline(line);
                storage.save(taskList);
                break;
            case EVENT:
                handleEvent(line);
                storage.save(taskList);
                break;
            default:
                throw new InvalidCommandException();
            }
        } catch (BronException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printByeMessage() {
        TextUI.showByeMessage();
    }

    private int parseIndex(String line) {
        String[] parts = line.split(" ");
        return Integer.parseInt(parts[1]) - 1;
    }

    private void handleList() {
        taskList.printTasks();
    }

    private void deleteTask(int index) throws TaskIndexOutOfBoundsException {
        if (index < 0 || index >= taskList.size()) {
            throw new TaskIndexOutOfBoundsException();
        }

        taskList.deleteTask(index);
        System.out.println("You have " + taskList.size() + " task(s)");
    }

    private void handleMark(int index) throws TaskIndexOutOfBoundsException {
        if (index < 0 || index >= taskList.size()) {
            throw new TaskIndexOutOfBoundsException();
        }

        taskList.getTask(index).markAsDone();
        System.out.println("Good shit kid! I've marked this task as done:");
        System.out.println("  " + taskList.getTask(index));
    }

    private void handleUnmark(int index) throws TaskIndexOutOfBoundsException {
        if (index < 0 || index >= taskList.size()) {
            throw new TaskIndexOutOfBoundsException();
        }

        taskList.getTask(index).markAsNotDone();
        System.out.println("Get yo shit together son, this task aint done yet:");
        System.out.println("  " + taskList.getTask(index));
    }

    private void handleTodo(String line) throws EmptyTodoDescriptionException {
            if (!line.contains(" ") || line.substring(line.indexOf(" ") + 1).trim().isEmpty()) {
                throw new EmptyTodoDescriptionException();
            }

            String taskDescription = line.substring(line.indexOf(" ") + 1).trim();
            ToDo todo = new ToDo(taskDescription);
            taskList.addTask(todo);

            System.out.println("Got it. I've added this task:");
            System.out.println(todo);
            System.out.println("You got " + taskList.size() + " task(s)");
    }

    private void handleDeadline(String line) throws InvalidDeadlineFormatException {
            if (!line.contains("/by")) {
                throw new InvalidDeadlineFormatException("The deadline must include '/by' followed by the date.");
            }

            String[] deadlineParts = line.split("/by", 2);
            if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty()) {
                throw new InvalidDeadlineFormatException("Please provide a valid deadline after '/by'.");
            }

            String taskDescription = deadlineParts[0].trim();
            if (taskDescription.length() < 9) {
                throw new InvalidDeadlineFormatException("The description of a deadline cannot be empty.");
            }
            taskDescription = taskDescription.substring(9).trim();  // Extract the task description
            String byWhen = deadlineParts[1].trim();

            Deadline deadline = new Deadline(taskDescription, byWhen);
            taskList.addTask(deadline);

            System.out.println("Got it. I've added this task:");
            System.out.println("  " + deadline);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private void handleEvent(String line) throws InvalidEventFormatException {
            if (!line.contains("/from") || !line.contains("/to")) {
                throw new InvalidEventFormatException("The event must include '/from' and '/to' to specify the time.");
            }

            String[] eventParts = line.split("/from", 2);
            if (eventParts.length < 2 || eventParts[1].trim().isEmpty()) {
                throw new InvalidEventFormatException("Please provide valid event timings after '/from' and '/to'.");
            }

            String taskDescription = eventParts[0].trim();
            if (taskDescription.length() < 6) {
                throw new InvalidEventFormatException("The description of an event cannot be empty.");
            }

            taskDescription = taskDescription.substring(6).trim();
            String[] timeParts = eventParts[1].split("/to", 2);
            if (timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
                throw new InvalidEventFormatException("Please provide valid start and end times.");
            }

            String fromTime = timeParts[0].trim();
            String toTime = timeParts[1].trim();

            Event event = new Event(taskDescription, fromTime, toTime);
            taskList.addTask(event);

            System.out.println("Got it. I've added this task:");
            System.out.println("  " + event);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}
