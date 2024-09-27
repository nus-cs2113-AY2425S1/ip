package bob.command;
import bob.task.Event;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;
import bob.storage.Storage;

public class AddEventCommand extends Command {
    private final String input;

    private static final String COMMAND_EVENT = "event";
    private static final String EVENT_START = " /from ";
    private static final String EVENT_END = " /to ";

    public AddEventCommand(String command) {
        this.input = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int fromIndex = this.input.indexOf(EVENT_START);
        int toIndex = this.input.indexOf(EVENT_END);
        String userTypedDescription = this.input.substring(COMMAND_EVENT.length()).trim();
        if (userTypedDescription.isEmpty()) {
            ui.printEmptyDescription("event");
        } else {
            try {
                String description = input.substring(COMMAND_EVENT.length() +1, fromIndex);
                String startTime = input.substring(fromIndex + EVENT_START.length(), toIndex);
                String endTime = input.substring(toIndex + EVENT_END.length());
                Task newTask = new Event(description, startTime, endTime);
                tasks.addTask(newTask);
                ui.printAddedTask(tasks);
                storage.appendTask(newTask);
                storage.save(tasks.getTaskList());
            } catch (StringIndexOutOfBoundsException e) {
                ui.printInvalidEvent();
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}