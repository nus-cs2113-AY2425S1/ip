package cristiano.exceptions;

import java.util.HashMap;
import java.util.Map;

/**
 * Custom exceptions for this program.
 * Certain exceptions are
 */
public class RonaldoException extends Exception {
    private static final Map<String, String> messages = new HashMap<String, String>() {{
        put("Format", "Invalid format! Please enter 'help' for full list of commands and formats.");
        put("Empty", "Your input is empty, just like Spurs' trophy cabinet.");
        put("Range", "Goal number is out of range!");
        put("Mark/Unmark", "Invalid mark format! Please use: <mark>/<unmark> <Goal number>");
        put("Event", "Invalid event format! Please use: <event> <Description> /from <Time> /to <Time>");
        put("Deadline", "Invalid deadline format! Please use: <deadline> <Description> /by <Time>");
        put("Todo", "Invalid todo format! Please use: <todo> <Description>");
        put("Delete", "Invalid delete format! Please use: <delete> <Goal number>");
        put("Find", "Invalid find format! Please use: <find> <Goal number>");
    }};


    /**
     * Constructs a RonaldoException with a message.
     * If the input is a key in the predefined message map, it uses the corresponding predefined message.
     * Otherwise, it uses the input message itself.
     *
     * @param input The input string that may be a key in the message map or the message itself.
     */
    public RonaldoException(String input) {
        super(messages.getOrDefault(input, input));
    }

}
