package tyrone;

import tyrone.command.Parser;
import tyrone.storage.Storage;

public class Tyrone {

    /**
     * Main loop of the chatbot.
     * Keeps taking in input from user and passing to Parser
     * until chatbot receives exit command, upon which loop
     * terminates.
     */
    private static void runUntilExitCommand() {
        while (true) {
            String input = Ui.receiveInput();
            if (Parser.isExitCommand(input)) {
                break;
            }
            Parser.handleInput(input);
        }
    }

    /**
     * Main method of the chatbot
     *
     * @param args
     */
    public static void main(String[] args) {
        Storage.createSaveFile();
        Storage.initTaskListFromSaveFile();

        Ui.printIntroMessage();
        runUntilExitCommand();
        Ui.printExitMessage();
    }
}
