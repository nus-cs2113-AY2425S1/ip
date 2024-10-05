package ran.command;

import main.java.TaskList;
import main.java.Ui;
import main.java.Storage;

import ran.task.Task;
import ran.exception.EmptyListException;
import ran.exception.OutOfListBoundsException;
import java.io.IOException;
import ran.exception.MissingArgumentException;

public class FindCommand extends Command {
    public FindCommand(String commandArg) {
        super(commandArg);
    }

    private int getMatchList(TaskList tasks, int taskCount, TaskList matchList) throws OutOfListBoundsException {
        int matchListCount = 0;
        for (int i = 0; i < taskCount; i++) {
            String taskDescription = tasks.getTask(i).getDescription();
            if (taskDescription.indexOf(commandArg) >= 0) {
                matchList.addTask(tasks.getTask(i));
                matchListCount++;
            }
        }
        return matchListCount;
    }

    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws IOException, 
            OutOfListBoundsException, EmptyListException, MissingArgumentException {
        int taskCount = tasks.getTaskCount();
        if (taskCount == 0) {
            throw new EmptyListException();
        }
        if (commandArg.equals("")) {
            throw new MissingArgumentException(CommandType.FIND);
        }
        TaskList matchList = new TaskList();
        int matchListCount = getMatchList(tasks, taskCount, matchList);
        if (matchListCount > 0) {
            ui.printFound(matchList.getAllTasks(), matchListCount);
        } else {
            ui.printMessage(new String[] {"I couldn't find anything.", 
                    "Perhaps try a different keyphrase?"});
        }
        return false;
    }
}
