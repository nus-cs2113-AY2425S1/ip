package niwa;

import niwa.command.*;
import niwa.data.Storage;
import niwa.data.task.TaskList;
import niwa.exception.NiwaException;
import niwa.exception.NiwaInvalidArgumentException;
import niwa.exception.NiwaInvalidSyntaxException;
import niwa.exception.NiwaTaskIndexOutOfBoundException;
import niwa.data.task.Task;
import niwa.messages.NiwaMesssages;
import niwa.parser.CommandParser;
import niwa.ui.NiwaUI;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    static final String LOGO = " _   _\n"
            + "\t| \\ | | (_)  _       _  ___\n"
            + "\t|  \\| | | | | | __  // //| |\n"
            + "\t| |\\  | | | | |/  |// //_| |\n"
            + "\t|_| \\_|_|_| |__/|__/ //  |_|";

    private static final String HOME = System.getProperty("user.home");

    // inserts correct file path separator on *nix and Windows
    // works on *nix
    // works on Windows
    private static final Path PATH = Paths.get(HOME, "Niwa", "data", "NiwaTaskList.txt");

    /** Variable to check if the chatbot is running */
    private boolean isRunning;

    private CommandParser parser;
    private NiwaUI ui;

    /**
     * Getter for the chatbot's file path.
     *
     * @return The file path of the chatbot.
     */
    public static String getOutputFilePath() {
        return PATH.toString();
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    /** Runs the program until termination.  */
    public void run() {
        start();
        runCommandLoop();
    }

    public void start() {
        ui.printMessages(LOGO,
                String.format(NiwaMesssages.HI_MESSAGE, NAME),
                NiwaMesssages.HI_MESSAGE_1,
                NiwaMesssages.HI_MESSAGE_2,
                NiwaMesssages.MESSAGE_READ_DEFAULT);

        CommandResult readResult = processCommand(ReadCommand.COMMAND_WORD + " " + getOutputFilePath());
        ui.showCommandResult(readResult);
    }

    private void runCommandLoop() {
        while (isRunning) {
            String commandString = ui.getUserCommand();
            CommandResult result = processCommand(commandString);
            ui.showCommandResult(result);
        }
    }
    /**
     * Constructor initializes the chatbot and displays a greeting and user guide.
     */
    public Niwa() {
        isRunning = true;

        parser = new CommandParser();
        ui = new NiwaUI();

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

        helpCommand.setCommands(new ArrayList<Command>(parser.getCommands().values()));
    }

    /**
     * Processes the command entered by the user.
     *
     * @param commandString The user command to process.
     */
    public CommandResult processCommand(String commandString) {
        Command command;
        CommandResult result = null;
        try {
            command = parser.parseCommand(commandString);
            result = command.execute();
        } catch (NiwaException e) {
            result = new CommandResult(e.getMessage());
        }
        return result;
    }
}
