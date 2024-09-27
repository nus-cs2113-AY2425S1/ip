package esme.command;

import esme.storage.Storage;
import esme.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * The command manager is responsible for interpreting and executing user commands.
 * The command manager is also responsible for loading and saving the task list to
 * and from the external storage.
 */
public class CommandManager {
    private Storage storage;
    private Ui ui;

    /**
     * Constructor that takes a Ui object and a Storage object.
     *
     * @param ui The user interface object.
     * @param storage The storage object.
     */
    public CommandManager(Ui ui, Storage storage) {
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Loads the content of the storage file and executes the commands in it.
     *
     * @throws FileNotFoundException If the storage file does not exist.
     */
    public void loadContent() throws FileNotFoundException {
        ArrayList<String> content = storage.loadFileContents();
        for (String text : content) {
            String[] parts = text.split(" /c ");
            System.out.println(parts[0]);
            System.out.println(parts[1]);
            handleCommand(parts[0],parts[1].equals("true"));
        }
    }

    /**
     * Returns true if the loadContent() method has been successful. Otherwise, returns false.
     *
     * @return True if the loadContent() method has been successful, false otherwise.
     */
    public boolean hasLoadSuccessful() {
        boolean isSuccessful = true;
        // Save the current System.out
        PrintStream originalOut = System.out;

        // Suppress output
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        try {
            loadContent();
        } catch (FileNotFoundException e) {
            isSuccessful = false;
        }
        System.setOut(originalOut);
        return isSuccessful;
    }

    /**
     * Saves the task list to the storage file.
     *
     * @throws IOException If the storage file cannot be written to.
     */
    public void saveContent() throws IOException {
        storage.writeToFile(ui.getFormattedTasks());
    }

    /**
     * Returns true if the saveContent() method has been successful. Otherwise, returns false.
     *
     * @return True if the saveContent() method has been successful, false otherwise.
     */
    public boolean hasSaveSuccessful() {
        boolean isSuccessful = true;
        try {
            saveContent();
        } catch (IOException e) {
            isSuccessful = false;
            ui.printSaveErrorMessage();
        }
        return isSuccessful;
    }

    /**
     * Handles task completion. If the task is completed, it will mark the task in the task list.
     *
     * @param isTaskCompleted True if the task is completed, false otherwise.
     */
    public void handleTaskCompletion(boolean isTaskCompleted) {
        if (isTaskCompleted) {
            ui.markTaskInList(ui.getNumberOfTasks());
        }
    }

    /**
     * Handles a user command. If the command is invalid, it will print an error message.
     *
     * @param line The user input to be processed.
     * @return True if the command has been executed successfully, false otherwise.
     */
    public boolean handleCommand(String line) {
        if (line.isEmpty()) {
            ui.promptEmptyInput();
            return false;
        }
        return this.handleCommand(line,false);
    }

    /**
     * Handles a user command. If the command is invalid, it will print an error message.
     *
     * @param line The command to be executed.
     * @param isTaskCompleted True if the task is completed, false otherwise.
     * @return True if the command has been executed successfully, false otherwise.
     */
    public boolean handleCommand(String line, boolean isTaskCompleted) {
        boolean toExit = false;
        String[] words = line.split(" ");
        switch (words[0].toLowerCase()) {
        case "bye":
            new ExitCommand(ui).run();
            toExit = hasSaveSuccessful();
            break;
        case "todo":
        case "deadline":
        case "event":
            new EntryCommand(ui,words[0].toLowerCase(),line).run();
            handleTaskCompletion(isTaskCompleted);
            break;
        case "mark":
        case "unmark":
        case "delete":
            new ActionCommand(ui,words).run();
            break;
        case "list":
        case "find":
        case "task":
            new PrintCommand(ui,words,line).run();
            break;
        case "help":
            new HelpCommand(ui).run();
            break;
        default:
            new UnknownCommand(ui).run();
            break;
        }
        return toExit;
    }
}
