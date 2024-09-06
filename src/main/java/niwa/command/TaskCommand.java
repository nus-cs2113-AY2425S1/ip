package niwa.command;

import niwa.task.Task;

import java.util.List;

public abstract class TaskCommand extends Command{
    protected static List<Task> tasks;
    public TaskCommand(List<Task> tasks) {
        setTasks(tasks);
    }

    public void setTasks(List<Task> tasks) {
        if (tasks != null) {
            TaskCommand.tasks = tasks;
        }
    }
}
