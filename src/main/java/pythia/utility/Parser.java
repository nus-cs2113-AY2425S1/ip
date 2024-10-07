package pythia.utility;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import pythia.exceptions.PythiaException;
import pythia.Pythia;

/**
 * The {@code Parser} class is responsible for parsing and executing
 * user commands in the Pythia application.
 * It validates commands, extracts necessary arguments, and invokes
 * corresponding actions.
 */
public class Parser {
    private String commandType = null;
    private ArrayList<String> argumentList = null;
    private String parsingErrorMessage = "Parsing of add command unsuccessful.";

    /**
     * Constructs a {@code Parser} object.
     */
    public Parser() {
    }

    /**
     * Parses the command type from the given raw text.
     * The command type is identified as the first word before any spaces.
     *
     * @param rawText the whole, unaltered command input from the user
     * @return the command type as a string
     */
    private String parseCommandType(String rawText) {
        int spaceIndex = rawText.indexOf(' ');

        String firstPart;
        if (spaceIndex != -1) {
            firstPart = rawText.substring(0, spaceIndex);
        } else {
            firstPart = rawText;
        }
        return firstPart;
    }

    /**
     * Handles parsing for the "bye" command.
     * As no arguments are required for this command, this method is intentionally left empty.
     *
     * @param rawText the whole, unaltered command input from the user
     */
    private void parseBye(String rawText) {
    }

    /**
     * Handles parsing for the "list" command.
     * As no arguments are required for this command, this method is intentionally left empty.
     *
     * @param rawText the whole, unaltered command input from the user
     */
    private void parseList(String rawText) {
    }

    /**
     * Parses the "add" command, checking if the input format is correct.
     *
     * @param rawText the whole, unaltered command input from the user
     * @throws PythiaException if the command lacks a valid task description
     */
    private void parseAdd(String rawText) throws PythiaException {
        argumentList = tokenize(rawText, "add\\s(.+)");
        boolean isCorrectFormat = argumentList.size() == 1;

        if (!isCorrectFormat) {
            throw new PythiaException(parsingErrorMessage, "I am not sure what to add.");
        }
    }

    /**
     * Parses the "mark" command, ensuring it includes a valid task number.
     *
     * @param rawText the whole, unaltered command input from the user
     * @throws PythiaException if the command lacks a valid task number
     */
    private void parseMark(String rawText) throws PythiaException {
        argumentList = tokenize(rawText, "mark\\s(.+)");
        boolean isCorrectFormat = argumentList.size() == 1 && argumentList.get(0).matches("\\d+");

        if (!isCorrectFormat) {
            throw new PythiaException(parsingErrorMessage, "Please specify what should I mark as done.");
        }
    }

    private void parseToDo(String rawText) throws PythiaException {
        argumentList = tokenize(rawText, "todo\\s(.+)");
        boolean isCorrectFormat = argumentList.size() == 1;

        if (!isCorrectFormat) {
            throw new PythiaException(parsingErrorMessage, "Todo should have a description.");
        }
    }

    private void parseDeadline(String rawText) throws PythiaException {
        argumentList = tokenize(rawText, "deadline\\s(.+)\\s/by\\s(.+)");
        boolean isCorrectFormat = argumentList.size() == 2;

        if (!isCorrectFormat) {
            throw new PythiaException(parsingErrorMessage, "Deadline should have description and a date.");
        }
    }

    private void parseEvent(String rawText) throws PythiaException {
        argumentList = tokenize(rawText, "event\\s(.+)\\s/from\\s(.+)\\s/to\\s(.+)");
        boolean isCorrectFormat = argumentList.size() == 3;

        if (!isCorrectFormat) {
            throw new PythiaException(parsingErrorMessage, "Event should have description and from and to dates.");
        }
    }

    public void parseDelete(String rawText) throws PythiaException {
        argumentList = tokenize(rawText, "delete\\s(.+)");
        boolean isCorrectFormat = argumentList.size() == 1 && argumentList.get(0).matches("\\d+");

        if (!isCorrectFormat) {
            throw new PythiaException(parsingErrorMessage, "Please specify what should I delete.");
        }
    }

    public void parseFind(String rawText) throws PythiaException {
        argumentList = tokenize(rawText, "find\\s(.+)");
        boolean isCorrectFormat = argumentList.size() == 1;

        if (!isCorrectFormat) {
            throw new PythiaException(parsingErrorMessage, "Please specify what should I find.");
        }
    }

    /**
     * Splits the given command string into tokens based on the provided regular expression.
     *
     * <p>Example usage:</p>
     * <pre>{@code
     * String rawText = "event <eventname> /from <startDate> /to <endDate>";
     * String regex = "event\\s(.+)\\s/from\\s(.+)\\s/to\\s(.+)";
     * ArrayList<String> tokens = tokenize(rawText, regex);
     * // Resulting tokens = {<eventname>, <startDate>, <endDate>}
     * }</pre>
     *
     * @param rawText the whole, unaltered command input from the user
     * @param regex the regular expression used to capture command arguments
     * @return a list of tokens extracted from the input text
     */
    private ArrayList<String> tokenize(String rawText, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(rawText);

        ArrayList<String> tokens = new ArrayList<>();

        if (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                tokens.add(matcher.group(i));  // Get specific captured groups
            }
        }

        return tokens;
    }

    /**
     * Parses the entire command input and determines which specific parsing method to call.
     * The command is identified by its type and validated accordingly.
     *
     * @param rawText the whole, unaltered command input from the user
     * @throws PythiaException if the command is invalid
     */
    public void parse(String rawText) throws PythiaException {
        commandType = parseCommandType(rawText);

        switch (commandType) {
            case "bye" -> parseBye(rawText);
            case "list" -> parseList(rawText);
            case "add" -> parseAdd(rawText);
            case "mark" -> parseMark(rawText);
            case "todo" -> parseToDo(rawText);
            case "deadline" -> parseDeadline(rawText);
            case "event" -> parseEvent(rawText);
            case "delete" -> parseDelete(rawText);
            case "find" -> parseFind(rawText);
        }
    }

    /**
     * Executes the parsed command by invoking the corresponding method in the {@link Pythia} class.
     * If no command has been parsed, it throws a {@link PythiaException}.
     *
     * @throws PythiaException if no command was parsed or the command is unrecognized
     */
    public void execute() throws PythiaException {
        if (commandType == null) {
            throw new PythiaException(parsingErrorMessage, "Sorry, I am busy.");
        }

        switch (commandType) {
            case "bye" -> Pythia.sayBye();
            case "list" -> Pythia.listTasks();
            case "add" -> Pythia.addTask(argumentList.get(0));
            case "mark" -> Pythia.markTask(Integer.parseInt(argumentList.get(0)));
            case "todo" -> Pythia.addToDo(argumentList.get(0));
            case "deadline" -> Pythia.addDeadline(argumentList.get(0), argumentList.get(1));
            case "event" -> Pythia.addEvent(argumentList.get(0), argumentList.get(1), argumentList.get(2));
            case "delete" -> Pythia.deleteTask(Integer.parseInt(argumentList.get(0)));
            case "find" -> Pythia.findTasks(argumentList.get(0));
            default -> throw new PythiaException(parsingErrorMessage, "Hmm. I am not sure what you mean.");
        }
    }
}