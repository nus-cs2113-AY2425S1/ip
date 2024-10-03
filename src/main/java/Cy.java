import Parser.Parser;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;
import exceptions.*;

import java.util.Scanner;

public class Cy {

    private final Parser parser;
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    public Cy() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
        taskList = new TaskList();
    }

    public static void main(String[] args) throws IllegalCommandException, IllegalEmptyException, IllegalTaskException, IllegalKeywordException, IllegalIndexException {
        new Cy().run();
    }

    /**
     * This method runs the application.
     * It would first load existing data from the txt file.
     * Next, it would scan and handle commands until the "bye" command is entered,
     * which would exit this method.
     */
    public void run() throws IllegalCommandException, IllegalEmptyException, IllegalTaskException, IllegalKeywordException, IllegalIndexException {

        Ui.printWelcomeMessage();

        storage.loadFileData();

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        while (!input.equalsIgnoreCase("bye")) {
            String[] splitInputs = input.split(" ");
            String command = splitInputs[0].toLowerCase();

            try {
                parser.handleCommand(input, command, splitInputs);
            } catch (IllegalEmptyException | IllegalCommandException | IllegalTaskException | IllegalKeywordException |
                     IllegalIndexException e) {
                System.out.println("Error: " + e.getMessage());
            }

            input = scan.nextLine();
        }

        Ui.printEndingMessage();
        scan.close();
    }
}