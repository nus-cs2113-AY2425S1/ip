package medea.command.createTask;

import medea.core.TaskList;

public class TodoCommand extends TaskCommand {
    public static final String COMMAND_WORD = "todo";
    private String description;

    public TodoCommand(String description){
        this.description = description;
    }

    @Override
    public String addTask(TaskList tasks) {
        return tasks.addTodo(description);
    }
}
