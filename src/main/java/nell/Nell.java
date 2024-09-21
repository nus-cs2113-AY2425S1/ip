package nell;

import nell.common.Messages;
import nell.parser.Parser;
import nell.storage.Storage;
import nell.tasks.Deadline;
import nell.tasks.Event;
import nell.tasks.ToDo;
import nell.ui.Ui;

import java.util.Scanner;

public class Nell {
    private static TaskList tasks = new TaskList();
    private static Storage dataStorage = new Storage("./data/data.txt", tasks);
    private static Parser parser = new Parser(tasks);
    private static Ui ui = new Ui(parser, dataStorage);

    public static void main(String[] args) {
        dataStorage.loadFromFile();
        ui.greetUser();
        ui.getCommands();
        ui.sayBye();
    }
}