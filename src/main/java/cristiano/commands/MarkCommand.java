package cristiano.commands;

import cristiano.exceptions.RonaldoException;
import cristiano.ui.Ronaldo;

public class MarkCommand extends Command {
    private final String[] input;

    public MarkCommand(String[] input) {
        this.input = input;
    }

    @Override
    public void execute(Ronaldo ronaldo) throws RonaldoException {
        ronaldo.handleGoal(input);
    }
}
