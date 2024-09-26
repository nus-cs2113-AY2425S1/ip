package bosco.command;

import static bosco.ui.Ui.DIVIDER;
import static bosco.ui.Ui.INDENT_START;

public class ListCommand extends Command {
    @Override
    public void execute() {
        if (tasks.getSize() == 0) {
            ui.printMessages("No tasks in list. You're all caught up!");
            return;
        }
        System.out.println(DIVIDER);
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println(INDENT_START + (i + 1) + "." + tasks.getTaskAtIndex(i));
        }
        System.out.println(DIVIDER);
    }
}
