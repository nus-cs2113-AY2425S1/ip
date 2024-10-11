public class Parser {
    public static String parseCommand(String input) {
        // Trim leading/trailing spaces
        input = input.trim();
        if (input.isEmpty()) {
            return "";
        }

        // Find the index of the first space
        int spaceIndex = input.indexOf(' ');
        if (spaceIndex == -1) {
            return input.toLowerCase();
        } else {
            return input.substring(0, spaceIndex).toLowerCase();
        }
    }

    static int parseTaskNumber(String input) {
        return Integer.parseInt(input.split(" ")[1]) - 1;
    }

    static String parseTodo(String input) {
        return input.substring(5).trim();
    }

    public static String[] parseDeadline(String input) {
        String[] parts = input.replaceFirst("deadline ", "").split(" /by ");
        String description = parts[0].trim();
        String by = parts[1].trim();
        return new String[]{description, by};
    }

    public static String[] parseEvent(String input) {
        String[] parts = input.replaceFirst("event ", "").split(" /from | /to ");
        String description = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        return new String[]{description, from, to};
    }

    public static String parseKeyword(String input) {
        String[] parts = input.split(" ", 2);
        return parts[1];
    }
}
