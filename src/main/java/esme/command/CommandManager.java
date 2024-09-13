package esme.command;

import esme.Storage.Storage;
import esme.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class CommandManager {
    private Storage storage;
    private Ui ui;

    public CommandManager(Ui ui, Storage storage) {
        this.storage = storage;
        this.ui = ui;
    }

    public boolean hasLoadSuccessful() {
        return loadContent();
    }

    public boolean loadContent() {
        boolean isSuccessful = true;
        // Save the current System.out
        PrintStream originalOut = System.out;

        // Suppress output
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        try {
            ArrayList<String> content = storage.loadFileContents();
            for (String text : content) {
                String[] parts = text.split(" /c ");
                System.out.println(parts[0]);
                System.out.println(parts[1]);
                handleCommand(parts[0],parts[1].equals("true"));
            }
        } catch (FileNotFoundException e) {
            isSuccessful = false;
        }
        System.setOut(originalOut);
        return isSuccessful;
    }

    public boolean handleCommand(String line) {
        return this.handleCommand(line,false);
    }

    public boolean saveContent() {
        boolean isSuccessful = true;
        try {
            storage.writeToFile(ui.getFormattedTasks());
        } catch (IOException e) {
            isSuccessful = false;
            ui.printSaveErrorMessage();
        }
        return isSuccessful;
    }

    public boolean handleCommand(String line, boolean isTaskCompleted) {
        boolean toExit = false;
        String[] words = line.split(" ");
        switch (words[0]) {
        case "bye":
            new ExitCommand(ui).run();
            toExit = saveContent();
            break;
        case "todo":
        case "deadline":
        case "event":
            new EntryCommand(ui,words[0],line).run();
            if (isTaskCompleted) {
                ui.markTaskInList(ui.getNumberOfTasks());
            }
            break;
        case "mark":
        case "unmark":
            new ActionCommand(ui,words).run();
            break;
        case "list":
            new PrintCommand(ui).run();
            break;
        default:
            new UnknownCommand(ui).run();
            break;
        }
        return toExit;
    }
}
