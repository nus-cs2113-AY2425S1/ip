package nell.ui;

import nell.command.Command;
import nell.common.Messages;
import nell.parser.Parser;
import nell.storage.Storage;

import java.util.Scanner;

/**
 * Represents an object that takes user input and prints messages in Nell.
 */
public class Ui {
    private static final String BYE_COMMAND = "bye";

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

    /**
     * Says bye to the user
     */
    public void sayBye() {
        System.out.println(Messages.BYE_MESSAGE);
    }

    /**
     * Greets the user upon program startup
     */
    public void greetUser() {
        System.out.println(Messages.HELLO_MESSAGE);
        System.out.println(Messages.PROMPT_MESSAGE);
    }

    /**
     * Gets commands from user, then parses and executes commands received
     */
    public void getCommands() {
        Scanner input = new Scanner(System.in);

        boolean isGettingCommands = true;

        while (isGettingCommands) {
            String rawCommand = input.nextLine();
            if (rawCommand.equals(BYE_COMMAND)) {
                isGettingCommands = false;
            } else {
                Command parsedCommand = parser.parseCommand(rawCommand);
                parsedCommand.execute();
            }
            dataStorage.saveToFile();
        }
    }
}
