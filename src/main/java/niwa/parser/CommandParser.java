package niwa.parser;

import niwa.command.Command;
import niwa.exception.NiwaException;
import niwa.exception.NiwaInvalidSyntaxException;
import niwa.messages.NiwaMesssages;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The class {@code CommandParser} is responsible for parsing user commands into {@code Command} objects.
 * It manages the registration of commands and the extraction of arguments for each command.
 */
public class CommandParser {
    /** Map that associates command words with their corresponding {@code Command} objects */
    private Map<String, Command> commands = new LinkedHashMap<>();

    /**
     * Getter for the map of registered commands.
     *
     * @return A map of command words to {@code Command} objects.
     */
    public Map<String, Command> getCommands() {
        return commands;
    }

    /**
     * Registers a {@code Command} with its corresponding command word.
     *
     * @param command The {@code Command} object to be registered.
     */
    public void registerCommands(Command command) {
        String commandWord = null;
        try {
            commandWord = (String) command.getClass().getField("COMMAND_WORD").get(null);
            commands.put(commandWord, command);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            // Ignored
        }
    }

    /**
     * Parses a command string into a {@code Command} and assign its arguments.
     *
     * @param commandString The command string entered by the user.
     * @return The {@code Command} associated with the command string, with assigned arguments.
     * @throws NiwaException If parsing the command fails.
     * @throws NiwaInvalidSyntaxException If the command syntax is invalid.
     */
    public Command parseCommand(String commandString) throws NiwaException {
        String[] commandParts = commandString.split(" ", 2);

        Map<String, String> arguments = new HashMap<>();

        Command command = commands.get(commandParts[0]);

        String argumentString = "";
        if (commandParts.length == 2) {
            argumentString = commandParts[1];
        }

        if (command != null) {
            String[] keywords = null;
            try {
                keywords = (String[]) command.getClass().getField("COMMAND_KEYWORDS").get(null);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                throw new NiwaException(String.format(NiwaMesssages.MESSAGE_PARSE_FAILED, e.getMessage()));
            }
            // Recursively split the command arguments
            splitCommandRecursively(argumentString.trim(), keywords, arguments, "");
            command.setArguments(arguments);
        } else {
            throw new NiwaInvalidSyntaxException();
        }

        return command;
    }

    /**
     * Recursively splits the argument string based on the provided keywords.
     *
     * @param argumentString The argument string to be split.
     * @param keywords The keywords to search for in the argument string.
     * @param arguments A map to store the extracted arguments.
     * @param prevKeyword The keyword found in the previous recursive call.
     */
    private static void splitCommandRecursively(String argumentString, String[] keywords,
                                                Map<String, String> arguments, String prevKeyword) {
        if (argumentString.isEmpty()) {
            return; // Base case: no more arguments to split
        }

        int keywordIndex = -1;
        String keywordFound = null;

        // Find the first keyword in the argument string
        for (String keyword : keywords) {
            if (keyword.isEmpty()) {
                continue;
            }
            int index = argumentString.indexOf(keyword);
            if (index != -1 && (keywordIndex == -1 || index < keywordIndex)) {
                keywordIndex = index;
                keywordFound = keyword;
            }
        }

        if (keywordIndex != -1) {
            // If a keyword is found, extract the text before it
            String beforeKeyword = argumentString.substring(0, keywordIndex).trim();
            if (!beforeKeyword.isEmpty()) {
                arguments.put(prevKeyword, beforeKeyword);
            }

            // Get the remaining argument string after the keyword
            String afterKeyword = getAfterKeyword(argumentString, keywordIndex);

            // Create a new keywords array without the found keyword
            String finalKeywordFound = keywordFound;
            String[] updatedKeywords = Arrays.stream(keywords)
                    .filter(k -> !k.equals(finalKeywordFound))
                    .toArray(String[]::new);

            // Continue splitting recursively with the updated keywords
            splitCommandRecursively(afterKeyword, updatedKeywords, arguments, keywordFound);
        } else {
            // If no more keywords are found, assign the remaining argument
            arguments.put(prevKeyword, argumentString);
        }
    }

    /**
     * Retrieves the substring of the argument string that appears after the specified keyword index.
     *
     * @param argumentString The original argument string.
     * @param keywordIndex The index of the keyword found in the argument string.
     * @return The substring after the keyword.
     */
    private static String getAfterKeyword(String argumentString, int keywordIndex) {
        String afterKeyword = argumentString.substring(keywordIndex).trim();

        int nextSpaceIndex = afterKeyword.indexOf(' ');
        if (nextSpaceIndex != -1) {
            afterKeyword = afterKeyword.substring(nextSpaceIndex).trim();
        } else {
            afterKeyword = "";
        }

        return afterKeyword;
    }
}
