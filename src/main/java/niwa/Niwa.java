package niwa;

import niwa.command.*;
import niwa.data.Storage;
import niwa.data.task.TaskList;
import niwa.exception.NiwaInvalidArgumentException;
import niwa.exception.NiwaInvalidSyntaxException;
import niwa.exception.NiwaTaskIndexOutOfBoundException;
import niwa.data.task.Task;
import niwa.parser.CommandParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * niwa.Niwa chatbot class that processes user commands to manage a task list.
 */
public class Niwa {

    // Static variables to hold the chatbot's name and logo
    static final String NAME = "Niwa";
    static final String LOGO = "\t _   _\n"
            + "\t| \\ | | (_)  _       _  ___\n"
            + "\t|  \\| | | | | | __  // //| |\n"
            + "\t| |\\  | | | | |/  |// //_| |\n"
            + "\t|_| \\_|_|_| |__/|__/ //  |_|";

    // Static variables to format messages
    private static final String PREFIX = "\t";
    private static final String SEPARATOR = PREFIX + "---------------------------------------------";

    private static final String HI_MESSAGE = PREFIX + "Hello sweeties! I'm %s!\n"
            + PREFIX + "What can I do for you? Let's chat <3\n"
            + PREFIX + "---> Type 'help' to see the guide.\n\n";

    private static final String HOME = System.getProperty("user.home");

    // inserts correct file path separator on *nix and Windows
    // works on *nix
    // works on Windows
    private static final java.nio.file.Path PATH = java.nio.file.Paths.get(HOME, "Niwa", "data", "NiwaTaskList.txt");

    /** Variable to check if the chatbot is running */
    private boolean isRunning;

    private CommandParser parser;
    private Storage storage;
    /**
     * Getter for the chatbot's name.
     *
     * @return The name of the chatbot.
     */
    public static String getName() {
        return NAME;
    }

    /**
     * Getter for the chatbot's file path.
     *
     * @return The file path of the chatbot.
     */
    public static String getOutputFilePath() {
        return PATH.toString();
    }

    /**
     * Getter for the chatbot's logo.
     *
     * @return The logo of the chatbot.
     */
    public static String getLogo() {
        return LOGO;
    }

    /**
     * Getter for the isRunning flag.
     *
     * @return True if the chatbot is running, false otherwise.
     */
    public boolean getRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    /**
     * Constructor initializes the chatbot and displays a greeting and user guide.
     */
    public Niwa() {
        isRunning = true;
        printGreet(NAME, LOGO);
        storage = new Storage(getOutputFilePath());
        parser = new CommandParser();
        try {
            parser.registerCommands(new ByeCommand(this));
            parser.registerCommands(new EchoCommand());

            parser.registerCommands(new DeadlineCommand());
            parser.registerCommands(new TodoCommand());
            parser.registerCommands(new EventCommand());

            parser.registerCommands(new ListCommand());

            parser.registerCommands(new DeleteCommand());
            parser.registerCommands(new ClearCommand());
            parser.registerCommands(new MarkCommand());
            parser.registerCommands(new UnmarkCommand());


            parser.registerCommands(new SaveCommand());
            parser.registerCommands(new ReadCommand());

            HelpCommand helpCommand = new HelpCommand();
            parser.registerCommands(helpCommand);

            helpCommand.setCommands(new ArrayList<Command>(parser.getCommands().values()));

            System.out.println(PREFIX + "---> Reading from default data file...");

            ReadCommand readCommand = new ReadCommand();
            parser.registerCommands(readCommand);
            processCommand(ReadCommand.COMMAND_WORD+ " "+ getOutputFilePath());

        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Processes the command entered by the user.
     *
     * @param commandString The user command to process.
     */
    public void processCommand(String commandString) {
        System.out.println(SEPARATOR);
        Command command = null;
        try {
            command = parser.parseCommand(commandString);
            command.execute();
        } catch (NoSuchFieldException | IllegalAccessException | NiwaInvalidSyntaxException |
                 NiwaInvalidArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(SEPARATOR);
        }

    }

    /**
     * Prints a greeting message when the chatbot starts.
     *
     * @param name The name of the chatbot.
     * @param logo The logo of the chatbot.
     */
    public void printGreet(String name, String logo) {
        System.out.println(logo);
        System.out.println(SEPARATOR);
        System.out.printf(HI_MESSAGE, name);
    }
}
