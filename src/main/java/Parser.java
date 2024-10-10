/**
 * Parses user input commands and extracts relevant information for processing.
 */
public class Parser {

  /**
   * Splits the full command into an array of strings, handling
   * special cases for commands with additional parameters.
   */
  public static String[] parse(String fullCommand) {
    // Split the command into words
    String[] parts = fullCommand.split(" ");

    // Handling the case where the command has a specific format (like deadline)
    if (parts[0].equals("deadline") && parts.length > 2) {
      // Finding the positioning of "/by"
      int byIndex = -1;
      for (int i = 0; i < parts.length; i++) {
        if (parts[i].equals("/by")) {
          byIndex = i;
          break;
        }
      }

      // If "/by" is found, combining the description and date parts correctly
      if (byIndex != -1 && byIndex < parts.length - 1) {
        String description = String.join(" ", java.util.Arrays.copyOfRange(parts, 1, byIndex));
        String dateTime =
            parts[byIndex + 1] + " " + parts[byIndex + 2]; // Adding the time part as well
        return new String[]{"deadline", description, "/by", dateTime};
      }
    }

    return parts;
  }
}
