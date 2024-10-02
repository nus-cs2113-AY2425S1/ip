package lovespiritual;

import lovespiritual.exception.lovespiritualException;

public class Parser {
    public static String parseCommand(String input) throws lovespiritualException {
        String command = input.trim().toLowerCase();
        if (command.equals("bye")) {
            return "bye";
        } else if (command.equals("list")) {
            return "list";
        } else if (command.startsWith("mark")) {
            return "mark";
        } else if (command.startsWith("unmark")) {
            return "unmark";
        } else if (command.startsWith("todo")) {
            return "todo";
        } else if (command.startsWith("deadline")) {
            return "deadline";
        } else if (command.startsWith("event")) {
            return "event";
        } else if (command.startsWith("delete")) {
            return "delete";
        } else if (command.startsWith("find")){
            return "find";
        } else {
            throw new lovespiritualException("(^_^) Let's get started with a command!");
        }
    }
}
