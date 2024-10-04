public class Parser {

    public String parseCommand(String input) {
        return input.split(" ")[0];
    }

    public String parseTaskDescription(String input, String command) {
        return input.substring(command.length()).trim();
    }

    public int parseTaskIndex(String input) {
        return Integer.parseInt(input.split(" ")[1]) - 1;
    }
}