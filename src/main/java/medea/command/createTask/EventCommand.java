package medea.command.createTask;

import medea.core.TaskList;


public class EventCommand extends TaskCommand {
    public static final String COMMAND_WORD = "event";
    private String description;
    private String from;
    private String to;

    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public String addTask(TaskList tasks) {
        return tasks.addEvent(description, from, to);
    }
}
