public class Parser {
    // Integer constants to access string inputs
    public static final int COMMAND_INDEX = 0;
    public static final int MAX_ARGS = 4;

    // String constants
    public static final String SPACE = " ";
    public static final String SLASH = "/";


    public static String[] extractCommandDetails(String input) {
        String[] commandDetails = new String[MAX_ARGS];
        String[] words = input.split(SPACE);
        int wordCount = words.length;

        commandDetails[COMMAND_INDEX] = words[COMMAND_INDEX];
        int commandDetailsIndex = COMMAND_INDEX + 1;
        for (int i = 1; i < wordCount; i++) {
            if (words[i].contains(SLASH)) {
                commandDetailsIndex += 1;
                continue;
            }

            if (commandDetails[commandDetailsIndex] == null) {
                commandDetails[commandDetailsIndex] = words[i];
            } else {
                commandDetails[commandDetailsIndex] += SPACE + words[i];
            }
        }
        return commandDetails;
    }
}