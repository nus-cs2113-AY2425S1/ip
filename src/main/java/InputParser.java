import java.util.HashMap;

public class InputParser {
    public static final String COMMAND = "command";
    public static final String ARGUMENT = "argument";

    public static HashMap<String, String> parseCommands(String input) {
        HashMap<String, String> commandArguments = new HashMap<>();
        String[] splitInput = input.split(" ");

        if (splitInput.length == 0) {
            commandArguments.put(InputParser.COMMAND, "");
            return commandArguments;
        }
        commandArguments.put(InputParser.COMMAND, splitInput[0]);

        String argumentDescription = InputParser.ARGUMENT;
        StringBuilder argument = new StringBuilder();

        for (int i = 1; i < splitInput.length; i++) {
            if (splitInput[i].charAt(0) == '/') {
                if (!argumentDescription.isEmpty()) {
                    commandArguments.put(argumentDescription, argument.toString().strip());
                }

                argumentDescription = splitInput[i];
                argument.setLength(0);
            } else {
                argument.append(" ").append(splitInput[i]);
            }
        }

        if (!argument.isEmpty()) {
            commandArguments.put(argumentDescription, argument.toString().strip());
        }

        return commandArguments;
    }
}
