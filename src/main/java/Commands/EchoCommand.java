package Commands;

import AlyBot.AlyException;
import AlyBot.Storage;
import AlyBot.TaskList;
import AlyBot.Ui;

/**
 * Represents a command to echo the instructions to the user.
 */
public class EchoCommand extends Command {

    /**
     * Constructs an EchoCommand with the specified instructions to echo.
     *
     * @param instructions The message to echo.
     */
    public EchoCommand(String instructions) {
        super(instructions);
    }

    /**
     * Executes the echo command by printing the instructions.
     *
     * @param taskList Unused parameter.
     * @param ui Unused parameter.
     * @param storage Unused parameter.
     * @throws AlyException If any exception occurs during execution.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AlyException {
        try {
            System.out.println(instructions);
        } catch (Exception e) {
            throw new AlyException("I honestly don't know what happened... Try again bah", e);
        }
    }
}