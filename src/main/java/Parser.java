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

    public String getCommand() {
        return command;
    }

    public String getPrompt() {
        return prompt;
    }

    public Hashtable<String, String> getArguments() {
        return arguments;
    }
}
