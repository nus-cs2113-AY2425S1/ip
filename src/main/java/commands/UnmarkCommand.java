package commands;

import tasklist.TaskList;

public class UnmarkCommand extends Command {
    public void execute(TaskList taskList, String line){
        int index = getIndex(line,taskList.getList().size());
        if (index==-1) {
            return;
        }
        taskList.unmarkTask(index);
    }
}
