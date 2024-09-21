package nell.ui;

import nell.parser.Parser;
import nell.storage.Storage;

import java.util.Scanner;

/**
 * Represents an object that takes user input and prints messages in Nell.
 */
public class Ui {
    private Parser parser;
    private Storage dataStorage;

    /**
     * Constructs a new Ui object with a given parser
     *
     * @param parser The parser object for the Ui object
     */
    public Ui(Parser parser, Storage dataStorage) {
        this.parser = parser;
        this.dataStorage = dataStorage;
    }
    }
}
