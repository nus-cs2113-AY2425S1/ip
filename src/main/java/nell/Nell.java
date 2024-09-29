package nell;

import nell.list.TaskList;
import nell.parser.Parser;
import nell.storage.Storage;
import nell.ui.Ui;

/**
 * Represents the main program for Nell
 */
public class Nell {
    private TaskList tasks;
    private Storage dataStorage;
    private Parser parser;
    private Ui ui;

    /**
     * Constructs a new Nell object
     */
    public Nell() {
        this.tasks = new TaskList();
        this.dataStorage = new Storage("./data/data.txt", tasks);
        this.parser = new Parser(tasks);
        this.ui = new Ui(parser, dataStorage);
    }

    /**
     * Runs the Nell program
     */
    public void run() {
        dataStorage.loadFromFile();
        ui.greetUser();
        ui.getCommands();
        ui.sayBye();
    }

    public static void main(String[] args) {
        Nell nell = new Nell();
        nell.run();
    }
}
