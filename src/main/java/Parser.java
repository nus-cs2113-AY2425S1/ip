import Commands.Command;

public class Parser {


    public static Command findCommand(String command) {
        String wordDigit = "\\d{1,3}$";
        String deadlineMatch = "(\\w+ )*/by .+";
        String eventMatch = "(\\w+ )*/from (\\w+ )*/to .+";
        boolean containsSpace = command.contains(" ");
        String cmd = containsSpace ? command.substring(0, command.indexOf(" ")) : command;
        String args = containsSpace ? command.substring(command.indexOf(" ") + 1) : "";
        if (args.matches(wordDigit)) {
            handleWordDigit(cmd, args);
        } else if (args.matches(eventMatch)) {
            handleEvent(cmd, args);
        } else if (args.matches(deadlineMatch)) {
            handleDeadline(cmd, args);
        } else {
            handleDefault(cmd, args);
        }
        storage.writeTaskData();
    }
}
