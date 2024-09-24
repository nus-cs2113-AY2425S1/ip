package medea.command.createTask;

import medea.TaskList;

public class TodoCommand extends CreateCommand {
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
