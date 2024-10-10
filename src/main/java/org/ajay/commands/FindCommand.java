package org.ajay.commands;

import java.util.ArrayList;
import org.ajay.data.TaskList;
import org.ajay.data.task.Task;
import org.ajay.parser.Parser;
import org.ajay.storage.Storage;
import org.ajay.ui.TextUi;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = """
            Find tasks by searching for a keyword.
            Example: """ + COMMAND_WORD;

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        String keyword = Parser.task;
        ArrayList<Task> foundTasks = TaskList.findTaskWithStreams(tasks.getTaskList(), keyword);
        tasks.printAllTasks(foundTasks);
    }
}
