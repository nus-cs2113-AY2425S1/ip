/**
 * This parser processes user input and converts it into commands and details 
 */
public class Parser {

    /**
     * Parses the user's input and returns a command and its arguments as an array of strings.
     *
     * @param input User's input to be parsed 
     * @return An array of strings where the first elements is the command, the other elements are detais of the task 
     * @throws AnBotException If the input format is invalid or a required argument is missing.
     */
    public String[] parse(String input) throws AnBotException {
        if (input.equals("bye")) {
            return new String[]{"bye"}; 
        } else if (input.equals("list")) {
            return new String[]{"list"}; 
        } else if (input.startsWith("todo ")) {
            String description = input.substring(5).trim(); 
            if (description.isEmpty()) {
                throw new AnBotException("Todo description cannot be empty"); 
            }
            return new String[]{"todo", description}; 
        } else if (input.startsWith("deadline ")) {
            String[] parts = input.substring(9).split(" /by "); 
            if (parts.length != 2) {
                throw new AnBotException("The deadline format is not correct.");
            }
            return new String[]{"deadline", parts[0].trim(), parts[1].trim()}; 
        } else if (input.startsWith("event ")) {
            String[] parts = input.substring(6).split(" /from | /to ");
            if (parts.length != 3) {
                throw new AnBotException("The event format is not correct.");
            }
            return new String[]{"event", parts[0].trim(), parts[1].trim(), parts[2].trim()}; 
        } else if (input.startsWith("mark ")) {
            try {
                int number = Integer.parseInt(input.substring(5).trim()); 
                return new String[]{"mark", String.valueOf(number)}; 
            } catch (NumberFormatException e) {
                throw new AnBotException("The task index for mark must be a number.");
            }
        } else if (input.startsWith("unmark ")) {
            try {
                int number = Integer.parseInt(input.substring(7).trim()); 
                return new String[]{"unmark", String.valueOf(number)}; 
            } catch (NumberFormatException e) {
                throw new AnBotException("The task index for unmark must be a number.");
            }
        } else if (input.startsWith("delete ")) {
            try {
                int number = Integer.parseInt(input.substring(7).trim()); 
                return new String[]{"delete", String.valueOf(number)}; 
            } catch (NumberFormatException e) {
                throw new AnBotException("The task index for delete must be a number.");
            }
        } else if (input.startsWith("find ")) {
            String keyword = input.substring(5).trim(); 
            if (keyword.isEmpty()) {
                throw new AnBotException("Find keyword cannot be empty"); 
            }
            return new String[]{"find", keyword}; 
        } else {
            throw new AnBotException("Error command. Please enter another input.");
        }
    }
}
