package bosco.command;

import bosco.ui.Ui;

public class ListCommand extends Command {
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
