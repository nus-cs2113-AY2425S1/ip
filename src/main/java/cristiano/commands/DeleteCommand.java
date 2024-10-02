package cristiano.commands;

import cristiano.exceptions.RonaldoException;
import cristiano.ui.Ronaldo;

public class DeleteCommand extends Command {
    private final String commands;

    public DeleteCommand(String commands) {
        this.commands = commands;
    }

    @Override
    public void execute(Ronaldo ronaldo) throws RonaldoException {
        ronaldo.delete(commands);
    }
}
