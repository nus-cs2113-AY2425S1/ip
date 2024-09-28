package king;

import king.command.Command;
import king.command.DeleteExitCommand;
import king.command.ExitCommand;

import java.io.IOException;

/**
 * The main class for the chatbot, King.
 * Handles the interaction with the user with a loop.
 * Stops when the user chooses to exit the chatbot.
 */
public class King {

    /**
     * The entry point of King. Initializes the task list, greets the user,
     * and enters a loop to process user commands until an exit command is given.
     *
     * @throws KingException If there is an issue with the task management system.
     * @throws IOException   If an I/O error occurs during command execution or file storage access.
     */
    public static void main(String[] args) throws KingException, IOException {
        TaskList taskList = new TaskList();
        Ui.toGreet(taskList);

        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = Ui.getUserInput();

                Command command = Parser.parse(userInput);

                command.execute(taskList, new Ui(), new Storage());

                if (command instanceof ExitCommand || command instanceof DeleteExitCommand) {
                    isExit = true;
                }

            } catch (KingException e) {
                Ui.showError(e.getMessage());
            }
        }
    }
}
