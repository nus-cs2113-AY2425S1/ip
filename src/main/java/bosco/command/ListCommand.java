package bosco.command;

import bosco.ui.Ui;

/**
 * Represents the command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Overrides the default execute method to format and print all tasks in the task list.
     * If task list is empty, print message indicating it is empty.
     */
    @Override
    public void execute() {
        if (tasks.getSize() == 0) {
            ui.printMessages("No tasks in list. You're all caught up!");
            return;
        }
        System.out.println(Ui.DIVIDER);
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println(Ui.INDENT_START + (i + 1) + "." + tasks.getTaskAtIndex(i));
        }
        System.out.println(Ui.DIVIDER);
    }
}
