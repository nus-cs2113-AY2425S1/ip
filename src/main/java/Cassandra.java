import CassHelpers.commands.Command;
import CassHelpers.exceptions.*;
import CassHelpers.types.Task;
import CassHelpers.util.Parser;
import CassHelpers.util.Storage;
import CassHelpers.util.TaskList;
import CassHelpers.util.UI;

import java.util.ArrayList;
import java.util.Scanner;

import static CassHelpers.util.UI.*;

public class Cassandra {

    private UI ui;
    private Storage storage;
    private TaskList tasks;

    public Cassandra(){
        ui = new UI();
        storage = new Storage("./data","tasks.txt");
        try{
            tasks = new TaskList(storage.readTaskFromFile(),storage);
        } catch (CassandraException e) {
            ui.showError(e);
            ui.drawLine();
            tasks = new TaskList(storage);
        }
    }

    public void run() {
        ui.displayIntroduction();
        while(tasks.getRunningState()) {
            String userInput = Parser.getUserInput();
            String commandArgs[] = Parser.getCommandArgsFromUserInput(userInput);
            ui.drawLine();
            try {
                Command c = Parser.parse(userInput, commandArgs, ui, tasks);
                c.execute();
            }catch (TaskNotFoundException | NoTaskIndexFoundException | TaskAlreadyMarkedException |
                    NoCommandException | TaskAlreadyUnmarkedException | InvalidEventFormatException |
                    InvalidDeadlineFormatException | InvalidCommandException e) {
                ui.showError(e);
            }
            ui.drawLine();
        }
    }

    public static void main(String[] args){
        new Cassandra().run();
    }
}
