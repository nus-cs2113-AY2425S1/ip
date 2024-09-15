package esme.command;

import esme.ui.Ui;

public abstract class Command {
    protected Ui ui;

    public Command(Ui ui) {
        this.ui = ui;
    }

    public abstract void run();
}
