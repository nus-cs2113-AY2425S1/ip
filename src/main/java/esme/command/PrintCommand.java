package esme.command;

import esme.ui.Ui;

public class PrintCommand extends Command {
    private String[] command;

    public PrintCommand(Ui ui, String[] command) {
        super(ui);
        this.command = command;
    }

    @Override
    public void run() {
        if (command[0].equals("list")) {
            super.ui.printTaskList();
        } else {
            super.ui.printTasksIn(command);
        }
    }
}
