package org.ajay;

import java.util.Scanner;
import org.ajay.commands.Command;
import org.ajay.common.Constants;
import org.ajay.data.TaskList;
import org.ajay.data.exceptions.EmptyArgumentException;
import org.ajay.data.exceptions.IllegalArgumentException;
import org.ajay.parser.Parser;
import org.ajay.storage.Storage;
import org.ajay.ui.TextUi;

public class Jarvis {

    private Storage storage;
    private TextUi ui;
    private TaskList tasks;

    public Jarvis(String filePath) {
        ui = new TextUi();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (EmptyArgumentException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO: Update me
            e.printStackTrace();
        }
    }

    public void run() {
        boolean isExit = false;

        ui.printLogo();
        ui.printGreetingMsgs();
        Scanner in = new Scanner(System.in); // Scanner object to read input from the user
        while (!isExit) {

            Parser.readInput(in); // Read the input from the user

            Command c = Parser.parseCommand(Parser.command, Parser.task); // Parse the input
            c.execute(tasks, ui, storage); // Execute the command
            isExit = c.getExitBool(); // Check if the command is an exit command

        }
        in.close();

    }

    public static void main(String[] args) {
        new Jarvis(Constants.FILE_PATH).run();
    }
}
