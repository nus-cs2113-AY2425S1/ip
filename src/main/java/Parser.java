import java.util.Hashtable;

public class Parser {
    private Hashtable<String, String> arguments;
    private String command;
    private String prompt;

    public Parser() {
        command = "";
        prompt = "";
        arguments = new Hashtable<>();
    }

    /**
     * Parses the given command line and extracts the command, prompt, and arguments.
     * The command is the first word in the line, the prompt is any non-argument
     * text following the command, and the arguments are key-value pairs prefixed
     * by "/".
     *
     * @param line the command line input to be parsed
     */
    public void parseCommand(String line) {
        prompt = "";
        arguments = new Hashtable<>();
        String[] words = line.trim().split("\\s+");
        command = words[0];
        if (words.length > 1) {
            StringBuilder value = new StringBuilder();
            String arg = "";
            boolean isArgument = false;
            for (int i = 1; i < words.length; i++) {
                if (!words[i].startsWith("/")) {
                    value.append(words[i]);
                    value.append(" ");
                    continue;
                }

                if (!isArgument) {
                    prompt = value.toString().trim();
                } else {
                    arguments.put(arg, value.toString().trim());
                }

                arg = words[i].substring(1).trim();
                isArgument = true;
                value = new StringBuilder();
            }

            if (!isArgument) {
                prompt = value.toString().trim();
            } else {
                arguments.put(arg, value.toString().trim());
            }
        }
    }

    /**
     * Returns the command extracted from the parsed input.
     *
     * @return the command as a string
     */
    public String getCommand() {
        return command;
    }

    /**
     * Returns the prompt text extracted from the parsed input.
     * The prompt is the text that follows the command but is not part of any argument.
     *
     * @return the prompt as a string
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * Returns the arguments extracted from the parsed input.
     * The arguments are stored as key-value pairs in a Hashtable, where each argument
     * key is prefixed with "/" in the original input.
     *
     * @return a Hashtable containing the arguments as key-value pairs
     */
    public Hashtable<String, String> getArguments() {
        return arguments;
    }
}
