package commands;

import tasklist.TaskList;
import tasklist.tasks.Todo;

import java.util.HashMap;

public class TodoCommand extends Command{
    private static final String[] TODO_REQUIRED_OPTIONS = {"desc"};

    public void execute(TaskList taskList, String line){
        HashMap<String,String> options = getFlags(line,TODO_REQUIRED_OPTIONS);
        if (options == null) {
            return;
        }
        taskList.addTask(new Todo(options.get("desc")));
    }
}
