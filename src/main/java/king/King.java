package king;

import king.command.Command;
import king.command.DeleteExitCommand;
import king.command.ExitCommand;

import java.io.IOException;

public class King {

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
