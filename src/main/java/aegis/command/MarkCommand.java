package aegis.command;

import aegis.AegisException;
import aegis.TaskList;
import aegis.Ui;
import aegis.Storage;

public class MarkCommand extends Command {
    private int taskIndex;
    private boolean isMark;

    public MarkCommand(int taskIndex, boolean isMark) {
        this.taskIndex = taskIndex;
        this.isMark = isMark;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AegisException {
        if (taskIndex < 0 || taskIndex >= tasks.getTasks().size()) {
            throw new AegisException("Invalid task number");
        }
        System.out.println();
        if (isMark) {
            tasks.getTasks().get(taskIndex).markAsDone();
            System.out.printf(" I've marked this task as done:%n   %s%n", tasks.getTasks().get(taskIndex));
        } else {
            tasks.getTasks().get(taskIndex).unmarkAsDone();
            System.out.printf(" I've marked this task as not done yet:%n   %s%n", tasks.getTasks().get(taskIndex));
        }
        System.out.println();
        storage.save(tasks.getTasks());
    }
}
