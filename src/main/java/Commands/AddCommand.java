package Commands;

import AlyBot.AlyException;
import AlyBot.Storage;
import AlyBot.TaskList;
import AlyBot.Ui;
import Task.Deadline;
import Task.Event;
import Task.Todo;

/**
 * Represents a command to add tasks (Todo, Deadline, Event) to the task list.
 */
public class AddCommand extends Command {

    private final String firstWord;

    /**
     * Constructs an AddCommand with the type of task and the instructions to add.
     *
     * @param firstWord The type of task (e.g., todo, deadline, event).
     * @param instructions The details for the task.
     */
    public AddCommand(String firstWord, String instructions) {
        super(instructions);
        this.firstWord = firstWord;
    }

    /**
     * Executes the add command by adding the appropriate task to the task list.
     *
     * @param taskList The task list to add the task to.
     * @param ui The user interface for output messages.
     * @param storage The storage system to save the updated task list.
     * @throws AlyException If an invalid task type or error occurs during task creation.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AlyException {
        try {
            switch (firstWord) {
            case "todo":
                addTodo(taskList, ui, instructions);
                storage.write(taskList);
                break;
            case "deadline":
                addDeadline(taskList, ui, instructions);
                storage.write(taskList);
                break;
            case "event":
                addEvent(taskList, ui, instructions);
                storage.write(taskList);
                break;
            default:
                throw new AlyException("Read instructions properly lah!");
            }
        } catch (AlyException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Adds a Todo task to the task list.
     *
     * @param taskList The task list to add the Todo task to.
     * @param ui The user interface for output messages.
     * @param instructions The details of the Todo task.
     * @throws AlyException If the task list is null or the instructions are empty.
     */
    private void addTodo(TaskList taskList, Ui ui, String instructions) throws AlyException {
        if (taskList == null) {
            throw new AlyException("Task list doesn't exist lah...");
        }

        if (instructions.isEmpty()) {
            throw new AlyException("No details bruh... What you expect me to do??");
        }

        taskList.addTask(new Todo(instructions.trim()));
        ui.addMessage(instructions);
        ui.showTaskSize(taskList.getSize());
    }

    /**
     * Adds a Deadline task to the task list.
     *
     * @param taskList The task list to add the Deadline task to.
     * @param ui The user interface for output messages.
     * @param instructions The details of the Deadline task.
     * @throws AlyException If the instructions are in the wrong format or missing details.
     */
    private void addDeadline(TaskList taskList, Ui ui, String instructions) throws AlyException {
        String[] taskParts = instructions.split("\\bby\\b");

        if (taskList == null) {
            throw new AlyException("Task list doesn't exist lah...");
        }

        if (instructions.isEmpty() || taskParts.length != 2) {
            throw new AlyException("Wrong format bruh... What you expect me to do??");
        }

        String taskDeadline = taskParts[0].trim();
        String taskBy = taskParts[1].trim();

        if (taskDeadline.isEmpty() || taskBy.isEmpty()) {
            throw new AlyException("Missing details lah, try again!");
        }

        taskList.addTask(new Deadline(taskDeadline, taskBy));
        ui.addMessage(taskDeadline);
        ui.showTaskSize(taskList.getSize());
    }

    /**
     * Adds an Event task to the task list.
     *
     * @param taskList The task list to add the Event task to.
     * @param ui The user interface for output messages.
     * @param instructions The details of the Event task.
     * @throws AlyException If the instructions are in the wrong format or missing details.
     */
    private void addEvent(TaskList taskList, Ui ui, String instructions) throws AlyException {
        String[] taskParts = instructions.split("\\bfrom\\b|\\bto\\b");

        if (instructions.isEmpty() || taskParts.length != 3) {
            throw new AlyException("Wrong format bruh... What you expect me to do??");
        }

        if (taskList == null) {
            throw new AlyException("Task list doesn't exist lah...");
        }

        String taskEvent = taskParts[0].trim();
        String taskFrom = taskParts[1].trim();
        String taskTo = taskParts[2].trim();

        if (taskEvent.isEmpty() || taskFrom.isEmpty() || taskTo.isEmpty()) {
            throw new AlyException("Missing details lah, try again!");
        }

        taskList.addTask(new Event(taskEvent, taskFrom, taskTo));
        ui.addMessage(taskEvent);
        ui.showTaskSize(taskList.getSize());
    }
}