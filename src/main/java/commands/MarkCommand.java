package commands;

import tasklist.TaskList;

import java.util.HashMap;

public class MarkCommand extends Command {
    public void execute(TaskList taskList, String line){
        int index = getIndex(line,taskList.getList().size());
        if (index==-1) {
            return;
        }
        taskList.markTask(index);
    }
}
