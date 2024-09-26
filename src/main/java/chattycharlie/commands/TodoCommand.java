package chattycharlie.commands;

import chattycharlie.CharlieExceptions;
import chattycharlie.userinteractions.Storage;
import chattycharlie.TaskList;
import chattycharlie.userinteractions.Ui;
import chattycharlie.task.Task;
import chattycharlie.task.Todo;

public class TodoCommand implements Command{
    private String description;

    public TodoCommand(String line) throws CharlieExceptions {
        String text = line.substring(4).trim();
        if (text.isEmpty()) {
            throw CharlieExceptions.missingDescription(CommandType.TODO);
        } else {
            this.description = text;
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){
        Task todoTask = new Todo(description);
        taskList.addTask(todoTask);
        ui.displayTaskAdded(todoTask);
    }
}
