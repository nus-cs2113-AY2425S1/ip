package commands;

import exception.IllegalCommandException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import static main.Sirius.*;

/**
 * The ByeCommand class handles the termination of the program when the user inputs the "bye" command.
 */
public class ByeCommand extends Command{
    private final String userInput;

    public ByeCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the ByeCommand by calling the `sayGoodbye` method of the Ui class.
     * This is used to display the goodbye message to the user.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        String[] commandPieces = userInput.split(SPACE);
        try{
            if (commandPieces.length >1){
                throw new IllegalCommandException(ILLEGAL_COMMAND_MESSAGE + "\n" + SEPARATOR);
            }
            ui.sayGoodbye();
        } catch (IllegalCommandException e){
            ui.showLine();
            ui.print(e.getMessage());
        }

    }

    /**
     * Overrides the isExit method to indicate that this command should terminate the program.
     *
     * @return true, if the command is just "bye", indicating that the program should exit after this command.
     */
    @Override
    public boolean isExit(){
        String[] commandPieces = userInput.split(SPACE);
        return commandPieces.length == 1;
    }
}
