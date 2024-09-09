package esme.command;

import esme.Ui;

public class Command {
    protected Ui ui;

    public Command(Ui ui) {
        this.ui = ui;
    }

    public void run() {}
}
