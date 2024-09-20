package erika;

import erika.command.Command;
import erika.console.Console;
import erika.filesystem.FileSystem;
import erika.parser.Parser;
import erika.settings.Settings;
import erika.ui.Ui;
import erika.tasklist.TaskList;

public class Erika {
    private static Ui ui;
    private static Parser parser;
    private FileSystem fileSystem;
    private TaskList taskList;

    public Erika() {
        ui = new Ui();
        parser = new Parser();
        fileSystem = new FileSystem(Settings.FILENAME, Settings.SEPARATOR);
        taskList = new TaskList(fileSystem.initializeFileSystem());
    }

    public void run() {
        Console.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            String line = ui.collectUserInput();
            Command c = parser.parseInput(line);
            c.execute(taskList, ui, fileSystem);
            isExit = c.isExit();
        }
    }
    public static void main(String[] args) {
        new Erika().run();
    }
}
