package erika;

import erika.command.Command;
import erika.console.Console;
import erika.exception.ErikaException;
import erika.exception.OutOfBoundsException;
import erika.filesystem.FileSystem;
import erika.parser.Parser;
import erika.settings.Settings;
import erika.ui.Ui;
import erika.tasklist.TaskList;

import java.io.IOException;
import java.util.NoSuchElementException;

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
            try {
                Command c = parser.parseInput(line);
                c.execute(taskList, fileSystem);
                isExit = c.isExit();
            } catch (ErikaException e) {
                Console.printMessage(e.getMessage());
            } catch (IOException e) {
                Console.printMessage("Error: IO Exception while writing to filesystem");
            } catch (NoSuchElementException e) {
                Console.printMessage("Error: No line to parse");
            }
        }
    }
    public static void main(String[] args) {
        new Erika().run();
    }
}
