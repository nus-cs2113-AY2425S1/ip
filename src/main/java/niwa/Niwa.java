package niwa;

import niwa.command.*;
import niwa.exception.NiwaInvalidArgumentException;
import niwa.exception.NiwaInvalidSyntaxException;
import niwa.exception.NiwaTaskIndexOutOfBoundException;
import niwa.task.Task;

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

    // Static variables for error messages
    private static final String ERR_INDEX_NUMBER_FORMAT = "Task's index must be a number!";
    private static final String OUTPUT_FILE_PATH = "./data/NiwaTaskList.txt";
    /** Variable to check if the chatbot is running */
    private boolean isRunning;

    /** List to store tasks */
    private List<Task> tasks = new ArrayList<>();

    /** Map command with its word **/
    private Map<String, Command> commands = new HashMap<>();

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
        return OUTPUT_FILE_PATH;
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

        registerCommands(new ByeCommand(this));
        registerCommands(new EchoCommand());

        registerCommands(new DeadlineCommand(tasks));
        registerCommands(new TodoCommand(tasks));
        registerCommands(new EventCommand(tasks));

        registerCommands(new ListCommand(tasks));

        registerCommands(new DeleteCommand(tasks));
        registerCommands(new ClearCommand(tasks));
        registerCommands(new MarkCommand(tasks));
        registerCommands(new UnmarkCommand(tasks));

        registerCommands(new SaveCommand(tasks));

        ReadCommand readCommand = new ReadCommand(tasks);
        registerCommands(readCommand);

        HelpCommand helpCommand = new HelpCommand();
        registerCommands(helpCommand);
        helpCommand.setCommands(new ArrayList<>(commands.values()));

        System.out.println(PREFIX + "---> Reading from default data file...");
        processCommand(readCommand.getWord() + " " + OUTPUT_FILE_PATH);
    }

    public void registerCommands(Command command) {
        commands.put(command.getWord(), command);
    }

    /**
     * Processes the command entered by the user.
     *
     * @param commandString The user command to process.
     */
    public void processCommand(String commandString) {
        System.out.println(SEPARATOR);
        String[] commandParts = commandString.split(" ", 2);

        try {
            Command command = commands.get(commandParts[0]);
            if (command != null) {
                if (commandParts.length == 2) { command.execute(commandParts[1]); }
                else { command.execute(""); }
            }
            else {
                throw new NiwaInvalidSyntaxException();
            }
        } catch (NiwaInvalidSyntaxException | NiwaInvalidArgumentException
                 | NiwaTaskIndexOutOfBoundException e) {
            System.out.println(PREFIX + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(PREFIX + ERR_INDEX_NUMBER_FORMAT);
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
