package cristiano.commands;

import cristiano.exceptions.RonaldoException;
import cristiano.ui.Ronaldo;

public class HiddenCommand extends Command{
    private final String input;

    public HiddenCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(Ronaldo ronaldo) throws RonaldoException {
        if (input.contains("messi")) {
            ronaldo.boast();
        } else if (input.contains("siu")) {
            ronaldo.exclaim();
        }
        Parser.isHidden = false;
        Command nextCommand = Parser.parse(input);  // Re-parse the new command
        nextCommand.execute(ronaldo);
    }

}
