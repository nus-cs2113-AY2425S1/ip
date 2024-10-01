import Parser.Parser;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;
import exceptions.*;

import java.util.Scanner;

public class Cy {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    public Cy() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage();
        tasks = new TaskList();
    }


    public void run() throws IllegalCommandException, IllegalEmptyException,
            IllegalTaskException, IllegalKeywordException, IllegalIndexException {

        Ui.printWelcomeMessage();

        Storage.loadExistingData();

        int count = 0;
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        while (!input.equalsIgnoreCase("bye")) {
            String[] splitInputs = input.split(" ");
            String command = splitInputs[0].toLowerCase();
            count = parser.handleCommand(input, count, command, splitInputs);
            input = scan.nextLine();
        }

        Ui.printEndingMessage();
        scan.close();
    }

    public static void main(String[] args) throws IllegalCommandException, IllegalEmptyException,
            IllegalTaskException, IllegalKeywordException,IllegalIndexException {
        new Cy().run();
    }
}