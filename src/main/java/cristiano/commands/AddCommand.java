package cristiano.commands;

import cristiano.exceptions.RonaldoException;
import cristiano.ui.Ronaldo;

/**
 * This class holds the command for adding events, todos and deadlines.
 */
public class AddCommand extends Command {
    private final String commands;

    public AddCommand(String commands) {
        this.commands = commands;
    }

    @Override
    public void execute(Ronaldo ronaldo) throws RonaldoException {
        String[] input = commands.split(" ", 2);
        String command = input[0].trim();

        switch (command) {
        case "todo":
            ronaldo.addTodo(commands);
            break;
        case "event":
            ronaldo.addEvent(commands);
            break;
        case "deadline":
            ronaldo.addDeadline(commands);
            break;
        default:
            throw new RonaldoException("Format");
        }
    }

}
