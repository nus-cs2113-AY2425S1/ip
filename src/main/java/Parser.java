public class Parser {

  public static String[] parse(String fullCommand) {
    String[] parts = fullCommand.split(" ", 2);  // Split the command into two parts: the command and the rest of the input

    // If the command is "add", handle the rest of the input as the task description
    if (parts[0].equals("add") && parts.length > 1) {
      return new String[]{"add", parts[1]};
    }

    if (parts[0].equals("deadline") && parts.length > 2) {
      int byIndex = -1;
      String[] tempParts = fullCommand.split(" ");
      for (int i = 0; i < tempParts.length; i++) {
        if (tempParts[i].equals("/by")) {
          byIndex = i;
          break;
        }
      }
      if (byIndex != -1 && byIndex < tempParts.length - 1) {
        String description = String.join(" ", java.util.Arrays.copyOfRange(tempParts, 1, byIndex));
        String dateTime = tempParts[byIndex + 1] + " " + tempParts[byIndex + 2];
        return new String[]{"deadline", description, "/by", dateTime};
      }
    }

    return parts;
  }
}
