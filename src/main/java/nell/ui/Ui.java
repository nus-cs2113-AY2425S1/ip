package nell.ui;

import nell.parser.Parser;

/**
 * Represents an object that takes user input and prints messages in Nell.
 */
public class Ui {
    private Parser parser;

    /**
     * Constructs a new Ui object with a given parser
     *
     * @param parser The parser object for the Ui object
     */
    public Ui(Parser parser) {
        this.parser = parser;
    }
}
