package cristiano.commands;

import cristiano.ui.Ronaldo;

public class HelpCommand extends Command{

    @Override
    public void execute(Ronaldo ronaldo) {
        ronaldo.help();
    }
}
