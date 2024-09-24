package medea.command.createTask;

import medea.TaskList;


public class DeadlineCommand extends CreateCommand {
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
