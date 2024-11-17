package commands;

import tasks.Task;
import data.TaskList;
import ui.Ui;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    public MarkCommand(String cmd, String args) {
        super(cmd, args);
    }

    /**
     * Mark the task at the given index as done
     *
     * @param tasks TaskList object that stores all the tasks currently stored
     * @param ui    Ui object used for interacting with the user
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui) {
        try {
            Task toMark = tasks.getTask(Integer.parseInt(args) - 1);
            toMark.markDone();
            System.out.println(Ui.DIVIDER + "Nice! I've marked this task as done: "
                    + toMark.getDescription() + "\n"
                    + Ui.DIVIDER);
        } catch (NumberFormatException e) {
            System.out.println("Must include a number to mark");
        }
        catch(IndexOutOfBoundsException e) {
            System.out.println("Invalid index");
        }
    }

}
