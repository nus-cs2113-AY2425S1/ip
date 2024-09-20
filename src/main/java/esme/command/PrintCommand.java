package esme.command;

import esme.ui.Ui;

public class PrintCommand extends Command {
    private String command;
    private String input;

    public PrintCommand(Ui ui, String command, String input) {
        super(ui);
        this.command = command;
        this.input = input;
    }

    @Override
    public void run() {
        if (command.equals("list")) {
            super.ui.printTaskList();
        } else if (command.equals("find")) {
            super.ui.printTaskFound(input);
        }
    }
}
