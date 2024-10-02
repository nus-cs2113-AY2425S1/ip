import java.io.IOException;
import java.time.DateTimeException;
import java.util.ArrayList;

import commands.Command;
import exceptions.IllegalCommandException;
import parser.Parser;
import storage.TaskDecoder;
import storage.TaskEncoder;
import tasks.TaskList;
import ui.Ui;


public class Nateh {
    private static Ui ui;
    private static TaskList taskList;
    public Nateh() {
        TaskEncoder.createFile();
        this.ui = new Ui();
        try {
            taskList = TaskDecoder.readTasks();
        } catch (IOException e) {
            taskList = new TaskList(new ArrayList<>());
        }
    }
    public void run() {
        ui.printWelcomeMessage();
        String input = "";
        while (!input.equals("bye")) {
            try {
                input = ui.receiveCommand();
                Command command = Parser.parse(input);
                command.execute(taskList, ui);
            } catch (IllegalCommandException e) {
                ui.printInvalidCommandError();
            } catch (DateTimeException e) {
                ui.printDateError();
            }
        }
    }
    public static void main(String[] args) {
        new Nateh().run();
    }
}
