package medea.command.createTask;

import medea.core.TaskList;


public class DeadlineCommand extends TaskCommand {
    public static final String COMMAND_WORD = "deadline";
    private String description;
    private String by;

    public DeadlineCommand(String description, String by){
        this.description = description;
        this.by = by;
    }

    @Override
    public String addTask(TaskList tasks) {
        return tasks.addDeadline(description, by);
    }
}
