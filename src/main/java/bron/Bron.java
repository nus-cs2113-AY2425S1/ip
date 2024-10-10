package bron;

import bron.parser.CommandProcessor;
import bron.storage.FileStorage;
import bron.task.TaskList;
import bron.ui.TextUI;

public class Bron {
    private TextUI ui;
    private TaskList tasks;
    private FileStorage storage;

    public static void main(String[] args) {
        FileStorage storage = new FileStorage();
        TaskList taskList = storage.load();

        CommandProcessor commandProcessor = new CommandProcessor(taskList, storage);
        commandProcessor.start();
    }
}
