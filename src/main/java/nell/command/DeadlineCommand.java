package nell.command;

import nell.TaskList;
import nell.common.Messages;
import nell.tasks.Deadline;

public class DeadlineCommand extends Command{
    private String description;
    private String by;

    public DeadlineCommand(TaskList tasks, String detail) throws IndexOutOfBoundsException {
        super("deadline", tasks);
        String[] details = detail.split("/by");

        if (details.length < 2) {
            throw new IndexOutOfBoundsException(Messages.DEADLINE_ERROR_MESSAGE);
        }

        this.description = details[0];
        this.by = details[1];
    }

    @Override
    public void execute() {
        Deadline deadlineToAdd = new Deadline(this.description, this.by);
        this.tasks.addTask(deadlineToAdd);
    }
}
