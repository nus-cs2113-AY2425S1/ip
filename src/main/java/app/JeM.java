package app;

import Commands.Command;
import taskmanager.Storage;
import exceptions.InvalidCommandException;

public class JeM {

    private Ui ui;
    private Storage storage;
    private Parser parser;

    public JeM(){
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
    }

    public void run() {
        boolean isRunning = true;
        ui.displayWelcomeMessage();

        while (isRunning) {
            String userInput = ui.readInput();
            if (userInput.equalsIgnoreCase("bye")) {
                ui.exitChatBot();
                isRunning = false;
            }
            try {
                Command command = parser.parse(userInput, storage);
                command.execute(storage);
            }catch(InvalidCommandException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new JeM().run();
    }
}