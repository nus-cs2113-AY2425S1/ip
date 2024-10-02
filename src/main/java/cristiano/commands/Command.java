package cristiano.commands;

import cristiano.exceptions.RonaldoException;
import cristiano.ui.Ronaldo;

public abstract class Command {
    protected boolean isEnd;

    public Command() {
        this.isEnd = false;
    }

    public boolean hasEnd() {
        return isEnd;
    }

    public abstract void execute(Ronaldo ronaldo) throws RonaldoException;
}
