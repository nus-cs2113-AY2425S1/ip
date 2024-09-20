import java.util.HashMap;

public class InputParser {
    public static final String COMMAND = "command";
    public static final String ARGUMENT = "argument";

    public static HashMap<String, String> parseCommands(String input) {
        HashMap<String, String> commandArguments = new HashMap<>();
        String[] splitInput = input.split(" ");

        // check if input is empty
        if (splitInput.length == 0) {
            commandArguments.put(InputParser.COMMAND, "");
            return commandArguments;
        }

        // set first element as command
        commandArguments.put(InputParser.COMMAND, splitInput[0]);

        String argumentDescription = InputParser.ARGUMENT;
        StringBuilder argument = new StringBuilder();

        // parse remaining input
        for (int i = 1; i < splitInput.length; i++) {
            String arg = splitInput[i];

            if (arg.startsWith("/")) {
                if (!argumentDescription.isEmpty()) {
                    commandArguments.put(argumentDescription, argument.toString().strip());
                }

                argumentDescription = arg;
                argument.setLength(0);
            } else {
                argument.append(" ").append(arg);
            }
        }

        // add last argument
        if (!argument.isEmpty()) {
            commandArguments.put(argumentDescription, argument.toString().strip());
        }

        return commandArguments;
    }
}
