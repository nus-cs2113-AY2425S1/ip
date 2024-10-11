package org.ajay;

import java.util.Scanner;
import org.ajay.commands.Command;
import org.ajay.common.Constants;
import org.ajay.data.TaskList;
import org.ajay.data.exceptions.IllegalArgumentException;
import org.ajay.parser.Parser;
import org.ajay.storage.Storage;
import org.ajay.ui.TextUi;

/**
 * Jarvis is a personal assistant chatbot that helps to keep track of various
 * tasks application.
 * Initializes the application and starts the interaction with the user.
 */
public class Jarvis {
    private Storage storage;
    private TextUi ui;
    private TaskList tasks;

    public Jarvis(String filePath) {
        ui = new TextUi();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (IllegalArgumentException e) {
            ui.printExceptions(e.getMessage());

            /** Create empty task list if tasks storage file not found */
            tasks = new TaskList();
        }
    }

    /** Runs the program until termination. */
    public void run() {
        boolean isExit = false;

        ui.printLogo();
        ui.printGreetingMsgs();

        /** Scanner object to read input from the user */
        try (Scanner in = new Scanner(System.in)) {
            while (!isExit) {
                Parser.readInput(in);

                Command c = Parser.parseCommand(Parser.command, Parser.task); // Parse the input
                c.execute(tasks, ui, storage); // Execute the command
                isExit = c.getExitBool(); // Check if the command is an exit command
            }
        }
    }

    public static void main(String[] args) {
        new Jarvis(Constants.FILE_PATH).run();
    }
}
