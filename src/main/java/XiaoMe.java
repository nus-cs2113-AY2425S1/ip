import commands.Command;
import exceptions.XiaoMeException;
import parser.Parser;
import storage.Storage;
import task.Task;

import java.util.ArrayList;
import ui.UI;


public class XiaoMe {
    ArrayList<Task> tasks;
    private UI ui;
    Storage storage;

    public static void main(String[] args) throws XiaoMeException {
        new XiaoMe().run();
    }

    public XiaoMe() throws XiaoMeException {
        ui = new UI();
        tasks = new ArrayList<>();
        storage = new Storage();
        try {
            tasks = storage.readFile();
        } catch (XiaoMeException e) {
            ui.printToUser(e.getError());
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                Command c = Parser.parseCommand(userInput);
                ui.printToUser(c.execute(tasks));
                isExit = c.isExit();
            } catch (XiaoMeException e) {
                ui.printToUser(e.getError());
            }
        }
    }
}
