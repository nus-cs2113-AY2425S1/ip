package cristiano.commands;

import cristiano.exceptions.RonaldoException;
import cristiano.ui.Ronaldo;

public class FindCommand extends Command{
    private final String commands;

    public FindCommand(String commands) {
        this.commands = commands;
    }

    @Override
    public void execute(Ronaldo ronaldo) throws RonaldoException {
        ronaldo.find(commands);
    }
}
