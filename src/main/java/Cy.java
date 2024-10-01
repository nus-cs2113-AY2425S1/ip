import Parser.Parser;
import Storage.Storage;
import Ui.Ui;
import exceptions.IllegalIndexException;
import exceptions.IllegalTaskException;
import exceptions.IllegalEmptyException;
import exceptions.IllegalKeywordException;
import exceptions.IllegalCommandException;

import java.util.Scanner;

public class Cy {

    private final Parser parser;

    public Cy() {
        parser = new Parser();
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