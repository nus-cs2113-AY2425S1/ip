package command;

import data.Storage;
import exceptions.IrisException;
import task.Task;
import task.TaskList;

public class MarkCommand extends Command {
    private static final String MARK_AS_COMPLETED_MESSAGE = "Nice! I've marked this task as done:";
    private static final String UNMARK_FROM_COMPLETED_MESSAGE = "OK, I've marked this task as not done yet:";

    private final boolean isMark;
    private final String description;

    public MarkCommand(String command, String description) {
        this.isMark = command.equals("mark");
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws IrisException {
        Task taskToChange = Parser.getTaskNum(tasks, description);
        taskToChange.mark(isMark);
        printChangeTaskStatusMessage(taskToChange, isMark);
    }

    private static void printChangeTaskStatusMessage(Task task, boolean isMark) {
        if (isMark) {
            System.out.println(MARK_AS_COMPLETED_MESSAGE);
        } else {
            System.out.println(UNMARK_FROM_COMPLETED_MESSAGE);
        }
        System.out.println(task);
    }
}
