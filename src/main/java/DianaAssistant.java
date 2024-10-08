import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The main entry point for the Diana task management assistant.
 *
 * This class handles the primary interaction with the user, including:
 * - Printing welcome and goodbye messages
 * - Reading user input
 * - Parsing and executing user commands
 * - Managing task list persistence
 */
public class DianaAssistant {
    private TaskList tasklist;
    private Ui ui;
    private Parser parser;

    public DianaAssistant() throws IOException {
        ui = new Ui();
        tasklist =  new TaskList();

        try {
            tasklist = Storage.loadTasks();
            parser = new Parser(tasklist);
        } catch (IOException e) {
            // ensures that tasks come with empty list if fail
            ui.showLoadingError();
        }
    }

    public void interact() {
        ui.printWelcomeMessage();

        String input;
        while (true) {
            input = ui.readInput();
            if ("bye".equals(input)) {
                try {
                    Storage.saveTasks(tasklist);
                } catch (IOException e) {
                    System.out.println("Saving tasks failed.");
                }
                break;
            }
            parser.determineCommand(input);
        }
        ui.printGoodByeMessage();
        ui.closeScanner();
        }
    }
