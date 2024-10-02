package cristiano.commands;

import cristiano.exceptions.RonaldoException;
import cristiano.ui.Ronaldo;

public class ListCommand extends Command{
    @Override
    public void execute(Ronaldo ronaldo) throws RonaldoException {
        ronaldo.showListOfGoals();
    }
}
