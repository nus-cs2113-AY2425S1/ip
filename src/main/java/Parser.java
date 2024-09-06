public class Parser {
    // Integer constants to access string inputs
    public static final int COMMAND_INDEX = 0;
    public static final int TASK_DETAILS_START_INDEX = COMMAND_INDEX + 1;

    // Defines the max inputs needed for a new task
    public static final int MAX_ARGS = 4;

    // String constants
    public static final String SPACE = " ";
    public static final String SLASH = "/";

    /**
     * Returns a String array that splits the user input into three respective parameters:
     * Command type, task details, and additional information
     *
     * @param input User input
     * @return String array, with the command type, task details, and additional information
     * in position 0, 1 and 2 (to 3) respectively
     */
    public static String[] extractInputDetails(String input) {
        String[] inputDetails = new String[MAX_ARGS];
        String[] words = input.split(SPACE);
        int wordCount = words.length;

        inputDetails[COMMAND_INDEX] = words[COMMAND_INDEX];
        int inputDetailsIndex = TASK_DETAILS_START_INDEX;
        for (int i = 1; i < wordCount; i++) {
            // Continue to next parameter (additional information) when a string with a forward slash is found
            if (words[i].contains(SLASH)) {
                inputDetailsIndex += 1;
                continue;
            }

            // Continue appending information to current parameter
            String currentParameter = inputDetails[inputDetailsIndex];
            if (currentParameter == null) {
                inputDetails[inputDetailsIndex] = words[i];
            } else {
                inputDetails[inputDetailsIndex] += SPACE + words[i];
            }
        }
        return inputDetails;
    }
}
