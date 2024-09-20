package doug.Commands;

import doug.Main.DougException;
import doug.Storage;
import doug.TaskList;
import doug.tasks.Deadline;

import java.io.IOException;

public class AddDeadlineCommand extends Command {
    public static void newDeadline(TaskList tasks, String deadlineName, String deadlineBy) throws DougException {
        Deadline deadlineTask = new Deadline(deadlineName, deadlineBy);
        addNewTask(tasks, deadlineTask);
    }
}
