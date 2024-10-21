package bosco.command;

import bosco.ui.Ui;

/**
 * Represents the command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Overrides the default execute method to format and print all tasks in the task list.
     * If task list is empty, it prints message indicating it is empty.
     */
    @Override
    public void execute() {
        if (tasks.getSize() == 0) {
            ui.printMessages(Ui.MESSAGE_EMPTY_LIST);
            return;
        }
        printAllTasks();
    }

    private void printAllTasks() {
        System.out.println(Ui.DIVIDER);
        System.out.println(Ui.INDENT_START + Ui.MESSAGE_LIST);
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println(Ui.INDENT_START + (i + 1) + "." + tasks.getTaskAtIndex(i));
        }
        System.out.println(Ui.DIVIDER);
    }
}
