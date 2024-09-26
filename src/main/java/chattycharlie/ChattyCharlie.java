package chattycharlie;

import chattycharlie.commands.*;
import chattycharlie.userinteractions. *;

public class ChattyCharlie {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public ChattyCharlie(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try{
            taskList = new TaskList(storage.load());
        } catch (CharlieExceptions e) {
            ui.displayError("First Time here, Welcome to a life of productivity!");
            taskList = new TaskList();
        }
    }

    public void run() { //Echo as a function
        ui.displayGreetings();
        Parser parser = new Parser();
        boolean isExit = false;

        while (!isExit) {
            try {
                String line = ui.getUserInput();
                CommandType commandType = parser.getCommand(line);

                Command command = CommandFactory.createCommand(commandType, line);
                command.execute(taskList, ui, storage);

                isExit = command.isExit();

            } catch (CharlieExceptions e) {
                ui.displayError(e.getMessage());
            } catch (IllegalArgumentException e) {
                ui.displayError("Oop, did you make a typo?");
            }
        }
    }

    public static void main (String[]args){
        new ChattyCharlie("data/tasks.txt").run();
    }
}
