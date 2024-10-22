/**
 * Parses user input commands and extracts relevant information for processing.
 */
public class Parser {

  public static String[] parse(String fullCommand) {
    String[] parts = fullCommand.split(" "); // Split the command into words

    // If the command is "add", handle the rest of the input as the task description
    if (parts[0].equals("add")) {
      String description = String.join(" ", java.util.Arrays.copyOfRange(parts, 1, parts.length));
      return new String[]{"add", description};
    }

    // Handle "deadline" command
    if (parts[0].equals("deadline")) {
      // Find the index of "/by"
      int byIndex = -1;
      for (int i = 1; i < parts.length; i++) {
        if (parts[i].equals("/by")) {
          byIndex = i;
          break;
        }
      }

      // If "/by" is found, combine the description and date parts
      if (byIndex != -1 && byIndex < parts.length - 1) {
        String description = String.join(" ", java.util.Arrays.copyOfRange(parts, 1, byIndex));
        String dateTime = String.join(" ", java.util.Arrays.copyOfRange(parts, byIndex + 1, parts.length)); // Get the full date string
        return new String[]{"deadline", description, "/by", dateTime};
      } else {
        // Handle the case where "/by" is not found or there are not enough parts
        return new String[]{"deadline", "", "/by", ""}; // Return empty values for description and date
      }
    }

    return parts; // Return the original parts if no command matched
  }
}
