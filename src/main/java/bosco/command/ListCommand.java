package bosco.command;

import bosco.ui.Ui;

public class ListCommand extends Command {
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
