package Ryan.commands;

import Ryan.utility.*;
import Ryan.tasks.*;
import java.util.ArrayList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ArrayList<Task> tasks = taskList.getTasks();
        ui.printTasks(tasks);
    }
}