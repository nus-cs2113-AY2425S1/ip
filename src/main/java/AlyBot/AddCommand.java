package AlyBot;

import Task.Deadline;
import Task.Event;
import Task.Todo;

public class AddCommand extends Command {

    private final String firstWord;

    public AddCommand(String firstWord, String instructions) {
        super(instructions);
        this.firstWord = firstWord;
    }

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
