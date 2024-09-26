package bot.command;

import bot.Storage;
import bot.TobyBotException;
import bot.Ui;
import task.Task;
import task.Deadline;
import task.Event;
import task.Todo;
import task.TaskList;

import java.io.IOException;

public class AddCommand extends Command {
    private final String type;
    private final String details;

    public AddCommand(String type, String details) {
        this.type = type;
        this.details = details;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TobyBotException {
        Task task;
        switch (type) {
            case "todo":
                if (details.isEmpty()) throw new TobyBotException("The description of a todo cannot be empty.");
                task = new Todo(details);
                break;
            case "deadline":
                String[] deadlineParts = details.split(" by ", 2);
                if (deadlineParts.length < 2) throw new TobyBotException("Please specify a deadline with by.");
                task = new Deadline(deadlineParts[0], deadlineParts[1]);
                break;
            case "event":
                String[] eventParts = details.split(" from | to ");
                if (eventParts.length < 3) throw new TobyBotException("Please specify an event with from and to.");
                task = new Event(eventParts[0], eventParts[1], eventParts[2]);
                break;
            default:
                throw new TobyBotException("Unknown task type.");
        }
        tasks.addTask(task);
        ui.showMessage("Got it. I've added this task:\n  " + task);
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            ui.showError("Unable to save tasks.");
        }
    }
}