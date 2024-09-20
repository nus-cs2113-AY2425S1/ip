package aegis.command;

import aegis.AegisException;
import aegis.TaskList;
import aegis.Ui;
import aegis.Storage;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AegisException {
        if (taskIndex < 0 || taskIndex >= tasks.getTasks().size()) {
            throw new AegisException("Invalid task number");
        }
        System.out.println();
        System.out.printf(" Noted. I've removed this task:%n   %s%n", tasks.getTasks().get(taskIndex));
        tasks.deleteTask(taskIndex);
        System.out.printf(" Now you have %d tasks in the list.%n", tasks.getTasks().size());
        System.out.println();
        storage.save(tasks.getTasks());
    }
}
