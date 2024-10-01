package niwa;

import niwa.command.*;
import niwa.exception.NiwaException;
import niwa.messages.NiwaMesssages;
import niwa.parser.CommandParser;
import niwa.ui.NiwaUI;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * {@code Niwa} chatbot processes user commands to manage a task list.
 * This class is responsible for initializing the chatbot, handling user commands,
 * and managing the chatbot's lifecycle.
 */
public class Niwa {

    // Static variables to hold the chatbot's name and logo
    static final String NAME = "Niwa";
    static final String LOGO = " _   _\n"
            + "\t| \\ | | (_)  _       _  ___\n"
            + "\t|  \\| | | | | | __  // //| |\n"
            + "\t| |\\  | | | | |/  |// //_| |\n"
            + "\t|_| \\_|_|_| |__/|__/ //  |_|";

    private static final String HOME = System.getProperty("user.home");

    // Inserts correct file path separator on *nix and Windows
    private static final Path PATH = Paths.get(HOME, "Niwa", "data", "NiwaTaskList.txt");

    /** Variable to check if the chatbot is running */
    private boolean isRunning;

    /** Command parser to handle user commands */
    private CommandParser parser;

    /** User interface for interacting with the chatbot */
    private NiwaUI ui;

    /**
     * Constructor initializes the chatbot and registers commands.
     * It sets up the command parser and text UI,
     * and registers available commands for use.
     */
    public Niwa() {
        isRunning = true;

        parser = new CommandParser();
        ui = new NiwaUI();

        // Registering commands
        parser.registerCommands(new ByeCommand(this));
        parser.registerCommands(new ListCommand());
        parser.registerCommands(new ClearCommand());
        parser.registerCommands(new EchoCommand());
        parser.registerCommands(new DeadlineCommand());
        parser.registerCommands(new TodoCommand());
        parser.registerCommands(new EventCommand());
        parser.registerCommands(new DeleteCommand());
        parser.registerCommands(new FindCommand());
        parser.registerCommands(new MarkCommand());
        parser.registerCommands(new UnmarkCommand());
        parser.registerCommands(new SaveCommand());
        parser.registerCommands(new ReadCommand());

        HelpCommand helpCommand = new HelpCommand();
        parser.registerCommands(helpCommand);

        // Set commands for the help command
        helpCommand.setCommands(new ArrayList<Command>(parser.getCommands().values()));
    }

    /**
     * Getter for the chatbot's default file path.
     *
     * @return The default file path of the chatbot as a string.
     */
    public static String getOutputFilePath() {
        return PATH.toString();
    }

    /**
     * Setter for the chatbot's running state.
     *
     * @param isRunning A boolean showing if the chatbot should continue running.
     */
    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    /**
     * Starts the chatbot and enters the command processing loop.
     */
    public void run() {
        start();
        runCommandLoop();
    }

    /**
     * Starts the chatbot by printing the logo and greeting messages,
     * and reads the default task list from the default file path.
     */
    public void start() {
        ui.printMessages(LOGO,
                String.format(NiwaMesssages.HI_MESSAGE, NAME),
                NiwaMesssages.HI_MESSAGE_1,
                NiwaMesssages.HI_MESSAGE_2,
                NiwaMesssages.MESSAGE_READ_DEFAULT);

        CommandResult readResult = processCommand(ReadCommand.COMMAND_WORD + " " + getOutputFilePath());
        ui.showCommandResult(readResult);
    }

    /**
     * Main command processing loop that retrieves user commands, processes, and displays the results.
     * The loop continues until the chatbot is stopped.
     */
    private void runCommandLoop() {
        while (isRunning) {
            String commandString = ui.getUserCommand();
            CommandResult result = processCommand(commandString);
            ui.showCommandResult(result);
        }
    }

    /**
     * Processes the command entered by the user.
     *
     * @param commandString The user command to process as a string.
     * @return The {@code CommandResult} of executing the command.
     */
    public CommandResult processCommand(String commandString) {
        Command command;
        CommandResult result;
        try {
            command = parser.parseCommand(commandString);
            result = command.execute();
        } catch (NiwaException e) {
            result = new CommandResult(e.getMessage());
        }
        return result;
    }
}
