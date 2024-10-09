package bron.parser;

import bron.command.Command;
import bron.command.CommandHandler;
import bron.storage.FileStorage;
import bron.task.Task;
import bron.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class CommandProcessor {

    private CommandHandler commandHandler;
    private Scanner input;
    private FileStorage storage;

    public CommandProcessor(TaskList taskList, FileStorage storage) {
        this.commandHandler = new CommandHandler(taskList, storage);
        this.input = new Scanner(System.in);
        this.storage = storage;
    }

    public void start() {
        boolean isReadingInput = true;

        while (isReadingInput) {
            String line = input.nextLine().trim();
            String[] parts = line.split(" ");
            String commandStr = parts[0].toLowerCase();

            Command command = parseCommand(commandStr);

            commandHandler.handleCommand(command, line);
            if (command == Command.BYE) {
                isReadingInput = false;
            }
        }
    }

    private Command parseCommand(String commandStr) {
        try {
            return Command.valueOf(commandStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
    }
}