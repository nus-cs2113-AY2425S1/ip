package aegis.command;

import aegis.AegisException;
import aegis.TaskList;
import aegis.Ui;
import aegis.Storage;
import aegis.task.Task;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AegisException {
        tasks.addTask(task);
        System.out.println();
        System.out.printf(" New task added: %n   %s%n", task);
        System.out.printf(" %d tasks needed to be done. Let me assist you!%n", tasks.getTasks().size());
        System.out.println();
        storage.save(tasks.getTasks());
    }
}
