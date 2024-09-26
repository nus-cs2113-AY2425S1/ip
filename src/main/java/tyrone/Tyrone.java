package tyrone;

import tyrone.command.Parser;
import tyrone.storage.Storage;

public class Tyrone {

    private static void runUntilExitCommand() {
        while (true) {
            String input = Ui.receiveInput();
            if (Parser.isExitCommand(input)) {
                break;
            }
            Parser.handleInput(input);
        }
    }

    public static void main(String[] args) {
        Storage.createSaveFile();
        Storage.initTaskListFromSaveFile();

        Ui.printIntroMessage();
        runUntilExitCommand();
        Ui.printExitMessage();
    }
}
