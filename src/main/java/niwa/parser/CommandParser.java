package niwa.parser;

import niwa.command.Command;
import niwa.exception.NiwaException;
import niwa.exception.NiwaInvalidArgumentException;
import niwa.exception.NiwaInvalidSyntaxException;
import niwa.exception.NiwaTaskIndexOutOfBoundException;
import niwa.messages.NiwaMesssages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CommandParser {
    /** Map command with its word **/
    private Map<String, Command> commands = new LinkedHashMap<>();

    public Map<String, Command> getCommands() {
        return commands;
    }

    public void registerCommands(Command command) {
        String commandWord = null;
        try {
            commandWord = (String) command.getClass().getField("COMMAND_WORD").get(null);
            commands.put(commandWord, command);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            //Pass
        }
    }

    public Command parseCommand (String commandString)
            throws NiwaException {
        String[] commandParts = commandString.split(" ", 2);

        Map<String, String> arguments = new HashMap<>();

        Command command = commands.get(commandParts[0]);

        String argumentString = "";
        if (commandParts.length == 2) {
            argumentString = commandParts[1];
        }

        if (command != null) {
            String [] keywords = null;
            try {
                keywords = (String[]) command.getClass().getField("COMMAND_KEYWORDS").get(null);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                throw new NiwaException(String.format(NiwaMesssages.MESSAGE_PARSE_FAILED, e.getMessage()));
            }
            splitCommandRecursively(argumentString, keywords, arguments, "");
            command.setArguments(arguments);
        } else {
            throw new NiwaInvalidSyntaxException();
        }

        return command;
    }

    private static void splitCommandRecursively
            (String argumentString, String[] keywords, Map<String, String> arguments, String prevKeyword) {

        argumentString = argumentString.trim();

        if (argumentString.isEmpty()) {
            return;
        }

        int keywordIndex = -1;
        String keywordFound = null;

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
            String beforeKeyword = argumentString.substring(0, keywordIndex).trim();
            if (!beforeKeyword.isEmpty()) {
                arguments.put(prevKeyword, beforeKeyword);
            }

            String afterKeyword = getAfterKeyword(argumentString, keywordIndex);

            splitCommandRecursively(afterKeyword, keywords, arguments, keywordFound);
        } else {
            arguments.put(prevKeyword, argumentString);
        }
    }

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
