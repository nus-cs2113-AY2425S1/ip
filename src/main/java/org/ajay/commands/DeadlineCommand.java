package org.ajay.commands;

import org.ajay.common.Constants;
import org.ajay.data.TaskList;
import org.ajay.data.exceptions.EmptyArgumentException;
import org.ajay.data.exceptions.InvalidCommandFormatException;
import org.ajay.data.task.Deadline;
import org.ajay.parser.Parser;
import org.ajay.storage.Storage;
import org.ajay.ui.TextUi;

/**
 * Creates a deadline task.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = Deadline.COMMAND_WORD;
    public static final String MESSAGE_USAGE = """
            Creates a deadline task.
            Example: """ + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Got it. I've added this task:";

    /**
     * Prints success message after adding the task.
     *
     * @param tasks   TaskList containing all tasks.
     * @param ui      TextUi object to interact with the user.
     * @param storage Storage object to save the task list.
     */
    private void printSuccessMessage(TaskList tasks, TextUi ui, Storage storage) {
        ui.printBreakLine();
        ui.printSuccess(MESSAGE_SUCCESS);
        ui.printSuccess(Constants.PADDING + tasks.getLatestTask().toString());
        tasks.printNumberOfTasks();
        ui.printBreakLine();

        storage.saveTaskList(tasks.getTaskList());
    }

    /**
     * Executes the deadline command.
     *
     * @param tasks   TaskList containing all tasks.
     * @param ui      TextUi object to interact with the user.
     * @param storage Storage object to save the task list.
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        try {
            tasks.addTask(new Deadline(Parser.task));
        } catch (EmptyArgumentException e) {
            ui.printExceptions(e.getMessage());
        } catch (InvalidCommandFormatException e) {
            ui.printExceptions(e.getMessage());
            return;
        }

        printSuccessMessage(tasks, ui, storage);
    }

}
