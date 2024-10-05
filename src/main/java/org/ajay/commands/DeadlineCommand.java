package org.ajay.commands;

import org.ajay.data.TaskList;
import org.ajay.data.exceptions.EmptyArgumentException;
import org.ajay.data.task.Deadline;
import org.ajay.parser.Parser;
import org.ajay.storage.Storage;
import org.ajay.ui.TextUi;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = Deadline.COMMAND_STRING;
    public static final String MESSAGE_USAGE = """
                                               Creates a deadline task.
                                               Example: """ + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Deadline has been created!";

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        try {
            tasks.addTask(new Deadline(Parser.task));
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks.getLatestTask().toString());
            tasks.printNumberOfTasks();

            storage.saveTaskList(tasks.getTaskList());
        } catch (EmptyArgumentException e) {
            TextUi.printExceptions(e.getMessage());
        }
    }

}
