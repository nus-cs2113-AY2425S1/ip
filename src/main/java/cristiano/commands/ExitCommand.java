package cristiano.commands;

import cristiano.ui.Ronaldo;

public class ExitCommand extends Command {

    public ExitCommand() {
        this.isEnd = true;
    }

    @Override
    public void execute(Ronaldo ronaldo) {
        ronaldo.exit();
    }
}
