package bento;

import commands.Command;
import exception.BentoException;
import storage.Storage;
import tasks.TaskList;
import ui.Parser;
import ui.Ui;

public class Bento {
    private Ui ui;
    private Parser parser;
    private TaskList tasks;
    private Storage storage;
    private boolean isExit = false;

    public Bento() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.tasks = new TaskList();
        this.storage = new Storage();
    }


    public void run() {
        ui.sayKonichiwa();
        try {
            storage.loadTaskList(tasks, ui, parser);
        } catch (BentoException e) {
            System.out.print(e.getMessage());
        }

        while (!isExit) {
            try {
                String userInput = ui.getUserInput();
                Command userCommand = parser.getCommand(userInput, true);
                userCommand.execute(tasks, ui, storage);
                isExit = userCommand.isExit();
            } catch (BentoException e) {
                System.out.print(e.getMessage());
            }
        }
    }
}